/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.connection;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author shalini_w
 */
public class FileManagerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ServletFileUpload uploader = null;

    @Override
    public void init() throws ServletException {
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
        fileFactory.setRepository(filesDir);
        this.uploader = new ServletFileUpload(fileFactory);
    }

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

        try {
            if (!ServletFileUpload.isMultipartContent(request)) {
                throw new ServletException("Content type is not multipart/form-data");
            }
            
            System.out.println("file dir :"+request.getServletContext().getAttribute("FILES_DIR"));
            
            List<FileItem> fileItemsList = uploader.parseRequest(request);
            if (fileItemsList != null) {
                System.out.println("item list is not null");
            }
            Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
            while (fileItemsIterator.hasNext()) {
                FileItem fileItem = fileItemsIterator.next();
                System.out.println("FieldName=" + fileItem.getFieldName());
                System.out.println("FileName=" + fileItem.getName());
                System.out.println("ContentType=" + fileItem.getContentType());
                System.out.println("Size in bytes=" + fileItem.getSize());
                
                File file = new File(request.getServletContext().getAttribute("FILES_DIR") + File.separator + fileItem.getName());
                System.out.println("Absolute Path at server=" + file.getAbsolutePath());
                fileItem.write(file);
            }
            
            String msg = "File uploaded successfully";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
            
        } catch (FileUploadException ex) {
            Logger.getLogger(FileManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FileManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
