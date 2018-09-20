<%-- 
    Document   : header
    Created on : Sep 19, 2018, 3:42:54 PM
    Author     : shalini_w
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/resources/css/styles.css" rel="stylesheet">
        <link rel="stylesheet" href="resource/css/header.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    </head>
    <body>
        <c:choose>
            <c:when test="${uname != null}">
                <div class="row"></div>
                <div class="row">
                    <div class="col-md-9"></div>
                    <div class="col-md-2">
                        <h5>Welcome ${uname.toUpperCase()}!</h5>
                    </div>
                    <div class="col-md-1">
                        <span class="pull-right btn btn-danger btn-sm btn-logout" style="margin-top: 5px; margin-bottom: 5px; margin-right: 10px;"><a href="<%= request.getContextPath()%>/LogoutServlet">Logout</a></span> 
                    </div>
                </div>  
            </c:when>
            <c:when test="${uname == null}">
                <jsp:include page="invalid.jsp"></jsp:include>
            </c:when>
        </c:choose>
        
    </body>
</html>
