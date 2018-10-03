<%-- 
    Document   : new_page
    Created on : Oct 1, 2018, 1:52:29 PM
    Author     : shalini_w
--%>

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
                                    <h1>Add New Page</h1>
                                </div>
                                <div class="row" style="height: 75px;"></div>
                                <div class="row">
                                    <div class="col-md-2"></div>
                                    <div class="col-md-7">
                                        <form class="form-signin" action="UserFunctions" method="post">
                                            <table class="table">
                                                <tr>
                                                    <td>New Page Name&nbsp;&nbsp;</td> 
                                                    <td><input type="text" name="pagename" required autofocus style="width: 300px;"></td>
                                                </tr>
                                                <tr style="width: 10px;"></tr>
                                                <tr>
                                                    <td>New Page URL&nbsp;&nbsp;</td>
                                                    <td><input type="text" name="pageurl" required style="width: 300px;"></td>
                                                </tr>
                                                <tr style="width: 10px;"></tr>
                                                <tr>
                                                    <td>New Page Description&nbsp;&nbsp;</td>
                                                    <td><input type="text" name="pagedesc" required style="width: 300px;"></td>
                                                </tr>
                                                <tr style="width: 10px;"></tr>
                                                <tr>
                                                    <td>Select Privilages&nbsp;&nbsp;</td>
                                                    <td>
                                                        <ul style="list-style: none;">
                                                        <c:forEach var="item" items="${functions}">
                                                            <li><input type="checkbox" value="${item.getFunction_id()}" name="functions">&nbsp;&nbsp;<c:out value="${item.getFunction_name()}"></c:out></li>
                                                            </c:forEach>
                                                    </ul>
                                                </td>
                                            </tr>
                                        </table>

                                        <div class="row" style="height: 25px;"></div>

                                        <input type="hidden" name="action" value="new_page">
                                        <button class="btn btn-lg btn-primary btn-block" type="submit" name="Submit" value="Submit" style="width: 100px;">Save</button>
                                    </form>
                                </div>
                                <div class="col-md-3"></div>
                            </div>
                            <div class="row" style="height: 75px;"></div>
                            <div class="row">
                                <div class="col-md-1"></div>
                                <div class="col-md-10">
                                    <div class="row" style="height: 45px;"></div>
                                    <div class="row">
                                        <table class="table" id="Table">
                                            <caption><h3>Page Details</h3></caption>
                                            <thead>
                                                <tr>
                                                    <th>Page ID</th>
                                                    <th>Page Name</th>
                                                    <th>Assigned Functions</th>
                                                    <th>Action</th>                                                
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="page" items="${inter}">
                                                    <tr>
                                                        <td><c:out value="${page.getI_id()}" /></td>
                                                        <td><c:out value="${page.getI_name()}" /></td>
                                                        <td>
                                                            <c:forEach var="f" items="${page.getFbean()}">
                                                                
                                                                <c:out value="${f.getFunction_name()}"></c:out>&nbsp;&nbsp;
                                                            </c:forEach>
                                                        </td> 
                                                        <td>
                                                            <form  action="UserFunctions" method="post">
                                                                <input type="hidden" name="action" value="update_function">
                                                                <input type="hidden" name="pageid" value="${page.getI_id()}">
                                                                <button type="submit" class="btn btn-success" value="${page.getI_id()}">Update</button>  
                                                            </form>

                                                            <form action="UserFunctions" method="post">
                                                                <input type="hidden" name="action" value="delete_page">
                                                                <input type="hidden" name="pageid" value="${page.getI_id()}">
                                                                <button type="submit" class="btn btn-danger" value="${page.getI_id()}">Delete Page</button>  
                                                            </form>

                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="col-md-1"></div>
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