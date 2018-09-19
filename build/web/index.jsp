<%-- 
    Document   : index
    Created on : Sep 17, 2018, 10:37:17 AM
    Author     : shalini_w
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <center>
            <form action="home" method="post">
                <table>
                    <tr>
                        <td>Username :</td>
                        <td><input type="text" name="username" placeholder="Username"></td>
                    </tr>
                    <tr>
                        <td>Password :</td>
                        <td><input type="password" name="password" placeholder="Password"></td>
                    </tr>
                </table>
                <input type="submit" name="Submit" value="Submit">
            </form>
        </center>
    </body>
</html>
