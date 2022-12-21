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
                            <div class="">ระบบจัดการสต็อก (เเบบกล่อง)</div>
                        </div>
                        <div class="card-body">
                            <div class="card">
                                <div class="card-header">
                                    <div class="">ค้นหา</div>
                                </div>
                                <div class="card-body" id="barcode_pass">
                                    <div class="row mb-3">
                                        <div class="col-4 text-end">รหัสบาร์โค้ด (เอกสาร): </div>
                                        <div class="col-4">
                                            <input class="form-control form-control-sm " type="text" id="doc_id" required></input>
                                        </div> 
                                        
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-4 text-end">จำนวนคนต่อ 1 กล่อง : </div>
                                        <div class="col-4">
                                            <input class="form-control form-control-sm " type="number" id="num" required></input>
                                        </div> 
                                        
                                    </div>
                                    <div class="row mb-3 text-center">
                                        <div class="col-12">
                                            <button class="btn btn-success btn-sm" id="btn-getdata">ค้นหา</button>&nbsp;
                                            <button class="btn btn-secondary btn-sm disabled" id="btn-print" >พิมพ์สติ๊กเกอร์ทั้งหมด</button>
                                        </div> 
                                    </div>
                                </div>
                            </div>
                            <!--<div class="card mt-3">
                                <div class="card-header">
                                    <div class="">เเสดงข้อมูล</div>
                                </div>
                                <div class="card-body">
                                    
                                    <div class="viewdata">
                                        
                                    </div>
                                    
                                </div>
                            </div>-->
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
        function print(){
            var doc_id = $("#doc_id").val().trim();
            var num = $("#num").val();
            window.open("report/ReportBoxAll.jsp?doc_id=" + doc_id + "&num=" + num ,"_blank");
        }
        
        function getdata(){
            $("#barcode_pass").addClass("was-validated");
            var data = $("#doc_id").val().trim();
            var num = $("#num").val().trim();
            if(data != "" && num != ""){
                $.ajax({
                    type:"post",
                    url:"GetDataStock",
                    data:{
                        type:"getdataformbarcodebox",
                        doc_id:data,
                        num:num
                    },
                    success:function(msg){
                        if(msg == "true"){
                            Swal.fire({
                                title:"ดึงข้อมูลเรียบร้อย",
                                icon:"success",
                                text:"ดึงข้อมูลเรียบร้อย"
                            });
                            $("#btn-print").removeClass("disabled");
                        }else if(msg == "false"){
                            Swal.fire({
                                title:"ไม่พบข้อมูล",
                                icon:"error",
                                text:"ไม่พบข้อมูล"
                            });
                            $("#btn-print").addClass("disabled");
                        }      
                            
                        /*
                             $(".viewdata").html(msg);
                            var groupColumn = 0;
                            var table = $('#listdata').DataTable({
                                columnDefs: [
                                    { 
                                        visible: false, 
                                        targets: groupColumn
                                    }
                                ],
                                order: [[1, 'asc']],
                           
                                drawCallback: function (settings) {
                                    var api = this.api();
                                    var rows = api.rows({ page: 'current' }).nodes();
                                    var last = null;
 
                                    api
                                    .column(groupColumn, { page: 'current' })
                                    .data()
                                    .each(function (group, i) {
                                        if (last !== group) {
                                            $(rows)
                                            .eq(i)
                                            .before('<tr class="group"><td colspan="3" style="background-color: #ddd;" >' + group + '</td></tr>');
                                            last = group;
                                        }
                                    });
                                }
                            });
                            $('#listdata tbody').on('click', 'tr.group', function () {
                                var currentOrder = table.order()[0];
                                if (currentOrder[0] === groupColumn && currentOrder[1] === 'asc') {
                                    table.order([groupColumn, 'desc']).draw();
                                } else {
                                    table.order([groupColumn, 'asc']).draw();
                                }
                            });
                            
                         */
                    }
                });
            }else{
                Swal.fire({
                    title:"Error",
                    icon:"error",
                    text:"กรุณากรอกข้อมูลให้ถูกต้อง"
                })
            } 
        }    
        
        $(document).ready(function(){
        
            $("#btn-getdata").click(function(){
                getdata();
            });
            $("#btn-print").click(function(){
                print();
            });
        })

    </script>  
</html>
