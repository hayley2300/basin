package com.basin;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.basin.R;
import com.basin.adapter.main_recyle_card_adapter;
import com.basin.db.DBHelper;
import com.basin.dialog.SingleBtn;
import com.basin.http.RequestHttpURLConnection;
import com.basin.main.fab_dialog;
import com.basin.pojo.main_content_card;
import com.basin.thread.Main_geturllist_user;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private Context mContext;
    private SingleBtn oDialog ;
    long backKeyPressedTime;    //앱종료 위한 백버튼 누른시간
    EditText login_id;
    EditText login_pw ;
    TextView login_confirm;
    CheckBox login_autocheck;
    TextView login_joinbtn;
    TextView login_findpwbtn;
    DBHelper helper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_id = findViewById(R.id.login_id);
        login_pw = findViewById(R.id.login_pw);
        login_confirm = findViewById(R.id.login_confirm);
        login_autocheck = findViewById(R.id.login_autocheck);
        login_joinbtn = findViewById(R.id.login_joinbtn);
        login_findpwbtn = findViewById(R.id.login_findpwbtn);


        helper = new DBHelper(LoginActivity.this, "basin.db", null, 1);
        mContext = this;


        db = helper.getWritableDatabase();
        helper.onCreate(db);
        String autologin_bit="";

        Cursor c = db.query("basin_user",null,null,null,null,null,null,null);
    /*    if(!c.moveToNext()) // 회원정보가 없으면 Login Activity로
        {
            Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
            startActivity(intent);
            ((MainActivity) mContext).finish();
        }else {*/
            while (c.moveToNext()) {
                System.out.println("autologin_bit : " + c.getString(c.getColumnIndex("autologin_bit")));
                if (c.getString(c.getColumnIndex("autologin_bit")).trim().equals("1"))
                // 자동 로그인이면 Main으로 .
                {
                    Toast.makeText(mContext, "자동로그인 되었습니다.".toString(),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    ((LoginActivity) mContext).finish();
                }
            }

        login_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //데이터 담아서 팝업(액티비티) 호출
                if(login_id.getText().toString().trim().equals("")||login_pw.getText().toString().trim().equals("")){
                    //로그인 아이디, PW 입력 안했을 경우
                    oDialog = new SingleBtn(mContext,1);
                    oDialog.setCancelable(false);
                    oDialog.show();
                }else{
                    ContentValues addRowValue = new ContentValues();

                    addRowValue.put("memId", login_id.getText().toString());
                    addRowValue.put("memPw", login_pw.getText().toString());
                    LoginActivity.NetworkTask networkTask = new LoginActivity.NetworkTask("http://www.pfmac022.com/memLoginJson/", addRowValue);
                    networkTask.execute();
                }

            }
        });

        login_joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //데이터 담아서 팝업(액티비티) 호출
                Intent intent = new Intent(mContext, JoinActivity.class);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                startActivity(intent);
            }
        });

        login_findpwbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //데이터 담아서 팝업(액티비티) 호출
                Intent intent = new Intent(mContext, FindUserActivity.class);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                startActivity(intent);
                ((LoginActivity) mContext).finish();
            }
        });

 /*
        DBHelper helper;
        SQLiteDatabase db;
        helper = new DBHelper(LoginActivity.this, "basin.db", null, 1);
        db = helper.getWritableDatabase();
        helper.onCreate(db);
        String id ="";
        String pw = "";
        String autologin_bit="";

      Cursor c = db.query("basin_user",null,null,null,null,null,null,null);
        while(c.moveToNext()){
            System.out.println("autologin_bit : "+c.getString(c.getColumnIndex("autologin_bit")));
            if(c.getString(c.getColumnIndex("autologin_bit")).trim().equals("1"))
            // 자동 로그인이면 바로 Main page로 go
            {
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }
*/


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

            if(s != null){
                Log.i("result",s); // 사용자 컨텐츠 수..
                if(jsonParsing(s).trim().equals("2")){
                    oDialog = new SingleBtn(mContext,1);
                    oDialog.setCancelable(false);
                    oDialog.show();
                }
                else{

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // Toast.makeText(mContext, "로그인성공".toString(),Toast.LENGTH_LONG).show();
                        if(login_autocheck.isChecked()){
                            String sql = "INSERT INTO basin_user ('autologin_bit', 'user_id') values('1', '"+login_id.getText().toString().trim()+"');";
                            db.execSQL(sql);
                        }else{
                            String sql = "INSERT INTO basin_user ('autologin_bit', 'user_id') values('0', '"+login_id.getText().toString().trim()+"');";
                            //String sql = "DELETE FROM basin_user ;";
                            db.execSQL(sql);
                        }
                        Intent intent = new Intent(mContext, MainActivity.class);
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                        startActivity(intent);
                        ((LoginActivity) mContext).finish();
                    }
                });
                }
            }

            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
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
        if(System.currentTimeMillis()>backKeyPressedTime+2000){
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(this, "한번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
        //2번째 백버튼 클릭 (종료)
        else{
            AppFinish();
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