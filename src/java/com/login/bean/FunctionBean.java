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
    
    
    public FunctionBean(String func_id, String func_name) {
        this.function_id = func_id;
        this.function_name = func_name;
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

    public FunctionBean(String function_id) {
        this.function_id = function_id;
    }

    
}
