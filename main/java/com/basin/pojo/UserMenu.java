package com.basin.pojo;

import java.util.HashMap;

public class UserMenu {


    private String user_menu1;
    private int on_off;
    private String editext;

    public UserMenu() {

    }

    public UserMenu(String user_menu1) {
        this.user_menu1 = user_menu1;
        this.on_off = 1;
        this.editext="";
    }
    public UserMenu(String user_menu1, String on_off) {
        this.user_menu1 = user_menu1;
    }
    public int getOn_off() {
        return on_off;
    }

    public void setOn_off(int on_off) {
        this.on_off = on_off;
    }


    public String getUser_menu1() {
        return user_menu1;
    }

    public void setUser_menu1(String user_menu1) {
        this.user_menu1 = user_menu1;
    }
    public String getEditext() {
        return editext;
    }

    public void setEditext(String editext) {
        this.editext = editext;
    }
}
