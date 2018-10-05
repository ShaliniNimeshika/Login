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
                                        <form class="form-signin" action="UserFunctions" method="post">

                                            <input type="text" class="form-control" name="username" placeholder="Username" required autofocus>
                                            <div class="row" style="height: 10px;"></div>
                                            <input type="password" class="form-control" name="password" placeholder="Password" minlength="6" required>
                                            <div class="row" style="height: 10px;"></div>
                                            <c:forEach var="role" items="${roles}">
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <input type="radio" name="role" value="${role.getRoleid()}"><c:out value="${role.getRolename().toUpperCase()}"></c:out>
                                            </c:forEach>
                                            <div class="row" style="height: 25px;"></div>

                                            <input type="hidden" name="action" value="add_user">
                                            <button class="btn btn-lg btn-primary btn-block" type="submit" name="Submit" value="Submit">Register</button>
                                        </form>
                                    </div>
                                    <div class="col-md-5"></div>
                                </div>
                                <div class="row" style="height: 50px;"></div>
                            <c:if test="${roleid == 1}">
                                <div class="row">
                                    <form action="user_management" method="post">
                                        <input type="hidden" name="action" value="New Role">
                                        <button type="submit" class="btn btn-primary" value="new_role">ADD NEW USER ROLE</button>  
                                    </form>
                                </div>
                                <div class="row" style="height: 25px;"></div>
                                <div class="row">
                                    <form action="user_management" method="post">
                                        <input type="hidden" name="action" value="New Interface">
                                        <button type="submit" class="btn btn-primary" value="new_interface">ADD NEW INTERFACE</button>  
                                    </form>
                                </div>
                            </c:if>
                        </div>
                    </div>   
                </div>
            </div>
        </div>
    </body>
</html>
