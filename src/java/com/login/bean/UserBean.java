/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.bean;

/**
 *
 * @author shalini_w
 */
public class UserBean {
    private String userid;
    private String username;
    private String password;
    private String urole;

    public UserBean(String userid, String username,String urole){
        this.userid = userid;
        this.username = username;
        this.urole = urole;
    }
    
    public UserBean(String userid, String username,String password,String urole){
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.urole = urole;
    }
    /**
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the urole
     */
    public String getUrole() {
        return urole;
    }
    
}
