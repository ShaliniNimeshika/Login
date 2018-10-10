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
public class RoleBean {
    private String rolename;
    private String roleid;
    private ArrayList<PrivilageBean> pbean;
    private ArrayList<InterfaceBean> ibean;

    public ArrayList<InterfaceBean> getIbean() {
        return ibean;
    }

    public ArrayList<PrivilageBean> getPbean() {
        return pbean;
    }

    public RoleBean(String rolename, String roleid, ArrayList<InterfaceBean> ibean) {
        this.rolename = rolename;
        this.roleid = roleid;
        this.ibean = ibean;
    }
    
    public RoleBean(String roleid,String rolename){
        this.roleid = roleid;
        this.rolename = rolename;
    }
    
    public RoleBean(String rolename){
        this.rolename = rolename;
    }

    /**
     * @return the rolename
     */
    public String getRolename() {
        return rolename;
    }


    /**
     * @return the roleid
     */
    public String getRoleid() {
        return roleid;
    }

    
    
}
