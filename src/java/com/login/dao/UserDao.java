/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.dao;

import com.login.bean.UserBean;
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
public class UserDao {

    public static ArrayList<UserBean> loadAllUsers() {
        ArrayList<UserBean> user = new ArrayList<>();
        Connection con = DBConnection.createConnection();
        try {

            Statement statement = con.createStatement();
            String sql = "SELECT u.userid, u.username,u.password, u.active, r.rolename FROM user u, role r WHERE u.roleid=r.roleid";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                UserBean ub = new UserBean(rs.getString("u.userid"), rs.getString("u.username"), rs.getString("u.password"), rs.getString("r.rolename"),rs.getString("u.active"));
                user.add(ub);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }

    public static void addUser(String uname, String pwd, String role, String status, String rstatus, String duration) {
        String username = uname;
        String password = pwd;
        String rid = role;
        String active = status;
        String reset = rstatus;
        int reset_duration = Integer.parseInt(duration);
        Connection con = DBConnection.createConnection();
        try {
            String sql = "INSERT INTO user(roleid,username,password,active,status,reset_duration) VALUES ('" + rid + "','" + username + "','" + password + "','"+active+"','"+reset+"','"+reset_duration+"')";
            Statement statement2 = con.createStatement();
            statement2.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void deleteUser(String uid) {
        String userid = uid;
        Connection con = DBConnection.createConnection();
        try {
            String sql = "DELETE FROM user WHERE userid='" + userid + "'";
            Statement statement2 = con.createStatement();
            statement2.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static ArrayList<UserBean> loadUserData(String uid) {
        System.out.println("load user data function");
        ArrayList<UserBean> data = new ArrayList<>();
        String userid = uid;
        System.out.println("userid in function :"+userid);
        Connection con = DBConnection.createConnection();
        try {
            Statement update_statement = con.createStatement();
            String sql = "SELECT u.userid, u.username, u.password, u.active,u.reset_time, u.reset_duration, r.rolename FROM user u, role r WHERE u.userid='" + userid + "' and u.roleid=r.roleid";
            ResultSet result = update_statement.executeQuery(sql);

            while (result.next()) {
                
                UserBean ub = new UserBean(result.getString("u.userid"),result.getString("u.username"),result.getString("u.password"),result.getString("r.rolename"),result.getString("u.active"),result.getString("reset_time"), result.getInt("reset_duration"));
                data.add(ub);
                System.out.println("3:user data loaded successfully");
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return data;
    }

    public static void updateUser(String userid, String username, String password, String roleid, String activeS) {
        String uid = userid;
        String uname = username;
        String pwd = password;
        String rid = roleid;
        String status = activeS;
        Connection con = DBConnection.createConnection();
        try {
            String sql = "UPDATE user SET roleid='"+rid+"',username='"+uname+"',password='"+pwd+"',active='"+status+"' WHERE userid='"+uid+"'";
            Statement statement2 = con.createStatement();
            statement2.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static boolean isRegistered(String username) {
        boolean flag = true;
        Connection con = DBConnection.createConnection();
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
        } catch (NumberFormatException | SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return flag;
    }

    public static ArrayList<UserBean> getSearchUser(String keyword) {
        Connection con = DBConnection.createConnection();
        ArrayList<UserBean> result = new ArrayList<>();
        try {
            String sql = "select u.userid, u.username, r.rolename from user u, role r where username like '" + "%" + keyword + "%" +"' and u.roleid=r.roleid";
            Statement statemenet = con.createStatement();
            ResultSet resultSet = statemenet.executeQuery(sql);
            while(resultSet.next()){
                UserBean search = new UserBean(resultSet.getString("u.userid"), resultSet.getString("u.username"), resultSet.getString("r.rolename"));
                result.add(search);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result; 
    }

    

}
