package com.j2core.sts.webcrawler.crawler.pagehandler;

import com.j2core.sts.webcrawler.dao.model.informationdto.UrlData;

import java.util.LinkedList;
import java.util.List;

/*
 * Created by sts on 8/18/17.
 */

/**
 *
 *The class verify URLs
 */
public class UrlVerification {

    /**
     * The method verify collection with new URLs
     *
     * @param urlCollection   collection with new URLs
     * @return  collection with correctly URLs
     */
    static public List<UrlData>  verifyUrlCollection(List<UrlData> urlCollection){

        List<UrlData> correctlyUrls = new LinkedList<>();

        for (UrlData urlData : urlCollection){

            String url = verifyUrl(urlData.getUrl());

            if (url != null){

                correctlyUrls.add(urlData);
            }
        }

        return correctlyUrls;

    }


    /**
     * The method verify URL and if can correct it.
     *
     * @param url   URL for verification
     * @return String if URL is correctly, or null if URL was incorrectly
     */
    private static String verifyUrl(String url){

        String symbols = ".,;:'()!?{}[]=";
        String result = null;
        if (!url.contains("..")){

            String lastChar = String.valueOf(url.charAt(url.length()-1));
            if (symbols.contains(lastChar)){

                result = url.substring(0, url.length()-1);
            }else result = url;
        }
        return result;
    }
}
