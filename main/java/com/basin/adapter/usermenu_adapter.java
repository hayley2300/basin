package com.basin.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basin.R;
import com.basin.main.content_modi_dialog;
import com.basin.pojo.UserMenu;
import com.basin.pojo.main_content_card;
import com.basin.util.GetDrawbleIcon;
import com.basin.util.Util;

import java.util.ArrayList;
import java.util.HashMap;

public class usermenu_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    private ArrayList<UserMenu> mData = null;
    Util util = null;
    int posi;
    TextView menu_recycle_name;
    EditText menu_recycle_name2;
    ImageView menu_recycle_mdoify, menu_recycle_delete,  menu_recycle_mdoify2, menu_recycle_delete2 ;
    LinearLayout menu_recycle_layout, menu_recycle_layout2;
    static HashMap<String, Integer> position_manager = new HashMap<String, Integer>();
    private static final int VIEW_TYPE_TEXT = 0;
    private static final int VIEW_TYPE_IMAGE = 1;
    RecyclerView.ViewHolder holder_;

    // 생성자에서 데이터 리스트 객체를 전달받음.
    public usermenu_adapter(ArrayList<UserMenu> list, Context mContext) {
        mData = list;
        this.mContext = mContext;
    }

    public int getPosi(String phase_num) {
        return position_manager.get(phase_num);
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? VIEW_TYPE_TEXT : VIEW_TYPE_IMAGE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_TEXT) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.menu_recycle_card, parent, false);

            return new ViewHolder(itemView);//ViewHolder
        } else {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.menu_recycle_card, parent, false);

            return new ViewHolder(itemView);// ImageViewHolder
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        this.holder_ = holder;
        //   item = mData.get(position);
        if (util == null) {
            util = new Util();
        }
        posi = position;


        int getOn_off=mData.get(holder_.getAdapterPosition()).getOn_off();
        String getname1=mData.get(holder_.getAdapterPosition()).getUser_menu1();
        String getEdittext=mData.get(holder_.getAdapterPosition()).getEditext();

        menu_recycle_name = ((ViewHolder) holder).menu_recycle_name;
        menu_recycle_name2 = ((ViewHolder) holder).menu_recycle_name2;

        if(getOn_off == 1){
            ((ViewHolder) holder).menu_recycle_layout.setVisibility(View.VISIBLE);
            ((ViewHolder) holder).menu_recycle_layout2.setVisibility(View.GONE);
            menu_recycle_name2.setText("");
        }else{
            ((ViewHolder) holder).menu_recycle_layout.setVisibility(View.GONE);
            ((ViewHolder) holder).menu_recycle_layout2.setVisibility(View.VISIBLE);
            ((ViewHolder) holder).menu_recycle_name2.setHint(getname1);
            menu_recycle_name2.setText(getEdittext);
        }

        if(getname1!=null && !getname1.trim().equals("")){
            menu_recycle_name.setText(mData.get(holder_.getAdapterPosition()).getUser_menu1());
           // menu_recycle_name2.setText("");
            //menu_recycle_name2.setText(mData.get(holder_.getAdapterPosition()).getUser_menu1());
        }else{
            menu_recycle_name.setText("");
           // menu_recycle_name2.setText("");
        }


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

/*
static class TextViewHolder extends RecyclerView.ViewHolder {
    TextViewHolder(){

    }
    private TextView textView;
    // generate constructor here
}

static class ImageViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    // generate constructor here
}*/

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView  menu_recycle_name;
        EditText menu_recycle_name2;
        ImageView menu_recycle_mdoify, menu_recycle_delete,  menu_recycle_mdoify2, menu_recycle_delete2 ;
        LinearLayout menu_recycle_layout, menu_recycle_layout2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //image = itemView.findViewById(R.id.imageView);
            menu_recycle_name = itemView.findViewById(R.id.menu_recycle_name);
            menu_recycle_name2 = itemView.findViewById(R.id.menu_recycle_name2);
            menu_recycle_layout = itemView.findViewById(R.id.menu_recycle_layout1);
            menu_recycle_layout2 = itemView.findViewById(R.id.menu_recycle_layout2);
            menu_recycle_mdoify  = itemView.findViewById(R.id.menu_recycle_mdoify);
            menu_recycle_delete  = itemView.findViewById(R.id.menu_recycle_delete);
            menu_recycle_mdoify2  = itemView.findViewById(R.id.menu_recycle_mdoify2);
            menu_recycle_delete2  = itemView.findViewById(R.id.menu_recycle_delete2);
            menu_recycle_mdoify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {

                    menu_recycle_layout.setVisibility(View.GONE);
                    menu_recycle_layout2.setVisibility(View.VISIBLE);
                    UserMenu menu_card = mData.get(pos);
                    menu_card.setOn_off(2);
                    menu_recycle_name2.setHint(menu_card.getUser_menu1());
                    //menu_recycle_name2.setText(menu_card.getUser_menu1());

                }
                }
            });
            menu_recycle_mdoify2.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {

                        menu_recycle_layout2.setVisibility(View.GONE);
                        menu_recycle_layout.setVisibility(View.VISIBLE);
                        UserMenu menu_card = mData.get(pos);
                        menu_card.setOn_off(1);
                        menu_card.setEditext("");
                        menu_recycle_name2.setText("");
                        //menu_recycle_name2.setText(menu_card.getUser_menu1());

                    }
                }
            });

            menu_recycle_name2.addTextChangedListener(new TextWatcher() {
                int number;
                int pos = 0;

                //변경되기전 문자열을 담고있다.
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                //텍스트가 변경될때 마다 호출된다. 보통은 이 함수안에 이벤트를 많이 사용하는것 같다.
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    pos = getAdapterPosition();
                    UserMenu menu_card = mData.get(pos);
                    menu_card.setEditext(""+s.toString().trim());
                    Log.d("test ", s+"");
                }


                //텍스트가 변경된 이후에 호출.
                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        }

        public void setItem(UserMenu item) {

            menu_recycle_name.setText(item.getUser_menu1());
        }


    }

}
