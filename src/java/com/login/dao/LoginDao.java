/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.dao;

import com.login.bean.LoginBean;
import com.login.bean.PageBean;
import static com.login.connection.LoginServlet.session;
import com.login.util.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    static String roleid = null;
    
    public static boolean authenticateUser(LoginBean loginbean){
      
        String unameDB = null;
        String passwordDB = null;
        String roleDB = null;
//        String roleid = null;
        try {
            String username = loginbean.getUsername();
            String password = loginbean.getPassword();
                 
            con = DBConnection.createConnection();
            statement = con.createStatement();            
            String sql = "SELECT u.username , u.password , r.rolename , r.roleid from user u , role r where u.roleid=r.roleid";
            rs = statement.executeQuery(sql);
            
            while (rs.next()) {              
                unameDB = rs.getString("u.username");
                passwordDB = rs.getString("u.password");
                roleDB = rs.getString("r.rolename");
                roleid = rs.getString("r.roleid");
                
                if (username.equals(unameDB) && password.equals(passwordDB)) {
                    loginbean.setRoleid(roleid);
                    session.setAttribute("roleid", roleid);
                    return true;
                }
            }             
        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return false;
    }

    public static ArrayList<PageBean> loadPages(){
        ArrayList<PageBean> data = new ArrayList<PageBean>();
        
        try {
            con = DBConnection.createConnection();
            statement = con.createStatement();
            String sql = "SELECT i.interfaceid, i.url, i.name FROM interface i, func_interface fi, privilage p WHERE fi.if_id = p.if_id AND p.pid ='"+roleid+"'"; 
            rs = statement.executeQuery(sql);
            
            while (rs.next()) { 
                PageBean p = new PageBean(rs.getString("i.url"), rs.getString("i.name"),rs.getString("i.interfaceid"));
                data.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;        
    }
}
