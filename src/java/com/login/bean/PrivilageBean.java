/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.bean;

import java.util.ArrayList;

/**
 *
 * @author shalini_w
 */
public class PrivilageBean {
    private String pid;
    private String rid;
    private String ifid;
    
    private ArrayList<InterfaceBean> ibean;

    public PrivilageBean(String pid, String ifid, ArrayList<InterfaceBean> ibean) {
        this.pid = pid;
        this.ifid = ifid;
        this.ibean = ibean;
    }
    
    public PrivilageBean(String pid, String ifid) {
        this.pid = pid;
        this.ifid = ifid;
    }

    public String getRid() {
        return rid;
    }

    public String getIfid() {
        return ifid;
    }

    public ArrayList<InterfaceBean> getIbean() {
        return ibean;
    }
    
    

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
