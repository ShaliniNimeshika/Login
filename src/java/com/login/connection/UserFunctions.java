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
import com.login.bean.RoleBean;
import com.login.bean.UserBean;
import com.login.connection.LoginServlet;
import static com.login.connection.LoginServlet.al;
import com.login.dao.InterfaceDao;
import com.login.dao.LoginDao;
import com.login.dao.RoleDao;
import com.login.dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shalini_w
 */
public class UserFunctions extends HttpServlet {

    static String roleid = LoginServlet.session.getAttribute("roleid").toString();
    static String interfaceid;
    ArrayList<FunctionBean> fb;
    ArrayList<UserBean> ub;
    ArrayList<RoleBean> rb;

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserFunction</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserFunction at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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

        interfaceid = request.getParameter("index");
        String action = request.getParameter("action");
        System.out.println("button :" + action);

        if (action.equals("add_user")) {

            add_user(request, response);

        } else if (action.equals("update_user")) {

            update_user(request, response);

        } else if (action.equals("add_role")) {

            add_role(request, response);

        } else if (action.equals("new_page")) {

            add_page(request, response);

        } else if (action.equals("update_function")) {

            update_page(request, response);

        } else if (action.equals("update_interface")) {

            update_interface(request, response);

        } else if (action.equals("delete_page")) {

            delete_interface(request, response);

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
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        boolean flag = UserDao.isRegistered(username);
//        System.out.println("isRegistered :" + flag);
        if (flag == false) {
            UserDao.addUser(username, password, role);
            System.out.println("data added successfully");

            ub = UserDao.loadAllUsers();
            LoginServlet.session.setAttribute("users", ub);
            response.sendRedirect("user_management.jsp");
        } else {
            rb = RoleDao.loadRoleName();
            LoginServlet.session.setAttribute("roles", rb);
            response.sendRedirect("add_user.jsp");
        }
//        
//        UserDao.addUser(username, password, role);
//        System.out.println("data added successfully");
//
//        ub = UserDao.loadAllUsers();
//        LoginServlet.session.setAttribute("users", ub);
//        response.sendRedirect("user_management.jsp");

    }

    private void update_user(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String roleid = request.getParameter("role");
        String userid = request.getParameter("userid");

        UserDao.updateUser(userid, username, password, roleid);
        System.out.println("user updated successfully");

        ub = UserDao.loadAllUsers();
        LoginServlet.session.setAttribute("users", ub);
        response.sendRedirect("user_management.jsp");
    }

    private void add_role(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rolename = request.getParameter("role_name");
        String[] selection = request.getParameterValues("func");

        InterfaceDao.insertRole(rolename, selection);

        ArrayList<InterfaceBean> interfaces;
        ArrayList<InterfaceBean> ib;

        rb = RoleDao.loadRoleName();
        ib = InterfaceDao.loadInterfaceFunctions();
        interfaces = InterfaceDao.loadAllInterfaces();

        LoginServlet.session.setAttribute("roles", rb);
        LoginServlet.session.setAttribute("funcs", ib);
        LoginServlet.session.setAttribute("inter", interfaces);

        response.sendRedirect("add_role.jsp");
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
        
        rb = RoleDao.loadRoleName();
        ib = InterfaceDao.loadInterfaceFunctions();
        interfaces = InterfaceDao.loadAllInterfaces();
        al = LoginDao.loadPages();

        LoginServlet.session.setAttribute("pages", al);
        LoginServlet.session.setAttribute("roles", rb);
        LoginServlet.session.setAttribute("funcs", ib);
        LoginServlet.session.setAttribute("inter", interfaces);

        response.sendRedirect("add_role.jsp");
    }

    private void update_page(HttpServletRequest request, HttpServletResponse response) {
        try {
            String iid = request.getParameter("pageid");

//            load selected interface details
            ArrayList<InterfaceBean> interface_bean;
            interface_bean = InterfaceDao.loadInterface(iid);
            LoginServlet.session.setAttribute("interface_bean", interface_bean);

//          load all functions in database
            ArrayList<FunctionBean> functions;
            functions = InterfaceDao.loadAllFunctions();
            LoginServlet.session.setAttribute("functions", functions);

//            load the functions of selected interface
            ArrayList<InterfaceBean> inter;
            inter = InterfaceDao.loadFunctionInterface();
            LoginServlet.session.setAttribute("inter", inter);

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
            System.out.println("values :");
            for (int i = 0; i < value.length; i++) {
                System.out.println(value[i]);
            }

            LoginServlet.session.setAttribute("func_values", value);

            response.sendRedirect("update_page.jsp");
        } catch (IOException ex) {
            Logger.getLogger(UserFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void update_interface(HttpServletRequest request, HttpServletResponse response) {
        String pageid = request.getParameter("intid");
        String pagename = request.getParameter("pagename");
        String pageurl = request.getParameter("pageurl");
        String pagedesc = request.getParameter("pagedesc");
        String[] functions = request.getParameterValues("functions");
        
        //update interface
        InterfaceDao.updateInterface(pageid,pagename,pageurl,pagedesc);
        //update privilage
        InterfaceDao.updatePrivilages(pageid,functions);
        //update assinged functions
        
        
    }

    private void delete_interface(HttpServletRequest request, HttpServletResponse response) {
        try {
            String iid = request.getParameter("pageid");
            ArrayList<InterfaceBean> ibean = new ArrayList<>();
            ibean = InterfaceDao.findInterfaceFunctionID(iid);
            
            ArrayList<PrivilageBean> pbean = new ArrayList<>();
            pbean = InterfaceDao.findPrivilageID(ibean);
            
            //delete privilages assigned for the interface
            InterfaceDao.deletePrivilages(pbean);
            
            System.out.println("privilages deleted successfully");
            
            //delete functions assigned for the interface
            InterfaceDao.deleteInterfaceFunction(ibean);
            
            System.out.println("functions of interface deleted successfully");
            
            //delete interface
            InterfaceDao.deleteInterface(iid);
            
            System.out.println("interface deleted successfully");
            
            ArrayList<FunctionBean> functions;
            functions = InterfaceDao.loadAllFunctions();
            ArrayList<InterfaceBean> inter;
            inter = InterfaceDao.loadFunctionInterface();
            ArrayList<PageBean> al;
            al = LoginDao.loadPages();
            
            LoginServlet.session.setAttribute("pages", al);
            LoginServlet.session.setAttribute("functions", functions);
            LoginServlet.session.setAttribute("inter", inter);
            
            response.sendRedirect("new_page.jsp");
        } catch (IOException ex) {
            Logger.getLogger(UserFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
}
