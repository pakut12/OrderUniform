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
                                <div class="card-body">
                                    <div class="row mb-3">
                                        <div class="col-4 text-end">รหัสบาร์โค้ด : </div>
                                        <div class="col-4">
                                            <input class="form-control form-control-sm " type="text" id="cus_no"></input>
                                        </div> 
                                        <div class="col-4">
                                            <button class="btn btn-success btn-sm" id="btn-selectdepart">เลือก</button>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-4 text-end">เเผนก : </div>
                                        <div class="col-4">
                                            <select class="form-select form-select-sm" id="depart">
                                                <option value=""></option>
                                            </select>
                                        </div> 
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-4 text-end">จำนวนคนต่อ 1 กล่อง : </div>
                                        <div class="col-4">
                                            <input class="form-control form-control-sm " type="number" id="num"></input>
                                        </div>
                                        <div class="col-4">
                                            <button class="btn btn-success btn-sm" id="btn-getdata">ค้นหา</button>
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
        $(document).ready(function(){
            $("#btn-getdata").click(function(){
                var data = $("#cus_no").val().split("/", 2);
                
                $.ajax({
                    type:"post",
                    url:"GetDataStock",
                    data:{
                        type:"getdataformbarcodeboxdepart",
                        doc_id:data[0],
                        num:$("#num").val(),
                        depart:$("#depart").val()
                    },
                    success:function(msg){
                        $(".viewdata").html(msg);
                          
                        var groupColumn = 0;
                        var table = $('#listdata').DataTable({
                            columnDefs: [
                                { 
                                    visible: false, 
                                    targets: groupColumn
                                }
                            ],
                            order: [[groupColumn, 'asc']],
                           
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
                    }
                });
                 
            });  
           
            $("#btn-selectdepart").click(function(){
                var data = $("#cus_no").val().split("/", 2);
                
                $.ajax({
                    type:"post",
                    url:"GetDataStock",
                    data:{
                        type:"getdatadepart",
                        doc_id:data[0],
                        num:$("#num").val()
                    },
                    success:function(msg){
                        var data = JSON.parse(msg);
                    
                        $("#depart").empty();
                        $.each(data.depart,function(k,v){
                            $("#depart").append("<option value='"+v+"'>"+v+"</option>");
                        })
                          
                    }
                });
                 
            });
            
        })

    </script>  
</html>
