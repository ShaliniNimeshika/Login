<%-- 
    Document   : menu
    Created on : Sep 19, 2018, 3:43:18 PM
    Author     : shalini_w
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="resource/css/menu.css"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div id="wrapper">
            <!-- Sidebar -->
            <div id="sidebar-wrapper">
                <nav id="spy">
                    <ul class="sidebar-nav nav">
                        <li>
                            <a href="home.jsp" class="solo">
                                <i class="fa fa-dashboard fa-lg"></i>
                                Home
                            </a>
                        </li>
                        <c:forEach var="item" items="${pages}">
                            <li>
                                <form action="${item.getUrl()}" method="post">
                                    <input type="hidden" name="index" value="${item.getInterfaceId()}">
                                    <input type="hidden" name="action" value="${item.getName()}">
                                    
                                    <button class="solo" type="submit" value="${item.getUrl()}"><i class="fa fa-dashboard fa-lg"></i>${item.getName()}</button>
                                        
<!--                                    <a href="<c:url value="${item.getUrl()}"></c:url>" class="solo"><i class="fa fa-dashboard fa-lg"></i>
                                        <c:out value="${item.getName()}"></c:out>
                                    </a>-->

                                </form>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </div>
        </div>


    </body>
</html>
