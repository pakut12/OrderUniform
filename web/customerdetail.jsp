<%-- 
    Document   : customerdetail
    Created on : 11 มิ.ย. 2564, 10:56:56
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
        <div id="default-layout">
            <div class="modal fade" id="AddModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="exampleModalLabel">เพิ่มข้อมูล</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            
                            <div class="row mb-3">
                                <label for="no" class="text-center">รหัสพนักงาน</label>
                                <input class="form-control form-control-sm  w-75 mx-auto text-center" id="no" name="no"></input>
                            </div>
                            <div class="row mb-3">
                                <label for="no" class="text-center">คำนำหน้าชื่อ</label>
                                <input class="form-control form-control-sm  w-75 mx-auto text-center" id="no" name="no"></input>
                            </div>
                            <div class="row mb-3">
                                <label for="no" class="text-center">ชื่อ-นามสกุล</label>
                                <input class="form-control form-control-sm  w-75 mx-auto text-center" id="no" name="no"></input>
                            </div>
                            <div class="row mb-3">
                                <label for="no" class="text-center">เเผนก</label>
                                <input class="form-control form-control-sm  w-75 mx-auto text-center" id="no" name="no"></input>
                            </div>
                            
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary" id="AddSave">Save</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="EditModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="exampleModalLabel">เเก้ไขข้อมูล</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="row mb-3">
                                <label for="no" class="text-center">ลำดับ</label>
                                <input class="form-control form-control-sm  w-75 mx-auto text-center" id="no" name="no" value="" readonly></input>
                            </div>
                            <div class="row mb-3">
                                <label for="no" class="text-center">รหัสพนักงาน</label>
                                <input class="form-control form-control-sm  w-75 mx-auto text-center" id="no" name="no"></input>
                            </div>
                            <div class="row mb-3">
                                <label for="no" class="text-center">คำนำหน้าชื่อ</label>
                                <input class="form-control form-control-sm  w-75 mx-auto text-center" id="no" name="no"></input>
                            </div>
                            <div class="row mb-3">
                                <label for="no" class="text-center">ชื่อ-นามสกุล</label>
                                <input class="form-control form-control-sm  w-75 mx-auto text-center" id="no" name="no"></input>
                            </div>
                            <div class="row mb-3">
                                <label for="no" class="text-center">เเผนก</label>
                                <input class="form-control form-control-sm  w-75 mx-auto text-center" id="no" name="no"></input>
                            </div>
                            
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary" id="EditSave" >Save</button>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="container">
                <div class="row">
                    <div class="col-1">
                        <img src="css/bootstrap-icons-1.5.0/person-lines-fill.svg" 
                             alt="Bootstrap" 
                             width="100%" 
                             height="100%">
                    </div>
                    <div class="col-3">
                        <h1>ข้อมูลลูกค้า<h3>รูปแบบรายชื่อพนักงาน</h3></h1>
                        
                    </div>
                    <div class="col-8 text-end mt-4">
                        <button class="btn btn-success btn-lg" id="AddDataCustomer">เพิ่มข้อมูล</button>
                    </div>
                </div>
                <hr>
                <div id="table_customer_detail">
                    <!-- display table -->
                </div>
            </div>
        </div>
    </body>
    <footer>
        <%@ include file = "share/footer.jsp" %>
    </footer>
    
    <script language="javascript">
        $(document).ready(function(){
            loadData();
            
            $("#AddDataCustomer").click(function(){
                $('#AddModal').modal('show');
            });
            
            $("#AddSave").click(function(){
                $.ajax({
                    type:"post",
                    url:"Customer",
                    data:{
                        type:"adddetailcustomer"
                    },
                    success:function(msg){
                       console.log(msg);
                    }
                });
            })
            $("#EditSave").click(function(){
                alert("asd");
            })
          
        });
        
        function loadData(){
            $.get("Customer",{
                type : "getdetailcustomer"
            },function(result){
                document.getElementById("table_customer_detail").innerHTML = result;
                setupDatatable();
            }).fail(function(){
                console.log("cannot get data customer...")
            })     
        };
            
        function setupDatatable(){
            var groupColumn = 5;
            var table =  $('#customerdetail').DataTable({
                scrollY: '45vh',
                scrollCollapse: true,
                "columnDefs":[
                    { "visible" : false, "targets": groupColumn }
                ],
                "order": [[groupColumn, 'asc'],[0, 'asc']],
                "dispayLength": 100,
                "drawCallback": function ( settings ){
                    var api = this.api();
                    var rows = api.rows({page:'current'}).nodes();
                    var last = null;
                        
                    api.column(groupColumn, {page:'current'}).data().each( function (group, i) {
                        if( last !== group ){
                            $(rows).eq(i).before(
                            '<tr class="group" style="background-color:#ddd"><td colspan="7">'+group+'</td></tr>'
                        );
                            last = group;
                        }
                    });
                }
            });
           
            $('#customerdetail tbody').on( 'click', 'tr.group', function() {
                var currentOrder = table.order()[0];
                if ( currentOrder[0] === groupColumn && currentOrder[1] === 'asc' ){
                    table.order( [ groupColumn, 'desc' ] ).draw();
                } else {
                    table.order( [ groupColumn, 'asc' ] ).draw();
                }
            });
            
            $('#customerdetail tbody').on( 'click', '.edit_btn', function () {  
                $('#EditModal').modal('show');
            } );
            
            $('#customerdetail tbody').on( 'click', '.del_btn', function () {  
                alert("asdasdasd"); 
            } );
        }
    </script>
</html>
