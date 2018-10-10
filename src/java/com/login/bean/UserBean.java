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
    private String active;
    private String reset_time;
    private int reset_duration;

    public UserBean(String username) {
        this.username = username;
    }

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
    
     public UserBean(String userid, String username,String password,String urole,String active){
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.urole = urole;
        this.active = active;
    }
     
     public UserBean(String userid, String username, String password, String urole, String active, String reset_time, int reset_duration) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.urole = urole;
        this.active = active;
        this.reset_time = reset_time;
        this.reset_duration = reset_duration;
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

    /**
     * @return the active
     */
    public String getActive() {
        return active;
    }

    public String getReset_time() {
        return reset_time;
    }

    public int getReset_duration() {
        return reset_duration;
    }
    
}
