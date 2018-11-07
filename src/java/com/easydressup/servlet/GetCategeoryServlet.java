/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easydressup.servlet;

import com.easydressup.exception.FindCategoryException;
import com.easydressup.exception.FindClothsException;
import com.easydressup.model.Category;
import com.easydressup.model.Cloth;
import com.easydressup.model.User;
import com.easydressup.util.DatabaseUtil;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 
 */
@WebServlet(name = "GetCategeoryServlet", urlPatterns = {"/category"})
public class GetCategeoryServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInuser");
        if (null == user) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            try {
                String categoryId = request.getParameter("subcat");
                Category category = DatabaseUtil.getCategoryById(Integer.parseInt(categoryId));
                if (null != category) {
                    List<Integer> clothIds = DatabaseUtil.getClothIdsByCategoryIdAndUserId(category.getCategoryId(), user.getUserId());
                    request.getSession().setAttribute("clothIds", clothIds);
                    request.getRequestDispatcher("category.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("home").forward(request, response);
                }
            } catch (FindCategoryException ex) {
                request.getRequestDispatcher("home").forward(request, response);
            } catch (FindClothsException ex) {
                request.getRequestDispatcher("home").forward(request, response);
            }
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
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
