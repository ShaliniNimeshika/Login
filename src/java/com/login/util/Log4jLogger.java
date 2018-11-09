/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
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
			FileHandler fh;
                   
                        fh = new FileHandler("D:/Projects/Login Project/Login Log/aclLog.log", true);
                    
			l.addHandler(fh);
	        fh.setFormatter(new SimpleFormatter());
	        if(type.equals("warn")) {
	        	l.log(Level.WARNING, "{0} USERNAME: {1} IP ADDRESS: {2}\n\n", new Object[]{msg, username, request.getRemoteAddr()});
	        }
	        else if(type.equals("info")) {
	        	l.log(Level.INFO, "{0} USERNAME: {1} IP ADDRESS: {2}\n\n", new Object[]{msg, username, request.getRemoteAddr()});
	        }
	        fh.close();
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // TODO Auto-generated catch block
        
	}
}
