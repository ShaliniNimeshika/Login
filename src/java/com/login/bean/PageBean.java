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

public class PageBean {
    private String url;
    private String name;
    private String interfaceId;
    
    public PageBean(String url, String name, String interfaceId) {
        this.url = url;
        this.name = name;
        this.interfaceId = interfaceId;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the interfaceId
     */
    public String getInterfaceId() {
        return interfaceId;
    }
    
    
}
    
