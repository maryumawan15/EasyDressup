<%-- 
    Document   : home
    Created on : 31 Oct, 2018, 5:47:40 PM
    Author     : 
--%>
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
    </head>

    <body id="page-top" style="width: 100%; height: 100%; margin: 0; padding: 0">
        <jsp:include page="header.jsp"/>
        <div id="content" style="width: 100%;height: 100%;">
            <div id="menu" style="width:30%;height: 40%">
               
            </div>

        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
