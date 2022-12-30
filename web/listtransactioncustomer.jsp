<%-- 
    Document   : listtransactioncustomer
    Created on : 2 ก.ค. 2564, 10:03:38
    Author     : 111525
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.pg.lib.model.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <%
            OUPermission pm = (OUPermission) request.getSession().getAttribute("role");
            if (pm == null || !pm.getRole().equals("1")) {
                RequestDispatcher rdp = getServletContext().getRequestDispatcher("/login.jsp");
                rdp.forward(request, response);
            }
    %>
    <head>
        <%@ include file = "share/header.jsp" %>
    </head>
    
    <body>
        <!-- nav -->
        <%@ include file = "share/navbar.jsp" %>
        <!-- end nav -->
        <div id="layout-mainpage"> 
            <div class = "container">
                <div class="row">
                    <div class="col-1">
                        <img src="css/bootstrap-icons-1.5.0/file-earmark-person.svg" 
                             alt="Bootstrap" 
                             width="100%" 
                             height="100%">
                    </div>
                    <div class="col-3">
                        <h1>Transaction <h3>รูปแบบพนักงาน</h3></h1>
                    </div>
                </div>
                <hr>
                <div class="shadow-none p-3 mb-5 bg-light rounded">
                    <div id="displayTable">
                        
                    </div>
                </div>
            </div>
        </div>
    </body>
    
    <footer>
        <%@ include file = "share/footer.jsp" %>
    </footer>
    
    <script language="javascript">
        $(document).ready(function(){
            initialdata();
        })
        
        function initialdata(){
            $.get("TransactionCustomer",{
                type : "getheadertransaction"
            },function(data){
                document.getElementById("displayTable").innerHTML = data;
            }).fail(function(){
                alert("ไม่สามารถโหลดรายการได้ กรุณาลองใหม่อีกครั้ง...");
            }).done(function() {
                $('#list-transaction').DataTable();
            })
        }
    </script>
</html>
