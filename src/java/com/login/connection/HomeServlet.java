/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.connection;

import com.login.bean.FunctionBean;
import com.login.bean.RoleBean;
import com.login.bean.UserBean;
import com.login.common.Common;
import com.login.dao.InterfaceDao;
import com.login.dao.UserDao;
import com.login.util.SessionVarList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
public class HomeServlet extends HttpServlet {

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
        String select = request.getParameter("select");
        //getting session data
        HttpSession session = request.getSession();
        String roleid = (String) session.getAttribute("roleid");
        String newIP = session.getAttribute("currentIP").toString();
        String username = session.getAttribute("uname").toString();

        ServletContext context = getServletConfig().getServletContext();
        HashMap<String, String> usermap = (HashMap<String, String>) context.getAttribute(SessionVarList.USERMAP);
        HashMap<String, String> userdev = (HashMap<String, String>) context.getAttribute(SessionVarList.USERDEVICE);

        Common common = new Common();
        boolean logged = common.checkUserLogin(usermap, username);
        boolean sameDevice = common.checkUserDevice(userdev, username, newIP);

        if (logged == true && sameDevice == false) {
            request.setAttribute("msg", "Logged using another device");
            request.getRequestDispatcher("index.jsp").include(request, response);
        } else {
            ArrayList<FunctionBean> fb;
            ArrayList<UserBean> ub;
            try {
                switch (select) {
                    case "umgt":
                        String interfaceid = "1";
                        try {
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

                            fb = InterfaceDao.loadFunction(roleid, interfaceid);
                            request.setAttribute("noOfPages", noOfPages);
                            request.setAttribute("currentPage", page);

                            request.setAttribute("index", interfaceid);
                            request.setAttribute("functions", fb);
                            request.setAttribute("users", ub);
                            request.getRequestDispatcher("WEB-INF/user_management.jsp").forward(request, response);

                        } catch (IOException | ServletException ex) {
                            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case "rmgt":
                        try {
                            request.getRequestDispatcher("WEB-INF/role_management.jsp").forward(request, response);
                        } catch (IOException | ServletException ex) {
                            Logger.getLogger(DepositServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        break;

                    case "pmgt":
                        try {
                            request.getRequestDispatcher("WEB-INF/page_management.jsp").forward(request, response);
                        } catch (IOException | ServletException ex) {
                            Logger.getLogger(DepositServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

}
