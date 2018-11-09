<%-- 
    Document   : home
    Created on : Sep 17, 2018, 10:53:17 AM
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

                        <div class="row" style="height: 50px;"></div>
                        <div class="row">
                            <h2><center>Welcome to HOME</center></h2>
                                <%String message = (String) request.getAttribute("msg");%>
                    </div>
                    <div class="row" style="height: 50px;"></div>
                    <c:if test="${roleid == 1}">
                        <div class="row">

                            <div class="col-md-3"></div>
                            <div class="col-md-2">
                                <form action="HomeServlet" method="post">
                                    <input type="hidden" value="umgt" name="select">
                                    <button type="submit" class="btn btn-success" value="UMGT" style="width: 170px; height: 100px;">USER MANAGEMENT</button>
                                </form>  
                            </div>
                            <div class="col-md-2">
                                <form action="HomeServlet" method="post">
                                    <input type="hidden" value="rmgt" name="select">
                                    <button type="submit" class="btn btn-success" value="RMGT" style="width: 170px; height: 100px;">ROLE MANAGEMENT</button>
                                </form> 
                            </div>
                            <div class="col-md-2">
                                <form action="HomeServlet" method="post">
                                    <input type="hidden" value="pmgt" name="select">
                                    <button type="submit" class="btn btn-success" value="PMGT" style="width: 170px; height: 100px;">PAGE MANAGEMENT</button>
                                </form> 
                            </div>
                            <div class="col-md-3"></div>
                        </div>
                        <div class="row" style="height: 30px;"></div>
                        <div class="row">
                            <div class="col-md-3"></div>
                            <div class="col-md-4">
                                <div class="row"><label>Generate User Reports Here.....</label></div>
                            </div>
                            <div class="col-md-5"></div>
                        </div>
                        <div class="row" style="height: 15px;"></div>
                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-1">
                                <div class="row">
                                    <form action="UserReportServlet" method="post">
                                        <input type="hidden" name="action" value="pdf">
                                        <input type="hidden" name="interfaceid" value="1">
                                        <button type="submit" class="btn btn-primary" value="generate">As PDF</button>  
                                    </form>
                                </div>
                            </div>
                            <div class="col-md-1">
                                <div class="row">
                                    <form action="UserReportServlet" method="post">
                                        <input type="hidden" name="action" value="excel">
                                        <input type="hidden" name="interfaceid" value="1">
                                        <button type="submit" class="btn btn-primary" value="generate">As Excel Sheet</button>  
                                    </form>
                                </div>
                            </div>
                            <div class="col-md-6"></div>
                        </div>
                        <div class="row" style="height: 30px;"></div>
                        <div class="row">
                            <div class="col-md-3"></div>
                            <div class="col-md-4">
                                <div class="row"><label>Upload File.....</label></div>
                            </div>
                            <div class="col-md-5"></div>
                        </div>
                        <div class="row" style="height: 15px;"></div>
                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-3">
                                <div class="row">
                                    <form action="FileManagerServlet" method="post" enctype="multipart/form-data">
                                        Select File to Upload :<input type="file" name="fileName">
                                        <br>
                                        <button type="submit" class="btn btn-sm btn-primary" value="upload">Upload File</button>  
                                    </form>
                                </div>
                            </div>
                            <div class="col-md-5"></div>
                        </div>
                    </c:if>
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

