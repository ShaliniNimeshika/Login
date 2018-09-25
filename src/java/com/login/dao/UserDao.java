/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.dao;

import com.login.bean.UserBean;
import com.login.util.DBConnection;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author shalini_w
 */
public class UserDao {
    static Connection con = null;
    static Statement statement = null;
    static Statement statement2 = null;
    static ResultSet rs = null;
    static String role = null;
    static String roleid = null;

    public static ArrayList<UserBean> loadAllUsers() {
        ArrayList<UserBean> user = new ArrayList<UserBean>();
        
        try {
            con = DBConnection.createConnection();
            statement = con.createStatement();
            String sql = "SELECT u.userid, u.username, r.rolename FROM user u, role r WHERE u.roleid=r.roleid";
            rs = statement.executeQuery(sql);
            
            while (rs.next()) { 
                UserBean ub = new UserBean(rs.getString("u.userid"),rs.getString("u.username"),rs.getString("r.rolename"));
                user.add(ub);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public static void addUser(String uname, String pwd, String role) {
        String username = uname;
        String password = pwd;
        String roleid = role;
        
        try {
            String sql = "INSERT INTO user(roleid,username,password) VALUES ('"+roleid+"','"+username+"','"+password+"')";
            statement2=con.createStatement();
            statement2.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }   
}
