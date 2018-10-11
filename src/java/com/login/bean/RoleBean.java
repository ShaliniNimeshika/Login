/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shalini_w
 */
public class RoleBean {
    private String rolename;
    private String roleid;
    private ArrayList<PrivilageBean> pbean;
    private ArrayList<InterfaceBean> ibean;
    private List<RoleAccessBean> ra_bean;

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
    
    /**
     *
     * @param rolename
     * @param roleid
     * @param ra_bean
     */
    public RoleBean(String rolename, String roleid, List<RoleAccessBean> ra_bean) {
        this.rolename = rolename;
        this.roleid = roleid;
        this.ra_bean = ra_bean;
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

    public List<RoleAccessBean> getRa_bean() {
        return ra_bean;
    }

    
    
}
