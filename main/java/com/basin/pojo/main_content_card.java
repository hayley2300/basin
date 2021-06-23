package com.basin.pojo;

import java.lang.reflect.Array;

public class main_content_card {
    String title;
    String content;
    String tag1, tag2, tag3;
    String url;
    int num;
    int phase;
    int control_n;
    int cate, icon;
    String num_phase ;

    public main_content_card(String title, String content, String tag1, String tag2, String tag3, String url, int num, int phase, int control_n, int cate, int icon) {
        this.title = title;
        this.content = content;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.url = url;
        this.num = num;
        this.phase = phase;
        this.control_n = control_n;
        this.num_phase = String.valueOf(phase)+"N"+String.valueOf(num);
        this.cate = cate;
        this.icon = icon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }
    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }
    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setIdx(int num) {
        this.num = num;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public void setNum_phase(String num_phase) {
        this.num_phase = num_phase;
    }

    public void setCate(int cate) {
        this.cate = cate;
    }
    public void setIcon(int icon) {
        this.icon = icon;
    }
    public int getCate() {
        return cate;
    }
    public int getIcon() {
        return icon;
    }
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTag1() {
        return tag1;
    }
    public String getTag2() {
        return tag2;
    }
    public String getTag3() {
        return tag3;
    }

    public String getUrl() {
        return url;
    }

    public int getNum() {
        return num;
    }
    public int getPhase() {
        return phase;
    }

    public int getControl_n() {
        return control_n;
    }

    public String getNum_phase() {
        return num_phase;
    }
}
