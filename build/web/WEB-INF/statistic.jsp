<%-- 
    Document   : statistic
    Created on : Sep 18, 2018, 1:57:56 PM
    Author     : shalini_w
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Statistics</title>
        
        
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
                                <h1>STATISTICS</h1>
                            </div>
                    
                            <div class="row">
                                <div class="col-md-4"></div>
                                <div class="col-md-4">
                                    <div class="row">
                                        <c:forEach var="item" items="${functions}">
                                            <div class="col-md-3">
                                                <form action="user_management" method="post">
                                                    <input type="hidden" name="action" value="${item.getFunction_name()}">
                                                    <button type="submit" class="btn btn-primary" value="${item.getFunction_name()}">${item.getFunction_name()}</button>  
                                                </form>
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
