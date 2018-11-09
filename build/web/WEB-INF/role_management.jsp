<%-- 
    Document   : role_management
    Created on : Oct 29, 2018, 11:31:19 AM
    Author     : shalini_w
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home | Role Management</title>
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
                            <h2>ROLE MANAGEMENT</h2>
                        </div>
                        <div class="row" style="height: 50px;"></div>
                        <div class="row">
                            <div class="col-md-2">
                                <form action="user_management" method="post">
                                    <input type="hidden" name="action" value="New Role">
                                    <button type="submit" class="btn btn-primary" value="new_role" style="width: 170px; height: 100px;">ADD USER ROLE</button>  
                                </form>
                            </div>
                            <div class="col-md-2">
                                <form action="user_management" method="post">
                                    <input type="hidden" name="action" value="View Role">
                                    <button type="submit" class="btn btn-primary" value="view_role" style="width: 170px; height: 100px;">VIEW USER ROLES</button>  
                                </form>
                            </div>
                            <div class="col-md-6"></div>
                        </div>
                    </div>
                </div>   
                </div>
            </div>
        </div>
    </body>
</html>

