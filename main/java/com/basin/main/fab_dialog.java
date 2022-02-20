package com.basin.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import com.basin.util.GetDrawbleIcon;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.cardview.widget.CardView;

import com.basin.MainActivity;
import com.basin.R;
import com.basin.thread.MyContentsAsyncTask;
import com.basin.util.Validator;

import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class fab_dialog extends AppCompatActivity {
    ImageButton main_dialog_btn1, main_dialog_btn2;
    EditText main_dialog_title, main_dialog_content, main_dialog_url, main_dialog_tag;
    TextView main_dialog_warning_msg, fab_tag1_text, fab_tag2_text, fab_tag3_text;
    TextView main_fab_tag1_text_modi_can, main_fab_tag2_text_modi_can, main_fab_tag3_text_modi_can;
    ImageView main_dialog_image;
    CheckBox main_dialog_image_btn2 ;
    Intent intent;
    String before_tag = "";
    Context mContext;
    CardView fab_tag1, fab_tag2, fab_tag3;
    HorizontalScrollView tag_scroll;
    int tag_cnt, cate=0, icon=2;
    String userid, user_menu1, credate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_fab_dialog);
        main_dialog_btn1 = (ImageButton) findViewById(R.id.main_dialog_btn1);
        tag_cnt = 0;
        main_dialog_title = (EditText) findViewById(R.id.main_dialog_title);
        main_dialog_content = (EditText) findViewById(R.id.main_dialog_content);
        main_dialog_url = (EditText) findViewById(R.id.main_dialog_url);
        main_dialog_tag = (EditText) findViewById(R.id.main_dialog_tag);
        main_dialog_warning_msg = (TextView) findViewById(R.id.main_dialog_warning_msg);
        fab_tag1 = (CardView) findViewById(R.id.fab_tag1);
        fab_tag2 = (CardView) findViewById(R.id.fab_tag2);
        fab_tag3 = (CardView) findViewById(R.id.fab_tag3);
        tag_scroll = (HorizontalScrollView) findViewById(R.id.tag_scroll);
        fab_tag1_text = (TextView) findViewById(R.id.fab_tag1_text);
        fab_tag2_text = (TextView) findViewById(R.id.fab_tag2_text);
        fab_tag3_text = (TextView) findViewById(R.id.fab_tag3_text);
        main_fab_tag1_text_modi_can = (TextView)findViewById(R.id.main_fab_tag1_text_modi_can);
        main_fab_tag2_text_modi_can = (TextView)findViewById(R.id.main_fab_tag2_text_modi_can);
        main_fab_tag3_text_modi_can = (TextView)findViewById(R.id.main_fab_tag3_text_modi_can);
        main_dialog_image = (ImageView) findViewById(R.id.main_dialog_image);
        main_dialog_image_btn2 = (CheckBox) findViewById(R.id.main_dialog_image_btn2);
        main_dialog_image.setImageDrawable(GetDrawbleIcon.getIcon(2, mContext));

        main_dialog_image.setOnClickListener(new View.OnClickListener() {
                                        //@SuppressLint("RestrictedApi")
                                        @Override
                                        public void onClick(View view) {
                                            Context wrapper = new ContextThemeWrapper(fab_dialog.this, R.style.fab_icon_menu_style2);
                                            PopupMenu popup = new PopupMenu(wrapper, view);
                                            // popup = new PopupMenu(fab_dialog.this, view, 0,0, R.style.fab_icon_menu_style2);
                                            // Popup menu 생성
                                            getMenuInflater().inflate(R.menu.fab_icon_menu, popup.getMenu());
                                            try {

                                                Field[] fields = popup.getClass().getDeclaredFields();
                                                for (Field field : fields) {
                                                    if ("mPopup".equals(field.getName())) {
                                                        field.setAccessible(true);
                                                        Object menuPopupHelper = field.get(popup);

                                                        Class<?> classPopupHelper = Class.forName(menuPopupHelper
                                                                .getClass().getName());
                                                        Method setForceIcons = classPopupHelper.getMethod(
                                                                "setForceShowIcon", boolean.class);
                                                        setForceIcons.invoke(menuPopupHelper, true);
                                                        break;
                                                    }
                                                }

                                                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                                    @Override
                                                    public boolean onMenuItemClick(MenuItem a_item) {


                                                        //final PhCountryItem item = (PhCountryItem) mCountryAdapter.getItem(a_position);
                                                        switch (a_item.getItemId()) {
                                                            case R.id.icon_1:
                                                                icon = 1;
                                                                break;

                                                            case R.id.icon_2:

                                                                icon = 2;
                                                                break;

                                                            case R.id.icon_3:
                                                                icon = 3;
                                                                break;

                                                            case R.id.icon_4:
                                                                icon = 4;
                                                                break;
                                                            case R.id.icon_5:
                                                               
                                                                icon = 5;
                                                                break;

                                                            case R.id.icon_6:
                                                               
                                                                icon = 6;
                                                                break;

                                                            case R.id.icon_7:
                                                               
                                                                icon = 7;
                                                                break;

                                                            case R.id.icon_8:
                                                               
                                                                icon =8 ;
                                                                break;
                                                            case R.id.icon_9:
                                                               
                                                                icon = 9;
                                                                break;

                                                            case R.id.icon_10:
                                                               
                                                                icon = 10;
                                                                break;

                                                            case R.id.icon_11:
                                                               
                                                                icon =11 ;
                                                                break;

                                                            case R.id.icon_12:
                                                               
                                                                icon =12 ;
                                                                break;
                                                            case R.id.icon_13:
                                                               
                                                                icon =13 ;
                                                                break;

                                                            case R.id.icon_14:
                                                               
                                                                icon =14 ;
                                                                break;

                                                            case R.id.icon_15:
                                                               
                                                                icon =15 ;
                                                                break;

                                                            case R.id.icon_16:
                                                               
                                                                icon =16 ;
                                                                break;

                                                            case R.id.icon_17:
                                                               
                                                                icon =17 ;
                                                                break;
                                                            case R.id.icon_18:
                                                               
                                                                icon =18 ;
                                                                break;

                                                            case R.id.icon_19:
                                                               
                                                                icon =19 ;
                                                                break;

                                                            case R.id.icon_20:
                                                               
                                                                icon =20 ;
                                                                break;

                                                            case R.id.icon_21:
                                                               
                                                                icon =21 ;
                                                                break;
                                                            case R.id.icon_22:
                                                               
                                                                icon =22 ;
                                                                break;

                                                            case R.id.icon_23:
                                                               
                                                                icon =23 ;
                                                                break;

                                                            case R.id.icon_24:
                                                               
                                                                icon =24 ;
                                                                break;

                                                            case R.id.icon_25:
                                                               
                                                                icon =25 ;
                                                                break;
                                                            case R.id.icon_26:
                                                               
                                                                icon =26 ;
                                                                break;

                                                            case R.id.icon_27:
                                                               
                                                                icon =27 ;
                                                                break;

                                                            case R.id.icon_28:
                                                               
                                                                icon =28 ;
                                                                break;

                                                            case R.id.icon_29:
                                                               
                                                                icon =29 ;
                                                                break;
                                                            case R.id.icon_30:
                                                               
                                                                icon =30 ;
                                                                break;

                                                            case R.id.icon_31:
                                                               
                                                                icon =31 ;
                                                                break;

                                                            case R.id.icon_32:
                                                               
                                                                icon =32 ;
                                                                break;

                                                            case R.id.icon_33:
                                                               
                                                                icon =33 ;
                                                                break;
                                                            case R.id.icon_34:
                                                               
                                                                icon =34 ;
                                                                break;

                                                            case R.id.icon_35:
                                                               
                                                                icon =35 ;
                                                                break;

                                                            case R.id.icon_36:
                                                               
                                                                icon =36 ;
                                                                break;

                                                            case R.id.icon_37:
                                                               
                                                                icon =37 ;
                                                                break;
                                                            case R.id.icon_38:
                                                               
                                                                icon =38 ;
                                                                break;

                                                            case R.id.icon_39:
                                                               
                                                                icon =39 ;
                                                                break;

                                                            case R.id.icon_40:
                                                               
                                                                icon =40 ;
                                                                break;

                                                            case R.id.icon_41:
                                                               
                                                                icon =41 ;
                                                                break;
                                                            case R.id.icon_42:
                                                               
                                                                icon =42 ;
                                                                break;

                                                            case R.id.icon_43:
                                                               
                                                                icon =43 ;
                                                                break;

                                                            case R.id.icon_44:
                                                               
                                                                icon =44 ;
                                                                break;

                                                            case R.id.icon_45:
                                                               
                                                                icon =45;
                                                                break;
                                                            case R.id.icon_46:
                                                               
                                                                icon =46;
                                                                break;

                                                            case R.id.icon_47:
                                                               
                                                                icon =47 ;
                                                                break;

                                                            case R.id.icon_48:
                                                               
                                                                icon =48;
                                                                break;

                                                            case R.id.icon_49:

                                                                icon =49;
                                                                break;


                                                            default:
                                                                break;
                                                        }
                                                        main_dialog_image.setImageDrawable(GetDrawbleIcon.getIcon(icon, mContext));
                                                        return false;
                                                    }
                                                });

                                                popup.show();
                                            }catch(Exception e){

                                            }
                                        }
                                    }
                );
        main_dialog_image_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(fab_dialog.this, view);
                // Popup menu 생성
                getMenuInflater().inflate(R.menu.fab_menu, popup.getMenu());
                main_dialog_image_btn2.setChecked(true);
                // Popup menu click event 처리
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem a_item) {
                        //final PhCountryItem item = (PhCountryItem) mCountryAdapter.getItem(a_position);
                        switch (a_item.getItemId()) {
                            case R.id.action_insert:
                                cate = 1;
                                break;

                            case R.id.action_delete:
                                cate = 2;
                                break;

                            case R.id.action_modify:
                                cate = 3;
                                break;

                            case R.id.action_info:
                                cate = 0;
                                break;

                            default:
                                break;
                        }
                        main_dialog_image_btn2.setText(GetDrawbleIcon.getCate(cate));
                        return false;
                    }
                });
                popup.show();
                }
                }
                );

        main_fab_tag1_text_modi_can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fab_tag2.getVisibility()==View.VISIBLE
                        && fab_tag3.getVisibility()==View.VISIBLE ){
                    fab_tag1_text.setText(fab_tag2_text.getText());
                    fab_tag2_text.setText(fab_tag3_text.getText());
                    fab_tag3_text.setText("");
                    fab_tag3.setVisibility(View.INVISIBLE);
                }else if(fab_tag2.getVisibility()==View.VISIBLE){
                    fab_tag1_text.setText(fab_tag2_text.getText());
                    fab_tag2_text.setText("");
                    fab_tag2.setVisibility(View.INVISIBLE);
                }else{
                    fab_tag1.setVisibility(View.INVISIBLE);
                    fab_tag1_text.setText("");
                    tag_scroll.setVisibility(View.GONE);
                }
                tag_cnt--;
            }
        });

        main_fab_tag2_text_modi_can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fab_tag3.getVisibility()==View.VISIBLE){
                    fab_tag2_text.setText(fab_tag3_text.getText());
                    fab_tag3_text.setText("");
                    fab_tag3.setVisibility(View.INVISIBLE);
                }else{
                    fab_tag2.setVisibility(View.INVISIBLE);
                    fab_tag2_text.setText("");
                }
                tag_cnt--;
            }

        });

        main_fab_tag3_text_modi_can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab_tag3.setVisibility(View.INVISIBLE);
                fab_tag3_text.setText("");
                tag_cnt--;
            }
        });

        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");
        user_menu1 = intent.getStringExtra("user_menu1");
        credate = intent.getStringExtra("credate");

        main_dialog_tag.addTextChangedListener(new TextWatcher() {
            int number;

            //변경되기전 문자열을 담고있다.
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                before_tag = s.toString();
            }

            //텍스트가 변경될때 마다 호출된다. 보통은 이 함수안에 이벤트를 많이 사용하는것 같다.
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int len = 0;
                //밑의 editText.setText(number+""); 가 실행되면 onTextChanged()함수가 계속해서 다시 호출 되기 때문에 추가했다.
                // if(s.toString().equals(preText)) return;
                len = s.toString().length();
                Log.d("oooooooooooooooooo", "onTextChanged: " + number + ", s : " + s.toString() + " cnt : " + count + ", start :" + start + ", before : " + before + "fcs : " + main_dialog_tag.isFocusable());
                //editText에 포커스가 되어있고 텍스트가 하나라도 입력되어 있을때 동작하기 위해서 추가.
                if (len > 2 && " ".equals(s.toString().substring(len - 1, len))) {
                    if (tag_cnt == 0) {
                        tag_scroll.setVisibility(View.VISIBLE);
                        fab_tag1.setVisibility(View.VISIBLE);
                        fab_tag1_text.setText(s.toString());
                        tag_cnt++;
                        main_dialog_tag.setText("");
                    } else if (tag_cnt == 1) {
                        fab_tag2.setVisibility(View.VISIBLE);
                        fab_tag2_text.setText(s.toString());
                        tag_cnt++;
                        main_dialog_tag.setText("");
                    } else if (tag_cnt == 2) {
                        fab_tag3.setVisibility(View.VISIBLE);
                        fab_tag3_text.setText(s.toString());
                        tag_cnt++;
                        main_dialog_tag.setText("");
                    } else {
                        AlertDialog.Builder oDialog = new AlertDialog.Builder(mContext,
                                android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
                        String strHtml = "태그는 최대 3개까지 입력가능합니다.";
                        main_dialog_tag.setText(main_dialog_tag.getText().toString().trim());
                        Spanned oHtml;
                        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                            // noinspection deprecation
                            oHtml = Html.fromHtml(strHtml);
                        } else {
                            oHtml = Html.fromHtml(strHtml, Html.FROM_HTML_MODE_LEGACY);
                        }

                        oDialog.setTitle("태그 개수를 초과하였습니다.")
                                .setMessage(oHtml)
                                .setPositiveButton("ok", null)
                                .setCancelable(false)
                                .show();
                    }

                } else if (main_dialog_tag.isFocusable() && len > 20 && count != 0) {

                    try {
                        main_dialog_tag.setText(before_tag);
                        AlertDialog.Builder oDialog = new AlertDialog.Builder(mContext,
                                android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);

                        String strHtml =
                                // "<b><font color='#ff0000'>HTML 컨텐츠 팝업</font></b> 입니다.<br/>HTML이 제대로 표현되나요?";
                                "태그는 20자이상 입력불가합니다.";
                        Spanned oHtml;

                        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                            // noinspection deprecation
                            oHtml = Html.fromHtml(strHtml);
                        } else {
                            oHtml = Html.fromHtml(strHtml, Html.FROM_HTML_MODE_LEGACY);
                        }

                        oDialog.setTitle("태그 길이를 초과하였습니다.")
                                .setMessage(oHtml)
                                .setPositiveButton("ok", null)
                                .setCancelable(false)
                                .show();


                        // }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        return;
                    }
                }


            }


            //텍스트가 변경된 이후에 호출.
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        main_dialog_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //데이터 전달하기
                if (Validator.isUrl(main_dialog_url.getText().toString())) {
                    ContentValues addRowValue = new ContentValues();
                    //int url_split = main_dialog_url.getText().toString().indexOf("/");
                    /*String get_suburl = main_dialog_url.getText().toString().replace("http://","");
                    get_suburl = get_suburl.replace("http://","");
                    int url_split = get_suburl.indexOf("/");

                    addRowValue.put("userid", userid);
                    Log.e("url split ",url_split+"");
                    if(url_split!=-1) {
                        addRowValue.put("urlmain", main_dialog_url.getText().toString().substring(0, url_split));
                        addRowValue.put("urlsub", main_dialog_url.getText().toString().substring(url_split));
                    }else{
                        addRowValue.put("urlmain", main_dialog_url.getText().toString());
                    }*/
                    addRowValue.put("userid", userid);
                    addRowValue.put("urlmain", main_dialog_url.getText().toString());
                    addRowValue.put("urlsub", "");
                    addRowValue.put("user_menu1", user_menu1);
                    addRowValue.put("description", main_dialog_content.getText().toString());
                    addRowValue.put("title", main_dialog_title.getText().toString());
                    if(fab_tag1.getVisibility()==View.VISIBLE){
                        addRowValue.put("tag1", fab_tag1_text.getText().toString());
                    }if(fab_tag2.getVisibility()==View.VISIBLE){
                        addRowValue.put("tag2", fab_tag2_text.getText().toString());
                    }if(fab_tag3.getVisibility()==View.VISIBLE){
                        addRowValue.put("tag3", fab_tag3_text.getText().toString());
                    }
                    addRowValue.put("cate", cate+"");
                    addRowValue.put("icon", icon+"");

                    addRowValue.put("credate", credate);
                    Intent intent = new Intent();
                    try {
                        MyContentsAsyncTask networkTask = new MyContentsAsyncTask(1, "http://hayley2300.cafe24.com/conRJson/", addRowValue);
                        String rlt = networkTask.execute().get();
                        if (!rlt.equals("")) {
                            main_dialog_warning_msg.setVisibility(View.INVISIBLE);
                            intent.putExtra("mode", 1);
                            intent.putExtra("title", main_dialog_title.getText().toString());
                            intent.putExtra("description", main_dialog_content.getText().toString());
                            intent.putExtra("url", main_dialog_url.getText().toString());
                            intent.putExtra("tag1", fab_tag1_text.getText().toString());
                            intent.putExtra("tag2", fab_tag2_text.getText().toString());
                            intent.putExtra("tag3", fab_tag3_text.getText().toString());
                            intent.putExtra("icon", icon);
                            intent.putExtra("cate", cate);

                            setResult(RESULT_OK, intent);
                        } else {
                            setResult(RESULT_CANCELED, intent);
                        }
                        finish();
                    } catch (Exception e) {
                        setResult(RESULT_CANCELED, intent);
                        finish();
                    }


                } else {
                    main_dialog_warning_msg.setVisibility(View.VISIBLE);
                }


                //액티비티(팝업) 닫기


            }
        });

        main_dialog_btn2 = (ImageButton) findViewById(R.id.main_dialog_btn2);
        main_dialog_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }

}
