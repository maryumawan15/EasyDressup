/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easydressup.servlet;

import com.easydressup.exception.CreateUserException;
import com.easydressup.exception.FindUserException;
import com.easydressup.model.User;
import com.easydressup.util.DatabaseUtil;
import java.io.IOException;
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
@WebServlet(name = "UpdateUserServet", urlPatterns = {"/updateUser"})
public class UpdateUserServet extends HttpServlet {

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
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String errors = "";
        if (!StringUtil.isValid(firstName)) {
            errors += "First name is required<br/>";
        }
        if (!StringUtil.isValid(lastName)) {
            errors += "Last name is required<br/>";
        }

        if (errors.isEmpty()) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("loggedInuser");
            if (null != user) {
                email = user.getUserName();
                try {
                    DatabaseUtil.updateUser(email, password, firstName, lastName);
                    try {
                        user = DatabaseUtil.getUserByUserName(user.getUserName());
                        user.setPassword("");
                        request.getSession().setAttribute("loggedInuser", user);
                    } catch (FindUserException ex) {
                    }

                    request.getSession().setAttribute("messageClass", "alert-success");
                    request.getSession().setAttribute("message", "Your account is updated successfully.");
                    response.sendRedirect("profile");

                } catch (CreateUserException ex) {
                    request.getSession().setAttribute("messageClass", "alert-danger");
                    request.getSession().setAttribute("message", "Failed to update the user");
                    response.sendRedirect("profile");
                }

            }
        } else {
            request.getSession().setAttribute("message", errors);
            request.getSession().setAttribute("messageClass", "alert-danger");
            response.sendRedirect("profile");

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
