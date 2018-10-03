<%-- 
    Document   : update_user
    Created on : Sep 27, 2018, 8:25:13 AM
    Author     : shalini_w
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update User</title>
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
                                    <h2>USER MANAGEMENT >> UPDATE USER</h2>
                                </div>
                                <div class="row" style="height: 95px;"></div>
                                <div class="row">
                                    <div class="col-md-2"></div>
                                    <div class="col-md-5">

                                    <c:forEach var="user" items="${data}">
                                        <form class="form-signin" action="UserFunctions" method="post">
                                            <input type="text" class="form-control" name="username" value="${user.getUsername()}" required autofocus>
                                            <div class="row" style="height: 10px;"></div>
                                            <input type="password" class="form-control" name="password" value="${user.getPassword()}" required>
                                            <div class="row" style="height: 10px;"></div>
                                            <c:forEach var="role" items="${roles}">
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <c:choose>
                                                    <c:when test="${user.getUrole()==role.getRolename()}">
                                                        <input type="radio" name="role" checked="true" value="${role.getRoleid()}">
                                                        <c:out value="${role.getRolename().toUpperCase()}"></c:out>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" name="role" value="${role.getRoleid()}">
                                                        <c:out value="${role.getRolename().toUpperCase()}"></c:out>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                            <input type="hidden" name="action" value="update_user">
                                            <input type="hidden" name="userid" value="${user.getUserid()}">
                                            <div class="row" style="height: 70px;"></div>
                                            <button class="btn btn-lg btn-primary btn-block" type="submit" name="Submit" value="Submit">Update</button>
                                        </form>
                                    </c:forEach>

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
