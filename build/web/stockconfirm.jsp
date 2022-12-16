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
                            <div class="">โปรเเกรมสรุปผลจัดสินค้า </div>
                        </div>
                        <div class="card-body">
                            <div class="card">
                                <div class="card-header">
                                    <div class="">ค้นหา</div>
                                </div>
                                <div class="card-body">
                                    <div class="row ">
                                        <div class="col-4 text-end">รหัสบาร์โค้ด (เอกสาร) : </div>
                                        <div class="col-4">
                                            <form class="" id="myform">
                                                <input class="form-control form-control-sm " type="text" id="cus_no" value="" required></input>
                                            </form>
                                        </div>
                                        <div class="col-4">
                                            <button class="btn btn-sm btn-success" id="btn-getdata">ค้นหา</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card mt-3">
                                <div class="card-header">
                                    <div class="">เเสดงข้อมูล</div>
                                </div>
                                <div class="row">
                                    <div class="col-6 mx-auto">
                                        <div class="card-body">
                                            <div class="card mt-3">
                                                <div class="card-header">
                                                    <div class="">เเสดงข้อมูลเอกสาร</div>
                                                </div>
                                                <div class="card-body text-center">
                                                    <div class="mb-3">
                                                        <label for="exampleInputEmail1" class=" h6">เลขที่เอกสาร</label>
                                                        <input type="text" class="form-control form-control-sm text-center" id="h_id" readonly>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="exampleInputEmail1" class=" h6">ชื่อไฟล์เอกสาร</label>
                                                        <input type="text" class="form-control form-control-sm text-center" id="h_name" readonly>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="exampleInputEmail1" class=" h6">สถานะเอกสาร</label>
                                                        <input type="text" class="form-control form-control-sm text-center" id="h_status" readonly>
                                                    </div>
                                                    <div class="mb-3">
                                                        <button class="btn btn-sm btn-success disabled" type="button" id="btn_send_status" >ยืนยัน</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
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
        function confirmid(){
            var data = $("#cus_no").val().split("/", 2);
            Swal.fire({
                title: 'คุณต้องการบันทึกหรือไม่',
                text: "คุณต้องการบันทึกหรือไม่",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'OK'
            }).then(function(result){
                if (result.isConfirmed) {
                    $.ajax({
                        type:"post",
                        url:"TransactionCustomer",
                        data:{
                            type:"updatestatusheadertransaction",
                            h_id:data[0]
                        },
                        success:function(msg){                        
                            if(msg == "true"){
                                Swal.fire(
                                'บันทึกสำเร็จ',
                                'บันทึกสำเร็จ',
                                'success'
                            )
                                
                                
                            }else if(msg == "false"){
                                Swal.fire(
                                'บันทึกไม่สำเร็จ',
                                'บันทึกไม่สำเร็จ',
                                'error'
                            )
                            }     
                        }
                    });
                }
            });
        }
        
        
        function getdata(){
            var data = $("#cus_no").val().split("/", 2);
            $("#myform").addClass("was-validated");
                
            if(data != ""){
                $.ajax({
                    type:"post",
                    url:"TransactionCustomer",
                    data:{
                        type:"getheadertransactionwihtid",
                        h_id:data[0],
                        cus_no:data[1]
                    },
                    success:function(msg){
                        var data = JSON.parse(msg);
                        console.log(data);
                        if(msg != ""){
                            Swal.fire({
                                title:"ดึงข้อมูลสำเร็จ",
                                text:"ดึงข้อมูลสำเร็จ",
                                icon:"success"
                            })
                            
                            $("#btn_send_status").removeClass("disabled");
                            $("#h_id").val(data.id);
                            $("#h_name").val(data.name);
                            if(data.status == 0){
                                $("#h_status").removeClass("text-success");
                                $("#h_status").addClass("text-danger");
                                $("#h_status").val("ยังไม่ได้จัดสินค้าลงกล่อง");
                            }else if(data.status == 1){
                                $("#h_status").removeClass("text-danger");
                                $("#h_status").addClass("text-success");
                                $("#h_status").val("จัดสินค้าลงกล่องเรียบร้อย");
                            }
                        }else{
                            Swal.fire({
                                title:"ดึงข้อมูลไม่สำเร็จ",
                                text:"ดึงข้อมูลไม่สำเร็จ",
                                icon:"error"
                            })
                        }
                             
                    }
                });
            }else{
                Swal.fire({
                    title:"กรุณากรอกข้อมูลให้ถูกต้อง",
                    text:"กรุณากรอกข้อมูลให้ถูกต้อง",
                    icon:"error"
                })
            }
        }
        $(document).ready(function(){
            $("#btn-getdata").click(function(){
                getdata();
            });
            $("#btn_send_status").click(function (){
                confirmid(); 
            });
        })

    </script>  
</html>
