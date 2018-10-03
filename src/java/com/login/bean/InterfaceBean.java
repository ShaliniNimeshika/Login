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
public class InterfaceBean {

    private String interfaceid;

    private String functionid;
    private String f_name;
    private String if_id;
    private String i_name;
    private String i_id;
    private String i_url;
    private String desc;
    private ArrayList<FunctionBean> fbean;

    public InterfaceBean(String if_id, String interfaceid, String i_name, String functionid, String f_name) {
        this.interfaceid = interfaceid;
        this.i_name = i_name;
        this.functionid = functionid;
        this.f_name = f_name;
        this.if_id = if_id;
    }

    public InterfaceBean(String if_id, String i_name, String f_name) {
        this.i_name = i_name;
        this.f_name = f_name;
        this.if_id = if_id;
    }

    public InterfaceBean(String i_id, String i_name, String i_url, String desc) {
        this.i_id = i_id;
        this.i_name = i_name;
        this.i_url = i_url;
        this.desc = desc;
    }
    
    public InterfaceBean(String i_id, String i_name, String i_url, String desc, ArrayList<FunctionBean> f) {
        this.i_id = i_id;
        this.i_name = i_name;
        this.i_url = i_url;
        this.desc = desc;
        this.fbean = f;
    }

    public InterfaceBean(String if_id) {
        this.if_id = if_id;
    }

    /**
     * @return the interfaceid
     */
    public String getInterfaceid() {
        return interfaceid;
    }

    

    /**
     * @return the functionid
     */
    public String getFunctionid() {
        return functionid;
    }

    /**
     * @return the f_name
     */
    public String getF_name() {
        return f_name;
    }

    /**
     * @return the if_id
     */
    public String getIf_id() {
        return if_id;
    }

    /**
     * @return the i_name
     */
    public String getI_name() {
        return i_name;
    }
    
    /**
     * @return the i_id
     */
    public String getI_id() {
        return i_id;
    }

    /**
     * @return the i_url
     */
    public String getI_url() {
        return i_url;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @return the fbean
     */
    public ArrayList<FunctionBean> getFbean() {
        return fbean;
    }


}
