package com.basin.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.basin.LoginActivity;
import com.basin.R;

public class SingleBtn extends Dialog
{
    private Context mContext;
    SingleBtn m_oDialog;
    static int mode =0;
    public SingleBtn(Context context, int mode)
    {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mode = mode;
        mContext = context;
    }
    public SingleBtn(Context context, AttributeSet at, int mode)
    {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mode = mode;
        mContext = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.5f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.dialog_singlebtn);

        m_oDialog = this;

        TextView oView = (TextView) this.findViewById(R.id.dialog_single_text);
        // mode 1 = 아이디 pw
        if(mode==1){
            oView.setText("아이디 및 PW를 확인해주세요.");
        }
        else if(mode ==2){
            oView.setText("가입에 실패했습니다. \n잠시후 다시 시도해주세요.");
        }
        else{
            oView.setText("정보를 학인해주세요.");
        }

        TextView oBtn = (TextView) this.findViewById(R.id.dialog_single_btn);
        oBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickBtn(v);
            }
        });
    }

    public void onClickBtn(View _oView)
    {
       // this.dismiss();
        switch (_oView.getId()) {

            case R.id.dialog_single_btn:

                dismiss();
               // ((LoginActivity) mContext).finish();
                break;



/*
            case R.id.btn_ok:

                ((LoginActivity) mContext).finish();

                break;*/

        }

    }
}
