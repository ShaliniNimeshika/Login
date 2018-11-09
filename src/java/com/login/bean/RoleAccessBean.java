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
public class RoleAccessBean implements Serializable{
  private static final long serialVersionUID = 1L;
  
    private String rid;
    private String i_id;
    private String i_name;
    private String f_id;
    private String f_name;

    public String getIf_id() {
        return if_id;
    }

    public RoleAccessBean(String if_id) {
        this.if_id = if_id;
    }

    public RoleAccessBean(String rid, String i_id, String i_name, String f_id, String f_name, String if_id) {
        this.rid = rid;
        this.i_id = i_id;
        this.i_name = i_name;
        this.f_id = f_id;
        this.f_name = f_name;
        this.if_id = if_id;
    }
    private String if_id;

    public RoleAccessBean(String rid, String i_id, String i_name, String f_id, String f_name) {
        this.rid = rid;
        this.i_id = i_id;
        this.i_name = i_name;
        this.f_id = f_id;
        this.f_name = f_name;
    }

    public RoleAccessBean(String i_id, String i_name, String f_id, String f_name) {
        this.i_id = i_id;
        this.i_name = i_name;
        this.f_id = f_id;
        this.f_name = f_name;
    }

    public String getRid() {
        return rid;
    }

    public String getI_id() {
        return i_id;
    }

    public String getI_name() {
        return i_name;
    }

    public String getF_id() {
        return f_id;
    }

    public String getF_name() {
        return f_name;
    }
    
    
    
}
