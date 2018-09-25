<%-- 
    Document   : add_user
    Created on : Sep 25, 2018, 10:43:38 AM
    Author     : shalini_w
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New User</title>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2">
                    <jsp:include page="resource/menu.jsp"></jsp:include>
                    </div>
                    <div class="col-md-10">
                        <div class="row back">
                        <jsp:include page="resource/header.jsp"></jsp:include>
                        </div>

                        <div class="row">
                            <div class="col-md-1"></div>
                            <div class="col-md-11">
                                <div class="row">
                                    <h2>USER MANAGEMENT >> ADD USER</h2>
                                </div>
                                <div class="row" style="height: 95px;"></div>
                                <div class="row">
                                    <div class="col-md-2"></div>
                                    <div class="col-md-5">
                                        <form class="form-signin" action="add_user" method="post">
                                            
                                            <input type="text" class="form-control" name="username" placeholder="Username" required autofocus>
                                            <div class="row" style="height: 10px;"></div>
                                            <input type="password" class="form-control" name="password" placeholder="Password" required>
                                            <div class="row" style="height: 10px;"></div>
                                            <input type="radio" name="role" value="1">Admin &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <input type="radio" name="role" value="2" checked="true">User
                                        <c:forEach var="role" items="${roles}">
                                            <input type="radio" name="role" value="${role.getRolename()}"><c:out value="${role.getRolename().toUpperCase()}"></c:out>
                                        </c:forEach>
                                        <!--                                        <input type="radio" name="role" value="admin">Admin &nbsp;&nbsp;&nbsp;&nbsp;
                                                                                <input type="radio" name="role" value="user" checked="true">User-->
                                        <div class="row" style="height: 25px;"></div>
                                        <button class="btn btn-lg btn-primary btn-block" type="submit" name="Submit" value="Submit">Register</button>
                                    </form>
                                </div>
                                <div class="col-md-5"></div>
                            </div>
                        </div>
                    </div>   
                </div>
            </div>
        </div>
    </body>
</html>
