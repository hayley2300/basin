package com.basin.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basin.R;
import com.basin.pojo.main_content_card;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    TextView content;
    TextView tag1, tag2, tag3;
    TextView url;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.title);
        content = itemView.findViewById(R.id.content);
        tag1 = itemView.findViewById(R.id.tag1);
        tag2 = itemView.findViewById(R.id.tag2);
        tag3 = itemView.findViewById(R.id.tag3);
        url = itemView.findViewById(R.id.url);
    }

    public void setItem(main_content_card item) {
        title.setText(item.getTitle());
        content.setText(item.getContent());
        tag1.setText(item.getTag1());
        tag2.setText(item.getTag2());
        tag3.setText(item.getTag3());
        url.setText(item.getUrl());
    }


}

