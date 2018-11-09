/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.bean;

import java.io.Serializable;

/**
 *
 * @author shalini_w
 */
public class LoginBean implements Serializable{
  private static final long serialVersionUID = 1L;
  
    private String username;
    private String password;
    private String role;
    private String roleid;
    private String status;
    //private Page pages;
    /**
     * @return the username
     */
    
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the roleid
     */
    public String getRoleid() {
        return roleid;
    }

    /**
     * @param roleid the roleid to set
     */
    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
