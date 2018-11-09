/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.connection;

import com.login.util.Log4jLogger;
import com.login.util.SessionVarList;
import java.io.IOException;
import java.util.HashMap;
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
public class LogoutServlet extends HttpServlet {

    Log4jLogger log = new Log4jLogger();

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

        //invalidate the session and logout from the system
        HttpSession session = request.getSession(false);
        if (session != null) {

            ServletContext context = getServletConfig().getServletContext();
            HashMap<String, String> usermap = (HashMap<String, String>) context.getAttribute(SessionVarList.USERMAP);
            if (usermap != null) {
                usermap.remove(session.getAttribute("uname"));
            }
            
            HashMap<String, String> userdev = (HashMap<String, String>) context.getAttribute(SessionVarList.USERDEVICE);
            if (userdev != null) {
                userdev.remove(session.getAttribute("uname"));
            }
//                        
            log.getLogger("Logout", "info", session.getAttribute("uname").toString().toUpperCase(), request);
            session.removeAttribute("uname");
//            session.removeAttribute("listner");
            session.invalidate();
            request.getRequestDispatcher("index.jsp").forward(request, response);
//            response.sendRedirect(request.getContextPath());
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
        processRequest(request, response);
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

}
