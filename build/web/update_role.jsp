<%-- 
    Document   : update_role
    Created on : Oct 11, 2018, 2:02:47 PM
    Author     : shalini_w
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
                                <div class="row"><h2>UPDATE ROLE</h2></div>
                                <div class="row" style="height: 35px;"></div>

                                <div class="row" style="height: 35px;"></div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-6">
                                        <form class="form-signin" action="UserFunctions" method="post">
                                        <c:forEach var="urole" items="${alldata}">
                                            Role Name :<input type="text" class="form-control" name="role_name" value="${urole.getRolename()}" disabled="true">
                                        </c:forEach>
                                        <div class="row" style="height: 25px;"></div>
                                        <c:forEach var="interfaces" items="${inter}">
                                            <c:forEach var="ib" items="${funcs}" varStatus="func_count">
                                                <c:if test="${interfaces.getI_name()==ib.getI_name()}">
                                                    <div class="dropdown">
                                                        <button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" style="float: left; margin-left: 10px;"><c:out value="${interfaces.getI_name()}"></c:out><span class="caret"></span></button>    
                                                            <ul class="dropdown-menu" style="margin-top: 30px; inline-box-align: inherit;">
                                                            <c:forEach var="permission" items="${data}" varStatus="permission_count">
                                                                <c:if test="${func_count.count == permission_count.count}">
                                                                    <c:if test="${permission == '1'}">
                                                                        <li><input type="checkbox" value="${ib.getIf_id()}" checked="true" name="functions">&nbsp;&nbsp;<c:out value="${ib.getF_name()}"></c:out></li>
                                                                        </c:if>
                                                                        <c:if test="${permission == '0'}">
                                                                        <li><input type="checkbox" value="${ib.getIf_id()}" name="functions">&nbsp;&nbsp;<c:out value="${ib.getF_name()}"></c:out></li>
                                                                        </c:if>
                                                                    </c:if>
                                                                </c:forEach>
                                                        </ul>
                                                    </div>
                                                </c:if>
                                            </c:forEach>   
                                        </c:forEach>
                                        <br><br>
                                        <input type="hidden" name="action" value="update_role">
                                        <button class="btn btn-primary" type="submit" name="Submit" value="Submit" onclick="alertMessage()">Update</button>
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
