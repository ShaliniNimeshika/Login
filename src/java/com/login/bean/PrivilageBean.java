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
public class PrivilageBean {
    private String pid;
    private String rid;
    private String ifid;

    public PrivilageBean(String pid) {
        this.pid = pid;
    }
    

    /**
     * @return the pid
     */
    public String getPid() {
        return pid;
    }
    
    
}
