/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easydressup.servlet;

import com.easydressup.exception.FindClothsException;
import com.easydressup.model.Cloth;
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
@WebServlet(name = "ImageServlet", urlPatterns = {"/image"})
public class ImageServlet extends HttpServlet {

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
        int clothId = Integer.parseInt(request.getParameter("clothId"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInuser");
        if (null != user) {
            try {
                Cloth cloth = DatabaseUtil.getClothByClothId(clothId);
                if (null != cloth) {
                    response.setHeader("Content-Type", cloth.getContenttype());

                    response.setHeader("Content-Length", String.valueOf(cloth.getImage().length));

                    response.setHeader("Content-Disposition", "inline; filename=\"" + cloth.getFileName() + "\"");

                    // Write image data to Response.
                    response.getOutputStream().write(cloth.getImage());
                }
            } catch (FindClothsException ex) {
                ex.printStackTrace();
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