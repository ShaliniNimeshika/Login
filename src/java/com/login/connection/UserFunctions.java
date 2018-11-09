/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.connection;

import com.login.bean.FunctionBean;
import com.login.bean.InterfaceBean;
import com.login.bean.PageBean;
import com.login.bean.PrivilageBean;
import com.login.bean.RoleAccessBean;
import com.login.bean.RoleBean;
import com.login.bean.UserBean;
import com.login.common.Common;
import com.login.dao.EmailDao;
import com.login.dao.InterfaceDao;
import com.login.dao.LoginDao;
import com.login.dao.RoleDao;
import com.login.dao.UserDao;
import com.login.util.PasswordEncryptDecrypt;
import com.login.util.PasswordGenerator;
import com.login.util.SessionVarList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shalini_w
 */
public class UserFunctions extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String newIP = session.getAttribute("currentIP").toString();
        String username = session.getAttribute("uname").toString();

        ServletContext context = getServletConfig().getServletContext();
        HashMap<String, String> usermap = (HashMap<String, String>) context.getAttribute(SessionVarList.USERMAP);
        HashMap<String, String> userdev = (HashMap<String, String>) context.getAttribute(SessionVarList.USERDEVICE);

        Common common = new Common();
        boolean logged = common.checkUserLogin(usermap, username);
        boolean sameDevice = common.checkUserDevice(userdev, username, newIP);

        if (logged == true && sameDevice == false) {
            request.getRequestDispatcher("index.jsp").include(request, response);
        } else {

            String action = request.getParameter("action");

            switch (action) {
                case "home":
                    loadHomePage(request, response);
                    break;
                case "add_user":
                    add_user(request, response); //add new user
                    break;
                case "update_user":
                    update_user(request, response); //update existing user
                    break;
                case "add_role":
                    add_role(request, response); //add new user role
                    break;
                case "update_role":
                    update_role(request, response); //add new user role
                    break;
                case "delete_role":
                    delete_role(request, response); //add new user role
                    break;
                case "new_page":
                    add_page(request, response); //add new page(interface)
                    break;
                case "update_function":
                    update_page(request, response); //load update page interface
                    break;
                case "update_interface":
                    update_interface(request, response); //change the assigned functions of the page
                    break;
                case "delete_page":
                    delete_interface(request, response); //delete the page permanently 
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void add_user(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password;
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String actStatus = request.getParameter("activeStatus");
        String status = "0";
        String duration = request.getParameter("duration");

        HttpSession session = request.getSession();
        String roleid = session.getAttribute("roleid").toString();

        ArrayList<UserBean> ub;
        ArrayList<RoleBean> rb;
        boolean flag = UserDao.isRegistered(username);
        if (flag == false) {

            //generating password
            PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                    .useDigits(true)
                    .useLower(true)
                    .useUpper(true)
                    .build();
            password = passwordGenerator.generate(8);

            PasswordEncryptDecrypt pwdEn = new PasswordEncryptDecrypt();
            String passWord = pwdEn.getEncryptedPassword(password);

            UserDao.addUser(username, passWord, email, role, actStatus, status, duration);

            EmailDao.sendPasswordEmail(username, password, email);
            int page = 1;
            int recordsPerPage = 3;
            if (request.getParameter("recordsPerPage") != null) {
                recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
            }

            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            int noOfRecords = UserDao.NoOfUsers();
            ub = UserDao.viewAllUsers((page - 1) * recordsPerPage, recordsPerPage);

            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            ArrayList<FunctionBean> fb;
            fb = InterfaceDao.loadFunction(roleid, "1");
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);

            request.setAttribute("index", "1");
            request.setAttribute("functions", fb);
            request.setAttribute("users", ub);

            request.getRequestDispatcher("WEB-INF/user_management.jsp").forward(request, response);
        } else {
            rb = RoleDao.loadRoleName();
            request.setAttribute("roles", rb);
            request.getRequestDispatcher("WEB-INF/add_user.jsp").forward(request, response);
        }
    }

    private void update_user(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String pwd = request.getParameter("password");
        String rid = request.getParameter("role");
        String userid = request.getParameter("userid");
        String status = request.getParameter("activeStatus");

        //encrypt password 
        PasswordEncryptDecrypt pwdEn = new PasswordEncryptDecrypt();
        String password = pwdEn.getEncryptedPassword(pwd);

        //update user data
        UserDao.updateUser(userid, username, password, rid, status);

        HttpSession session = request.getSession();

        String roleid = session.getAttribute("roleid").toString();
        int page = 1;
        int recordsPerPage = 3;
        if (request.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        }

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int noOfRecords = UserDao.NoOfUsers();
        ArrayList<UserBean> ub;
        ub = UserDao.viewAllUsers((page - 1) * recordsPerPage, recordsPerPage);

        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        ArrayList<FunctionBean> fb;
        fb = InterfaceDao.loadFunction(roleid, "1");
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        request.setAttribute("index", "1");
        request.setAttribute("functions", fb);
        request.setAttribute("users", ub);
        request.getRequestDispatcher("WEB-INF/user_management.jsp").forward(request, response);

    }

    private void add_role(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rolename = request.getParameter("role_name");
        String[] selection = request.getParameterValues("func");

        InterfaceDao.insertRole(rolename, selection);

        ArrayList<InterfaceBean> interfaces;
        ArrayList<InterfaceBean> ib;
        ArrayList<RoleBean> alldata;
        ArrayList<RoleBean> rb;
        rb = RoleDao.loadRoleName();
        ib = InterfaceDao.loadInterfaceFunctions();
        interfaces = InterfaceDao.loadAllInterfaces();
        alldata = InterfaceDao.loadUserFunctions();

        request.setAttribute("roles", rb);
        request.setAttribute("funcs", ib);
        request.setAttribute("inter", interfaces);
        request.setAttribute("alldata", alldata);
        request.setAttribute("msg", "New Role Added Successfully!");
        request.getRequestDispatcher("WEB-INF/add_role.jsp").include(request, response);
    }

    private void add_page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pagename = request.getParameter("pagename");
        String pageurl = request.getParameter("pageurl");
        String pagedesc = request.getParameter("pagedesc");
        String[] functions = request.getParameterValues("functions");

        InterfaceDao.insertPage(pagename, pageurl, pagedesc, functions);

        ArrayList<InterfaceBean> interfaces;
        ArrayList<InterfaceBean> ib;
        ArrayList<PageBean> al;
        ArrayList<RoleBean> rb;
        ArrayList<RoleBean> alldata;
        rb = RoleDao.loadRoleName();
        ib = InterfaceDao.loadInterfaceFunctions();
        interfaces = InterfaceDao.loadAllInterfaces();
        al = LoginDao.loadPages();
        alldata = InterfaceDao.loadUserFunctions();

        request.setAttribute("pages", al);
        request.setAttribute("roles", rb);
        request.setAttribute("funcs", ib);
        request.setAttribute("inter", interfaces);
        request.setAttribute("alldata", alldata);

        request.getRequestDispatcher("WEB-INF/add_role.jsp").forward(request, response);
    }

    private void update_page(HttpServletRequest request, HttpServletResponse response) {
        try {
            String iid = request.getParameter("pageid");

//            load selected interface details
            ArrayList<InterfaceBean> interface_bean;
            interface_bean = InterfaceDao.loadInterface(iid);
            request.setAttribute("interface_bean", interface_bean);

//          load all functions in database
            ArrayList<FunctionBean> functions;
            functions = InterfaceDao.loadAllFunctions();
            request.setAttribute("functions", functions);

//            load the functions of selected interface
            ArrayList<InterfaceBean> inter;
            inter = InterfaceDao.loadFunctionInterface();
            request.setAttribute("inter", inter);

            String[] value = new String[functions.size()];

            for (int i = 0; i < functions.size(); i++) {
                FunctionBean get = functions.get(i);
                boolean v = false;
                for (int j = 0; j < interface_bean.get(0).getFbean().size(); j++) {
                    FunctionBean get1 = interface_bean.get(0).getFbean().get(j);
                    if (get1.getFunction_name().equals(get.getFunction_name())) {
                        v = true;
                        break;
                    }
                }
                if (v == true) {
                    value[i] = "1";
                } else {
                    value[i] = "0";
                }
            }

            request.setAttribute("func_values", value);
            request.getRequestDispatcher("WEB-INF/update_page.jsp").forward(request, response);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(UserFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void update_interface(HttpServletRequest request, HttpServletResponse response) {
        try {
            String pageid = request.getParameter("intid");
            String pagename = request.getParameter("pagename");
            String pageurl = request.getParameter("pageurl");
            String pagedesc = request.getParameter("pagedesc");
            String[] functions = request.getParameterValues("functions");

            List<FunctionBean> oldfunctions = InterfaceDao.loadOldFunctions(pageid);
            String[] old = new String[oldfunctions.size()];

            for (int i = 0; i < oldfunctions.size(); i++) {
                old[i] = oldfunctions.get(i).getFunction_id();
            }

            List<String> newfuncs = new CopyOnWriteArrayList<>();
            List<String> oldfuncs = new CopyOnWriteArrayList<>();

            for (String old1 : old) {
                oldfuncs.add(old1);
            }

            for (String function : functions) {
                newfuncs.add(function);
            }

            for (String x : oldfuncs) {
                for (String y : newfuncs) {
                    if (x.equals(y)) {
                        oldfuncs.remove(x);
                        newfuncs.remove(y);
                    }
                }
            }

            System.out.println("\n rest data from db:");
            for (String old1 : oldfuncs) {
                InterfaceDao.deleteFIFunction(old1, pageid);
            }
            System.out.println("\nrest data from UI:");
            for (String function : newfuncs) {
                InterfaceDao.insertFIFunction(function, pageid);
            }

            //update interface info
            InterfaceDao.updateInterface(pageid, pagename, pageurl, pagedesc);

            //load new_page.jsp
            ArrayList<FunctionBean> funcs;
            funcs = InterfaceDao.loadAllFunctions();
            ArrayList<InterfaceBean> inter;
            inter = InterfaceDao.loadFunctionInterface();

            request.setAttribute("functions", funcs);
            request.setAttribute("inter", inter);
            request.getRequestDispatcher("WEB-INF/new_page.jsp").forward(request, response);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(UserFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void delete_interface(HttpServletRequest request, HttpServletResponse response) {
        try {
            String iid = request.getParameter("pageid");
            ArrayList<InterfaceBean> ibean;
            ibean = InterfaceDao.findInterfaceFunctionID(iid);

            ArrayList<PrivilageBean> pbean;
            pbean = InterfaceDao.findPrivilageID(ibean);

            //delete privilages assigned for the interface
            InterfaceDao.deletePrivilages(pbean);

            //delete functions assigned for the interface
            InterfaceDao.deleteInterfaceFunction(ibean);

            //delete interface
            InterfaceDao.deleteInterface(iid);

            ArrayList<PageBean> al;
            al = LoginDao.loadPages();
            ArrayList<FunctionBean> function;
            function = InterfaceDao.loadAllFunctions();
            ArrayList<InterfaceBean> inte;
            inte = InterfaceDao.loadFunctionInterface();
            request.setAttribute("pages", al);
            request.setAttribute("functions", function);
            request.setAttribute("inter", inte);

            request.getRequestDispatcher("WEB-INF/new_page.jsp").forward(request, response);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(UserFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void update_role(HttpServletRequest request, HttpServletResponse response) {
        try {
            String rid = request.getParameter("rid");
            String[] newifid = request.getParameterValues("functions"); //catch newly added permission's ifids

            //filter exist ifids for the role
            ArrayList<RoleAccessBean> rab;
            rab = InterfaceDao.loadRoleIFID(rid);

            //create two new CopyOnWriteArrayLists to store those ids seperately and add them using loops
            List<String> oldid = new CopyOnWriteArrayList<>();
            List<String> newid = new CopyOnWriteArrayList<>();

            for (int i = 0; i < rab.size(); i++) {
                RoleAccessBean get = rab.get(i);
                oldid.add(get.getIf_id());
            }

            for (int i = 0; i < newifid.length; i++) {
                String string = newifid[i];
                newid.add(string);
            }

            //check the two arraylists and compare to remove the same valued elements
            for (String x : oldid) {
                for (String y : newid) {
                    if (x.equals(y)) {
                        oldid.remove(x);
                        newid.remove(y);
                    }
                }
            }

            //getting the rest of oldids after removing and revoke those permissions from role
            for (String old1 : oldid) {
                InterfaceDao.revokePermissions(rid, old1);
            }

            //getting the rest of newids after removing and grant those permissions to role
            for (String new1 : newid) {
                InterfaceDao.grantPermission(rid, new1);
            }

            //load neccessary values and load add_role.jsp page
            ArrayList<RoleBean> rb;
            ArrayList<InterfaceBean> ib;
            ArrayList<InterfaceBean> interfaces;
            ArrayList<RoleBean> alldata;

            rb = RoleDao.loadRoleName();
            ib = InterfaceDao.loadInterfaceFunctions();
            interfaces = InterfaceDao.loadAllInterfaces();
            alldata = InterfaceDao.loadUserFunctions();

            request.setAttribute("roles", rb);
            request.setAttribute("funcs", ib);
            request.setAttribute("inter", interfaces);
            request.setAttribute("alldata", alldata);
            request.getRequestDispatcher("WEB-INF/add_role.jsp").forward(request, response);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(UserFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void delete_role(HttpServletRequest request, HttpServletResponse response) {
        String rid = request.getParameter("roleid");
        String msg;
        int count = RoleDao.getPrivilageCount(rid);
        if (count == 0) {
            RoleDao.deleteRole(rid);
            msg = "Role Deleted Successfully!";
        } else {
            msg = "There are users for this role. So role cannot be deleted.";
        }
        ArrayList<RoleBean> rb;
        ArrayList<InterfaceBean> ib;
        ArrayList<InterfaceBean> interfaces;
        ArrayList<RoleBean> alldata;

        rb = RoleDao.loadRoleName();
        ib = InterfaceDao.loadInterfaceFunctions();
        interfaces = InterfaceDao.loadAllInterfaces();
        alldata = InterfaceDao.loadUserFunctions();

        request.setAttribute("roles", rb);
        request.setAttribute("funcs", ib);
        request.setAttribute("inter", interfaces);
        request.setAttribute("alldata", alldata);
        request.setAttribute("msg", msg);
        try {
            request.getRequestDispatcher("WEB-INF/add_role.jsp").include(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(UserFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadHomePage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        request.setAttribute("pages", session.getAttribute("pages"));
        request.setAttribute("uname", session.getAttribute("uname"));
        request.setAttribute("roleid", session.getAttribute("roleid"));
        
        request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
    }

}
