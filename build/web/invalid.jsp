<%-- 
    Document   : error
    Created on : Sep 20, 2018, 1:52:07 PM
    Author     : shalini_w
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/resources/css/styles.css" rel="stylesheet">
        <link rel="stylesheet" href="resource/css/header.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        
        <title>Invalid</title>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row" style="height: 100px"></div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <h1 style="font-weight: bold">Something went wrong</h1>
                </div>
                <div class="col-md-4"></div>
            </div>
            <div class="row" style="height: 30px"></div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <center>
                        <a href="<%= request.getContextPath()%>"><input type="button" class="btn btn-danger btn-lg" value="Process Login"></button></a>
                    </center>
                </div>
                <div class="col-md-4"></div>
            </div>
        </div> 
    </body> 
</html>
