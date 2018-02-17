package com.j2core.sts.webcrawler.ui.service;

/**
 * The singleton object for save application's context path
 */
public class ObjectContextPath{

    private static volatile String contextPath = null;   // application's context path

    public static String getPath(){

        if (contextPath == null){

            contextPath = EasyListener.context.getContextPath();

        }
        return contextPath;
    }

}
