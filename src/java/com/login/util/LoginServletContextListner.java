/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author shalini_w
 */
@WebListener
public class LoginServletContextListner implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {        
        System.out.println("######################################");
        System.out.println("ServletContextListener contextInitialized method has been called in here"+this.getClass().getName());
        ServletContext servletCon = sce.getServletContext();
        System.out.println(servletCon +" is Initialized");
        System.out.println("######################################");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("######################################");
        System.out.println("ServletContextListener contextDestroyed method has been called in here"+this.getClass().getName());
        ServletContext servletCon = sce.getServletContext();
        System.out.println(servletCon +" is destroyed");
        System.out.println("######################################");
    }
}
