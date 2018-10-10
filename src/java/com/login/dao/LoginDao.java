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

    static String role = null;
    static String roleid = null;

    public static boolean authenticateUser(LoginBean loginbean) {
        Connection con = DBConnection.createConnection();
        String unameDB = null;
        String passwordDB = null;
        String roleDB = null;
        try {

            String username = loginbean.getUsername();
            String password = loginbean.getPassword();

            Statement statement = con.createStatement();
            String sql = "SELECT u.username , u.password, u.active , r.rolename , r.roleid from user u , role r where u.roleid=r.roleid";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                unameDB = rs.getString("u.username");
                passwordDB = rs.getString("u.password");
                String active = rs.getString("u.active");
                roleDB = rs.getString("r.rolename");
                roleid = rs.getString("r.roleid");

                if (username.equals(unameDB) && password.equals(passwordDB)) {

                    if (active.equals("1")) {
                        loginbean.setRoleid(roleid);
                        session.setAttribute("roleid", roleid);
                        return true;
                    }

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public static ArrayList<PageBean> loadPages() {
        ArrayList<PageBean> data = new ArrayList<>();
        Connection con = DBConnection.createConnection();
        try {
//            con = DBConnection.createConnection();
            Statement statement = con.createStatement();
            String sql = "SELECT DISTINCT i.interfaceid, i.url, i.name FROM interface i, func_interface fi, privilage p WHERE p.if_id=fi.if_id AND fi.interfaceid=i.interfaceid ANd p.roleid ='" + roleid + "'";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                PageBean p = new PageBean(rs.getString("i.url"), rs.getString("i.name"), rs.getString("i.interfaceid"));
                data.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return data;
    }

    public static String getResetStatus(LoginBean loginbean) {
        Connection con = DBConnection.createConnection();
        String status = null;

        try {
            String username = loginbean.getUsername();
            String sql = "SELECT status from user where username = '"+username+"'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {                
                status = rs.getString("status");
            }
        } catch (SQLException ex) {
             Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    public static int getResetDuration(LoginBean loginbean) {
        Connection con = DBConnection.createConnection();
        int duration = 0;

        try {
            String username = loginbean.getUsername();
            String sql = "SELECT reset_duration from user where username = '"+username+"'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {                
                String days = rs.getString("reset_duration");
                duration = Integer.parseInt(days);
            }
        } catch (SQLException ex) {
             Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return duration;
    }

    public static String getResetTime(LoginBean loginbean) {
        Connection con = DBConnection.createConnection();
        String date = null;

        try {
            String username = loginbean.getUsername();
            String sql = "SELECT reset_time from user where username = '"+username+"'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {                
                date = rs.getString("reset_time");
            }
        } catch (SQLException ex) {
             Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return date;
    }
}
