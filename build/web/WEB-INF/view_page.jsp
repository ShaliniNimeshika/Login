<%-- 
    Document   : view_page
    Created on : Oct 29, 2018, 1:18:04 PM
    Author     : shalini_w
--%>

<%@page import="org.json.simple.JSONObject"%>
<%-- 
    Document   : role_management
    Created on : Oct 29, 2018, 11:31:19 AM
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
        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js" ></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css"/>
        <!--<script>var jData = ${jsonArray};</script>-->
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
                            <div class="col-md-10">
                                <div class="row">
                                    <h2>PAGE MANAGEMENT >> VIEW</h2>

                                </div>
                                <div class="row" style="height: 50px;"></div>
                                <div class="row">
                                    <table class="table" id="Table">
                                        <thead>
                                            <tr>
                                                <th>Page ID</th>
                                                <th>Page Name</th>
                                                <th>URL</th>
                                                <th>Assigned Functions</th>
                                                <th>Action</th>                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="page" items="${inter}">
                                            <tr>
                                                <td><c:out value="${page.getI_id()}" /></td>
                                                <td><c:out value="${page.getI_name()}" /></td>
                                                <td><c:out value="${page.getI_url()}" /></td>
                                                <td>
                                                    <c:forEach var="f" items="${page.getFbean()}">

                                                        <c:out value="${f.getFunction_name()}"></c:out>&nbsp;&nbsp;
                                                    </c:forEach>
                                                </td> 
                                                <td>
                                                    <div class="pull-left" style="margin-right: 5px; ">
                                                        <form  action="UserFunctions" method="post">
                                                            <input type="hidden" name="action" value="update_function">
                                                            <input type="hidden" name="pageid" value="${page.getI_id()}">
                                                            <button type="submit" class="btn btn-success" style="width: 70px; height: 30px; font-size: 12px;" value="${page.getI_id()}">Update</button>  
                                                        </form>
                                                    </div>
                                                    <div class="pull-left">
                                                        <form action="UserFunctions" method="post">
                                                            <input type="hidden" name="action" value="delete_page">
                                                            <input type="hidden" name="pageid" value="${page.getI_id()}">
                                                            <button type="submit" class="btn btn-danger" style="width: 90px; height: 30px; font-size: 12px;" onclick="return confirm('Are you sure you want to delete interface : ${page.getI_name()} ?')" value="${page.getI_id()}">Delete Page</button>  
                                                        </form>
                                                    </div>

                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <th>Page ID</th>
                                            <th>Page Name</th>
                                            <th>URL</th>
                                            <th>Assigned Functions</th>
                                            <!--<th>Action</th>-->                                                
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                            <div class="row" style="height: 30px;"></div>
                            <div class="row" style="height: 3px; background-color: grey;"></div>
                            <div class="row" style="height: 30px;"></div>
                            <div class="row">
                                <table class="table" id="Table1">
                                    <% String jsonArray = (String) request.getAttribute("jsonArray");%>
                                    <% String jsonObject = (String) request.getAttribute("jsonObject");%>

                                    <caption><h3>Using DataTable with AJAX</h3></caption>
                                    <thead>
                                        <tr>
                                            <th>Page ID</th>
                                            <th>Page Name</th>
                                            <th>URL</th>
                                            <th>Assigned Functions</th>
                                            <!--<th>Action</th>-->                                                
                                        </tr>
                                    </thead>
                                    <tbody>

                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <th>Page ID</th>
                                            <th>Page Name</th>
                                            <th>URL</th>
                                            <th>Assigned Functions</th>
                                            <!--<th>Action</th>-->                                                
                                        </tr>
                                    </tfoot>
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
<script>

    $(document).ready(function () {
        $('#Table').DataTable();
    });


//    var jO = ;
//    alert(jO);
//    $.ajax({
//        success: function () {
//            $.each(jO, function (i, object) {
////                alert(object.i_name);
//                var tr = "<tr>";
//                var td1 = "<td>" + object.i_id + "</td>";
//                var td2 = "<td>" + object.i_name + "</td>";
//                var td3 = "<td>" + object.i_url + "</td>";
//                var td4 = "<td>" + object.desc + "</td></tr>";
//
//                $("#Table1").append(tr + td1 + td2 + td3 + td4);
//            });
//        }
//    });
//    
//    $(document).ready(function () {
//        $('#Table1').DataTable();
//    });

    $(document).ready(function () {
        $('#Table1').DataTable();
    });


    $.ajax({
//        type: 'POST',
//        url: "user_management?action=View_Interface",
//        dataType: 'json',
        success: function () {
            console.log("populating data table...");
            // clear the table before populating it with more data
            $("#Table1").DataTable().clear();
            var testDataUrl = <%=jsonObject%>;

            $.each(testDataUrl.interface, function (i, result) {
//                 You could also use an ajax property on the data table initialization
                $('#Table1').dataTable().fnAddData([
                    result.id,
                    result.name,
                    result.url,
                    result.desc,
                    "<button type='submit' class='btn btn-sm btn-success' value='"+result.id+"'>Update</button>",
                    "<button type='submit' class='btn btn-sm btn-success' onclick='return confirm('Are you sure you want to delete interface :"+result.name+"?' value='"+result.id+"'>Delete</button>"
                ]);
            });
        } ,
        error: function (e) {
            console.log("There was an error with your request...");
            console.log("error: " + JSON.stringify(e));
        }
    });

</script>

