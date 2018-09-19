<%-- 
    Document   : transfer
    Created on : Sep 18, 2018, 1:56:44 PM
    Author     : shalini_w
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transfer</title>
    </head>
    <body>
        <jsp:include page="resource/menu.jsp"></jsp:include>
        <center><h1>TRANSFER</h1>
            <br>
            
            <c:forEach var="item" items="${functions}">
                <h3>    
                    <input type="button"  name="<c:url value="${item.getFunction_url()}?index=${item.getFunction_id()}"></c:url>" value="${item.getFunction_name()}"></input>  
                </h3>
            </c:forEach>
        </center>
    </body>
</html>
