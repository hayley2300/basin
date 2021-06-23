package com.basin.thread;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.basin.http.RequestHttpURLConnection;

import org.json.JSONObject;

public class MyMenuAsyncTask extends AsyncTask<Void, Integer, String>  {
    Context mContext;
    private String url;
    private ContentValues values;

    public MyMenuAsyncTask(Context mContext, String url, ContentValues values)
    {
        // this.textView = textView;
        this.mContext = mContext;
        this.url = url;
        this.values = values;
    }

    @Override
    protected String doInBackground(Void... strings){

        String result; // 요청 결과를 저장할 변수.
        RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
        result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

        return result;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(s != null){
            try {
                String result="";
                JSONObject jsonObject = new JSONObject(s);
                result = jsonObject.getString("inserMenuFlag");
                if(!result.equals("1")){

                }
                Log.d("e", jsonObject.getString("inserMenuFlag"));
            }catch (Exception e){

            }
        }
    }
}

