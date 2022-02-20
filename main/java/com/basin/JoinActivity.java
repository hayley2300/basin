package com.basin;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basin.dialog.SingleBtn;
import com.basin.http.RequestHttpURLConnection;
import com.basin.util.Base64Util;
import com.basin.util.MailSender;
import com.basin.util.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;

public class JoinActivity extends AppCompatActivity {
    private Context mContext;
    private SingleBtn oDialog ;
    Validator email_validator;
    Base64Util base64Util;
    EditText join_id;
    EditText join_pw ;
    EditText join_pw_re ;
    EditText join_email1;
    TextView join_confirmbtn;
    TextView join_cancelbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_join);
        mContext = this;
        join_id = findViewById(R.id.join_id);
        join_pw = findViewById(R.id.join_pw);
        join_email1 = findViewById(R.id.join_email1);
        email_validator = new Validator();
        base64Util = new Base64Util();
        join_confirmbtn = findViewById(R.id.join_confirmbtn);
        join_cancelbtn = findViewById(R.id.join_cancelbtn);
        join_pw_re = findViewById(R.id.join_pw_re);
        
        
        join_confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //데이터 담아서 팝업(액티비티) 호출
                if(join_id.getText().toString().trim().equals("")||join_id.getText().toString().trim().equals("")){
                    Toast.makeText(mContext, "아이디를 입력해주세요".toString(),Toast.LENGTH_LONG).show();
                }else if(join_id.getText().toString().trim().length()>20){
                    Toast.makeText(mContext, "아이디는 20자 초과 사용 불가합니다.".toString(),Toast.LENGTH_LONG).show();
                }else if(email_validator.isSpecialChar(join_id.getText().toString().trim())){
                    Toast.makeText(mContext, "아이디는 특수문자 사용 불가합니다.".toString(),Toast.LENGTH_LONG).show();
                }else if(!email_validator.isValidIdPw(join_id.getText().toString().trim())){
                    Toast.makeText(mContext, "아이디는 문자,숫자만 사용가능합니다.".toString(),Toast.LENGTH_LONG).show();
                }else if(join_pw.getText().toString().trim().equals("")||join_pw.getText().toString().trim().equals("")){
                    Toast.makeText(mContext, "비밀번호를 입력해주세요".toString(),Toast.LENGTH_LONG).show();
                }else if(join_pw.getText().toString().trim().length()>20){
                    Toast.makeText(mContext, "비밀번호는 20자 초과 사용 불가합니다.".toString(),Toast.LENGTH_LONG).show();
                }else if(!email_validator.isValidIdPw(join_pw.getText().toString().trim())){
                    Toast.makeText(mContext, "비밀번호는 문자,숫자,특수문자만 사용가능".toString(),Toast.LENGTH_LONG).show();
                }else if(join_pw_re.getText().toString().trim().equals("")){
                    Toast.makeText(mContext, "비밀번호확인을 입력해주세요".toString(),Toast.LENGTH_LONG).show();
                }else if(!join_pw_re.getText().toString().trim().equals(join_pw.getText().toString().trim())){
                    Toast.makeText(mContext, "비밀번호와 비밀번호확인이 다릅니다.".toString(),Toast.LENGTH_LONG).show();
                }else if(join_email1.getText().toString().trim().equals("")||join_email1.getText().toString().trim().equals("")){
                    Toast.makeText(mContext, "이메일을 입력해주세요".toString(),Toast.LENGTH_LONG).show();
                }else if(join_email1.getText().toString().trim().length()>40){
                    Toast.makeText(mContext, "이메일은 40자 초과 사용 불가합니다.".toString(),Toast.LENGTH_LONG).show();
                }else if(!email_validator.isValidEmail(join_email1.getText().toString().trim())){
                    // 이메일 형식 확인해야함
                    Toast.makeText(mContext, "이메일 형식이 맞지 않습니다.".toString(),Toast.LENGTH_LONG).show();
                }else{
                    ContentValues addRowValue = new ContentValues();

                    addRowValue.put("memId", base64Util.base64Encoder(join_id.getText().toString().trim()));
                    addRowValue.put("memPw", base64Util.base64Encoder(join_pw.getText().toString().trim()));
                    addRowValue.put("memEmail1", base64Util.base64Encoder(join_email1.getText().toString().trim()));
                    addRowValue.put("memEmail2", base64Util.base64Encoder(join_email1.getText().toString().trim()));
                    addRowValue.put("memUsingYN", "Y");
                    JoinActivity.NetworkTask networkTask = new JoinActivity.NetworkTask("http://hayley2300.cafe24.com/memJoinJson/", addRowValue);
                    networkTask.execute();
                }

            }
        });

        join_cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                ((JoinActivity) mContext).finish();
            }
        });

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
                Log.i("result",s); //
                if(jsonParsing(s).trim().equals("2")){
                    oDialog = new SingleBtn(mContext,3);
                    oDialog.setCancelable(false);
                    oDialog.show();
                }
                else{

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(mContext, "가입성공".toString(),Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(mContext, LoginActivity.class);
                            startActivity(intent);
                            ((JoinActivity) mContext).finish();
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
            result = jsonObject.getString("registFlag");
            Log.d("e",jsonObject.getString("registFlag"));

        }catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

}




