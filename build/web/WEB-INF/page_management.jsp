<%-- 
    Document   : page_management
    Created on : Oct 29, 2018, 1:16:04 PM
    Author     : shalini_w
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home | Page Management</title>
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
                                <h2>PAGE MANAGEMENT</h2>
                            </div>
                            <div class="row" style="height: 50px;"></div>
                            <div class="row">
                                <div class="col-md-2">
                                    <form action="user_management" method="post">
                                        <input type="hidden" name="action" value="New Interface">
                                        <button type="submit" class="btn btn-primary" value="new_interface" style="width: 170px; height: 100px;">ADD INTERFACE</button>  
                                    </form>
                                </div>
                                <div class="col-md-2">
                                    <form action="user_management" method="post">
                                        <input type="hidden" name="action" value="View_Interface">
                                        <button type="submit" class="btn btn-primary" value="view_page" style="width: 170px; height: 100px;">VIEW PAGE INFO</button>  
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


