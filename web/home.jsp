<%-- 
    Document   : home
    Created on : 31 Oct, 2018, 5:47:40 PM
    Author     : 
--%>
<%@page import="com.easydressup.model.User"%>
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html style="width: 100%; height: 100%; margin: 0; padding: 0">
    <head>

        <meta charset="utf-8">
        <title>EasyDressUp-Home</title>
        <!-- JSTL includes -->
        <link href="resources/css/cloths.css" rel="stylesheet" type="text/css"/>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
        <script src="resources/js/easydressup.js" type="text/javascript"></script>
    </head>

    <body id="page-top" style="width: 100%; height: 100%; margin: 0; padding: 0">
        <header
            style="background-color: #3b5998; height: 6.5%; width: 100%; position: absolute; top: 0">
            <div style="float: left; padding: 10px">

                <div
                    style="position: absolute; left: 10%;font-style: italic; color: white; font-size: xx-large; font-weight: bold;">
                    Easy Dresss Up</div>
                <div>
                    <%
                        User usr = (User) request.getSession().getAttribute("loggedInuser");
                        if (null == usr) {
                            response.sendRedirect("login.jsp");
                        }
                    %>
                    <div
                        style="top:2%;float: right; position: absolute; right: 4%; top: 10%; color: white; width: 16%;">
                        <table style='width: 100%;height: 100%'>
                            <tr>
                                <td>
                                    <b><a style="color:white;text-decoration: none;" href="profile"><%
                                        out.print(usr.getFirstName() + " " + usr.getLastName());
                                        %></a> 
                                    </b>
                                </td>
                                <td><form action="logout" method="post">
                                        <input type="submit"
                                               style="cursor: pointer;height: 30px;width: 100px;border: none; background-color: #3b5998; color: white; padding-left: 2%; padding-top: 2%"
                                               value="LogOut">
                                    </form>

                                </td>
                            </tr>
                        </table>

                    </div>
                </div>
            </div>
        </header>
        <%
            if ("admin".equals(usr.getRole())) {
        %>
        <jsp:include page="adminhome.jsp"/>
        <%} else {%>
        <jsp:include page="userhome.jsp"/>
        <%}%>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
