
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.connection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSerializer;
import com.login.bean.FunctionBean;
import com.login.bean.InterfaceBean;
import com.login.bean.RoleAccessBean;
import com.login.bean.RoleBean;
import com.login.bean.UserBean;
import com.login.common.Common;
import com.login.dao.InterfaceDao;
import com.login.dao.RoleDao;
import com.login.dao.UserDao;
import com.login.util.SessionVarList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
                        request.getRequestDispatcher("WEB-INF/add_user.jsp").forward(request, response);
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
                        request.getRequestDispatcher("WEB-INF/update_user.jsp").forward(request, response);
                    } catch (IOException | ServletException ex) {
                        Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                //go to add_role.jsp
                case "View Role":
                    rb = RoleDao.loadRoleName();
                    ArrayList<InterfaceBean> ibn;
                    ArrayList<InterfaceBean> interfacess;
                    ArrayList<RoleBean> all;

                    ibn = InterfaceDao.loadInterfaceFunctions();
                    interfacess = InterfaceDao.loadAllInterfaces();
                    all = InterfaceDao.loadUserFunctions();
                    try {

                        request.setAttribute("roles", rb);
                        request.setAttribute("funcs", ibn);
                        request.setAttribute("inter", interfacess);
                        request.setAttribute("alldata", all);
                        request.getRequestDispatcher("WEB-INF/view_role.jsp").forward(request, response);
                    } catch (IOException | ServletException ex) {
                        Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                case "New Role":
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
                        request.getRequestDispatcher("WEB-INF/add_role.jsp").forward(request, response);
                    } catch (IOException | ServletException ex) {
                        Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                // goto update_role.jsp
                case "update_role":
                    String selected_rid = request.getParameter("roleid");

                    rb = RoleDao.loadRoleName();

                    ArrayList<RoleBean> roledata;
                    ib = InterfaceDao.loadAllInterfaceFunctions(); //include ifid,iname,fname
                    interfaces = InterfaceDao.loadAllInterfaces();
                    roledata = InterfaceDao.loadRoleAcessibleFunctions(selected_rid); //include roleid,iid,iname,fid,fname,ifid

                    String[] permission = new String[ib.size()];
                    for (int i = 0; i < roledata.size(); i++) {
                        RoleBean get = roledata.get(i);

                    }

                    for (int i = 0; i < ib.size(); i++) {
                        InterfaceBean get = ib.get(i);
                        for (int j = 0; j < roledata.get(0).getRa_bean().size(); j++) {
                            RoleAccessBean get1 = roledata.get(0).getRa_bean().get(j);
                            if (get.getF_name().equals(get1.getF_name()) && get.getI_name().equals(get1.getI_name())) {
                                permission[i] = "1";
                                break;
                            }
                        }
                        if (permission[i] == null) {
                            permission[i] = "0";
                        }
                    }

                    try {
                        request.setAttribute("data", permission);
                        request.setAttribute("roles", rb);
                        request.setAttribute("funcs", ib);
                        request.setAttribute("inter", interfaces);
                        request.setAttribute("alldata", roledata);

                        request.getRequestDispatcher("WEB-INF/update_role.jsp").forward(request, response);
                    } catch (IOException | ServletException ex) {
                        Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                //go to new_page.jsp
                case "New Interface":
                    ArrayList<FunctionBean> function;
                    function = InterfaceDao.loadAllFunctions();
                    ArrayList<InterfaceBean> inte;
                    inte = InterfaceDao.loadFunctionInterface();
                    try {

                        request.setAttribute("functions", function);
                        request.setAttribute("inter", inte);

                        request.getRequestDispatcher("WEB-INF/new_page.jsp").forward(request, response);
                    } catch (IOException | ServletException ex) {
                        Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "View_Interface":
                    ArrayList<FunctionBean> function1;
                    function1 = InterfaceDao.loadAllFunctions();
                    ArrayList<InterfaceBean> inte1;
                    inte1 = InterfaceDao.loadFunctionInterface();

                    List<InterfaceBean> ibean = new ArrayList<>();
                    for (int i = 0; i < inte1.size(); i++) {
                        InterfaceBean get = inte1.get(i);
                        String id = get.getI_id();
                        String name = get.getI_name();
                        String url = get.getI_url();
                        String funcs = "";
                        for (int j = 0; j < get.getFbean().size(); j++) {
                            FunctionBean get1 = get.getFbean().get(j);
                            funcs = funcs + " " + get1.getFunction_name();
                        }

                        InterfaceBean interfaceBean = new InterfaceBean(id, name, url, funcs);
                        ibean.add(interfaceBean);
                    }
                    
                    
                    JSONObject obj = new JSONObject();
                    JSONObject main_obj = new JSONObject();
                    for (int i = 0; i < inte1.size(); i++) {
                        InterfaceBean get = inte1.get(i);
                        Map<String,String> map = new HashMap<>();
                        
                        String id = get.getI_id();
                        String name = get.getI_name();
                        String url = get.getI_url();
                        String funcs = "";
                        for (int j = 0; j < get.getFbean().size(); j++) {
                            FunctionBean get1 = get.getFbean().get(j);
                            funcs = funcs + " " + get1.getFunction_name();
                        }
                        
                        map.put("id", id);
                        map.put("name", name);
                        map.put("url", url);
                        map.put("desc", funcs);
                        
                        obj.put("interface"+(i+1), map);
                    }
                    main_obj.put("interface", obj);

                    try {
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");

                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        String json = gson.toJson(ibean);
                        String newJson = gson.toJson(main_obj);
//                        System.out.println("json string" + json);
//                        System.out.println(main_obj);
//                        System.out.println(newJson);
//
                        response.getWriter().write(json);
                        response.getWriter().write(newJson);
                        request.setAttribute("jsonArray", json);
                        request.setAttribute("jsonObject", newJson);
                        request.setAttribute("functions", function1);
                        request.setAttribute("inter", inte1);

                        request.getRequestDispatcher("WEB-INF/view_page.jsp").forward(request, response);
                    } catch (IOException | ServletException ex) {
                        Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                //if there is no specific action is required, user_management.jsp is loading
                default:
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
            }
        }

    }

    private void delete_user(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //function for deleting user from database starts here
        String uid = request.getParameter("userid");

        //delete user from database
        UserDao.deleteUser(uid);

        ArrayList<UserBean> ub;
        ub = UserDao.loadAllUsers();
        request.setAttribute("users", ub);
        request.getRequestDispatcher("WEB-INF/user_management.jsp").forward(request, response);
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
            request.getRequestDispatcher("WEB-INF/user_management.jsp").forward(request, response);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
