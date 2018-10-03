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
                UserBean ub = new UserBean(rs.getString("u.userid"), rs.getString("u.username"), rs.getString("r.rolename"));
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
        String rid = role;

        try {
            String sql = "INSERT INTO user(roleid,username,password) VALUES ('" + rid + "','" + username + "','" + password + "')";
            statement2 = con.createStatement();
            statement2.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void deleteUser(String uid) {
        String userid = uid;

        try {
            String sql = "DELETE FROM user WHERE userid='" + userid + "'";
            statement2 = con.createStatement();
            statement2.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<UserBean> loadUserData(String uid) {
        System.out.println("load user data function");
        ArrayList<UserBean> data = new ArrayList<>();
        String userid = uid;
        System.out.println("userid in function :"+userid);
        try {
            con = DBConnection.createConnection();
            Statement update_statement = con.createStatement();
            String sql = "SELECT u.userid, u.username, u.password, r.rolename FROM user u, role r WHERE u.userid='" + userid + "' and u.roleid=r.roleid";
            ResultSet result = update_statement.executeQuery(sql);

            while (result.next()) {
                UserBean ub = new UserBean(result.getString("u.userid"),result.getString("u.username"),result.getString("u.password"),result.getString("r.rolename"));
                data.add(ub);
                System.out.println("3:user data loaded successfully");
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }

    public static void updateUser(String userid, String username, String password, String roleid) {
        String uid = userid;
        String uname = username;
        String pwd = password;
        String rid = roleid;

        try {
            String sql = "UPDATE user SET roleid='"+rid+"',username='"+uname+"',password='"+pwd+"' WHERE userid='"+uid+"'";
            statement2 = con.createStatement();
            statement2.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean isRegistered(String username) {
        boolean flag = true;
        
        try {
            String sql = "SELECT COUNT(*) AS num FROM user WHERE username='"+username+"'";
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery(sql);
            
            while (result.next()) {                
                System.out.println("num is:"+result.getString("num"));
                
                if (Integer.parseInt(result.getString("num"))== 0) {
                    flag = false;
                    System.out.println("flg in if" + flag);
                    return flag;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

}
