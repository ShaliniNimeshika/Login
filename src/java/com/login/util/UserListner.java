/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.util;

import com.login.bean.UserBean;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 *
 * @author shalini_w
 */
public class UserListner implements HttpSessionBindingListener {

    private String userId;
    private boolean alreadyLoggedIn;

    //setters and getters of all your User dto including accountId...
//    private static Map<User, HttpSession> logins = new HashMap<User, HttpSession>();
//
//    @Override
//    public boolean equals(Object other) {
//        return (other instanceof UserListner) && (getAccountId() != null)
//                ? getAccountId().equals(((UserListner) other).getAccountId()) : (other == this);
//    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
//        HttpSession oldSession = logins.get(this);
//        if (oldSession != null) {
//            alreadyLoggedIn = true;
//        } else {
//            logins.put(this, event.getSession());
//        }
        //Note: you can comment above code and remove comments from below code. removing comments from 
        //below code will remove old session of user and let the user log-in from new session.
//        HttpSession session = logins.remove(this);
//        if (session != null) {
//          session.invalidate();
//        }
//        HttpSession put;
//        put = logins.put(event, event.getSession());
//        logins.put(this, event.getSession());  
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
//        logins.remove(this);
    }
}
