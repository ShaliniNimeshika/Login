/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.dao;

import com.login.bean.FunctionBean;
import com.login.bean.InterfaceBean;
import com.login.bean.PageBean;
import com.login.bean.PrivilageBean;
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
        String rid = null;
        try {
            String role_sql = "INSERT INTO role(rolename) VALUES ('" + rname + "')";
            statement = con.createStatement();
            statement.executeUpdate(role_sql);

            System.out.println("new user role added successfully");

            String sql = "SELECT roleid from role where rolename='" + rname + "'";
            statement2 = con.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                rid = rs.getString("roleid");
            }
            System.out.println("roleid in interfacedao :" + rid);

            for (int i = 0; i < selected.length; i++) {
                String selection_sql = "INSERT INTO privilage(roleid,if_id) VALUES ('" + rid + "','" + selected[i] + "')";
                statement3 = con.createStatement();
                statement3.executeUpdate(selection_sql);
            }
            System.out.println("privilage added successfully");
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<InterfaceBean> loadAllInterfaces() {
        ArrayList<InterfaceBean> interfaces = new ArrayList<>();
        try {
            String sql = "SELECT * FROM interface";
            statement = con.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                InterfaceBean in = new InterfaceBean(rs.getString("interfaceid"), rs.getString("name"), rs.getString("url"), rs.getString("description"));
                interfaces.add(in);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return interfaces;
    }

    public static ArrayList<FunctionBean> loadAllFunctions() {

        ArrayList<FunctionBean> functions = new ArrayList<>();
        try {
            String sql = "SELECT * FROM function";
            statement = con.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                FunctionBean func = new FunctionBean(rs.getString("functionid"), rs.getString("name"));
                functions.add(func);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return functions;
    }

    public static void insertPage(String pagename, String pageurl, String pagedesc, String[] functions) {
        String pname = pagename;
        String purl = pageurl;
        String pdesc = pagedesc;
        String funcs[] = functions;
        String iid = null;

        try {
            String page_sql = "INSERT INTO interface(name,url,description) VALUES ('" + pname + "','" + purl + "','" + pdesc + "')";
            statement = con.createStatement();
            statement.executeUpdate(page_sql);

            System.out.println("new page added successfully");

            String sql = "SELECT interfaceid from interface where name='" + pname + "'";
            statement2 = con.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                iid = rs.getString("interfaceid");
            }
            System.out.println("interfaceid in interfacedao :" + iid);

            for (int i = 0; i < funcs.length; i++) {
                String selection_sql = "INSERT INTO func_interface(interfaceid,functionid) VALUES ('" + iid + "','" + funcs[i] + "')";
                statement3 = con.createStatement();
                statement3.executeUpdate(selection_sql);
            }
            System.out.println("functions added successfully");

            addAdminPrivilage(iid);

            System.out.println("privilages added to admin");
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void addAdminPrivilage(String iid) {
        String inid = iid;

        try {
            String sql = "SELECT if_id from func_interface WHERE interfaceid='" + inid + "'";
            statement = con.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                String sql2 = "INSERT INTO privilage(roleid,if_id) values('" + roleid + "','" + rs.getString("if_id") + "')";
                statement2 = con.createStatement();
                statement2.executeUpdate(sql2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<InterfaceBean> loadFunctionInterface() {
        ArrayList<InterfaceBean> ibean = new ArrayList<>();
        try {
            String i_name;
            String i_id;
            String i_url;
            String i_desc;

            String sql = "SELECT DISTINCT i.interfaceid,i.name,i.url,i.description FROM interface i";
            statement = con.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                i_id = rs.getString("i.interfaceid");
                i_name = rs.getString("i.name");
                i_url = rs.getString("i.url");
                i_desc = rs.getString("i.description");
                ArrayList<FunctionBean> fbean = new ArrayList<>();

                String sql2 = "SELECT DISTINCT f.functionid, f.name FROM function f, func_interface fi WHERE fi.interfaceid='" + i_id + "' AND f.functionid=fi.functionid";
                statement2 = con.createStatement();
                rs2 = statement2.executeQuery(sql2);

                while (rs2.next()) {
                    //System.out.println(i_name + "\n");
                    //System.out.println(rs2.getString("f.functionid") + " and " + rs2.getString("f.name"));
                    FunctionBean fb = new FunctionBean(rs2.getString("f.functionid"), rs2.getString("f.name"));
                    fbean.add(fb);
                }

                InterfaceBean ib = new InterfaceBean(i_id, i_name, i_url, i_desc, fbean);
                ibean.add(ib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ibean;
    }

    public static ArrayList<InterfaceBean> loadInterface(String iid) {
        String ifaceid = iid;
        ArrayList<InterfaceBean> ibean = new ArrayList<>();

        try {

            String iname;
            String idesc;
            String iurl;
            String sql = "SELECT * FROM interface WHERE interfaceid='" + ifaceid + "'";
            statement = con.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                ArrayList<FunctionBean> fbean = new ArrayList<>();
                iname = rs.getString("name");
                iurl = rs.getString("url");
                idesc = rs.getString("description");

                String sql2 = "select DISTINCT f.functionid, f.name from function f, func_interface fi where fi.functionid=f.functionid and fi.interfaceid='" + ifaceid + "'";
                statement2 = con.createStatement();
                rs2 = statement2.executeQuery(sql2);
                while (rs2.next()) {
                    FunctionBean fb = new FunctionBean(rs2.getString("f.functionid"), rs2.getString("f.name"));
                    fbean.add(fb);
                }

                InterfaceBean ib = new InterfaceBean(iid, iname, iurl, idesc, fbean);
                ibean.add(ib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ibean;
    }

    public static ArrayList<InterfaceBean> findInterfaceFunctionID(String iid) {
        ArrayList<InterfaceBean> ibean = new ArrayList<>();

        try {
            String sql = "SELECT if_id from func_interface WHERE interfaceid='" + iid + "'";
            statement = con.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                InterfaceBean ib = new InterfaceBean(rs.getString("if_id"));
                ibean.add(ib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ibean;
    }

    public static ArrayList<PrivilageBean> findPrivilageID(ArrayList<InterfaceBean> ibean) {
        ArrayList<PrivilageBean> pbean = new ArrayList<>();
        ArrayList<InterfaceBean> ib = ibean;

        for (int i = 0; i < ib.size(); i++) {
            try {
                InterfaceBean get = ib.get(i);

                String sql = "SELECT pid from privilage WHERE if_id='" + get.getIf_id() + "'";
                statement = con.createStatement();
                rs = statement.executeQuery(sql);

                while (rs.next()) {
                    PrivilageBean pb = new PrivilageBean(rs.getString("pid"));
                    pbean.add(pb);
                }
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return pbean;
    }

    public static void deletePrivilages(ArrayList<PrivilageBean> pbean) {
        try {
            ArrayList<PrivilageBean> pb = pbean;

            for (int i = 0; i < pb.size(); i++) {
                PrivilageBean get = pb.get(i);

                String sql = "DELETE FROM privilage WHERE pid='" + get.getPid() + "'";
                statement2 = con.createStatement();
                statement2.executeUpdate(sql);

            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteInterfaceFunction(ArrayList<InterfaceBean> ibean) {
        try {
            ArrayList<InterfaceBean> ib = ibean;

            for (int i = 0; i < ib.size(); i++) {
                InterfaceBean get = ib.get(i);

                String sql = "DELETE FROM func_interface WHERE if_id='" + get.getIf_id() + "'";
                statement2 = con.createStatement();
                statement2.executeUpdate(sql);

            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteInterface(String iid) {
        try {
            String inid = iid;

            String sql = "DELETE FROM interface WHERE interfaceid='" + inid + "'";
            statement2 = con.createStatement();
            statement2.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
