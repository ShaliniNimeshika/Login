<%-- 
    Document   : user_management
    Created on : Sep 25, 2018, 10:32:35 AM
    Author     : shalini_w
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Management</title>
        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js" ></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css"/>
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
                                    <h1>USER MANAGEMENT</h1>
                                </div>
                                <div class="row" style="height: 40px;"
                                <div class="row">
                                    <div class="col-md-2"></div>
                                    <div class="col-md-8">
                                        <table class="table" id="Table">
                                            <caption><h3>List of Users</h3></caption>
                                            <thead>
                                            <tr>
                                                <th>User ID</th>
                                                <th>User Name</th>
                                                <th>Role</th>
                                                <th>Action</th>                                                
                                            </tr>
                                            </thead>
                                            <tbody>
                                        <c:forEach var="user" items="${users}">
                                            <tr>
                                                <td><c:out value="${user.getUserid()}" /></td>
                                                <td><c:out value="${user.getUsername()}" /></td>
                                                <td><c:out value="${user.getUrole()}" /></td> 
                                                <td>
                                                    <c:forEach var="item" items="${functions}">
                                                        <c:if test="${item.getFunction_name()=='Update'}">
                                                            <form action="user_management" method="post">
                                                                <input type="hidden" name="action" value="${item.getFunction_name()}">
                                                                <input type="hidden" name="userid" value="${user.getUserid()}">
                                                                <button type="submit" class="btn btn-success" value="${item.getFunction_name()}">${item.getFunction_name()}</button>  
                                                            </form>
                                                        </c:if>
                                                        <c:if test="${item.getFunction_name()=='Delete'}">
                                                            <form action="user_management" method="post">
                                                                <input type="hidden" name="action" value="${item.getFunction_name()}">
                                                                <input type="hidden" name="userid" value="${user.getUserid()}">
                                                                <button type="submit" class="btn btn-danger" value="${item.getFunction_name()}">${item.getFunction_name()}</button>  
                                                            </form>
                                                        </c:if>
                                                    </c:forEach>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                            </tbody>
                                    </table>
                                </div>
                                <div class="col-md-2"></div>
                            </div>

                            <div class="row">
                                <div class="col-md-4"></div>
                                <div class="col-md-4">
                                    <div class="row">

                                        <c:forEach var="item" items="${functions}">
                                            <div class="col-md-6">
                                                <c:if test="${item.getFunction_name()=='Add'}">
                                                    <form action="user_management" method="post">
                                                        <input type="hidden" name="action" value="${item.getFunction_name()}">
                                                        <button type="submit" class="btn btn-primary" value="${item.getFunction_name()}">${item.getFunction_name()}</button>  
                                                    </form>
                                                </c:if>


                                                <c:if test="${item.getFunction_name()=='Search'}">
                                                    <form action="user_management" method="post">
                                                        <input type="hidden" name="action" value="${item.getFunction_name()}">
                                                        <button type="submit" class="btn btn-primary" value="${item.getFunction_name()}">${item.getFunction_name()}</button>  
                                                    </form>
                                                </c:if>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="col-md-4"></div>
                            </div>
                        </div>
                    </div>   
                </div>
            </div>
        </div>

    </body>
</html>
<script>
    $(document).ready(function () {
        $('#Table').DataTable();
    });
</script>
