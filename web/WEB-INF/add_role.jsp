<%-- 
    Document   : add_role
    Created on : Sep 27, 2018, 1:26:19 PM
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
        <script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        
        <script src="js/multiselect.js"></script>

        <title>New Role</title>
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


                        <h2>ROLE MANAGEMENT >> ADD ROLE</h2>
                    <% String message = (String) request.getAttribute("msg");%>

                    <div class="row" style="height: 20px;"></div>
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-8">
                            <h3>Add New >></h3><br>
                            <form class="form-signin" action="UserFunctions" method="post">
                                <label>Insert New Role Name</label>
                                <input type="text" class="form-control" name="role_name" placeholder="Role Name" required autofocus style="width: 250px;">
                                <div class="row" style="height: 25px;"></div>

                                <%--<c:forEach var="in" items="${inter}">--%>

                                <!--<div class="dropdown">-->
                                    <!--<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" style="float: left; margin-left: 10px;"><c:out value="${in.getI_name()}"></c:out><span class="caret"></span></button>-->    
                                    <!--<ul class="dropdown-menu" style="margin-top: 30px; inline-box-align: inherit;">-->
                                <%--<c:forEach var="func" items="${funcs}">--%>
                                <%--<c:if test="${func.getI_name()==in.getI_name()}">--%>
                                    <!--<li><input type="checkbox" name="func" value="${func.getIf_id()}"/>&nbsp;<c:out value="${func.getF_name()}"></c:out></li>-->
                                <%--</c:if>--%>
                                <%--</c:forEach>--%>
                                <!--</ul>-->
                                <!--</div>-->
                                <%--</c:forEach>--%>

                                <!--start of multi select-->

                                <div class="row">
                                    <div class="col-md-5">

                                        <!--interface selection select box-->
                                        <label>Select interface </label>
                                        <select name="in_name" class="form-control" id="in_funcs" onchange="loadPageFunctions(this.value)">
                                            <c:forEach var="interfaces" items="${inter}" >
                                                <option value="${interfaces.getI_name()}">${interfaces.getI_name()}</option>
                                            </c:forEach>
                                        </select>
                                        <br>

                                        <!--function selection multi select box-->
                                        <select name="currentBox" id="multiselect" ondblclick="toright()" class="form-control" size="6" multiple="true">

                                        </select>
                                    </div>
                                    <div class="col-md-2">
                                        <br><br><br>
                                        <!--task buttons-->
                                        <button type="button" id="multiselect_rightAll" class="btn btn-block" onclick="torightall()"><i class="glyphicon glyphicon-forward"></i></button>
                                        <button type="button" id="multiselect_rightSelected" class="btn btn-block" onclick="toright()"><i class="glyphicon glyphicon-chevron-right"></i></button>
                                        <button type="button" id="multiselect_leftSelected" class="btn btn-block" onclick="toleft()"><i class="glyphicon glyphicon-chevron-left"></i></button>
                                        <button type="button" id="multiselect_leftAll" class="btn btn-block" onclick="toleftall()"><i class="glyphicon glyphicon-backward"></i></button>
                                    </div>
                                    <div class="col-md-5">
                                        <br><br><br><br>

                                        <!--multi select box for containing selected functions-->
                                        <select name="newBox" id="multiselect_to" ondblclick="toleft()" class="form-control" size="6" multiple="true">
                                        </select>
                                    </div>
                                </div>

                                <!--end of multi select-->
                                <div class="row" style="height: 10px;"></div>
                                <div class="row">
                                    <div class="col-md-5"></div>
                                    <div class="col-md-2"></div>
                                    <div class="col-md-5">
                                        <button type="button" id="set" class="btn btn-block" onclick="setToSelector()">Set</button>
                                    </div>
                                </div>
                                <div class="row" id="selection">

                                </div>

                                <input type="hidden" name="action" value="add_role">
                                <br> 
                                <button class="btn btn-primary" type="submit" name="Submit" value="Submit" onclick="return postMessage('role added successfully!')">Submit</button>
                            </form>
                        </div>
                        <div class="row" style="height: 50px;"></div>
                        <div class="col-md-3"></div>
                    </div>

                    <div class="row" style="height: 50px;"></div>
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

//    select neccessary functions for selected interface

    function loadPageFunctions(interface) {
        $('#multiselect').empty();
        $('#multiselect_to').empty();
        $('#multiselect_to option').prop('selected', true);
        $('#multiselect option').prop('selected', true);

        $.ajax({
            type: "POST",
            url: "MultiSelectServlet",
            data: "interface=" + interface,
            dataType: "json",
            success: function (json)
            {
                $.each(json, function (i, object) {
                    var name;
                    var id;
                    $.each(object, function (y, obj) {
                        if (y === "f_name") {
                            name = obj;
                        }
                        if (y === "if_id") {
                            id = obj;
                        }
                    });
//                    alert(name+" "+id);
                    $("#multiselect").append($('<option></option>').val(id).html(name));
                });

            }
        });
//        alert(interface);
    }



//    javascript functions for arrow buttons in multiselect box

    function toleft() {
        $("#multiselect_to option:selected").each(function () {
            $("#multiselect").append($('<option>', {
                value: $(this).val(),
                text: $(this).text()
            }));
            $(this).remove();
        });
    }

    function toright() {
        $("#multiselect option:selected").each(function () {
            $("#multiselect_to").append($('<option>', {
                value: $(this).val(),
                text: $(this).text()
            }));
            $(this).remove();
        });
    }
    function toleftall() {
        $("#multiselect_to option").each(function () {
            $("#multiselect").append($('<option>', {
                value: $(this).val(),
                text: $(this).text()
            }));
            $(this).remove();
        });
    }
    function torightall() {
        $("#multiselect option").each(function () {
            $("#multiselect_to").append($('<option>', {
                value: $(this).val(),
                text: $(this).text()
            }));
            $(this).remove();
        });
    }
</script>

