<%-- 
    Document   : password_reset
    Created on : Oct 9, 2018, 4:15:00 PM
    Author     : shalini_w
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
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
                                    <h2>Reset Password</h2>
                                </div>
                                <div class="row" style="height: 95px;"></div>
                                <div class="row">
                                    <div class="col-md-2"></div>
                                    <div class="col-md-5">
                                        <form class="form-signin" action="reset_password" method="post">

                                            <input type="password" class="form-control" id="curPassword" name="cur_password" placeholder="Current Password" minlength="6" required>
                                            <div class="row" style="height: 10px;"></div>
                                            <input type="password" class="form-control" id="newPassword" name="new_password" placeholder="New Password" minlength="6" required>
                                            <div class="row" style="height: 10px;"></div>
                                            <input type="password" class="form-control" id="ConfirmPassword" name="re_password" placeholder="Re-type New Password" minlength="6" required>
                                            <div class="row" style="height: 25px;"></div>

                                            <input type="hidden" name="action" value="reset_pwd">
                                            <input type="hidden" name="username" value="${username}">
                                            <input type="hidden" id="DBpwd" name="passwordDB" value="${password}">
                                        <button class="btn btn-lg btn-primary btn-block" type="submit" name="Submit" value="Submit" onclick="return Validate()">Register</button>
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
<script type="text/javascript">
    function Validate() {
        var password = document.getElementById("newPassword").value;
        var confirmPassword = document.getElementById("ConfirmPassword").value;
        var DBpassword = document.getElementById("DBpwd").value;
        var curPassword = document.getElementById("curPassword").value;
        if (password !== confirmPassword) {
            alert("New and Confirm Passwords do not match.");
            return false;
        }
        if (DBpassword !== curPassword) {
            alert("Current Password is wrong!");
            return false;
        }
        if (DBpassword == password) {
            alert("New password cannot be Current password!");
            return false;
        }
        return true;
    }
</script>
