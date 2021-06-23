package com.basin.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basin.R;
import com.basin.main.content_modi_dialog;
import com.basin.main.fab_dialog;
import com.basin.pojo.main_content_card;
import com.basin.thread.DrawUrlImageTask;
import com.basin.util.GetDrawbleIcon;
import com.basin.util.Util;

import java.util.ArrayList;
import java.util.HashMap;

public class main_recyle_card_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    private ArrayList<main_content_card> mData = null;
    Util util = null;
    int posi;
    int cate, icon;
    TextView title, content, tag1, tag2, tag3, url, num_phase, main_card_cate;
    ImageView main_card_icon;
    static HashMap<String, Integer> position_manager = new HashMap<String, Integer>();
    private static final int VIEW_TYPE_TEXT = 0;
    private static final int VIEW_TYPE_IMAGE = 1;
    main_content_card item;
    RecyclerView.ViewHolder holder_;

    // 생성자에서 데이터 리스트 객체를 전달받음.
    public main_recyle_card_adapter(ArrayList<main_content_card> list, Context mContext) {
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
                    .inflate(R.layout.main_recycle_card, parent, false);

            return new ViewHolder(itemView);//ViewHolder
        } else {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.main_recycle_card, parent, false);

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

        title = ((ViewHolder) holder).title;
        title.setText(mData.get(holder_.getAdapterPosition()).getTitle());
        Log.d("position111111", position + "");


        content = ((ViewHolder) holder).content;
        content.setText(mData.get(holder_.getAdapterPosition()).getContent());

        tag1 = ((ViewHolder) holder).tag1;
        String gettag1=mData.get(holder_.getAdapterPosition()).getTag1();
        String gettag2=mData.get(holder_.getAdapterPosition()).getTag2();
        String gettag3=mData.get(holder_.getAdapterPosition()).getTag3();
        if(gettag1!=null && !gettag1.trim().equals(""))
            tag1.setText("#"+mData.get(holder_.getAdapterPosition()).getTag1());
        else
            tag1.setText("");

        tag2 = ((ViewHolder) holder).tag2;

        if(gettag2!=null && !gettag2.trim().equals(""))
            tag2.setText("#"+mData.get(holder_.getAdapterPosition()).getTag2());
        else
            tag2.setText("");

        tag3 = ((ViewHolder) holder).tag3;
        if(gettag3!=null && !gettag3.trim().equals(""))
            tag3.setText("#"+mData.get(holder_.getAdapterPosition()).getTag3());
        else
            tag3.setText("");

        url = ((ViewHolder) holder).url;
        url.setText(mData.get(holder_.getAdapterPosition()).getUrl());

        num_phase = ((ViewHolder) holder).num_phase;
        num_phase.setText(mData.get(holder_.getAdapterPosition()).getNum_phase());

        main_card_cate  = ((ViewHolder) holder).main_card_cate;
        if(mData.get(holder_.getAdapterPosition()).getCate()>-1)
            main_card_cate.setText(mData.get(holder_.getAdapterPosition()).getCate()+"");
        else
            main_card_cate.setText(0+"");

        main_card_icon = ((ViewHolder) holder).main_card_icon;
        if(mData.get(holder_.getAdapterPosition()).getIcon()>0){
            main_card_icon.setImageDrawable(GetDrawbleIcon.getIcon(mData.get(holder_.getAdapterPosition()).getIcon(),mContext));
        }else{
            main_card_icon.setImageDrawable(null);
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
        TextView url;
        ImageView image, main_card_icon;
        TextView title;
        TextView content;
        TextView tag1, tag2, tag3;
        View itemView;
        TextView num_phase, main_card_cate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //image = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            tag1 = itemView.findViewById(R.id.tag1);
            tag2 = itemView.findViewById(R.id.tag2);
            tag3 = itemView.findViewById(R.id.tag3);
            url = itemView.findViewById(R.id.url);
            main_card_icon = itemView.findViewById(R.id.main_card_icon);
            main_card_cate = itemView.findViewById(R.id.main_card_cate);

            this.itemView = itemView;
            num_phase = itemView.findViewById(R.id.num_phase);
            title.setTag(getAdapterPosition());
            Log.d("position : ", getAdapterPosition() + "!");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        // 데이터 리스트로부터 아이템 데이터 참조.
                        //RecyclerItem item = mData.get(pos) ;
                        Log.d("position ~~: ", getAdapterPosition() + "!!!");
                        //  mData.get(pos)
                        // TODO : use item.
                        main_content_card mcc = mData.get(pos);
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        String url = mcc.getUrl().trim();
                        if (!url.startsWith("http://") && !url.startsWith("https://"))
                            url = "http://" + url;
                        intent.setData(Uri.parse(url));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        Toast.makeText(mContext, "일반클릭", Toast.LENGTH_SHORT).show();
                        Log.d("position 1!!!: ", holder_.getAdapterPosition() + "!");
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        main_content_card mcc = mData.get(pos);
                        LayoutInflater inflater = LayoutInflater.from(mContext);
                        Intent intent = new Intent(mContext, content_modi_dialog.class);
                        intent.putExtra("data", "Test Popup");
                        intent.putExtra("title", mcc.getTitle());
                        intent.putExtra("description", mcc.getContent());
                        intent.putExtra("url", mcc.getUrl());
                        intent.putExtra("tag1", mcc.getTag1());
                        intent.putExtra("tag2", mcc.getTag2());
                        intent.putExtra("tag3", mcc.getTag3());
                        intent.putExtra("phase_num", mcc.getNum_phase());
                        intent.putExtra("position",pos);
                        intent.putExtra("cate",mcc.getCate());
                        intent.putExtra("icon",mcc.getIcon());
                        ((Activity) mContext).startActivityForResult(intent, 1);
                        //mContext.startActivity(intent);
                        Toast.makeText(mContext, "롱클릭", Toast.LENGTH_SHORT).show();
                        Log.d("position2!!! : ", getAdapterPosition() + "tag :"+mcc.getTag1()+","+mcc.getTag2()+","+mcc.getTag3()+" icon:"+mcc.getIcon());
                    }
                    return true;  //true 설정

                }
            });

        }

        public void setItem(main_content_card item) {

            title.setText(item.getTitle());
            content.setText(item.getContent());
            tag1.setText(item.getTag1());
            tag2.setText(item.getTag2());
            tag3.setText(item.getTag3());
            url.setText(item.getUrl());
            num_phase.setText(item.getNum_phase());
        }


    }

}

//RecyclerView.Adapter<main_recyle_card_adapter.ViewHolder>
/*{
    ArrayList<main_content_card> items = new ArrayList<main_content_card>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.main_recycle_card, parent, false);

        return new ViewHolder(itemView);
    }

   @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        main_content_card item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(main_content_card item){
        items.add(item);
    }
}*/
