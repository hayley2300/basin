package com.basin.thread;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main_geturllist_user extends AsyncTask<String, Void, JSONArray> {

    JSONArray get_list;

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected JSONArray doInBackground(String... strUrl){
        URL url = null;
        HttpURLConnection conn = null;
        InputStream in = null;
        BufferedReader bufferedReader = null;
        StringBuffer builder = null;

        try{

            url = new URL(strUrl[0]);
            conn = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(conn.getInputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            builder = new StringBuffer();

            String inputString = null;
            while((inputString = bufferedReader.readLine())!=null){
                builder.append(inputString);
            }

            String s = builder.toString();
            get_list = new JSONArray(s);

        }catch(IOException e){
            e.printStackTrace();
        }catch(JSONException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            conn.disconnect();
            try {
                bufferedReader.close();
                in.close();
            }catch(IOException e){
                e.printStackTrace();
            }

        }

        return get_list;
    }

}