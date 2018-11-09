/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 *
 * @author shalini_w
 */
public class UserListner implements HttpSessionBindingListener {
    ServletContext context;
    private String username;
    private boolean loogedIn;
    
    public UserListner(ServletContext context){
        this.context = context;
    }

    public UserListner(String username, boolean loogedIn) {
        this.username = username;
        this.loogedIn = loogedIn;
    }


    public String getUsername() {
        return username;
    }

    public boolean isLoogedIn() {
        return loogedIn;
    }
    
    @Override
    public void valueBound(HttpSessionBindingEvent event){
        System.out.println("The value bound is:" + event.getValue()+" username:"+getUsername()+" log status:"+loogedIn);
    }
    
    @Override
    public void valueUnbound(HttpSessionBindingEvent event){
        System.out.println("The value unbound is " + "username:"+getUsername());
        this.username = null;
        this.loogedIn = false;
    }
}
