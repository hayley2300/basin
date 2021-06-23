package com.basin.thread;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

import com.basin.http.RequestHttpURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

public class MyContentsAsyncTask extends AsyncTask<Void, Void, String> {

    private String url;
    private ContentValues values;
    private int mode;

    public MyContentsAsyncTask(int mode, String url, ContentValues values) {
        this.mode = mode;
        this.url = url;
        this.values = values;
    }

    @Override
    protected String doInBackground(Void... params) {

        String result; // 요청 결과를 저장할 변수.
        RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
        result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.
        try {
            if(mode ==1) {
                JSONObject jsonObject = new JSONObject(result);
                result = jsonObject.getString("registFlag");
                Log.d("e!!", jsonObject.getString("registFlag"));
            }else if(mode ==2) {
                JSONObject jsonObject = new JSONObject(result);
                result = jsonObject.getString("modifyFlag");
                Log.d("modifyFlag!!", jsonObject.getString("modifyFlag"));
            }else if(mode ==3) {
                JSONObject jsonObject = new JSONObject(result);
                result = jsonObject.getString("removeFlag");
                Log.d("removeFlag!!", jsonObject.getString("removeFlag"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            result = "";
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        String result = "";
        int u_content_cnt = 0;
        if (s != null) {






        }

        //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
        //tv_outPut.setText(s);
    }
}
