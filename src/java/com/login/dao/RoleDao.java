/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.dao;

import com.login.bean.RoleBean;
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
public class RoleDao {

    static String role = null;
    static String roleid = null;
    
    public static ArrayList<RoleBean> loadRoleName() {
        ArrayList<RoleBean> roles = new ArrayList<>();
        Connection con = DBConnection.createConnection();
        try {

            Statement statement = con.createStatement();
            String sql = "SELECT * from role";
            ResultSet rs = statement.executeQuery(sql);
            
            while (rs.next()) { 
                RoleBean rb = new RoleBean(rs.getString("roleid"),rs.getString("rolename"));
                roles.add(rb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(RoleDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return roles;
    }
    
}
