/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easydressup.servlet;


import com.easydressup.exception.CreateCategoryException;
import com.easydressup.exception.FindCategoryException;
import com.easydressup.model.Category;
import com.easydressup.util.DatabaseUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 
 */
@WebServlet(name = "AddCategoryServlet", urlPatterns = {"/addCategory"})
public class AddCategoryServlet extends HttpServlet {

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
        String parentCategory = request.getParameter("parentCategory");
        String category = request.getParameter("categoryName");
        if (StringUtil.isValid(category)) {
            try {
                if (DatabaseUtil.getCategoryByName(category) == null) {
                    int parentCat = "-1".equals(parentCategory) ? -1 : Integer.parseInt(parentCategory);
                    DatabaseUtil.addCategory(category, parentCat);
                    List<Category> categories = DatabaseUtil.getAllCategory();
                    request.getSession().setAttribute("categories", categories);
                    request.getSession().setAttribute("message", "Category created successfully");
                    request.getSession().setAttribute("messageClass", "alert-success");
                    request.getRequestDispatcher("home.jsp").forward(request, response);
                } else {
                    request.getSession().setAttribute("message", "Category already exist");
                    request.getSession().setAttribute("messageClass", "alert-danger");
                    request.getRequestDispatcher("home.jsp").forward(request, response);
                }
            } catch (CreateCategoryException | FindCategoryException ex) {
                request.getSession().setAttribute("message", ex.getMessage());
                request.getSession().setAttribute("messageClass", "alert-danger");
                request.getRequestDispatcher("home.jsp").forward(request, response);
            }
        } else {
            String errors = "Category name is required<br/>";
            request.getSession().setAttribute("message", errors);
            request.getSession().setAttribute("messageClass", "alert-danger");
            request.getRequestDispatcher("home.jsp").forward(request, response);
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
        try {
            List<Category> categories = DatabaseUtil.getAllCategory();
            request.getSession().setAttribute("categories", categories);
            request.getRequestDispatcher("categories.jsp").forward(request, response);
        } catch (FindCategoryException ex) {
            ex.printStackTrace();
            request.getSession().setAttribute("message", "Failed to load categories");
            request.getSession().setAttribute("messageClass", "alert-danger");
            request.getRequestDispatcher("categories.jsp").forward(request, response);
        }
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
