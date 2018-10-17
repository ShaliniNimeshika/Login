
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.connection;

import com.login.bean.FunctionBean;
import com.login.bean.InterfaceBean;
import com.login.bean.RoleAccessBean;
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
        HttpSession session = request.getSession();
        String roleid = session.getAttribute("roleid").toString();
        String interfaceid = request.getParameter("index");
        String action = request.getParameter("action");
        ArrayList<UserBean> data;
        ArrayList<FunctionBean> fb;
        ArrayList<UserBean> ub;
        ArrayList<RoleBean> rb;

        //get the hidden value as action to specify, which action that the user want to do.
        switch (action) {
            //go to add_user.jsp
            case "Add": {
                rb = RoleDao.loadRoleName();
                try {
                    request.setAttribute("roles", rb);
                    request.getRequestDispatcher("add_user.jsp").forward(request, response);
                } catch (IOException | ServletException ex) {
                    Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            //delete user from database
            case "Delete":
                delete_user(request, response);
                break;
            //search user from database
            case "Search":
                search_user(request, response);
                break;
            //go to update_user.jsp 
            case "Update":
                String uid = request.getParameter("userid");

                data = UserDao.loadUserData(uid);
                rb = RoleDao.loadRoleName();
                try {
                    request.setAttribute("roles", rb);
                    request.setAttribute("data", data);
                    request.getRequestDispatcher("update_user.jsp").forward(request, response);
                } catch (IOException | ServletException ex) {
                    Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            //go to add_role.jsp
            case "New Role":
                System.out.println("try to add new user role");
                rb = RoleDao.loadRoleName();
                ArrayList<InterfaceBean> ib;
                ArrayList<InterfaceBean> interfaces;
                ArrayList<RoleBean> alldata;
                ib = InterfaceDao.loadInterfaceFunctions();
                interfaces = InterfaceDao.loadAllInterfaces();
                alldata = InterfaceDao.loadUserFunctions();
                try {

                    request.setAttribute("roles", rb);
                    request.setAttribute("funcs", ib);
                    request.setAttribute("inter", interfaces);
                    request.setAttribute("alldata", alldata);
                    request.getRequestDispatcher("add_role.jsp").forward(request, response);
                } catch (IOException | ServletException ex) {
                    Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            // goto update_role.jsp
            case "update_role":
                String selected_rid = request.getParameter("roleid");
                System.out.println("update user role");
                rb = RoleDao.loadRoleName();

                ArrayList<RoleBean> roledata;
                ib = InterfaceDao.loadAllInterfaceFunctions(); //include ifid,iname,fname
                interfaces = InterfaceDao.loadAllInterfaces();
                roledata = InterfaceDao.loadRoleAcessibleFunctions(selected_rid); //include roleid,iid,iname,fid,fname,ifid
                
                String[] permission = new String[ib.size()];
                for (int i = 0; i < roledata.size(); i++) {
                    RoleBean get = roledata.get(i);
                    System.out.println("Role name : "+i+" "+get.getRolename());
                }
                for (int i = 0; i < ib.size(); i++) {
                    InterfaceBean get = ib.get(i);
                    System.out.println(get.getF_name());
                    for (int j = 0; j < roledata.get(0).getRa_bean().size(); j++) {
                        RoleAccessBean get1 = roledata.get(0).getRa_bean().get(j);
                        if (get.getF_name().equals(get1.getF_name())) {
                            permission[i] = "1";
                            break;
                        } 
                    }
                    
                    if(permission[i] == null)
                        permission[i] = "0";
                }
                
                System.out.println("\n");
                ArrayList<String> per = new ArrayList<>();
                for (int i = 0; i < permission.length; i++) {
                    String string = permission[i];
                    per.add(string);
                    System.out.println(string);
                }
                
                
                
                try {
                    request.setAttribute("data", permission);
                    request.setAttribute("roles", rb);
                    request.setAttribute("funcs", ib);
                    request.setAttribute("inter", interfaces);
                    request.setAttribute("alldata", roledata);

                    request.getRequestDispatcher("update_role.jsp").forward(request, response);
                } catch (IOException | ServletException ex) {
                    Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            //go to new_page.jsp
            case "New Interface":
                System.out.println("Adding new page with functions nb");
                ArrayList<FunctionBean> function;
                function = InterfaceDao.loadAllFunctions();
                ArrayList<InterfaceBean> inte;
                inte = InterfaceDao.loadFunctionInterface();
                try {

                    request.setAttribute("functions", function);
                    request.setAttribute("inter", inte);

                    request.getRequestDispatcher("new_page.jsp").forward(request, response);
                } catch (IOException | ServletException ex) {
                    Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            //if there is no specific action is required, user_management.jsp is loading    
            default:
                fb = InterfaceDao.loadFunction(roleid, interfaceid);
                ub = UserDao.loadAllUsers();
                try {

                    request.setAttribute("index", interfaceid);
                    request.setAttribute("functions", fb);
                    request.setAttribute("users", ub);
                    request.getRequestDispatcher("user_management.jsp").forward(request, response);
                } catch (IOException | ServletException ex) {
                    Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
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

    private void delete_user(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //function for deleting user from database starts here
        String uid = request.getParameter("userid");
        System.out.println("delete user id" + uid);
        UserDao.deleteUser(uid);
        System.out.println("data delete successfully");
        ArrayList<UserBean> ub;
        ub = UserDao.loadAllUsers();
        request.setAttribute("users", ub);
        request.getRequestDispatcher("user_management.jsp").forward(request, response);
    }

    private void search_user(HttpServletRequest request, HttpServletResponse response) {
        //function for searching user from database starts here
        String search = request.getParameter("searching");
        HttpSession session = request.getSession();
        String roleid = session.getAttribute("roleid").toString();
        ArrayList<UserBean> getUser;
        ArrayList<FunctionBean> fb;
        getUser = UserDao.getSearchUser(search);
        fb = InterfaceDao.loadFunction(roleid, "1");
        try {
            request.setAttribute("functions", fb);
            request.setAttribute("users", getUser);
            request.getRequestDispatcher("user_management.jsp").forward(request, response);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
