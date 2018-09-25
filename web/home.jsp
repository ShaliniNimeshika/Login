<%-- 
    Document   : home
    Created on : Sep 17, 2018, 10:53:17 AM
    Author     : shalini_w
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        
        
    </head>
    
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
                            <h1>Home page</h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    

