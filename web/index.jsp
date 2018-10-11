<%-- 
    Document   : index
    Created on : Sep 17, 2018, 10:37:17 AM
    Author     : shalini_w
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link href="/resources/css/styles.css" rel="stylesheet">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

        <title>Login</title>
    </head>
    <body>
        <div class="container">
            <div class="row" style="height: 150px;"></div>
            <div class="row">
                <div class="col-sm-6 col-md-4 col-md-offset-4">
                    <h1 class="text-center login-title">Sign in</h1><br>
                    <div class="account-wall">
                        <form class="form-signin" action="home" method="post">
                            <input type="text" class="form-control" name="username" placeholder="Username" required autofocus>
                            <div class="row" style="height: 5px;"></div>
                            <input type="password" class="form-control" name="password" placeholder="Password" required>
                            <div class="row" style="height: 25px;"></div>
                            <button class="btn btn-lg btn-primary btn-block" type="submit" name="Submit" value="Submit">Sign in</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
