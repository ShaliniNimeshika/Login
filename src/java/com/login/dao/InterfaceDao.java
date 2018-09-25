/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.dao;

import com.login.bean.FunctionBean;
import com.login.bean.PageBean;
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
    static ResultSet rs = null;
    static String interfaceid = null;
    static String roleid = null;

    public static ArrayList<FunctionBean> loadFunction(String rid,String inid) {
        ArrayList<FunctionBean> data = new ArrayList<FunctionBean>();
        interfaceid = inid;
        roleid = rid;
        
        try {
            con = DBConnection.createConnection();
            statement = con.createStatement();
            //String sql = "SELECT f.functionid, f.name, fi.url from function f, func_interface fi, privilage p where p.if_id=fi.if_id and p.roleid='"+roleid+"' and fi.interfaceid='"+interfaceid+"' and f.functionid=fi.functionid";
            String sql = "SELECT f.functionid, f.name, fi.url from function f, func_interface fi, privilage p where p.if_id=fi.if_id and p.roleid='"+roleid+"' and fi.interfaceid='"+interfaceid+"' and f.functionid=fi.functionid";
            rs = statement.executeQuery(sql);
            
            while (rs.next()) { 
                FunctionBean fb = new FunctionBean(rs.getString("f.functionid"),rs.getString("f.name"),rs.getString("fi.url"));
                data.add(fb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    
    
}
