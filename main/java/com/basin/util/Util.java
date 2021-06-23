package com.basin.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Util {
    // URL에서 메타데이터를 추출하는 메소드
    public String getMetadataFromUrl(String url) {
        try {
           // Metadata metadata = new Metadata();
            String imageUrl;
            //metadata.url = url;
            Document doc = Jsoup.connect(url).get();
            //metadata.title = doc.select("meta[property=og:title]").first().attr("content");
           // metadata.description = doc.select("meta[property=og:description]").get(0).attr("content");
            imageUrl = doc.select("meta[property=og:image]").get(0).attr("content");
            return imageUrl;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
/*
    // 텍스트에서 대표 URL을 추출하는 메소드
    public static String extractUrlFromText(String text) {
        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);

        ArrayList<String> urls =  new ArrayList<>();
        while (urlMatcher.find()) {
            urls.add(text.substring(urlMatcher.start(0), urlMatcher.end(0)));
        }

        String resultUrl = null;
        for (String url : urls) {
            if (Validator.isUrl(url)) {
                resultUrl = url;
                break;
            }
        }
        return resultUrl;
    }*/
}

