<%-- 
    Document   : withdraw
    Created on : Sep 18, 2018, 1:57:18 PM
    Author     : shalini_w
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Withdraw</title>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-4">
                    <jsp:include page="resource/menu.jsp"></jsp:include>
                </div>
                <div class="col-md-8">
                    <jsp:include page="resource/header.jsp"></jsp:include>
                    <h1>WITHDRAW</h1>
                    <br>
                        <c:forEach var="item" items="${functions}">
                            <h3>    
                                <input type="button"  name="<c:url value="${item.getFunction_url()}?index=${item.getFunction_id()}"></c:url>" value="${item.getFunction_name()}"></input>  
                            </h3>
                        </c:forEach>
                </div>
            </div>
        </div>
    </body>
</html>
