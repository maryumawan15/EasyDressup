/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easydressup.servlet;

import com.easydressup.model.Cloth;
import com.easydressup.model.User;
import com.easydressup.util.DatabaseUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author 
 */
@WebServlet(name = "UpdateClothServlet", urlPatterns = {"/updateCloth"})
public class UpdateClothServlet extends HttpServlet {

    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 200 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file;

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
        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");
        String clothId = request.getParameter("clothId");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);
        File file = new File(System.getProperty("java.io.tmpdir"));
        // Location to save data that is larger than maxMemSize.
        factory.setRepository(file);
        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);
        try {
            // Parse the request to get file items.
            List<FileItem> fileItems = upload.parseRequest(request);

            // Process the uploaded file items
            Iterator<FileItem> i = fileItems.iterator();
            InputStream inputStream = null;
            String contentType = "";
            String fileName = "";
            while (i.hasNext()) {
                FileItem item = (FileItem) i.next();
                if (!item.isFormField()) {
                    fileName = item.getName();
                    inputStream = item.getInputStream();
                    contentType = item.getContentType();
                } else {
                    if (item.getFieldName().equals("clothId")) {
                        clothId = item.getString();
                    }
                }
            }
            Cloth cloth = DatabaseUtil.getClothByClothId(Integer.parseInt(clothId));
            if (null != cloth) {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("loggedInuser");
                if (null != user) {
                    DatabaseUtil.updateCloths(inputStream, cloth.getClothId(), contentType, fileName);
                    request.getSession().setAttribute("message", "Cloths uploaded successfully");
                    request.getSession().setAttribute("messageClass", "alert-success");
                    response.sendRedirect("category.jsp?parent=" + cloth.getCatgroy().getParent().getCategoryName() + "&subcat=" + cloth.getCatgroy().getCategoryId());
                }
            } else {
                request.getSession().setAttribute("message", "Failed to upload cloths");
                request.getSession().setAttribute("messageClass", "alert-danger");
                response.sendRedirect("home");
            }
        } catch (Exception ex) {
            request.getSession().setAttribute("message", "Failed to upload cloths");
            request.getSession().setAttribute("messageClass", "alert-danger");
            response.sendRedirect("home");
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
