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
public class FunctionBean {
    private String function_id;
    private String function_name;
    private String function_url;
    
    
    public FunctionBean(String func_id, String func_name, String func_url) {
        this.function_id = func_id;
        this.function_name = func_name;
        this.function_url = func_url;
     }

    /**
     * @return the function_id
     */
    public String getFunction_id() {
        return function_id;
    }

    /**
     * @return the function_name
     */
    public String getFunction_name() {
        return function_name;
    }

    /**
     * @return the function_url
     */
    public String getFunction_url() {
        return function_url;
    }
    
}
