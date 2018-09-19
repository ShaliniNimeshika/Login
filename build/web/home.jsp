<%-- 
    Document   : home
    Created on : Sep 17, 2018, 10:53:17 AM
    Author     : shalini_w
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
    <center><h1>Welcome ${uname.toUpperCase()}!</h1></center>
       
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
