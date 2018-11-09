<%-- 
    Document   : viewReport
    Created on : Oct 18, 2018, 5:12:43 PM
    Author     : shalini_w
--%>

<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="net.sf.jasperreports.engine.JasperCompileManager"%>
<%@page import="net.sf.jasperreports.engine.JasperReport"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="com.login.util.DBConnection"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="application/pdf"%>

<% 
    Connection con = null;
    
    try{
        con = DBConnection.createConnection();
        String jrxmlFile = session.getServletContext().getRealPath("/users.jrxml");
        InputStream input = new FileInputStream(new File(jrxmlFile));
        
        JasperReport jreport = JasperCompileManager.compileReport(input);
        JasperPrint jprint = JasperFillManager.fillReport(jreport, null , con);
        
        JasperExportManager.exportReportToPdfStream(jprint, response.getOutputStream());
        
        response.getOutputStream().flush();
        response.getOutputStream().close();
        
    }catch(Exception e){
        e.printStackTrace();
    } finally {
        if (con!=null) {
                con.close();
            }
    }
%>


