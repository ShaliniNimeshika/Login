<%-- 
    Document   : add_role
    Created on : Sep 27, 2018, 1:26:19 PM
    Author     : shalini_w
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Role</title>
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
                                    <h2>ADD NEW ROLE</h2>
                                </div>
                                <div class="row" style="height: 35px;"></div>
                                <div class="row">
                                    <div class="col-md-2"></div>
                                    <div class="col-md-5">
                                        <div class="row">
                                            <table class="table">
                                                <caption><h3>List of Roles</h3></caption>
                                                <tr>
                                                    <th>Role ID</th>
                                                    <th>Role Name</th>
                                                </tr>
                                            <c:forEach var="role" items="${roles}">
                                                <tr>
                                                    <td><c:out value="${role.getRoleid()}"></c:out></td>
                                                    <td><c:out value="${role.getRolename()}"></c:out></td>
                                                    </tr>
                                            </c:forEach>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="col-md-5"></div>
                                </div>
                                <div class="row" style="height: 35px;"></div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-6">
                                        <form class="form-signin" action="UserFunctions" method="post">
                                            Insert New Role Name :<input type="text" class="form-control" name="role_name" placeholder="Role Name" required autofocus>
                                            <div class="row" style="height: 10px;"></div>
                                            <c:forEach var="item" items="${funcs}">
                                                <br>
                                                <input type="checkbox" name="func" value="${item.getIf_id()}">
                                                <c:out value="${item.getI_name()}"></c:out>&nbsp;-&nbsp;<c:out value="${item.getF_name()}"></c:out>
                                            </c:forEach>
                                                <br><br>
                                                <input type="hidden" name="action" value="add_role">
                                            <button class="btn btn-primary" type="submit" name="Submit" value="Submit">Submit</button>
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
