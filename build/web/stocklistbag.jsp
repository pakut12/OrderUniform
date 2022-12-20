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
                            <div class="">โปรเเกรมจัดสินค้า </div>
                        </div>
                        <div class="card-body">
                            <div class="card">
                                <div class="card-header">
                                    <div class="">ค้นหา</div>
                                </div>
                                <div class="card-body">
                                    <div class="row ">
                                        <div class="col-4 text-end">รหัสบาร์โค้ด (รายชื่อ) : </div>
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
                                <div class="card-body">
                                    
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
 
        function confirmid(){
            var data = $("#cus_no").val().split("/", 2).trim();
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
                        url:"Customer",
                        data:{
                            type:"updatestatuscustomer",
                            cm_id:data[1]
                        },
                        success:function(msg){     
                            if(msg == "true"){
                                Swal.fire(
                                'บันทึกสำเร็จ',
                                'บันทึกสำเร็จ',
                                'success'
                            )
                                $("#cus_status").val("จัดสินค้าเรียบร้อย");     
                                $("#print_sticker").removeClass("disabled");     
                                $("#print_sticker").click(function(){
                                    window.open("report/ReportBag.jsp?doc_id="+ data[0] +"&cus_no=" +data[1], 'new')
                                })
                                
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
        
        $(document).ready(function(){
            $("#btn-getdata").click(function(){
                var data = $("#cus_no").val().split("/", 2);
                $("#myform").addClass("was-validated");
                
                if(data != ""){
                    $.ajax({
                        type:"post",
                        url:"GetDataStock",
                        data:{
                            type:"getdataformbarcodebag",
                            doc_id:data[0],
                            cus_no:data[1]
                        },
                        success:function(msg){
                            if(msg != ""){
                                Swal.fire({
                                    title:"ดึงข้อมูลสำเร็จ",
                                    text:"ดึงข้อมูลสำเร็จ",
                                    icon:"success"
                                })
                            
                                $(".viewdata").html(msg);
                                $("#listdata").DataTable();
                                $("#btn_confirm").click(function (){
                                    confirmid();  
                                });
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
                        title:"Error",
                        text:"กรุณากรอกข้อมูลให้ถูกต้อง",
                        icon:"error"
                    })

                }
                


            });
            
        })

    </script>  
</html>
