
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
import com.login.dao.InterfaceDao;
import com.login.dao.RoleDao;
import com.login.dao.UserDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shalini_w
 */
public class UserManagement extends HttpServlet {

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

        doPost(request, response);
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
        //processRequest(request, response);
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
//        session1.setAttribute("action", action); 
//        System.out.println("button :" + action);
        if (action.equals("Add")) {

            ArrayList<RoleBean> rb;
            rb = RoleDao.loadRoleName();
            try {
                request.setAttribute("roles",rb);
                request.getRequestDispatcher("add_user.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (action.equals("Delete")) {

            delete_user(request, response);

        } else if (action.equals("Update")) {
//            System.out.println("1:get action as Update");
            String uid = request.getParameter("userid");
//            System.out.println("userid for update :"+uid);
            
            ArrayList<UserBean> data;
            data = UserDao.loadUserData(uid);
            
            try {

                request.setAttribute("roles", rb);
                request.setAttribute("data", data);
                request.getRequestDispatcher("update_user.jsp").forward(request, response);
//                System.out.println("4:interface is loaded");
            } catch (Exception ex) {
                Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (action.equals("New Role")) {

            System.out.println("try to add new user role");

            rb = RoleDao.loadRoleName();
            ArrayList<InterfaceBean> ib;
            ArrayList<InterfaceBean> interfaces;
            ib = InterfaceDao.loadInterfaceFunctions();
            interfaces = InterfaceDao.loadAllInterfaces();
            try {
                LoginServlet.session.setAttribute("funcs", ib);
                LoginServlet.session.setAttribute("inter", interfaces);
                request.setAttribute("roles", rb);
                request.setAttribute("funcs", ib);
                request.setAttribute("inter", interfaces);
                request.getRequestDispatcher("add_role.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (action.equals("New Interface")) {

            System.out.println("Adding new page with functions nb");
            ArrayList<FunctionBean> functions;
            functions = InterfaceDao.loadAllFunctions();
            ArrayList<InterfaceBean> inter;
            inter = InterfaceDao.loadFunctionInterface();
            try {
                LoginServlet.session.setAttribute("functions", functions);
                LoginServlet.session.setAttribute("inter", inter);
                request.setAttribute("functions", functions);
//                request.setAttribute("funcs", ib);
                request.setAttribute("inter", inter);
                
                request.getRequestDispatcher("new_page.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            rb = RoleDao.loadRoleName();
            fb = InterfaceDao.loadFunction(roleid, interfaceid);
            ub = UserDao.loadAllUsers();
            try {

                LoginServlet.session.setAttribute("index", interfaceid);
                LoginServlet.session.setAttribute("functions", fb);
                LoginServlet.session.setAttribute("users", ub);
                request.setAttribute("index", interfaceid);
                request.setAttribute("functions", fb);
                request.setAttribute("users", ub);
                request.getRequestDispatcher("user_management.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    private void delete_user(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = request.getParameter("userid");
        System.out.println("delete user id" + uid);
        UserDao.deleteUser(uid);
        System.out.println("data delete successfully");

        ub = UserDao.loadAllUsers();
        LoginServlet.session.setAttribute("users", ub);
        response.sendRedirect("user_management.jsp");
    }

}
