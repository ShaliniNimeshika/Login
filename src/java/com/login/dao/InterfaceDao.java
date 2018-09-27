/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.dao;

import com.login.bean.FunctionBean;
import com.login.bean.InterfaceBean;
import com.login.bean.PageBean;
import com.login.connection.LoginServlet;
import static com.login.dao.LoginDao.con;
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
public class InterfaceDao {

    static Connection con = null;
    static Statement statement = null;
    static Statement statement2 = null;
    static Statement statement3 = null;
    static ResultSet rs = null;
    static ResultSet rs2 = null;
    static String interfaceid = null;
    static String roleid = null;
    
    public static ArrayList<FunctionBean> loadFunction(String rid, String inid) {
        ArrayList<FunctionBean> data = new ArrayList<>();
        interfaceid = inid;
        roleid = (String) LoginServlet.session.getAttribute("roleid");
        
        try {
            con = DBConnection.createConnection();
            statement = con.createStatement();
            String sql = "SELECT f.functionid, f.name from function f, func_interface fi, privilage p where p.if_id=fi.if_id and p.roleid='" + roleid + "' and fi.interfaceid='" + interfaceid + "' and f.functionid=fi.functionid";
            rs = statement.executeQuery(sql);
            
            while (rs.next()) {                
                FunctionBean fb = new FunctionBean(rs.getString("f.functionid"), rs.getString("f.name"));
                data.add(fb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    public static ArrayList<InterfaceBean> loadInterfaceFunctions() {
        ArrayList<InterfaceBean> data = new ArrayList<>();
        
        try {
            con = DBConnection.createConnection();
            statement = con.createStatement();
            String sql = "SELECT fi.if_id,i.interfaceid,i.name,f.functionid,f.name FROM interface i, function f, func_interface fi WHERE i.interfaceid=fi.interfaceid AND f.functionid=fi.functionid";
            rs = statement.executeQuery(sql);
            
            while (rs.next()) {                
                InterfaceBean ib = new InterfaceBean(rs.getString("fi.if_id"), rs.getString("i.name"), rs.getString("f.name"));
                data.add(ib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    public static void insertRole(String rolename, String[] selection) {
        String[] selected = selection;
        String rname = rolename;
        String roleid = null;
        try {
            String role_sql = "INSERT INTO role(rolename) VALUES ('" + rname + "')";
            statement = con.createStatement();
            statement.executeUpdate(role_sql);
            
            System.out.println("new user role added successfully");
            
            String sql = "SELECT roleid from role where rolename='" + rname + "'";
            statement2 = con.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {                
                roleid = rs.getString("roleid");
            }
            System.out.println("roleid in interfacedao :" + roleid);
            
            for (int i = 0; i < selected.length; i++) {
                String selection_sql = "INSERT INTO privilage(roleid,if_id) VALUES ('" + roleid + "','" + selected[i] + "')";
                statement3 = con.createStatement();
                statement3.executeUpdate(selection_sql);                
            }
            System.out.println("privilage added successfully");
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
