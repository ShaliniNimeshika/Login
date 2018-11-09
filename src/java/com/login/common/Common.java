/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.common;

import java.util.HashMap;

/**
 *
 * @author shalini_w
 */
public class Common {

    public boolean checkUserLogin(HashMap<String, String> usermap, String username) {

        HashMap<String, String> umap = usermap;
        String uname = username;
        String oldSessionID = umap.get(uname);
        //checking the old session ID of user.
        if (oldSessionID == null) {
            //no user is found
            return false;
        } else {
            //there is a session ID for this user
            return true;
        }
    }

    public boolean checkUserDevice(HashMap<String, String> userdev, String username, String newIP) {
        HashMap<String, String> userdevice = userdev;
        String uname = username;
        String newIPAddr = newIP;
        String oldIPAddr = userdevice.get(uname);
        
        if (oldIPAddr == null) {
            //not a logged device
            return true;
        } else {
            if (oldIPAddr.equals(newIPAddr)) {
                //found same device - APPROVE LOGIN
                return true;
            } else {
                //found different device : RESTRICT LOGIN for first machine
                return false;
            }
        }
    }
}
