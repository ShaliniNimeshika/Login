/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.dao;

import com.login.util.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shalini_w
 */
public class PasswordDao {

    public static void resetPassword(String username, String curpwd, String newpwd) {
        String uname = username;
        String status = "1";
        String new_pwd = newpwd;

        LocalDate today = LocalDate.now();
        String reset_date = today.toString();
        Connection con = DBConnection.createConnection();

        try {
            String sql = "UPDATE user SET password='" + new_pwd + "', status='" + status + "', reset_time='" + reset_date + "' WHERE username='" + uname + "'";
            Statement statement = con.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PasswordDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PasswordDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
