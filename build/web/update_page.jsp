<%-- 
    Document   : update_page
    Created on : Oct 3, 2018, 9:21:26 AM
    Author     : shalini_w
--%>

<%@page import="com.sun.org.apache.bcel.internal.generic.BREAKPOINT"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
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
                                    <h1>Update Page</h1>
                                </div>
                                <div class="row" style="height: 75px;"></div>
                                <div class="row">
                                    <div class="col-md-2"></div>
                                    <div class="col-md-7">
                                    <c:forEach var="iface" items="${interface_bean}">
                                        <form class="form-signin" action="UserFunctions" method="post">
                                            <table class="table">
                                                <tr>
                                                    <td>Page Name&nbsp;&nbsp;</td> 

                                                    <td><input type="text" class="form-control" name="pagename" value="${iface.getI_name()}" required autofocus style="width: 300px;"></td>
                                                </tr>
                                                <tr style="width: 10px;"></tr>
                                                <tr>
                                                    <td>Page URL&nbsp;&nbsp;</td>
                                                    <td><input type="text" class="form-control" name="pageurl" value="${iface.getI_url()}" required style="width: 300px;" ></td>
                                                </tr>
                                                <tr style="width: 10px;"></tr>
                                                <tr>
                                                    <td>Page Description&nbsp;&nbsp;</td>
                                                    <td><input type="text" class="form-control" name="pagedesc" value="${iface.getDesc()}" required style="width: 300px;"></td>
                                                </tr>
                                                <tr style="width: 10px;"></tr>
                                                <tr>
                                                    <td>Privilages&nbsp;&nbsp;</td>
                                                    <td>
                                                        <ul style="list-style: none;">
                                                            <c:forEach var="item" items="${functions}" varStatus="functionsCount">
                                                                <c:forEach var="func" items="${func_values}" varStatus="valueCount">

                                                                    <c:if test="${functionsCount.count == valueCount.count}">
                                                                        <c:if test="${func == '1'}">
                                                                            <li><input type="checkbox" checked="true" value="${item.getFunction_id()}" name="functions">&nbsp;&nbsp;<c:out value="${item.getFunction_name()}"></c:out></li>
                                                                        </c:if>

                                                                        <c:if test="${func == '0'}">
                                                                            <li><input type="checkbox" value="${item.getFunction_id()}" name="functions">&nbsp;&nbsp;<c:out value="${item.getFunction_name()}"></c:out></li>

                                                                        </c:if>
                                                                    </c:if>
                                                                </c:forEach>   
                                                            </c:forEach>
                                                        </ul>
                                                    </td>
                                                </tr>
                                            </table>

                                            <div class="row" style="height: 25px;"></div>

                                            <input type="hidden" name="action" value="update_interface">
                                            <button class="btn btn-lg btn-primary btn-block" type="submit" name="Submit" value="Submit" style="width: 100px;">Update</button>
                                        </form>
                                    </c:forEach>
                                </div>
                                <div class="col-md-3"></div>
                            </div>                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
<script>
    $(document).ready(function () {
        $('#Table').DataTable();
    });
</script>
