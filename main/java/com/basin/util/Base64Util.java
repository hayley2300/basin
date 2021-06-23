package com.basin.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;


public class Base64Util {
    public static String base64Encoder(String s){
        String encodedString ="";
        try {
            String target = s;
            byte[] targetBytes = target.getBytes("UTF-8");

            // Base64 인코딩 ///////////////////////////////////////////////////
            Base64.Encoder encoder = Base64.getEncoder();
            // Encoder#encode(byte[] src) :: 바이트배열로 반환
            byte[] encodedBytes = encoder.encode(targetBytes);
            System.out.println(new String(encodedBytes));

            // Encoder#encodeToString(byte[] src) :: 문자열로 반환
            encodedString = encoder.encodeToString(targetBytes);
            System.out.println();

            // Base64 디코딩 ///////////////////////////////////////////////////
            java.util.Base64.Decoder decoder = Base64.getDecoder();

            // Decoder#decode(bytes[] src)
            byte[] decodedBytes1 = decoder.decode(encodedBytes);
            // Decoder#decode(String src)
            byte[] decodedBytes2 = decoder.decode(encodedString);
        }catch (UnsupportedEncodingException e){

        }
        return encodedString;
    }
    public static String base64Decoder(String s){
        String decodedString ="";
        try {
            String target = s;
            Base64.Decoder decoder = Base64.getDecoder();
            // Decoder#decode(bytes[] src)
            byte[] decodedBytes1 = decoder.decode(target);
            // 디코딩한 문자열을 표시
            decodedString = new String(decodedBytes1, "UTF-8");

        }catch (UnsupportedEncodingException e){

        }
        return decodedString;
    }
    // post 보낼 때, +=가 공백으로 바뀌는 현상 방진
    // JSP에서는 +가 %2B, =가 %26 이지만
    // HTML(JSON)에서는 + %2B로 동일하지만, =가 %3D이다.
    public static String replacefinal(String s){
        // JSP
       // return s.replaceAll("\\+", "%2B").replaceAll("=", "%26");
        return s.replaceAll("\\+", "%2B").replaceAll("=", "%3D");
    }
}
