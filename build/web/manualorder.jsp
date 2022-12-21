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
                                    <b>วิธีใช้งาน : </b> ระบบจัดการออเดอร์ (รายชื่อพนักงาน)
                                </div>
                                <hr>
                                <div class="row">
                                    <li><b>ขั้นตอนที่ 1 </b><span>: เลือกดาวน์โหลดฟอร์มเพื่อนำไปกรอกข้อมูลของลูกค้า(เลือกตามประเภทของข้อมูล)</span> 
                                    <span style="color:green"> - 
                                        [ 
                                        <a href="attachfile/download/form/Department.xls">แบบแผนก</a> 
                                        ||
                                        <a  href="attachfile/download/form/OrderUniform_Customer.xls">แบบรายชื่อพนักงาน</a>
                                        ]
                                    </span>
                                </div>
                                <br>
                                <div class="row">
                                    <li><b>ขั้นตอนที่ 2 </b><span>: เพิ่มรายชื่อบริษัท</span>
                                    <span style="color:green"> [ จัดการข้อมูลมาสเตอร์ --> <a href="managecompany.jsp">รายชื่อบริษัท</a> ] </span>
                                </div>
                                <br>
                                <div class="row">
                                    <li><b>ขั้นตอนที่ 3 </b><span>: อัพโหลด excel ที่โหลดไปกรอกจากขั้นตอนที่ 1 </span>
                                    <span style="color:green"> [ จัดการข้อมูลมาสเตอร์ -->  <a href="managecustomer.jsp">ลูกค้า</a> ] </span>
                                </div>
                                <br>
                                <div class="row">
                                    <li><b>ขั้นตอนที่ 4 </b><span>: เพิ่มข้อมูลรายการ Items</span>
                                    <span style="color:green"> [ จัดการข้อมูลมาสเตอร์ --> <a  href="managematerial.jsp">รายการ Items</a> ] </span>
                                </div>
                                <br>
                                <div class="row">
                                    <li><b>ขั้นตอนที่ 5 </b><span> : ดาวน์โหลดฟอร์ม Excel ที่โปรแกรมสร้างให้ใน </span>
                                    <span style="color:green"> [ <a  href="manageorder.jsp"> ดาวน์โหลด Excel</a> ]</span>
                                </div>
                                <br>
                                <div class="row">
                                    <li><b>ขั้นตอนที่ 6 </b><span>: หลังจากนำฟอร์มไปกรอกเก็บข้อมูลเรียบร้อยแล้ว ให้ทำการอัพโหลดที่เมนู </span>
                                    <span style="color:green"> [ <a href="uploadtransaction.jsp"> อัพโหลด Excel</a> ]</span>
                                    <span>โดยเลือกตามหัวข้อ</span>
                                </div>
                                <br>
                                <div class="row">
                                    <li><b>ขั้นตอนที่ 7 </b><span>: หลังจากทำการอัพโหลดข้อมูลเรียบร้อยแล้ว ระบบจะทำการสรุปยอดต่างๆ สามารถดูรายละเอียดได้ที่ </span>
                                    <span style="color:green"> [ <a href="listtransactioncustomer.jsp">รูปแบบพนักงาน</a> || <a href="listtransactiondepartment.jsp">รูปแบบแผนก</a>]</span>
                                    <span>โดยเลือกตามหัวข้อ</span>
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
