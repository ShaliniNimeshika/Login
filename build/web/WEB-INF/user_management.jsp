<%-- 
    Document   : user_management
    Created on : Sep 25, 2018, 10:32:35 AM
    Author     : shalini_w
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home | User Management</title>
        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <!--                <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
                        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js" ></script>-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <!--<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css"/>-->
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>-->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
                                    <h1>USER MANAGEMENT</h1>
                                <% String message = (String) request.getAttribute("msg");%>
                            </div>
                            <div class="row" style="height: 40px;"
                                 <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-9">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="dropdown">
                                                    <button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" style="float: left;">
                                                        <c:out value="Select Entries"></c:out><span class="caret"></span></button>
                                                        <ul class="dropdown-menu" style="margin-top: 30px; inline-box-align: inherit;">    
                                                            <li>
                                                                <form method="POST" action="user_management">
                                                                    <input type="hidden" name="recordsPerPage" value="3"/>
                                                                    <input type="hidden" name="action" value="rows"/>
                                                                    <input type="hidden" name="index" value="1"/>
                                                                    <button type="submit" class="btn btn-primary" style="width: 100%;">3</button>
                                                                </form>  
                                                            </li>
                                                            <li>
                                                                <form method="POST" action="user_management">
                                                                    <input type="hidden" name="recordsPerPage" value="5"/>
                                                                    <input type="hidden" name="action" value="rows"/>
                                                                    <input type="hidden" name="index" value="1"/>
                                                                    <button type="submit" class="btn btn-primary" style="width: 100%;">5</button>
                                                                </form>  
                                                            </li>
                                                            <li>
                                                                <form method="POST" action="user_management">
                                                                    <input type="hidden" name="recordsPerPage" value="10"/>
                                                                    <input type="hidden" name="action" value="rows"/>
                                                                    <input type="hidden" name="index" value="1"/>
                                                                    <button type="submit" class="btn btn-primary" style="width: 100%;">10</button>
                                                                </form>  
                                                            </li>
                                                            <li>
                                                                <form method="POST" action="user_management">
                                                                    <input type="hidden" name="recordsPerPage" value="20"/>
                                                                    <input type="hidden" name="action" value="rows"/>
                                                                    <input type="hidden" name="index" value="1"/>
                                                                    <button type="submit" class="btn btn-primary" style="width: 100%;">20</button>
                                                                </form>  
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                <c:forEach var="item" items="${functions}">
                                                    <c:if test="${item.getFunction_name()=='Search'}">
                                                        <form action="user_management" method="post">
                                                            <input type="text" name="searching" placeholder="Search by name...">
                                                            <input type="hidden" name="action" value="${item.getFunction_name()}">
                                                            <button type="submit" class="btn btn-sm btn-primary" value="${item.getFunction_name()}">${item.getFunction_name()}</button>  
                                                        </form>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>




                                        <table class="table" id="Table">
                                            <caption><h3>List of Users</h3></caption>
                                            <thead>
                                                <tr>
                                                    <th>User ID</th>
                                                    <th>User Name</th>
                                                    <th>Role</th>
                                                    <th>Status</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="user" items="${users}">
                                                    <tr>
                                                        <td><c:out value="${user.getUserid()}" /></td>
                                                        <td><c:out value="${user.getUsername()}" /></td>
                                                        <td><c:out value="${user.getUrole()}" /></td> 
                                                        <td>
                                                            <c:if test="${user.getActive()=='1'}">Active</c:if>
                                                            <c:if test="${user.getActive()=='0'}">Deactive</c:if>
                                                            </td>
                                                            <td>
                                                            <c:forEach var="item" items="${functions}">
                                                                <c:if test="${item.getFunction_name()=='Update'}">
                                                                    <div class="pull-left" style="margin-right: 10px;">
                                                                        <form action="user_management" method="post">
                                                                            <input type="hidden" name="action" value="${item.getFunction_name()}">
                                                                            <input type="hidden" name="userid" value="${user.getUserid()}">
                                                                            <button type="submit" class="btn btn-success" style="width: 70px; height: 30px; font-size: 12px;" value="${item.getFunction_name()}">${item.getFunction_name()}</button>  
                                                                        </form>
                                                                    </div>
                                                                </c:if>
                                                                <c:if test="${item.getFunction_name()=='Delete'}">
                                                                    <div class="pull-left">
                                                                        <form action="user_management" method="post">
                                                                            <input type="hidden" name="action" value="${item.getFunction_name()}">
                                                                            <input type="hidden" name="userid" value="${user.getUserid()}">
                                                                            <button type="submit" class="btn btn-danger" style="width: 70px; height: 30px; font-size: 12px;" onclick="return confirm('Are you sure you want to delete user : ${user.getUsername()} ?')" value="${item.getFunction_name()}">${item.getFunction_name()}</button>  
                                                                        </form>
                                                                    </div>
                                                                </c:if>
                                                            </c:forEach>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                            <tfoot>
                                                <tr>
                                                    <th>User ID</th>
                                                    <th>User Name</th>
                                                    <th>Role</th>
                                                    <th>Status</th>
                                                    <th>Action</th>
                                                </tr>
                                            </tfoot>
                                        </table>

                                    </div>
                                    <div class="col-md-2"></div>
                                </div>                       
                                <div class="row">
                                    <div class="col-md-3">
                                    </div>
                                    <div class="col-md-7">
                                        <div class="row">
                                            <div class="col-md-6"></div>
                                            <div class="col-md-6">
                                                <div class="row">
                                                    <div class="col-md-3">
                                                        <c:if test="${currentPage != 1}">
                                                            <form action="user_management" method="post">
                                                                <input type="hidden" name="action" value="previous">
                                                                <input type="hidden" name="page" value="${currentPage-1}"/>
                                                                <input type="hidden" name="index" value="1">
                                                                <button type="submit" style="width: 80px;" class="btn btn-sm btn-primary" value="${currentPage-1}">Previous</button>  
                                                            </form>
                                                        </c:if>
                                                        <c:if test="${currentPage == 1}">
                                                            <button type="submit" style="width: 80px;" class="btn btn-sm btn-primary" value="${currentPage-1}" disabled="true">Previous</button>  
                                                        </c:if>
                                                    </div>
                                                    <div class="col-md-1">
                                                        <input type="text" style="width:  30px; height: 30px;" disabled="true" value="${currentPage}"></button>
                                                    </div>
                                                    <div class="col-md-3">
                                                        <c:if test="${currentPage != noOfPages}">
                                                            <form action="user_management" method="post">
                                                                <input type="hidden" name="action" value="next">
                                                                <input type="hidden" name="page" value="${currentPage + 1}"/>
                                                                <input type="hidden" name="index" value="1">
                                                                <button type="submit" style="width:  80px; text-align: center;" class="btn btn-sm btn-primary" value="${currentPage + 1}">Next</button>  
                                                            </form>
                                                        </c:if>
                                                        <c:if test="${currentPage == noOfPages}">
                                                            <button type="submit" style="width:  80px;" class="btn btn-sm btn-primary" value="${currentPage + 1}" disabled="true">Next</button> 
                                                        </c:if>

                                                    </div>
                                                    <div class="col-md-5"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-2"></div>
                                </div>
                                <div class="row"></div>
                                <div class="row">
                                    <div class="col-md-2">
                                        <c:forEach var="item" items="${functions}">
                                            <c:if test="${item.getFunction_name()=='Add'}">
                                                <form action="user_management" method="post">
                                                    <input type="hidden" name="action" value="${item.getFunction_name()}">
                                                    <button type="submit" style="width: 90px;" class="btn btn-primary" value="${item.getFunction_name()}">${item.getFunction_name()} User</button>  
                                                </form>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </div>

                            </div>
                        </div>   
                    </div>
                </div>
            </div>

    </body>
</html>
<script type="text/javascript">
    var msg = "<%=message%>";
    if (msg !== 'null') {
        alert(msg);
    }
</script>

