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
import android.widget.LinearLayout;
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

public class LoginFindUserActivity extends AppCompatActivity {
    private Context mContext;
    private SingleBtn oDialog ;
    static MailSender mailSender ;
    boolean SendmailFlag = false;
    BackgroundTask emailTask ;
    private String AuthDigit ;
    Validator email_validator;
    Base64Util base64Util;
    LinearLayout login_finduser_linear1, login_finduser_linear2;
    TextView login_finduser_findidpw, login_finduser_cancel1, login_finduser_cancel2, login_finduser_modify ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_finduser);
        mContext = this;
        login_finduser_linear1 = findViewById(R.id.login_finduser_linear1);
        login_finduser_linear2 = findViewById(R.id.login_finduser_linear2);
        login_finduser_findidpw = findViewById(R.id.login_finduser_findidpw);
        login_finduser_cancel1 = findViewById(R.id.login_finduser_cancel1);
        login_finduser_cancel2 = findViewById(R.id.login_finduser_cancel2);
        login_finduser_modify = findViewById(R.id.login_finduser_modify);

        email_validator = new Validator();
        base64Util = new Base64Util();
        login_finduser_linear2.setVisibility(View.INVISIBLE);


        login_finduser_findidpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_finduser_linear2.setVisibility(View.VISIBLE);
                login_finduser_linear1.setVisibility(View.INVISIBLE);
            }
        });

        login_finduser_cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                ((LoginFindUserActivity) mContext).finish();
            }
        });
        login_finduser_cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                ((LoginFindUserActivity) mContext).finish();
            }
        });
/*
        login_join_confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //데이터 담아서 팝업(액티비티) 호출
                if(login_join_id.getText().toString().trim().equals("")||login_join_id.getText().toString().trim().equals("")){
                    Toast.makeText(mContext, "아이디를 입력해주세요".toString(),Toast.LENGTH_LONG).show();
                }else if(login_join_id.getText().toString().trim().length()>20){
                    Toast.makeText(mContext, "아이디는 20자 초과 사용 불가합니다.".toString(),Toast.LENGTH_LONG).show();
                }else if(email_validator.isSpecialChar(login_join_id.getText().toString().trim())){
                    Toast.makeText(mContext, "아이디는 특수문자 사용 불가합니다.".toString(),Toast.LENGTH_LONG).show();
                }else if(!email_validator.isValidIdPw(login_join_id.getText().toString().trim())){
                    Toast.makeText(mContext, "아이디는 문자,숫자만 사용가능합니다.".toString(),Toast.LENGTH_LONG).show();
                }else if(login_join_pw.getText().toString().trim().equals("")||login_join_pw.getText().toString().trim().equals("")){
                    Toast.makeText(mContext, "비밀번호를 입력해주세요".toString(),Toast.LENGTH_LONG).show();
                }else if(login_join_pw.getText().toString().trim().length()>20){
                    Toast.makeText(mContext, "비밀번호는 20자 초과 사용 불가합니다.".toString(),Toast.LENGTH_LONG).show();
                }else if(!email_validator.isValidIdPw(login_join_pw.getText().toString().trim())){
                    Toast.makeText(mContext, "비밀번호는 문자,숫자,특수문자만 사용가능".toString(),Toast.LENGTH_LONG).show();
                }else if(login_join_pw_re.getText().toString().trim().equals("")||login_join_pw_re.getText().toString().trim().equals("")){
                    Toast.makeText(mContext, "비밀번호확인을 입력해주세요".toString(),Toast.LENGTH_LONG).show();
                }else if(!login_join_pw_re.getText().toString().trim().equals(login_join_pw.getText().toString().trim())){
                    Toast.makeText(mContext, "비밀번호와 비밀번호확인이 다릅니다.".toString(),Toast.LENGTH_LONG).show();
                }else if(login_join_email1.getText().toString().trim().equals("")||login_join_email1.getText().toString().trim().equals("")){
                    Toast.makeText(mContext, "이메일을 입력해주세요".toString(),Toast.LENGTH_LONG).show();
                }else if(login_join_email1.getText().toString().trim().length()>40){
                    Toast.makeText(mContext, "이메일은 40자 초과 사용 불가합니다.".toString(),Toast.LENGTH_LONG).show();
                }else if(!email_validator.isValidEmail(login_join_email1.getText().toString().trim())){
                    // 이메일 형식 확인해야함
                    Toast.makeText(mContext, "이메일 형식이 맞지 않습니다.".toString(),Toast.LENGTH_LONG).show();
                }else if(!login_join_email2.getText().toString().trim().equals("")
                        &&!email_validator.isValidEmail(login_join_email2.getText().toString().trim())){
                    // 보조 이메일이 있는 경우 형식 확인
                    Toast.makeText(mContext, "보조이메일 형식이 맞지 않습니다.".toString(),Toast.LENGTH_LONG).show();
                }else if(login_join_email2.getText().toString().trim().length()>40){
                    Toast.makeText(mContext, "보조이메일은 40자 초과 사용 불가합니다.".toString(),Toast.LENGTH_LONG).show();
                }else if(login_join_email1.getText().toString().trim().equals(login_join_email2.getText().toString().trim())){
                    Toast.makeText(mContext, "이메일과 보조이메일이 같습니다.".toString(),Toast.LENGTH_LONG).show();
                } else if(login_join_4digit.getText().toString().trim().equals("")||login_join_4digit.getText().toString().trim().equals("")){
                    Toast.makeText(mContext, "메일로 발송된 인증번호를 입력해주세요".toString(),Toast.LENGTH_LONG).show();
                }else if(login_join_4digit.getText().toString().trim().length()!=4){
                    Toast.makeText(mContext, "인증번호는 네자리 숫자입니다".toString(),Toast.LENGTH_LONG).show();
                }else if(!login_join_4digit.getText().toString().trim().equals(AuthDigit)){
                    Toast.makeText(mContext, "인증번호를 다시 확인해주세요".toString(),Toast.LENGTH_LONG).show();
                }else{
                    ContentValues addRowValue = new ContentValues();

                    addRowValue.put("memId", base64Util.base64Encoder(login_join_id.getText().toString().trim()));
                    addRowValue.put("memPw", base64Util.base64Encoder(login_join_pw.getText().toString().trim()));
                    addRowValue.put("memEmail1", base64Util.base64Encoder(login_join_email1.getText().toString().trim()));
                    addRowValue.put("memEmail2", base64Util.base64Encoder(login_join_email2.getText().toString().trim()));
                    addRowValue.put("memUsingYN", "Y");
                    LoginFindUserActivity.NetworkTask networkTask = new LoginFindUserActivity.NetworkTask("http://www.pfmac022.com/memJoinJson/", addRowValue);
                    networkTask.execute();
                }

            }
        });

        login_join_cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                ((LoginFindUserActivity) mContext).finish();
            }
        });

        login_join_sendmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(login_join_email1.getText().toString().trim().equals("")||login_join_email1.getText().toString().trim().equals("")){
                    Toast.makeText(mContext, "이메일을 입력해주세요".toString(),Toast.LENGTH_LONG).show();
                }else if(!email_validator.isValidEmail(login_join_email1.getText().toString().trim())){
                    // 이메일 형식 확인해야함
                    Toast.makeText(mContext, "이메일 형식이 맞지 않습니다.".toString(),Toast.LENGTH_LONG).show();
                }else{
                    emailTask = new BackgroundTask();
                    emailTask.execute();
                }
            }


        });*/
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
                            ((LoginFindUserActivity) mContext).finish();
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

    //새로운 TASK정의 (AsyncTask)
    // < >안에 들은 자료형은 순서대로 doInBackground, onProgressUpdate, onPostExecute의 매개변수 자료형을 뜻한다.(내가 사용할 매개변수타입을 설정하면된다)
    class BackgroundTask extends AsyncTask<Integer , Integer , Integer> {
        ProgressBar progress;
        int value=0;
        //초기화 단계에서 사용한다. 초기화관련 코드를 작성했다.
        protected void onPreExecute() {
           // progress.setProgress(value);
            value =(int)Math.random()*10000+1000;
        }

        //스레드의 백그라운드 작업 구현
        //여기서 매개변수 Intger ... values란 values란 이름의 Integer배열이라 생각하면된다.
        //배열이라 여러개를 받을 수 도 있다. ex) excute(100, 10, 20, 30); 이런식으로 전달 받으면 된다.
        protected Integer doInBackground(Integer ... values) {
            //isCancelled()=> Task가 취소되었을때 즉 cancel당할때까지 반복
            if (isCancelled() == false) {

                try {
                    SimpleDateFormat format1 = new SimpleDateFormat( "MddHHmmss");
                    Calendar time = Calendar.getInstance();
                    String format_time = format1.format(time.getTime());
                    format_time = String.valueOf(Integer.parseInt(format_time)*40);
                    AuthDigit = format_time.substring(format_time.length()-4, format_time.length());
                    mailSender = new MailSender("hayley2300", "tivkexvcnfesaixb");
                    mailSender.sendMail("[Basin] 가입인증 " , "인증번호 : "+AuthDigit+" 입니다.", "login_join_email1.getText().toString().trim()");
                    Log.d("auth_num",""+format_time);
                } catch (SendFailedException e) {
                    e.printStackTrace();
                  //  Toast.makeText(mContext, "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                } catch (MessagingException e) {
                    e.printStackTrace();
                 //   Toast.makeText(mContext, "인터넷 연결을 확인해주십시오", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //위에 onCreate()에서 호출한 excute(100)의 100을 사용할려면 이런식으로 해줘도 같은 결과가 나온다.
                //밑 대신 이렇게해도됨 if (value >= values[0].intValue())

            }

            return value;
        }

        //UI작업 관련 작업 (백그라운드 실행중 이 메소드를 통해 UI작업을 할 수 있다)
        //publishProgress(value)의 value를 값으로 받는다.values는 배열이라 여러개 받기가능
        protected void onProgressUpdate(Integer ... values) {
           // progress.setProgress(values[0].intValue());
            //textView.setText("현재 진행 값 : " + values[0].toString());
        }


        //이 Task에서(즉 이 스레드에서) 수행되던 작업이 종료되었을 때 호출됨
        protected void onPostExecute(Integer result) {
            SendmailFlag =  true;
            Toast.makeText(mContext, "이메일을 성공적으로 보냈습니다.", Toast.LENGTH_SHORT).show();
           // progress.setProgress(0);
            //textView.setText("완료되었습니다");
        }

        //Task가 취소되었을때 호출
        protected void onCancelled() {
            SendmailFlag =  false;
            Toast.makeText(mContext, "이메일 전송 실패! 주소를 확인해주세요.", Toast.LENGTH_SHORT).show();
            //progress.setProgress(0);
           // textView.setText("취소되었습니다");
        }
    }
}




