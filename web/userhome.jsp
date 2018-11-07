<%-- 
    Document   : userhome
    Created on : 1 Nov, 2018, 11:54:25 PM
    Author     : 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="resources/js/easydressup.js" type="text/javascript"></script>
<div id="mainDiv">
    <div style="top:10%;width:15%;height: 50%;position: absolute;left:0;">
        <ul >
            <c:forEach var="category" items="${categories}" >
                <li class="dropbtn"onclick="toogleMenu(event)" id="<c:out value="${category.key}"></c:out>"><c:out value="${category.key}"></c:out></li>
                <div  id="<c:out value="subCat_${category.key}"></c:out>" class="dropdown-content">
                        <ul>
                        <c:forEach var="childCategory" items="${category.value}">
                            <li><a style="cursor: pointer" href="category?parent=<c:out value="${childCategory.parent.categoryName}"></c:out>&subcat=<c:out value="${childCategory.categoryId}"></c:out>"><c:out value="${childCategory.categoryName}"></c:out></a>
                                </li>
                        </c:forEach>
                    </ul>
                </div>
            </c:forEach>
        </ul>
    </div>
</div>