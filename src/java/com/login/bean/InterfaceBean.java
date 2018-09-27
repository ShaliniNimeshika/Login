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
public class InterfaceBean {
    private String interfaceid;
    private String i_name;
    private String functionid;
    private String f_name;
    private String if_id;

    public InterfaceBean(String if_id,String interfaceid,String i_name,String functionid,String f_name) {
        this.interfaceid=interfaceid;
        this.i_name=i_name;
        this.functionid=functionid;
        this.f_name=f_name;
        this.if_id = if_id;
    }
    
    public InterfaceBean(String if_id,String i_name,String f_name) {
        this.i_name=i_name;
        this.f_name=f_name;
        this.if_id = if_id;
    }

    /**
     * @return the interfaceid
     */
    public String getInterfaceid() {
        return interfaceid;
    }

    /**
     * @return the i_name
     */
    public String getI_name() {
        return i_name;
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
    
    
}
