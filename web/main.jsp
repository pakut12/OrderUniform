<%-- 
    Document   : main
    Created on : 31 พ.ค. 2564, 16:54:04
    Author     : 111525
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.pg.lib.model.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%@ include file = "share/header.jsp" %>
    </head>
    <body>
        <%
            OUPermission pm = (OUPermission) request.getSession().getAttribute("role");
            if (pm == null || !pm.getRole().equals("1")) {
                RequestDispatcher rdp = getServletContext().getRequestDispatcher("/login.jsp");
                rdp.forward(request, response);
            }
        %>
        <!-- nav -->
        <%@ include file = "share/navbar.jsp" %>
        <!-- end nav -->
        <div id="layout-mainpage"> 
            <div class="container">
                <div class="shadow p-3 mb-5 bg-body rounded" id="card-department"> 
                    <div class="card text-start" >
                        <div class="card-header">
                            <h2>คู่มือการใช้งานโปรแกรม</h2>
                        </div>
                        <div class="card-body mx-auto">
                            <div class="row">
                                <li><b>คู่มือระบบจัดการออเดอร์ </b><span>: </span>
                                <span style="color:green"> [>> <a href="manualorder.jsp">ระบบจัดการออเดอร์</a> <<] </span>
                            </div>
                            <div class="row mt-3">
                                <li><b>คู่มือระบบจัดการสต็อก</b><span> : </span>
                                <span style="color:green"> [>> <a href="manualstock.jsp">ระบบจัดการสต็อก</a> <<] </span>
                            </div>
                        </div>
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
            //document.getElementById("manualDetail").style.display = "none";
        })

    </script>  
</html>
