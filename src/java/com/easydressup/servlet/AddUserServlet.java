package com.easydressup.servlet;

import com.easydressup.exception.CreateUserException;
import com.easydressup.exception.FindCategoryException;
import com.easydressup.exception.FindUserException;
import com.easydressup.model.Category;
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

/**
 * Servlet implementation class add user
 */
@WebServlet("/register")
public class AddUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
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
     * @return @see HttpServlet#doPost(HttpServletRequest request,
     * HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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

        if (!StringUtil.isValid(password)) {
            errors += "Password is required<br/>";
        }
        if (!StringUtil.isValid(email)) {
            errors += "Email is required<br/>";
        }
        if (errors.isEmpty()) {
            try {
                User dbUser = DatabaseUtil.getUserByUserName(email);
                if (null == dbUser) {
                    try {
                        DatabaseUtil.addUser(email, password, firstName, lastName, "user");
                        request.getSession().setAttribute("messageClass", "alert-success");
                        request.getSession().setAttribute("message", "Your account is created successfully.");
                        request.getRequestDispatcher("login.jsp").forward(request, response);

                    } catch (CreateUserException ex) {
                        request.getSession().setAttribute("messageClass", "alert-danger");
                        request.getSession().setAttribute("message", "Failed to add the user");
                        request.getRequestDispatcher("registration.jsp").forward(request, response);
                    }

                } else {
                    request.getSession().setAttribute("message", "The user is already registered");
                    request.getSession().setAttribute("messageClass", "alert-danger");
                    request.getRequestDispatcher("registration.jsp").forward(request, response);
                }
            } catch (FindUserException ex) {
                Logger.getLogger(AddUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            request.getSession().setAttribute("message", errors);
            request.getSession().setAttribute("messageClass", "alert-danger");
            request.getRequestDispatcher("registration.jsp").forward(request, response);

        }

    }

}
