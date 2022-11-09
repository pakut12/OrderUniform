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
                            <div class="">ระบบจัดการสต็อก</div>
                        </div>
                        <div class="card-body">
                            <div class="card">
                                <div class="card-header">
                                    <div class="">ค้นหา</div>
                                </div>
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-4 text-end">รหัสพนักงาน : </div>
                                        <div class="col-4">
                                            <input class="form-control form-control-sm" type="text" id="cus_no"></input>
                                        </div>
                                        <div class="col-4">
                                            <button class="btn btn-sm btn-success" id="btn-getdata">ค้นหา</button>
                                            <button class="btn btn-sm btn-secondary">พิมพ์สติ๊กเกอร์</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card mt-3">
                                <div class="card-header">
                                    <div class="">เเสดงข้อมูล</div>
                                </div>
                                <div class="card-body">
                                    <table class="table " id="mytable">
                                        <thead>
                                            <tr>
                                                <th> ลำดับ </th>  
                                                <th> ชื่อ-นามสกุล </th>
                                                <th> ชื่อสินค้า</th>
                                                <th> รหัสสินค้า </th>
                                                <th> สี </th>
                                                <th> ไซส์ </th>
                                                <th> รหัสสินค้า 18 หลัก </th>
                                                <th> Barcode </th>
                                                <th> จำนวน </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>asdasd</td>
                                                <td>asdasd</td>
                                                <td>asdasd</td>
                                                <td>asdasd</td>
                                                <td>asdasd</td>
                                                <td>asdasd</td>
                                                <td>asdasd</td>
                                                <td>asdasd</td>
                                                <td>asdasd</td>
                                            </tr>
                                        </tbody>
                                        
                                    </table>
                                    
                                </div>
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
            $("#mytable").DataTable();
            $("#btn-getdata").click(function(){
                var data = $("#cus_no").val();
                $.ajax({
                    type:"post",
                    url:"GetDataStock",
                    data:{
                        cus_no:data
                    },
                    success:function(msg){
                        console.log(msg);
                    }
                });
            });
            
        })

    </script>  
</html>
