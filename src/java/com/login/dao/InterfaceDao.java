/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.dao;

import com.login.bean.FunctionBean;
import com.login.bean.InterfaceBean;
import com.login.bean.PrivilageBean;
import com.login.bean.RoleBean;
import com.login.connection.LoginServlet;
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

    static String interfaceid = null;
    static String roleid = null;

    public static ArrayList<FunctionBean> loadFunction(String rid, String inid) {
        ArrayList<FunctionBean> data = new ArrayList<>();
        interfaceid = inid;
        roleid = (String) LoginServlet.session.getAttribute("roleid");
        Connection con = DBConnection.createConnection();
        try {

            Statement statement = con.createStatement();
            String sql = "SELECT f.functionid, f.name from function f, func_interface fi, privilage p where p.if_id=fi.if_id and p.roleid='" + roleid + "' and fi.interfaceid='" + interfaceid + "' and f.functionid=fi.functionid";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                FunctionBean fb = new FunctionBean(rs.getString("f.functionid"), rs.getString("f.name"));
                data.add(fb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return data;
    }

    public static ArrayList<InterfaceBean> loadInterfaceFunctions() {
        ArrayList<InterfaceBean> data = new ArrayList<>();
        Connection con = DBConnection.createConnection();
        try {
            Statement statement = con.createStatement();
            String sql = "SELECT fi.if_id,i.interfaceid,i.name,f.functionid,f.name FROM interface i, function f, func_interface fi WHERE i.interfaceid=fi.interfaceid AND f.functionid=fi.functionid";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                InterfaceBean ib = new InterfaceBean(rs.getString("fi.if_id"), rs.getString("i.name"), rs.getString("f.name"));
                data.add(ib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return data;
    }

    public static void insertRole(String rolename, String[] selection) {
        String[] selected = selection;
        String rname = rolename;
        String rid = null;
        Connection con = DBConnection.createConnection();
        try {
            String role_sql = "INSERT INTO role(rolename) VALUES ('" + rname + "')";
            Statement statement = con.createStatement();
            statement.executeUpdate(role_sql);

            System.out.println("new user role added successfully");

            String sql = "SELECT roleid from role where rolename='" + rname + "'";
            Statement statement2 = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                rid = rs.getString("roleid");
            }
            System.out.println("roleid in interfacedao :" + rid);

            for (int i = 0; i < selected.length; i++) {
                String selection_sql = "INSERT INTO privilage(roleid,if_id) VALUES ('" + rid + "','" + selected[i] + "')";
                Statement statement3 = con.createStatement();
                statement3.executeUpdate(selection_sql);
            }
            System.out.println("privilage added successfully");
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static ArrayList<InterfaceBean> loadAllInterfaces() {
        ArrayList<InterfaceBean> interfaces = new ArrayList<>();
        Connection con = DBConnection.createConnection();
        try {
            String sql = "SELECT * FROM interface";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                InterfaceBean in = new InterfaceBean(rs.getString("interfaceid"), rs.getString("name"), rs.getString("url"), rs.getString("description"));
                interfaces.add(in);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return interfaces;
    }

    public static ArrayList<FunctionBean> loadAllFunctions() {
        Connection con = DBConnection.createConnection();
        ArrayList<FunctionBean> functions = new ArrayList<>();
        try {
            String sql = "SELECT * FROM function";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                FunctionBean func = new FunctionBean(rs.getString("functionid"), rs.getString("name"));
                functions.add(func);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return functions;
    }

    public static void insertPage(String pagename, String pageurl, String pagedesc, String[] functions) {
        String pname = pagename;
        String purl = pageurl;
        String pdesc = pagedesc;
        String funcs[] = functions;
        String iid = null;
        Connection con = DBConnection.createConnection();
        try {
            String page_sql = "INSERT INTO interface(name,url,description) VALUES ('" + pname + "','" + purl + "','" + pdesc + "')";
            Statement statement = con.createStatement();
            statement.executeUpdate(page_sql);

            System.out.println("new page added successfully");

            String sql = "SELECT interfaceid from interface where name='" + pname + "'";
            Statement statement2 = con.createStatement();
            ResultSet rs = statement2.executeQuery(sql);
            while (rs.next()) {
                iid = rs.getString("interfaceid");
            }
            System.out.println("interfaceid in interfacedao :" + iid);

            for (int i = 0; i < funcs.length; i++) {
                String selection_sql = "INSERT INTO func_interface(interfaceid,functionid) VALUES ('" + iid + "','" + funcs[i] + "')";
                Statement statement3 = con.createStatement();
                statement3.executeUpdate(selection_sql);
            }
            System.out.println("functions added successfully");

            addAdminPrivilage(iid);

            System.out.println("privilages added to admin");
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void addAdminPrivilage(String iid) {
        String inid = iid;
        Connection con = DBConnection.createConnection();
        try {
            String sql = "SELECT if_id from func_interface WHERE interfaceid='" + inid + "'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String sql2 = "INSERT INTO privilage(roleid,if_id) values('" + roleid + "','" + rs.getString("if_id") + "')";
                Statement statement2 = con.createStatement();
                statement2.executeUpdate(sql2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static ArrayList<InterfaceBean> loadFunctionInterface() {
        ArrayList<InterfaceBean> ibean = new ArrayList<>();
        Connection con = DBConnection.createConnection();
        try {
            String i_name;
            String i_id;
            String i_url;
            String i_desc;

            String sql = "SELECT DISTINCT i.interfaceid,i.name,i.url,i.description FROM interface i";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                i_id = rs.getString("i.interfaceid");
                i_name = rs.getString("i.name");
                i_url = rs.getString("i.url");
                i_desc = rs.getString("i.description");
                ArrayList<FunctionBean> fbean = new ArrayList<>();

                String sql2 = "SELECT DISTINCT f.functionid, f.name FROM function f, func_interface fi WHERE fi.interfaceid='" + i_id + "' AND f.functionid=fi.functionid";
                Statement statement2 = con.createStatement();
                ResultSet rs2 = statement2.executeQuery(sql2);

                while (rs2.next()) {
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
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ibean;
    }

    public static ArrayList<InterfaceBean> loadInterface(String iid) {
        String ifaceid = iid;
        ArrayList<InterfaceBean> ibean = new ArrayList<>();
        Connection con = DBConnection.createConnection();
        try {

            String iname;
            String idesc;
            String iurl;
            String sql = "SELECT * FROM interface WHERE interfaceid='" + ifaceid + "'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                ArrayList<FunctionBean> fbean = new ArrayList<>();
                iname = rs.getString("name");
                iurl = rs.getString("url");
                idesc = rs.getString("description");

                String sql2 = "select DISTINCT f.functionid, f.name from function f, func_interface fi where fi.functionid=f.functionid and fi.interfaceid='" + ifaceid + "'";
                Statement statement2 = con.createStatement();
                ResultSet rs2 = statement2.executeQuery(sql2);
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
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ibean;
    }

    public static ArrayList<InterfaceBean> findInterfaceFunctionID(String iid) {
        ArrayList<InterfaceBean> ibean = new ArrayList<>();
        Connection con = DBConnection.createConnection();
        try {
            String sql = "SELECT if_id from func_interface WHERE interfaceid='" + iid + "'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                InterfaceBean ib = new InterfaceBean(rs.getString("if_id"));
                ibean.add(ib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ibean;
    }

    public static ArrayList<PrivilageBean> findPrivilageID(ArrayList<InterfaceBean> ibean) {
        ArrayList<PrivilageBean> pbean = new ArrayList<>();
        ArrayList<InterfaceBean> ib = ibean;
        Connection con = DBConnection.createConnection();
        for (int i = 0; i < ib.size(); i++) {
            try {
                InterfaceBean get = ib.get(i);

                String sql = "SELECT pid from privilage WHERE if_id='" + get.getIf_id() + "'";
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery(sql);

                while (rs.next()) {
                    PrivilageBean pb = new PrivilageBean(rs.getString("pid"));
                    pbean.add(pb);
                }
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return pbean;
    }

    public static void deletePrivilages(ArrayList<PrivilageBean> pbean) {
        Connection con = DBConnection.createConnection();
        try {
            ArrayList<PrivilageBean> pb = pbean;

            for (int i = 0; i < pb.size(); i++) {
                PrivilageBean get = pb.get(i);

                String sql = "DELETE FROM privilage WHERE pid='" + get.getPid() + "'";
                Statement statement2 = con.createStatement();
                statement2.executeUpdate(sql);

            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void deleteInterfaceFunction(ArrayList<InterfaceBean> ibean) {
        Connection con = DBConnection.createConnection();
        try {
            ArrayList<InterfaceBean> ib = ibean;

            for (int i = 0; i < ib.size(); i++) {
                InterfaceBean get = ib.get(i);

                String sql = "DELETE FROM func_interface WHERE if_id='" + get.getIf_id() + "'";
                Statement statement2 = con.createStatement();
                statement2.executeUpdate(sql);

            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void deleteInterface(String iid) {
        Connection con = DBConnection.createConnection();
        try {
            String inid = iid;

            String sql = "DELETE FROM interface WHERE interfaceid='" + inid + "'";
            Statement statement2 = con.createStatement();
            statement2.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void updateInterface(String pageid, String pagename, String pageurl, String pagedesc) {
        Connection con = DBConnection.createConnection();
        try {
            String iid = pageid;
            String iname = pagename;
            String iurl = pageurl;
            String idesc = pagedesc;

            String sql = "UPDATE interface SET name='" + iname + "',description='" + idesc + "' WHERE interfaceid='" + iid + "'";
            Statement statement2 = con.createStatement();
            statement2.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void updatePrivilages(String pageid, String[] functions) {
        String iid = pageid;
        String[] new_fid = functions;
        Connection con = DBConnection.createConnection();
        //get if_id of all old func_interface
    }

    public static ArrayList<FunctionBean> loadOldFunctions(String pageid) {
        ArrayList<FunctionBean> fbean = new ArrayList<>();
        Connection con = DBConnection.createConnection();
        try {

            String sql2 = "select DISTINCT f.functionid, f.name from function f, func_interface fi where fi.functionid=f.functionid and fi.interfaceid='" + pageid + "'";
            Statement statement2 = con.createStatement();
            ResultSet rs2 = statement2.executeQuery(sql2);
            while (rs2.next()) {
                FunctionBean fb = new FunctionBean(rs2.getString("f.functionid"), rs2.getString("f.name"));
                fbean.add(fb);
            }

        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return fbean;
    }

    public static void deleteFIFunction(String func, String pageid) {
        Connection con = DBConnection.createConnection();
        try {
            String iid = pageid;
            String fid = func;
            String ifid = "";
            //search for if_id
            String sql = "SELECT DISTINCT if_id from func_interface WHERE interfaceid='" + iid + "' and functionid='" + fid + "'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                ifid = rs.getString("if_id");
            }

            //search for privilages
            String sql2 = "SELECT DISTINCT pid from privilage WHERE if_id='" + ifid + "'";
            Statement statement2 = con.createStatement();
            ResultSet rs2 = statement2.executeQuery(sql2);

            while (rs2.next()) {
                String pid = rs2.getString("pid");

                //delete privilage 
                String sql3 = "DELETE FROM privilage WHERE pid='" + pid + "'";
                Statement statement3 = con.createStatement();
                statement3.executeUpdate(sql3);
                System.out.println("delete pid:" + pid);
            }
            String sql4 = "DELETE FROM func_interface WHERE if_id='" + ifid + "'";
            Statement statement4 = con.createStatement();
            statement4.executeUpdate(sql4);
            System.out.println("delete function if_id:" + ifid);
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void insertFIFunction(String newF, String pageid) {
        String iid = pageid;
        String fid = newF;
        Connection con = DBConnection.createConnection();

        try {
            String sql = "INSERT INTO func_interface(interfaceid,functionid) VALUES ('" + iid + "','" + fid + "')";
            Statement statement = con.createStatement();
            statement.executeUpdate(sql);
            System.out.println("insert new function for interface:" + iid);
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static ArrayList<RoleBean> loadUserFunctions() {
        ArrayList<RoleBean> rbean = new ArrayList<>();
        Connection con = DBConnection.createConnection();

        try {
            String sql1 = "SELECT * FROM role";
            Statement statement1 = con.createStatement();
            ResultSet rs1 = statement1.executeQuery(sql1);

            while (rs1.next()) {
                String rid = rs1.getString("roleid");
                String rname = rs1.getString("rolename");

                String sql2 = "select distinct i.interfaceid, i.name from interface i, func_interface fi, privilage p where p.roleid='"+rid+"' and p.if_id=fi.if_id";
                Statement statement2 = con.createStatement();
                ResultSet rs2 = statement2.executeQuery(sql2);
                ArrayList<InterfaceBean> ibean = new ArrayList<>();
                while (rs2.next()) {
                    String iid = rs2.getString("i.interfaceid");
                    String iname = rs2.getString("i.name");

                    String sql3 = "select distinct f.functionid, f.name from function f, interface i, func_interface fi, privilage p where p.roleid='"+rid+"' and i.interfaceid = '"+iid+"' and fi.functionid=f.functionid and fi.if_id=p.if_id";
                    Statement statement3 = con.createStatement();
                    ResultSet rs3 = statement3.executeQuery(sql3);
                    ArrayList<FunctionBean> fbean = new ArrayList<>();
                    
                    while (rs3.next()) {                        
                        String fid = rs3.getString("f.functionid");
                        String fname = rs3.getString("f.name");
                        
                        FunctionBean fb = new FunctionBean(fid, fname);
                        fbean.add(fb);
                    }
                    InterfaceBean ib = new InterfaceBean(iid, iname, fbean);
                    ibean.add(ib);
                }

                RoleBean rb = new RoleBean(rname, rid, ibean);
                rbean.add(rb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        String sql5 = "";
        Statement statement5;
        ResultSet rs5;

        return rbean;
    }

}
