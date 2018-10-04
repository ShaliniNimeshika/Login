/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.servlet.http.HttpServletRequest;
//import org.apache.log4j.Logger;

/**
 *
 * @author shalini_w
 */
public class Log4jLogger {
    public void getLogger(String msg, String type, String username, HttpServletRequest request) {
		Logger l = Logger.getLogger("Log");
		try {
			FileHandler fh = null;
                   
                        fh = new FileHandler("D:/Projects/Sample Projects/Login Log/aclLog.log", true);
                    
			l.addHandler(fh);
	        fh.setFormatter(new SimpleFormatter());
	        if(type.equals("warn")) {
	        	l.warning(msg+" USERNAME: "+username+" IP ADDRESS: "+ request.getRemoteAddr()+"\n\n");
	        }
	        else if(type.equals("info")) {
	        	l.info(msg+" USERNAME: "+username+" IP ADDRESS: "+ request.getRemoteAddr()+"\n\n");
	        }
	        fh.close();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
