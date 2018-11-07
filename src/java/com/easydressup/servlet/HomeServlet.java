/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easydressup.servlet;

import com.easydressup.exception.FindCategoryException;
import com.easydressup.model.Category;
import com.easydressup.model.User;
import com.easydressup.util.DatabaseUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInuser");
        if (null == user) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }else{
            processRequest(request, response);
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInuser");
        if (null != user) {
            processRequest(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Category> categories = DatabaseUtil.getAllCategory();
            Collections.sort(categories, new Comparator<Category>() {
                @Override
                public int compare(Category o1, Category o2) {
                    return o1.getCategoryName().compareTo(o2.getCategoryName());
                }
            });
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("loggedInuser");
            if ("admin".equals(user.getRole())) {
                request.getSession().setAttribute("categories", categories);
            } else {
                Map<String, List<Category>> categoryMap = new HashMap<>();
                for (Category category : categories) {
                    List<Category> list = new ArrayList<>();
                    if (null != category.getParent()) {
                        if (categoryMap.containsKey(category.getParent().getCategoryName())) {
                            list.addAll(categoryMap.get(category.getParent().getCategoryName()));
                        }
                        list.add(category);
                        categoryMap.put(category.getParent().getCategoryName(), list);
                    } else {
                        if (categoryMap.containsKey(category.getCategoryName())) {
                            list.addAll(categoryMap.get(category.getCategoryName()));
                        }
                        categoryMap.put(category.getCategoryName(), list);
                    }

                }
                request.getSession().setAttribute("categories", categoryMap);
            }
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } catch (FindCategoryException ex) {
            request.getSession().setAttribute("message", "Failed to load categories");
            request.getSession().setAttribute("messageClass", "alert-danger");
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }
}