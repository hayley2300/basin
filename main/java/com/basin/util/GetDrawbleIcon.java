package com.basin.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.basin.R;

public class GetDrawbleIcon {

    public static Drawable getIcon(int icon, Context context){
        Bitmap bitmap = null;
        Drawable d= null;
        switch (icon) {
            case 1:
                //Toast.makeText(mContext, "1111111 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable)context.getDrawable(R.drawable.n001_wifi_signal)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 2:
                //Toast.makeText(mContext, "222222222", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n002_volume)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 3:
                // Toast.makeText(mContext, "3333333333 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n003_vision)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 4:
                // Toast.makeText(mContext, "4444444444444444 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n004_view)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                
            case 5:
                //Toast.makeText(mContext, "1111111 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n005_video)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 6:
                //Toast.makeText(mContext, "222222222", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n006_trash)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 7:
                // Toast.makeText(mContext, "3333333333 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n007_time)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 8:
                // Toast.makeText(mContext, "4444444444444444 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n008_settings)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                
            case 9:
                //Toast.makeText(mContext, "1111111 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n009_send)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 10:
                //Toast.makeText(mContext, "222222222", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n010_search)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 11:
                // Toast.makeText(mContext, "3333333333 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n011_repair)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 12:
                // Toast.makeText(mContext, "4444444444444444 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n012_portfolio)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                
            case 13:
                //Toast.makeText(mContext, "1111111 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n013_pin)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 14:
                //Toast.makeText(mContext, "222222222", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n014_pie_chart)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 15:
                // Toast.makeText(mContext, "3333333333 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n015_photography)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 16:
                // Toast.makeText(mContext, "3333333333 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n016_user)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 17:
                // Toast.makeText(mContext, "4444444444444444 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n017_pencil)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                
            case 18:
                //Toast.makeText(mContext, "1111111 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n018_mute)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 19:
                //Toast.makeText(mContext, "222222222", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n019_mouse)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 20:
                // Toast.makeText(mContext, "3333333333 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n020_mobile)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 21:
                // Toast.makeText(mContext, "4444444444444444 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n021_microphone)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                
            case 22:
                //Toast.makeText(mContext, "1111111 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n022_message)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 23:
                //Toast.makeText(mContext, "222222222", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n023_menu)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 24:
                // Toast.makeText(mContext, "3333333333 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n024_love)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 25:
                // Toast.makeText(mContext, "4444444444444444 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n025_lock)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                
            case 26:
                //Toast.makeText(mContext, "1111111 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n026_location)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 27:
                //Toast.makeText(mContext, "222222222", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n027_letter)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 28:
                // Toast.makeText(mContext, "3333333333 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n028_incoming)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 29:
                // Toast.makeText(mContext, "4444444444444444 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n029_idea)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                
            case 30:
                //Toast.makeText(mContext, "1111111 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n030_home)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 31:
                //Toast.makeText(mContext, "222222222", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n031_headset)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 32:
                // Toast.makeText(mContext, "3333333333 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n032_folder)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 33:
                // Toast.makeText(mContext, "4444444444444444 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n033_flag_1)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                
            case 34:
                //Toast.makeText(mContext, "1111111 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n034_flag)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 35:
                //Toast.makeText(mContext, "222222222", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n035_files)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 36:
                // Toast.makeText(mContext, "3333333333 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n036_favorite)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 37:
                // Toast.makeText(mContext, "4444444444444444 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n037_contacts_1)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                
            case 38:
                //Toast.makeText(mContext, "1111111 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n038_contacts)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 39:
                //Toast.makeText(mContext, "222222222", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n039_computer)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 40:
                // Toast.makeText(mContext, "3333333333 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n040_communication)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 41:
                // Toast.makeText(mContext, "4444444444444444 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n041_coffee)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                
            case 42:
                //Toast.makeText(mContext, "1111111 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n042_cloud)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 43:
                //Toast.makeText(mContext, "222222222", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n043_clock)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 44:
                // Toast.makeText(mContext, "3333333333 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n044_check)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 45:
                // Toast.makeText(mContext, "4444444444444444 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n045_check_list)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                
            case 46:
                //Toast.makeText(mContext, "1111111 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n046_call)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 47:
                //Toast.makeText(mContext, "222222222", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n047_browser)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 48:
                // Toast.makeText(mContext, "3333333333 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n048_bell)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                

            case 49:
                // Toast.makeText(mContext, "4444444444444444 ", Toast.LENGTH_SHORT).show();
                bitmap = ((BitmapDrawable) context.getDrawable(R.drawable.n049_attachment)).getBitmap();
                d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 30, true));
                return d;
                


            default:
                return d;
        }
    }

    public static String getCate(int cate){
        switch (cate) {
            case 1:
                return "일상";

            case 2:
                return "배움";

            case 3:
                return "즐거움";

            case 0:
                return "기타";

            default:
                return "기타";
        }
    }
}
