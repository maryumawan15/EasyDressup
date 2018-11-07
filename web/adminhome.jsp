<%-- 
    Document   : adminhome
    Created on : 1 Nov, 2018, 11:54:14 PM
    Author     : 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="mainDiv">
    <div style="top:10%;width: 30%;height: 30%;position: absolute;left: 2%;">
        <form action="addCategory" method="post">
            <div id="messages">
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
            <table>
                <tr style="padding-bottom: 10px">
                    <td>
                        <label><b>Parent Category</b></label>
                    </td>
                    <td>
                        <select name="parentCategory"style="padding-left: 10px;height: 30px;width:300px">
                            <option value="-1">Select Category</option>
                            <c:forEach var="category" items="${categories}">
                                <c:if test="${category.parent==null}">
                                    <option value="${category.categoryId}"><c:out value="${category.categoryName}"></c:out></option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label><b>Child Category:</b></label></td>
                    <td><input style="height: 30px;width:300px" type="text" name="categoryName"></td>
                </tr>
                <tr><td><input type="submit" value="Add Category" id="add_cat"></td></tr>
            </table>
        </form> 
    </div>

    <div style="top:10%;position: absolute;left: 35%;width: 60%;height: 60%;">
        <table id="categories">
            <thead>
                <tr>
                    <th>Category Name</th>
                    <th>Parent Category</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="category" items="${categories}" >
                    <tr>
                        <td><c:out value="${category.categoryName}" /></td>
                        <td>
                            <c:choose>

                                <c:when test="${null!=category.parent}">
                                    <c:out value="${category.parent.categoryName}" /></td>
                                </c:when>
                                <c:otherwise>
                                    <c:out value=""></c:out>

                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>