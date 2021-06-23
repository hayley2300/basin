package com.basin.thread;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.AsyncTask;

public class MyAsyncTask extends AsyncTask<Void, Integer, Boolean> {
    Context mContext;
    //TextView textView;

    public MyAsyncTask(Context mContext)
    {
        // this.textView = textView;
        this.mContext = mContext;
    }

    @Override
    protected Boolean doInBackground(Void... strings){

        ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(mContext.CLIPBOARD_SERVICE);
        String pasteData = "";
// 클립보드에 데이터가 없거나 텍스트 타입이 아닌 경우
        if (!(clipboard.hasPrimaryClip())) {
            System.out.print("111"+pasteData);
            ;
        }
        else if (!(clipboard.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))) {
            System.out.print("222"+pasteData);
            ;
        }
        else {
            System.out.print("33333"+clipboard.getPrimaryClip().getItemCount());
            for(int i =0; i < clipboard.getPrimaryClip().getItemCount(); i++){
                ClipData.Item item = clipboard.getPrimaryClip().getItemAt(i);
                pasteData = pasteData + " "+item.getText().toString();
            }


            System.out.print("33333"+pasteData.replaceAll("\n"," "));
        }


          /*  for(int i=0; i< 10000; i++)
            {
                publishProgress(i);
            }*/

        return true;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        // textView.setText(values[0].toString());

        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Boolean s) {
        super.onCancelled(s);
    }
}
