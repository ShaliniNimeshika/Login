/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.reports;

import com.login.bean.FunctionBean;
import com.login.bean.UserBean;
import com.login.dao.InterfaceDao;
import com.login.dao.UserDao;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

/**
 *
 * @author shalini_w
 */
public class UserReportServlet extends HttpServlet {

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
        try {
            String action = request.getParameter("action");
            String interfaceid = request.getParameter("interfaceid");
            List<Map<String, Object>> data = new ArrayList<>();
            for (UserBean ubean : UserDao.loadAllUsers()) {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("userid", ubean.getUserid());
                parameters.put("username", ubean.getUsername());
                parameters.put("rolename", ubean.getUrole());
                parameters.put("created_time", ubean.getcDate());
                if (ubean.getActive().equals("1")) {
                    parameters.put("active", "Active");
                } else {
                    parameters.put("active", "Deactive");
                }
                data.add(parameters);
            }

            String path = getServletContext().getRealPath("WEB-INF/jrxmlFiles/user_report.jrxml");
            InputStream input = new FileInputStream(new File(path));
            JRDataSource jRDataSource = new JRBeanCollectionDataSource(data);
            JasperReport jasperReport = JasperCompileManager.compileReport(input);
            JasperPrint fillReport = JasperFillManager.fillReport(jasperReport, null, jRDataSource);

            switch (action) {
                case "pdf":
                    System.out.println("Export as PDF");
                    JRPdfExporter exporter = new JRPdfExporter();
                    SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
                    exporter.setExporterInput(new SimpleExporterInput(fillReport));
                    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
                    exporter.setConfiguration(configuration);
                    exporter.exportReport();
                    break;

                case "excel":
                    System.out.println("Export as Excel Sheet");
                    SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd_hh-mm-ss");
                    
                    JRXlsxExporter exporter2 = new JRXlsxExporter();
                    exporter2.setExporterInput(new SimpleExporterInput(fillReport));
                    File outputFile = new File("D://Projects//Login Project//Excel Reports//excelTest "+ formatter.format(new Date()) +".xlsx");
                    exporter2.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
                    SimpleXlsxReportConfiguration configuration2 = new SimpleXlsxReportConfiguration();
                    configuration2.setDetectCellType(true);//Set configuration as you like it!!
                    configuration2.setCollapseRowSpan(false);
                    configuration2.setOnePagePerSheet(false);
                    configuration2.setRemoveEmptySpaceBetweenRows(true);
                    configuration2.setWhitePageBackground(false);
                    exporter2.setConfiguration(configuration2);
                    exporter2.exportReport();

                    loadIndexPage(request, response);
                    break;

                default:
                    JRPdfExporter exporter1 = new JRPdfExporter();
                    SimplePdfExporterConfiguration configuration1 = new SimplePdfExporterConfiguration();
                    exporter1.setExporterInput(new SimpleExporterInput(fillReport));
                    exporter1.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
                    exporter1.setConfiguration(configuration1);
                    exporter1.exportReport();
                    break;
            }

        } catch (JRException ex) {
            Logger.getLogger(UserReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadIndexPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            String interfaceid = request.getParameter("interfaceid");
            HttpSession session = request.getSession();
            String roleid = (String) session.getAttribute("roleid");
            int page = 1;
            int recordsPerPage = 3;
            if (request.getParameter("recordsPerPage") != null) {
                recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
            }

            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            int noOfRecords = UserDao.NoOfUsers();
            ArrayList<UserBean> ub = UserDao.viewAllUsers((page - 1) * recordsPerPage, recordsPerPage);

            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

            ArrayList<FunctionBean> fb = InterfaceDao.loadFunction(roleid, interfaceid);
            String msg = "Report is Exported as Excel\nGo to D://Projects//Login Project//Excel Reports";

            request.setAttribute("msg", msg);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);

            request.setAttribute("index", interfaceid);
            request.setAttribute("functions", fb);
            request.setAttribute("users", ub);
            request.getRequestDispatcher("WEB-INF/user_management.jsp").forward(request, response);
        } catch (IOException | NumberFormatException | ServletException e) {
            e.printStackTrace();
        }
    }

}
