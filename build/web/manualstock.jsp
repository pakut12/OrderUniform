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
                        <div class="card-body">
                            <div id="manualDetail">
                                <div class="text-start h2"> 
                                    <b>วิธีใช้งาน : </b> ระบบจัดการสต็อก (รายชื่อพนักงาน)
                                </div>
                                <hr>
                                <div class="row">
                                    <li><b>ขั้นตอนที่ 1 </b><span>: เลือกโปรเเกรมค้นหาเอกสารเเละเลือกเอกสารที่ต้องการ</span> 
                                    <span style="color:green"> - 
                                        [ 
                                        <a href="listtransactioncustomer.jsp">โปรเเกรมค้นหาเอกสาร</a> 
                                        ]
                                    </span>
                                </div>
                                <br>
                                <div class="row">
                                    <li><b>ขั้นตอนที่ 2 </b><span>: กดปุ่มพิมพ์ Report</span>
                                </div>
                                <br>
                                <div class="row">
                                    <li><b>ขั้นตอนที่ 3 </b><span>: เลือกโปรเเกรมจัดสินค้าลงถุงเเละยิงรหัสบาร์โค้ดตามรายชื่อ</span> 
                                    <span style="color:green"> - 
                                        [ 
                                        <a href="stocklistbag.jsp">โปรเเกรมจัดสินค้าลงถุง</a> 
                                        ]
                                    </span>
                                </div>
                                <br>
                                <div class="row">
                                    <li><b>ขั้นตอนที่ 4 </b><span>: เลือกโปรเเกรมจัดสินค้าลงกล่องเเละใส่ข้อมูลให้ถูกต้อง</span> 
                                    <span style="color:green"> - 
                                        [ 
                                        <a href="stockboxall.jsp">โปรเเกรมจัดสินค้าลงกล่อง</a> 
                                        ]
                                    </span>
                                </div>
                                <br>
                                <div class="row">
                                    <li><b>ขั้นตอนที่ 5 </b><span>: เลือกโปรเเกรมสรุปผลการจัดสินค้าลงกล่องเเละใส่ข้อมูลให้ถูกต้อง</span> 
                                    <span style="color:green"> - 
                                        [ 
                                        <a  href="stockcustomeviewstatus.jsp"> โปรเเกรมสรุปผลการจัดสินค้าลงกล่อง</a>
                                        ]
                                    </span>
                                </div>
                                <br>
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
