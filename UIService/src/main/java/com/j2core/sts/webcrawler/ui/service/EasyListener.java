package com.j2core.sts.webcrawler.ui.service;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * The class easy implement ServletContextListener interface
 */
public class EasyListener implements ServletContextListener {

    public static ServletContext context;

    public void contextInitialized(ServletContextEvent servletContextEvent) {

        context = servletContextEvent.getServletContext();

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        context = null;

    }
}
