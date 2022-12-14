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
                            <div class="">โปรเเกรมดูรายละเอียด</div>
                        </div>
                        <div class="card-body">
                            <div class="card">
                                <div class="card-header">
                                    <div class="">ค้นหา</div>
                                </div>
                                <div class="card-body" id="barcode_pass">
                                    <div class="row mb-3">
                                        <div class="col-4 text-end">รหัสบาร์โค้ด (เอกสาร) : </div>
                                        <div class="col-4">
                                            <input class="form-control form-control-sm " type="text" id="cus_no" required></input>
                                        </div> 
                                        <div class="col-4">
                                            <button class="btn btn-success btn-sm" id="btn-getdata">ค้นหา</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="card mt-3">
                                <div class="card-header">
                                    <div class="">เเสดงข้อมูลรายชื่อ</div>
                                </div>
                                <div class="card-body">
                                    <div class="row mb-5">
                                        <div class="col-3">
                                            <div class="card bg-success text-white text-center shadow-lg">
                                                <div class="card-header ">
                                                    รายชื่อที่จัดสินค้าเเล้ว
                                                </div>
                                                <div class="card-body">
                                                    <div class="h3" id="cms_packsuccess">
                                                        0 คน
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-3">
                                            <div class="card bg-danger text-white  text-center shadow-lg">
                                                <div class="card-header">
                                                    รายชื่อที่ยังไม่ได้จัดสินค้า
                                                </div>
                                                <div class="card-body">
                                                    <div class="h3" id="cms_packerror">
                                                        0 คน
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-3">
                                            <div class="card bg-warning text-dark text-center shadow-lg">
                                                <div class="card-header">
                                                    รายชื่อทั้งหมด
                                                </div>
                                                <div class="card-body">
                                                    <div class=" h3" id="cms_packtotal">
                                                        0 คน
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-3">
                                            <div class="card bg-primary text-white text-center shadow-lg">
                                                <div class="card-header">
                                                    ผลสรุป
                                                </div>
                                                <div class="card-body">
                                                    <div class="h3" id="cms_packresult">
                                                        -
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="text-center h3">
                                        ดูข้อมูลสรุปทั้งหมด : <a id="result_docid" class="text-primary">>>Link<<</a>
                                    </div>
                                    <div class="viewdata">     
                                    </div>
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
        function gettabeldata(){
            $("#barcode_pass").addClass("was-validated");
            var data = $("#cus_no").val().split("/", 2);
            $.ajax({
                type:"post",
                url:"GetDataStock",
                data:{
                    type:"getdetail",
                    doc_id:data[0],
                    num:$("#num").val()
                },
                beforeSend:function(){
                    $("#btn-getdata").addClass("disabled");
                    $("#btn-getdata").text("กำลังค้นหา");
                },
                success:function(msg){
                    $("#btn-getdata").removeClass("disabled");
                    $("#btn-getdata").text("ค้นหา");
                    $(".viewdata").html(msg);
                    $('#listdata').DataTable();
                }
            });
        }
        function getsumbydoc(){
            var data = $("#cus_no").val().split("/", 2);
            $.ajax({
                type:"post",
                url:"GetDataStock",
                data:{
                    type:"getnumbydoc",
                    doc_id:data[0]
                },
                success:function(msg){
                    console.log(msg);
                    var jsondata = JSON.parse(msg);
                    $("#cms_packsuccess").text(jsondata.success + " คน");
                    $("#cms_packerror").text(jsondata.error + " คน");
                    $("#cms_packtotal").text(jsondata.total + " คน");
                    $("#cms_packresult").text(jsondata.result);
                }
            });
        }
        $(document).ready(function(){
        
            $("#btn-getdata").click(function(){
                gettabeldata();
                getsumbydoc();
            }); 
            
            $("#result_docid").click(function(){
                var data = $("#cus_no").val().split("/", 2);
                if(data[0] == "" ){
                    Swal.fire({
                        title:"กรุณาใส่รหัส Barcode",
                        text:"กรุณาใส่รหัส Barcode",
                        icon:"error"
                    })
                }else{
                    window.open("TransactionCustomer?doc_id="+ data[0], 'new')
                }
               
            });
        })

    </script>  
</html>
