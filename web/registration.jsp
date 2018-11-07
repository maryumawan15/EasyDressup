<%-- 
    Document   : register
    Created on : 31 Oct, 2018, 5:47:40 PM
    Author     : 
--%>
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html style="width: 100%; height: 100%; margin: 0; padding: 0">
    <head>

        <meta charset="utf-8">
        <title>EasyDressUp-Registration</title>
        <!-- JSTL includes -->
        <link href="resources/css/cloths.css" rel="stylesheet" type="text/css"/>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    </head>

    <body id="page-top" style="width: 100%; height: 100%; margin: 0; padding: 0">

        <div id="mainDiv1">

            <div
                style="position: absolute; left: 35%; top: 15%; width: 30%; height: 50%;">
                <h1 style="color: black; position: absolute; left: 30px;">Register</h1>
                <div
                    style="position: absolute; width: 100%; left: 0; padding: 0; top: 20%;">
                    <form action="register" method="post">

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
                                       name="firstName" id="firstName" placeholder="First Name" />
                            </div>
                            <div style="width: 100%; height: 20px; padding-bottom: 5%">
                                <input style="width: 100%; height: 30px" type="text"
                                       name="lastName" id="lastName" placeholder="LastName"/>
                            </div>
                            <div style="width: 100%; height: 20px; padding-bottom: 5%">
                                <input style="width: 100%; height: 30px" type="text"
                                       name="email" id="email" placeholder="Email"  />                            </div>

                            <div style="width: 100%; height: 20px; padding-bottom: 5%">
                                <input style="width: 100%; height: 30px" type="password"
                                       name="password" id="password" placeholder="Password" />
                            </div>
                            <div style="width: 100%; height: 20px; padding-bottom: 5%">
                                <table>
                                    <tr>
                                        <td style="padding-right: 10px;">
                                            <input type="submit" value="Register" id="login_btn" name="register" style="margin-right:10px"><input type="reset" value="Reset" id="login_btn" name="reset"></td>

                                        <td><a style="padding-right: 10px" href="login.jsp">Login</a></td>
                                    </tr>
                                </table>
                            </div>
                        </fieldset>
                        </form:form>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
