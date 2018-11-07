<%-- 
    Document   : login
    Created on : 31 Oct, 2018, 5:47:40 PM
    Author     : 
--%>
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html style="width: 100%; height: 100%; margin: 0; padding: 0">
    <head>

        <meta charset="utf-8">
        <title>EasyDressUp-Login</title>
        <!-- JSTL includes -->
        <link href="resources/css/cloths.css" rel="stylesheet" type="text/css"/>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    </head>

    <body id="page-top" style="width: 100%; height: 100%; margin: 0; padding: 0;">
        <header
            style="background-color: #3b5998; height: 6%; width: 100%; position: absolute; top: 0">
            <div style="float: left; padding: 10px">

                <div
                    style="position: absolute; left: 10%;font-style: italic; color: white; font-size: xx-large; font-weight: bold;">
                    Easy Dresss Up</div>
            </div>
        </header>
        <div id="mainDiv1">

            <div
                style="position: absolute; left: 35%; top: 15%; width: 30%; height: 50%;">
                <h1 style="color: black; position: absolute; left: 30px;">Log In</h1>
                <div
                    style="position: absolute; width: 100%; left: 0; padding: 0; top: 20%;">
                    <form action="login" method="post">

                        <div id="errors">
                            <c:choose>
                                <c:when test="${not empty message }">
                                    <p class="alert ${messageClass}">${message }</p>
                                    <%
                                        session.setAttribute("message", null);
                                        session.setAttribute("messageClass", null);
                                    %>
                                </c:when>
                            </c:choose>

                        </div>
                        <fieldset style="border: none; width: 100%; height: 60%;">
                            <div style="width: 100%; height: 20px; padding-bottom: 5%">
                                <input style="width: 100%; height: 30px" type="text"
                                       name="userName" id="email" placeholder="Email" />
                            </div>
                            <div style="width: 100%; height: 20px; padding-bottom: 5%">
                                <input style="width: 100%; height: 30px" type="password"
                                       name="password" id="password" placeholder="Password"
                                       />
                            </div>
                            <div style="width: 100%; height: 20px; padding-bottom: 5%">
                                <table>
                                    <tr>
                                        <td style="padding-right: 10px;">
                                            <input type="submit" value="Log In" id="login_btn" name="login">
                                            <input type="reset" value="Reset" id="reset_btn" name="reset">
                                        </td>
                                        <td><a style="padding-right: 10px" href="registration.jsp">Register</a></td>
                                    </tr>
                                </table>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
