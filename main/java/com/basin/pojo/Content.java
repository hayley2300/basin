package com.basin.pojo;

import java.util.HashMap;

public class Content {

    private int num;
    private int phase;
    private String userid;
    private int cate;
    private int icon;
    private String user_menu1;
    private String user_menu2;
    private String description;
    private String title;
    private String tag1;
    private String tag2;
    private String tag3;
    private int user_clickcnt;
    private String credate;
    private String modidate;
    private String delYN;
    private String favoriteYN;
    private int scrapcnt;
    private String urlmain;
    private String urlsub;

    public Content() {

    }
    public Content(int num, int phase, String userid, int cate, int icon,String user_menu1, String user_menu2, String description, String title,
                   String tag1, String tag2, String tag3, int user_clickcnt, String credate, String modidate, String delYN, String favoriteYN, int scrapcnt
            ,String urlmain, String urlsub) {
        super();
        this.num = num;
        this.phase = phase;
        this.userid = userid;
        this.cate = cate;
        this.icon = icon;
        this.user_menu1 = user_menu1;
        this.user_menu2 = user_menu2;
        this.description = description;
        this.title = title;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.user_clickcnt = user_clickcnt;
        this.credate = credate;
        this.modidate = modidate;
        this.delYN = delYN;
        this.favoriteYN = favoriteYN;
        this.scrapcnt = scrapcnt;
        this.urlmain = urlmain;
        this.urlsub = urlsub;
    }
    public Content(String userid, int cate, int icon, String user_menu1, String user_menu2, String description, String title,
                   String tag1, String tag2, String tag3, String credate,String urlmain, String urlsub) {
        super();
        this.userid = userid;
        this.cate = cate;
        this.icon = icon;
        this.user_menu1 = user_menu1;
        this.user_menu2 = user_menu2;
        this.description = description;
        this.title = title;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.credate = credate;
        this.urlmain = urlmain;
        this.urlsub = urlsub;
    }


    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }

    public int getPhase() {
        return phase;
    }
    public void setPhase(int phase) {
        this.phase = phase;
    }

    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public int getCate() {
        return cate;
    }
    public int getIcon() {
        return icon;
    }
    public void setCate(int cate) {
        this.cate = cate;
    }
    public void setIcon(int icon) {
        this.icon = icon;
    }
    public String getUser_menu1() {
        return user_menu1;
    }
    public void setUser_menu1(String user_menu1) {
        this.user_menu1 = user_menu1;
    }

    public String getUser_menu2() {
        return user_menu2;
    }
    public void setUser_menu2(String user_menu2) {
        this.user_menu2 = user_menu2;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTag1() {
        return tag1;
    }
    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }
    public String getTag2() {
        return tag2;
    }
    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }
    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public int getUser_clickcnt() {
        return user_clickcnt;
    }
    public void setUser_clickcnt(int user_clickcnt) {
        this.user_clickcnt = user_clickcnt;
    }

    public String getCredate() {
        return credate;
    }
    public void setCredate(String credate) {
        this.credate = credate;
    }
    public String getModidate() {
        return modidate;
    }
    public void setModidate(String modidate) {
        this.modidate = modidate;
    }
    public String getDelYN() {
        return delYN;
    }
    public void setDelYN(String delYN) {
        this.delYN = delYN;
    }

    public String getFavoriteYN() {
        return favoriteYN;
    }
    public void setFavoriteYN(String favoriteYN) {
        this.favoriteYN = favoriteYN;
    }
    public int getScrapcnt() {
        return scrapcnt;
    }
    public void setScrapcnt(int scrapcnt) {
        this.scrapcnt = scrapcnt;
    }


    public String getUrlmain() {
        return urlmain;
    }

    public void setUrlmain(String urlmain) {
        this.urlmain = urlmain;
    }

    public String getUrlsub() {
        return urlsub;
    }

    public void setUrlsub(String urlsub) {
        this.urlsub = urlsub;
    }
}
