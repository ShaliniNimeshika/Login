/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.connection;

import com.login.bean.FunctionBean;
import com.login.bean.InterfaceBean;
import com.login.bean.RoleBean;
import com.login.bean.UserBean;
import com.login.connection.LoginServlet;
import com.login.dao.InterfaceDao;
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

        }

        //add_user(request, response);
//        search_user(request, response);
//        update_user(request, response);
//        delete_user(request, response);
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

        UserDao.addUser(username, password, role);
        System.out.println("data added successfully");

        ub = UserDao.loadAllUsers();
        LoginServlet.session.setAttribute("users", ub);
        response.sendRedirect("user_management.jsp");

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

        rb = RoleDao.loadRoleName();
        LoginServlet.session.setAttribute("roles", rb);
        response.sendRedirect("add_role.jsp");
    }

}
