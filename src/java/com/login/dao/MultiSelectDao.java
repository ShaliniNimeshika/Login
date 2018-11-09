/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.dao;

import com.login.bean.InterfaceBean;
import com.login.util.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shalini_w
 */
public class MultiSelectDao {

    public static List<InterfaceBean> loadFunctions(String name) {
        List<InterfaceBean> ibean = new ArrayList<>();
        String interface_name = name;
        Connection con = DBConnection.createConnection();
        try {
            String sql = "SELECT distinct fi.if_id, f.name from func_interface fi, interface i, function f where fi.interfaceid = i.interfaceid and fi.functionid=f.functionid and i.name = '" + interface_name + "'";

            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while (result.next()) {                
                InterfaceBean ib = new InterfaceBean(result.getString("fi.if_id"),result.getString("f.name"));
                ibean.add(ib);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MultiSelectDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ibean;
    }
}
