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
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-4">
                    <jsp:include page="resource/menu.jsp"></jsp:include>
                </div>
                <div class="col-md-8">
                    <jsp:include page="resource/header.jsp"></jsp:include>
                    <p>Home page</p>
                </div>
            </div>
        </div>
    </body>
</html>
