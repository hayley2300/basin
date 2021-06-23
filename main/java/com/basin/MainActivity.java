package com.basin;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.basin.adapter.main_recyle_card_adapter;
import com.basin.db.DBHelper;
import com.basin.http.RequestHttpURLConnection;
import com.basin.main.fab_dialog;
import com.basin.pojo.Content;
import com.basin.pojo.UserMenu;
import com.basin.pojo.main_content_card;
import com.basin.service.BasinService;
import com.basin.thread.Main_geturllist_user;
import com.basin.thread.MyAsyncTask;
import com.basin.thread.MyFolderAsyncTask;
import com.basin.util.GetDrawbleIcon;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {
    static public Context mContext;
    private AppBarConfiguration mAppBarConfiguration;
    TextView main_username;
    long backKeyPressedTime;    //앱종료 위한 백버튼 누른시간
    TextView logout_btn;
    ImageView img_view;
    DBHelper helper;
    SQLiteDatabase db;
    boolean initial_flag = true;
    String current_menu = "기본 폴더";
    static public main_recyle_card_adapter mAdapter = null;
    static public ArrayList<main_content_card> mList = new ArrayList<main_content_card>();
    String user_id = "";
    Main_geturllist_user geturllist_user = null;
    JSONArray jsonArray_urllist_user = null;
    Menu menu;
    ArrayList<Content> contentArrayList;
    static public HashMap<String, Integer> contentMap = new HashMap<>();
    ArrayList<UserMenu> menuArrayList;
    int item_num, item_phase;
    int control_n = 0;
    public MainActivity(){
        mList.clear();
        contentMap.clear();
    }
    /*W
    ServiceConnection sconn = new ServiceConnection() {
        @Override //서비스가 실행될 때 호출
        public void onServiceConnected(ComponentName name, IBinder service) {
            BasinService.BasinBinder myBinder = (BasinService.BasinBinder) service;
            mService = myBinder.getService();

            isBind = true;
            Log.e("LOG", "onServiceConnected()");
        }

        @Override //서비스가 종료될 때 호출
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            isBind = false;
            Log.e("LOG", "onServiceDisconnected()");
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        menu = navigationView.getMenu();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        View headerView = navigationView.getHeaderView(0);

        main_username = headerView.findViewById(R.id.main_username);
        logout_btn = headerView.findViewById(R.id.logout_btn);
        img_view = headerView.findViewById(R.id.imageView);
        mContext = this;
        FloatingActionButton fab = findViewById(R.id.fab);

        helper = new DBHelper(MainActivity.this, "basin.db", null, 1);
        db = helper.getWritableDatabase();
        helper.onCreate(db);


        db = helper.getWritableDatabase();
        helper.onCreate(db);
        String autologin_bit = "";

        Cursor c = db.query("basin_user", null, null, null, null, null, null, null);
    /*    if(!c.moveToNext()) // 회원정보가 없으면 Login Activity로
        {
            Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
            startActivity(intent);
            ((MainActivity) mContext).finish();
        }else {*/
        while (c.moveToNext()) {
            System.out.println("user_id : " + c.getString(c.getColumnIndex("user_id")));
            user_id = c.getString(c.getColumnIndex("user_id")).trim();
            if (user_id.equals(""))
            // 자동 로그인이면 Main으로 .
            {
                Toast.makeText(mContext, "로그인 정보가 없습니다. 다시 로그인해주세요.".toString(), Toast.LENGTH_SHORT).show();
                String sql = "DELETE FROM basin_user ;";
                db.execSQL(sql);
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivityForResult(intent, 1);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                ((MainActivity) mContext).finish();
            }else{
                main_username.setText(user_id);
            }
        }
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "로그아웃 되었습니다.".toString(), Toast.LENGTH_SHORT).show();
                String sql = "DELETE FROM basin_user ;";
                db.execSQL(sql);
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivityForResult(intent, 1);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                ((MainActivity) mContext).finish();
            }
        });
        img_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText edittext = new EditText(mContext);

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("폴더 추가");
                builder.setMessage("생성할 폴더이름 입력");
                builder.setView(edittext);
                builder.setPositiveButton("생성",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                ContentValues addRowValue = new ContentValues();
                                String rlt ="";
                                if(user_id.trim().equals("")){
                                    Cursor c = db.query("basin_user", null, null, null, null, null, null, null);
                                    user_id = c.getString(c.getColumnIndex("user_id")).trim();
                                    Log.e("User ID ",user_id);
                                }
                                addRowValue.put("userid", user_id);
                                addRowValue.put("user_menu1", edittext.getText().toString());
                                try {
                                    MyFolderAsyncTask networkTask = new MyFolderAsyncTask("http://www.pfmac022.com/menuRJson/", addRowValue);
                                    rlt = networkTask.execute().get();
                                }catch(Exception e ){
                                    rlt ="";
                                }
                                if(!rlt.equals("")){
                                    addMenu(edittext.getText().toString());
                                }

                            }
                        });
                builder.setNegativeButton("취소",
                            new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();


//                menu.add(0, 2, 2, menuIconWithText(getResources().getDrawable(R.mipmap.add_user), getResources().getString(R.string.action_add_user)));
//                menu.add(0, 3, 3, menuIconWithText(getResources().getDrawable(R.mipmap.switch_profile), getResources().getString(R.string.action_switch_profile)));
//                menu.add(0, 4, 4, menuIconWithText(getResources().getDrawable(R.mipmap.logout), getResources().getString(R.string.action_sign_out)));

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //데이터 담아서 팝업(액티비티) 호출
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat mFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                Log.e("Time ",mFormat.format(date));
                Intent intent = new Intent(getApplicationContext(), fab_dialog.class);
                intent.putExtra("data", "Test Popup");
                intent.putExtra("userid", user_id);
                intent.putExtra("user_menu1", current_menu);
                intent.putExtra("credate", mFormat.format(date));
                startActivityForResult(intent, 1);
                // startActivity(intent);

               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
        RecyclerView mRecyclerView = null;
        mRecyclerView = findViewById(R.id.recycler1);
        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        mAdapter = new main_recyle_card_adapter(mList, mContext);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ContentValues addRowValue = new ContentValues();

        addRowValue.put("userid", user_id);
        addRowValue.put("user_menu1", "기본 폴더");
        addRowValue.put("num", 0);
        addRowValue.put("phase", 0);


            NetworkTask networkTask = new NetworkTask("http://www.pfmac022.com/conSJson/", addRowValue);
        networkTask.execute();


        try {
            // jsonArray_urllist_user = geturllist_user.execute("URL").get();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //=========================


    }

    public int addMenu(String menuName) {
        try {
            MenuItem itm = menu.add(1, 0, 2, menuIconWithText(GetDrawbleIcon.getIcon(40,mContext),
                    menuName));
            //menu.getItem(menu.size()).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
         /*   itm.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Toast.makeText(mContext, "Click!!".toString(), Toast.LENGTH_SHORT).show();

                    //item.set
                    return true;
                }
            });*/
            View v = new View(this);
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(mContext, "Long Click!!".toString(), Toast.LENGTH_SHORT).show();
                    //Your longclick listener callback logic goes here
                    return true;
                }

            });
            itm.setActionView(v);

            return 1;
        } catch (Exception e) {
            return 2;
        }

    }

    private CharSequence menuIconWithText(Drawable r, String title) {

        r.setBounds(0, 0, r.getIntrinsicWidth(), r.getIntrinsicHeight());

        //SpannableString sb = new SpannableString("    " + title);
        SpannableString sb = new SpannableString("    " + title);
        ImageSpan imageSpan = new ImageSpan(r, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sb;
    }

    public void addItem(String title, String content, String tag1, String tag2, String tag3, String url, int item_num, int item_phase, int cate, int icon) {

        main_content_card item = new main_content_card(title, content, tag1, tag2, tag3,url, item_num, item_phase, control_n, cate, icon);
        Log.i("con",tag1+","+tag2+","+tag3);
        control_n++;
        //   item.setIcon(icon);
        //  item.setTitle(title);
        //   item.setDesc(desc);

        contentMap.put(String.valueOf(item_phase) + "N" + String.valueOf(item_num), mList.size());
        mList.add(item);

    }

    public static Bitmap getResizedBitmap(Resources resources, int id, int size, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = size;
        Bitmap src = BitmapFactory.decodeResource(resources, id, options);
        // device image 사용 시
        // Bitmap src = BitmapFactory.decodeFile(file_route, options);
        Bitmap resized = Bitmap.createScaledBitmap(src, width, height, true);
        return resized;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if (requestCode == 1) {
        Log.d("Result ok ", "onActivityResult: " + data);
        if (resultCode == RESULT_OK) {
            //데이터 받기

                String result = data.getStringExtra("result");
                if(data.getIntExtra("mode",0) ==1) {
                    addItem(data.getStringExtra("title"), data.getStringExtra("description"),
                            data.getStringExtra("tag1"), data.getStringExtra("tag2"),
                            data.getStringExtra("tag3"), data.getStringExtra("url"),
                            data.getIntExtra("num", 0), data.getIntExtra("phase", 0),
                            data.getIntExtra("cate",0), data.getIntExtra("icon",0));
                    mAdapter.notifyDataSetChanged();
                }else if(data.getIntExtra("mode",0) ==2) {
                    int position = data.getIntExtra("position",0);

                    mList.get(position).setTitle(data.getStringExtra("title"));
                    mList.get(position).setContent(data.getStringExtra("description"));
                    mList.get(position).setUrl(data.getStringExtra("url"));
                    mList.get(position).setTag1(data.getStringExtra("tag1"));
                    mList.get(position).setTag2(data.getStringExtra("tag2"));
                    mList.get(position).setTag3(data.getStringExtra("tag3"));
                    mList.get(position).setCate(data.getIntExtra("cate",0));
                    mList.get(position).setIcon(data.getIntExtra("icon",2));
                    mAdapter.notifyDataSetChanged();
                }else if(data.getIntExtra("mode",0) ==3) {
                    int position = data.getIntExtra("position",0);
                    mList.remove(position);
                    mAdapter.notifyDataSetChanged();
                }
            // }
        }
    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            int u_content_cnt = 0;
            if (s != null) {
                Log.i("result", s); // 사용자 컨텐츠 수..
                contentArrayList = new ArrayList<>();
                menuArrayList = new ArrayList<>();
                contentArrayList = jsonArrayParsing(s);
                //   for(int i = 0 ; i < Integer.parseInt(s) ; i++){
                for (int i = 0; i < menuArrayList.size(); i++) {
                    MenuItem itm = null;
                    if(initial_flag) {
                        itm = menu.add(1, 2, 2, menuIconWithText(GetDrawbleIcon.getIcon(40,mContext),
                                menuArrayList.get(i).getUser_menu1()));
                      /*  View v = new View(mContext);
                        v.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                Toast.makeText(mContext, "Long Click!!".toString(), Toast.LENGTH_SHORT).show();
                                //Your longclick listener callback logic goes here
                                return true;
                            }

                        });
                        itm.setActionView(v);*/
                    }

                    if(itm !=null)
                    itm.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            Toast.makeText(mContext, "Click!! "+item.getTitle(), Toast.LENGTH_SHORT).show();
                            //menu.clear();
                            mList.clear();
                            contentMap.clear();
                            mAdapter.notifyDataSetChanged();
                            ContentValues addRowValue = new ContentValues();
                            current_menu = item.getTitle().toString().trim();
                            addRowValue.put("userid", user_id);
                            addRowValue.put("user_menu1", item.getTitle().toString().trim());
                            addRowValue.put("num", 0);
                            addRowValue.put("phase", 0);

                            NetworkTask networkTask = new NetworkTask("http://www.pfmac022.com/conSJson/", addRowValue);
                            networkTask.execute();
                            return true;
                        }
                    });


       /*             View v = new View(mContext);
                    v.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            Toast.makeText(mContext, "Long Click!!".toString(), Toast.LENGTH_SHORT).show();
                            //Your longclick listener callback logic goes here
                            return true;
                        }

                    });
                    itm.setActionView(v);*/

                }

                for (int i = 0; i < contentArrayList.size(); i++) {
                    addItem(contentArrayList.get(i).getTitle(), contentArrayList.get(i).getDescription(), contentArrayList.get(i).getTag1(),
                            contentArrayList.get(i).getTag2(),contentArrayList.get(i).getTag3(),contentArrayList.get(i).getUrlmain()
                            + contentArrayList.get(i).getUrlsub(), contentArrayList.get(i).getNum(), contentArrayList.get(i).getPhase(),
                            contentArrayList.get(i).getCate(), contentArrayList.get(i).getIcon());
                }

                // }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                        initial_flag = false;
                    }
                });


                MyAsyncTask myAsyncTask = new MyAsyncTask(mContext);
                myAsyncTask.execute();
            }

            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
            //tv_outPut.setText(s);
        }
    }

    private ArrayList<Content> jsonArrayParsing(String json) {

        String result = "";
        try {
            int menuSize = 0;
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (i == 0) {
                    menuSize = Integer.parseInt(jsonObject.getString("user_menu1"));
                } else if (i <= menuSize) {
                    UserMenu userMenu = new UserMenu(jsonObject.getString("user_menu1"));
                    menuArrayList.add(userMenu);
                } else {
                    Content content = new Content(jsonObject.getInt("num"),
                            jsonObject.getInt("phase"),
                            jsonObject.getString("userid"),
                            jsonObject.getInt("cate"),
                            jsonObject.getInt("icon"),
                            jsonObject.getString("user_menu1"),
                            jsonObject.getString("user_menu2"),
                            jsonObject.getString("description"),
                            jsonObject.getString("title"),
                            jsonObject.getString("tag1"),
                            jsonObject.getString("tag2"),
                            jsonObject.getString("tag3"),
                            jsonObject.getInt("user_clickcnt"),
                            jsonObject.getString("credate"),
                            jsonObject.getString("modidate"),
                            jsonObject.getString("delYN"),
                            jsonObject.getString("favoriteYN"),
                            jsonObject.getInt("scrapcnt"),
                            jsonObject.getString("urlmain"),
                            jsonObject.getString("urlsub"));
                    contentArrayList.add((Content) content);
                    Log.d("e", content.getNum() + "");
                }
// menu.add(1, 2, 2, menuIconWithText(getResources().getDrawable(R.drawable.ic_menu_gallery),
//                                        edittext.getText().toString()));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return contentArrayList;
    }

    private String jsonParsing(String json) {
        String result = "";
        try {

            JSONObject jsonObject = new JSONObject(json);
            result = jsonObject.getString("loginFlag");
            Log.d("e", jsonObject.getString("loginFlag"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    //뒤로가기 2번하면 앱종료
    @Override
    public void onBackPressed() {
        //1번째 백버튼 클릭
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(this, "한번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
        //2번째 백버튼 클릭 (종료)
        else {
            AppFinish();
        }
    }

    //앱종료
    public void AppFinish() {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        ActivityCompat.finishAffinity(this);
    }


}