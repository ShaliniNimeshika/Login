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
                            
                            <div class="row">
                                <div class="col-md-2"></div>
                                <div class="col-md-6">
                                    <table border="1" cell-padding="15" style="border: 1px solid black; width:100%">
                                        <caption><h3>List of Users</h3></caption>
                                        <tr>
                                            <th>User ID</th>
                                            <th>User Name</th>
                                            <th>Role</th>
                                        </tr>
                                        
                                    <c:forEach var="user" items="${users}">
                                        <tr>
                                            <td><c:out value="${user.getUserid()}" /></td>
                                            <td><c:out value="${user.getUsername()}" /></td>
                                            <td><c:out value="${user.getUrole()}" /></td> 
                                        </tr>
                                    </c:forEach>
                                        
                                    </table>
                                </div>
                                <div class="col-md-4"></div>
                            </div>
                            
                            <div class="row">
                                <div class="col-md-4"></div>
                                <div class="col-md-4">
                                    <div class="row">
                                        <c:forEach var="item" items="${functions}">
                                            <div class="col-md-3">
                                                <h3> 
                                                    <a href="<c:url value="${item.getFunction_url()}.jsp"></c:url>">   
                                                        <input type="button" class="btn btn-primary" value="${item.getFunction_name()}"></input>  
                                                    </a>
                                                </h3>
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
