/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easydressup.servlet;

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
 * Login Servlet
 *
 * @author
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    /**
     * Serial version uid
     */
    private static final long serialVersionUID = -4083331825457551909L;

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

        try {
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            User dbUser = DatabaseUtil.getUserByUserName(userName);
            if (null != dbUser) {
                if (dbUser.getPassword().equals(password)) {
                    User user = dbUser;
                    user.setPassword("");
                    HttpSession session = request.getSession(true);
                    session.setAttribute("loggedInuser", user);
                    response.sendRedirect("home");
                } else {
                    request.getSession().setAttribute("message", "Username and Password not matching");
                    request.getSession().setAttribute("messageClass", "alert-danger");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }

            } else {
                request.getSession().setAttribute("message", "Username and Password not matching");
                request.getSession().setAttribute("messageClass", "alert-danger");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (FindUserException ex) {
            request.getSession().setAttribute("message", "Username and Password not matching");
            request.getSession().setAttribute("messageClass", "alert-danger");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

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
        if (null != user) {
            response.sendRedirect("home");
        } else {
            response.sendRedirect("login.jsp");
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
}
