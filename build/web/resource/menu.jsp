<%-- 
    Document   : menu
    Created on : Sep 19, 2018, 3:43:18 PM
    Author     : shalini_w
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
    </head>
    <body>
        <a href="home.jsp">Home</a><br>
        <h2>Menu</h2>
        <c:forEach var="item" items="${pages}">
            <br>
            <h3>    
                <c:out value="${item.getInterfaceId()}"></c:out> : 
                        <a href="<c:url value="${item.getUrl()}?index=${item.getInterfaceId()}"></c:url>"> 
                            <c:out value="${item.getName()}"></c:out>
                        </a>
            </h3>
        </c:forEach>
    </body>
</html>
