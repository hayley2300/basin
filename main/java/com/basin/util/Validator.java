package com.basin.util;

import android.text.TextUtils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator { // URL Validator
    public static boolean isUrl(String text) {
        Pattern p = Pattern.compile("^(?:https?:\\/\\/)?(?:www\\.)?[a-zA-Z0-9./]+$");
        Matcher m = p.matcher(text);
        if  (m.matches()) return true;
        URL u = null;
        try {
            u = new URL(text);
        } catch (MalformedURLException e) {
            return false;
        }
        try {
            u.toURI();
        } catch (URISyntaxException e) {
            return false;
        }
        return true;
    }

    /**
     * Comment  : 정상적인 이메일 인지 검증.
     */
    public static boolean isValidEmail(String email) {
        boolean err = false;
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if(m.matches()) {
            err = true;
        }
        return err;
    }
    // 알파벳, 숫자, 특수문자만 들어있는지 검사하기
    public static boolean isValidIdPw(String s){
        String str = "abAB12!@#~`!@#$%^&*()_+=-|][}{;:";
        boolean chk = Pattern.matches("^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~`]+$", str);
        return chk;
    }

    // 특수문자가 있는지 검사
    public static boolean isSpecialChar(String s){
            if (TextUtils.isEmpty(s)) {
                return false;
            }

            for (int i = 0; i < s.length(); i++) {
                if (!Character.isLetterOrDigit(s.charAt(i))) {
                    return true;
                }
            }

            return false;
    }
}


