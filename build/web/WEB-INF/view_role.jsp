<%-- 
    Document   : view_role
    Created on : Oct 26, 2018, 2:52:23 PM
    Author     : shalini_w
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
        <script src="js/multiselect.js"></script>

        <title>View Role</title>
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


                        <div class="col-md-1"></div>


                        <h2>ROLE MANAGEMENT >> VIEW ROLES</h2>

                    <div class="row" style="height: 35px;"></div>
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-10">
                            <div class="row">
                                <h3>List of Roles >></h3><br>
                                <table class="table">
                                    <!--<caption><h3>List of Roles</h3></caption>-->
                                    <tr>
                                        <th>Role ID</th>
                                        <th>Role Name</th>
                                        <th>Accessible Interface & Functions</th>
                                        <th>Action</th>
                                    </tr>
                                    <c:forEach var="role" items="${alldata}">
                                        <tr>
                                            <td><c:out value="${role.getRoleid()}"></c:out></td>
                                            <td><c:out value="${role.getRolename()}"></c:out></td>
                                                <td>
                                                    <table>
                                                    <c:forEach var="interfaces" items="${inter}">

                                                        <tr>
                                                            <td><strong><c:out value="${interfaces.getI_name()}"></c:out></strong>&nbsp;&nbsp;&nbsp;</td>
                                                                <td>
                                                                <c:forEach var="intf" items="${role.getRa_bean()}">
                                                                    <c:if test="${intf.getI_name()==interfaces.getI_name()}">
                                                                        <c:out value="${intf.getF_name()}"></c:out>&nbsp;&nbsp;&nbsp;
                                                                    </c:if>  
                                                                </c:forEach> 
                                                            </td>
                                                        </tr>  
                                                    </c:forEach>
                                                </table>
                                            </td>
                                            <td style="width: 160px;">
                                                <div class="pull-left">
                                                    <form  action="user_management" method="post">
                                                        <input type="hidden" name="action" value="update_role">
                                                        <input type="hidden" name="roleid" value="${role.getRoleid()}">
                                                        <button type="submit" class="btn btn-success" value="${role.getRoleid()}" style="width: 70px; height: 30px; font-size: 12px;" pull-right>Update</button>  
                                                    </form>
                                                </div>
                                                <div class="pull-right">
                                                    <form  action="UserFunctions" method="post" >
                                                        <input type="hidden" name="action" value="delete_role">
                                                        <input type="hidden" name="roleid" value="${role.getRoleid()}">
                                                        <button type="submit" class="btn btn-danger" value="${role.getRoleid()}" style="width: 70px; height: 30px; font-size: 12px;">Delete</button>  
                                                    </form>
                                                </div>
                                            </td>

                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                </div>
            </div>

        </div>
    </body>
</html>


