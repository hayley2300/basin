package com.basin;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.basin.adapter.main_recyle_card_adapter;
import com.basin.adapter.usermenu_adapter;
import com.basin.db.DBHelper;
import com.basin.dialog.SingleBtn;
import com.basin.http.RequestHttpURLConnection;
import com.basin.pojo.UserMenu;
import com.basin.pojo.main_content_card;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuManageActivity extends AppCompatActivity {
    private Context mContext;
    private SingleBtn oDialog ;
    long backKeyPressedTime;    //앱종료 위한 백버튼 누른시간
    public ArrayList<UserMenu> mList = new ArrayList<UserMenu>();
    public usermenu_adapter mAdapter = null;
    static public HashMap<String, Integer> contentMap = new HashMap<>();
    TextView menu_save, menu_close;
    Menu getMenu;

    public MenuManageActivity(){
        mList.clear();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        menu_save = (TextView) findViewById(R.id.menu_save);
        menu_close = (TextView) findViewById(R.id.menu_close);
        getMenu= MainActivity.menu;

        mContext = this;
        RecyclerView mRecyclerView = null;
        mRecyclerView = findViewById(R.id.menu_recycler);
        mAdapter = new usermenu_adapter(mList, mContext);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        for (int i =0; i < MainActivity.menu.size(); i++){
            addItem(MainActivity.menu.getItem(i).getTitle().toString().trim());
        }
        mAdapter.notifyDataSetChanged();


        menu_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });

        menu_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0; i< mAdapter.getItemCount(); i ++){
                    if (!mList.get(i).getEditext().equals("")){
                        mList.get(i).setEditext("");
                    }
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    ((MenuManageActivity) mContext).finish();
                }
            }

        });
    }
    public void addItem(String usermenu) {

        UserMenu item = new UserMenu(usermenu);
        Log.i("con",usermenu);
        //   item.setIcon(icon);
        //  item.setTitle(title);
        //   item.setDesc(desc);
        mList.add(item);

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


    }
    private String jsonParsing(String json)
    {
                String result="";
        try{

            JSONObject jsonObject = new JSONObject(json);
            result = jsonObject.getString("loginFlag");
            Log.d("e",jsonObject.getString("loginFlag"));

        }catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
    //앱종료시간체크


    //뒤로가기 2번하면 앱종료
    @Override
    public void onBackPressed() {
        //1번째 백버튼 클릭
        ((MenuManageActivity) mContext).finish();
        if(System.currentTimeMillis()>backKeyPressedTime+2000){
            backKeyPressedTime = System.currentTimeMillis();
            ((MenuManageActivity) mContext).finish();
            //Toast.makeText(this, "한번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
        //2번째 백버튼 클릭 (종료)
        else{
            //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
           // AppFinish();
        }
    }

    //앱종료
    public void AppFinish(){
        //finish();

        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        ActivityCompat.finishAffinity(this);
       // finishAffinity();
      //  System.runFinalization();
      //  System.exit(0);
      //  android.os.Process.killProcess(android.os.Process.myPid());

    }


}