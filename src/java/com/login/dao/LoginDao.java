/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.dao;

import com.login.bean.LoginBean;
import com.login.util.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shalini_w
 */
public class LoginDao {
    
    static Connection con = null;
    static Statement statement = null;
    static ResultSet rs = null;
    static String role = null;
    
    public static String authenticateUser(LoginBean loginbean){
      
        String unameDB = "";
        String passwordDB = "";
        String roleDB = "";
        try {
            String username = loginbean.getUsername();
            String password = loginbean.getPassword();
                 
            con = DBConnection.createConnection();
            statement = con.createStatement();            
            String sql = "SELECT u.username , u.password , r.rolename from user u , role r where u.userid=r.roleid";
            rs = statement.executeQuery(sql);
            
            while (rs.next()) {              
                unameDB = rs.getString("u.username");
                passwordDB = rs.getString("u.password");
                roleDB = rs.getString("r.rolename");
                
                if (username.equals(unameDB) && password.equals(passwordDB)) {
                    return roleDB;
                }
            }             
        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return "invalid";
    }
}
