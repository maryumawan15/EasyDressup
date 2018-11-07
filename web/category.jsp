<%-- 
    Document   : category
    Created on : 4 Nov, 2018, 11:16:27 PM
    Author     : 
--%>

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
        <script type="text/javascript">
            var modal = document.getElementById('modal');
            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }

        </script>
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
                                    <b> <a href="profile" style="color:white;text-decoration: none;"><%
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
        <c:choose>
            <c:when test="${'admin'==user.role}">
                <jsp:include page="adminhome.jsp"/>
            </c:when>
            <c:otherwise>
                <div style="top:10%;width:15%;height: 50%;position: absolute;left:0;">
                    <%
                        String parent = request.getParameter("parent");
                        int subcat = Integer.parseInt(request.getParameter("subcat"));

                    %>
                    <ul >
                        <c:forEach var="category" items="${categories}" >
                            <li class="dropbtn"onclick="toogleMenu(event)" id="<c:out value="${category.key}"></c:out>"><c:out value="${category.key}"></c:out></li>
                                <c:choose>
                                    <c:when test="${category.key==param.parent}">
                                    <div  id="<c:out value="subCat_${category.key}"></c:out>" class="dropdown-content show">
                                            <ul>
                                            <c:forEach var="childCategory" items="${category.value}">
                                                <c:choose>
                                                    <c:when test="${childCategory.categoryId==param.subcat}">
                                                        <li><a style="background-color: black;color:white;cursor: pointer" href="category?parent=<c:out value="${childCategory.parent.categoryName}"></c:out>&subcat=<c:out value="${childCategory.categoryId}"></c:out>"><c:out value="${childCategory.categoryName}"></c:out></a>
                                                            </li>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <li><a style="cursor: pointer" href="category?parent=<c:out value="${childCategory.parent.categoryName}"></c:out>&subcat=<c:out value="${childCategory.categoryId}"></c:out>"><c:out value="${childCategory.categoryName}"></c:out></a>
                                                            </li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <ul>
                                        <c:forEach var="childCategory" items="${category.value}">
                                            <li><a style="cursor: pointer" href="category?parent=<c:out value="${childCategory.parent.categoryName}"></c:out>&subcat=<c:out value="${childCategory.categoryId}"></c:out>"><c:out value="${childCategory.categoryName}"></c:out></a>
                                                </li>
                                        </c:forEach>

                                    </ul>

                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </ul>
                </div>
            </c:otherwise>
        </c:choose>
        <div style="position: absolute;left: 20%;top:10%">
            <form action="upload" method="post" enctype = "multipart/form-data">
                <input type="hidden" value="<% out.print(request.getParameter("subcat"));%>" name="categoryId">
                <b>Choose File:</b> <input type="file" accept="image/*" name="clothImage" >
                <input type="submit" value="Upload"id="upload_btn"/>
            </form>
            <div id="messages"   style="margin-top: 3%">
                <c:choose>
                    <c:when test="${not empty message }">
                        <p class="alert ${messageClass}">${message }</p>
                        <%
                            session.setAttribute("message", null);
                            session.setAttribute("messageClass", null);
                        %>
                    </c:when>
                </c:choose>


                <table  style="margin-top: 3%">
                    <c:forEach var="clothId" items="${clothIds}" varStatus="loop">
                        <c:if test="${loop.index%5==0}"><tr></c:if>
                                <td><div style="border:1px solid black;width: 200px">
                                        <img  height="150" width="150" style="padding:10px" src="image?clothId=${clothId}">
                                    <div style="margin-left: 20%"> <a href="#" style="margin-right: 10%;" onclick="showModal('${clothId}');">Update</a><a href="removeCloth?clothId=${clothId}" onclick="return confirm('Do you want delete the cloth?')">Delete<a></a>
                                    </div> </div></td>
                            <c:if test="${loop.index!=0 && loop.index%5==0}"></tr></c:if>
                        </c:forEach>
                </table>
            </div> 
        </div>
        <div id="modal"  class="modal ">
            <!-- Modal content -->
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close" onclick="closeModal();">&times;</span>
                    <h2>Upload Image</h2>
                </div>

                <form action="updateCloth" method="post" enctype = "multipart/form-data">
                    <input type="hidden" id="update_clothId" name="clothId">
                    <b>Choose File:</b> <input type="file" accept="image/*" name="clothImage" >
                    <input type="submit" value="Upload" id="update_cloth_btn"/>
                </form>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
