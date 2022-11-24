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
            <div class="modal fade " id="AddModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="exampleModalLabel">เพิ่มข้อมูล</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            
                            <div class="row mb-3">
                                <label for="addcmsno" class="text-center">รหัสพนักงาน</label>
                                <input class="form-control form-control-sm  w-75 mx-auto text-center" id="addcmsno" name="addcmsno" required ></input>
                            </div>
                            <div class="row mb-3">
                                <label for="addpname" class="text-center">คำนำหน้าชื่อ</label>
                                <input class="form-control form-control-sm  w-75 mx-auto text-center" id="addpname" name="addpname" required></input>
                            </div>
                            <div class="row mb-3">
                                <label for="addfname" class="text-center">ชื่อ-นามสกุล</label>
                                <input class="form-control form-control-sm  w-75 mx-auto text-center" id="addfname" name="addfname" required></input>
                            </div>
                            <div class="row mb-3">
                                <label for="adddepart" class="text-center">เเผนก</label>
                                <input class="form-control form-control-sm  w-75 mx-auto text-center" id="adddepart" name="adddepart" required></input>
                            </div>
                            <div class="row mb-3">
                                <label for="addcompany" class="text-center">บริษัท</label>
                                <select class="form-select form-select-sm w-75 mx-auto text-center" id="addcompany" name="addcompany" required>
                                    
                                </select>
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
                                <input class="form-control form-control-sm  w-75 mx-auto text-center" id="cms_id" name="cms_id" readonly></input>
                            </div>
                            <div class="row mb-3">
                                <label for="no" class="text-center">รหัสพนักงาน</label>
                                <input class="form-control form-control-sm  w-75 mx-auto text-center" id="cms_no" name="cms_no"></input>
                            </div>
                            <div class="row mb-3">
                                <label for="no" class="text-center">คำนำหน้าชื่อ</label>
                                <input class="form-control form-control-sm  w-75 mx-auto text-center" id="cms_pname" name="cms_pname"></input>
                            </div>
                            <div class="row mb-3">
                                <label for="no" class="text-center">ชื่อ-นามสกุล</label>
                                <input class="form-control form-control-sm  w-75 mx-auto text-center" id="cms_fname" name="cms_fname"></input>
                            </div>
                            <div class="row mb-3">
                                <label for="no" class="text-center">เเผนก</label>
                                <input class="form-control form-control-sm  w-75 mx-auto text-center" id="cms_depart" name="cms_depart"></input>
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
            getCompanyName();
            $("#AddDataCustomer").click(function(){
                $('#AddModal').modal('show');
            });
            
            $("#AddSave").click(function(){
                $('#AddModal').addClass('was-validated');
                var i1 = $("#addcmsno").val();
                var i2 = $("#addpname").val();
                var i3 = $("#addfname").val();
                var i4 = $("#adddepart").val();
                var i5 = $("#addcompany").val();
                
                if(i1.trim() === "" || i2.trim() === "" || i3.trim() === "" || i4.trim() === "" || i5.trim() === ""  ){
                    Swal.fire(
                    'กรุณากรอกข้อมูลให้ครบถ้วน',
                    'กรุณากรอกข้อมูลให้ครบถ้วน',
                    'error'
                )
                }else{
                    $.ajax({
                        type:"post",
                        url:"Customer",
                        data:{
                            type:"adddetailcustomer",
                            addcmsno:$("#addcmsno").val(),
                            addpname:$("#addpname").val(),
                            addfname:$("#addfname").val(),
                            adddepart:$("#adddepart").val(),
                            addcompany:$("#addcompany").val()
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
                            loadData();
                        }
                    });
                }
               
            });
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
        function getCompanyName(){
            $.get("CompanyList",{
                type : "getCompanyName",
                content : "customer"
            },
            function(result){
                $.each(result,function(k,v){
                    console.log(v.name);
                    $("#addcompany").append('<option value="'+v.code.replace("C","")+'">'+v.name+'</option>');
                });
               
            }).done(function (){
                //do nothing...
            }).fail(function (){
                console.log("getCompanyName Failure!!!");
            })
        }
        function setupDatatable(){
            var groupColumn = 6;
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
                            '<tr class="group" style="background-color:#ddd"><td colspan="8">'+group+'</td></tr>'
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
           
              
            $("#EditSave").click(function(){
                
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
                        $('#EditModal').addClass('was-validated');
                        var i1 = $("#cms_id").val();
                        var i2 = $("#cms_no").val();
                        var i3 = $("#cms_pname").val();
                        var i4 = $("#cms_fname").val();
                        var i5 = $("#cms_depart").val();
        
        
                        if(i1.trim() === "" || i2.trim() === "" || i3.trim() === "" || i4.trim() === "" || i5.trim() === ""  ){
                            Swal.fire(
                            'กรุณากรอกข้อมูลให้ครบถ้วน',
                            'กรุณากรอกข้อมูลให้ครบถ้วน',
                            'error'
                        )
                        }else{
                            $.ajax({
                                type:"post",
                                url:"Customer",
                                data:{
                                    type:"editdetailcustomer",
                                    cms_id:$("#cms_id").val(),
                                    cms_no:$("#cms_no").val(),
                                    cms_pname:$("#cms_pname").val(),
                                    cms_fname:$("#cms_fname").val(),
                                    cms_depart:$("#cms_depart").val()
                                },
                                success:function(msg){
                                    loadData();
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
                                    $('#EditModal').removeClass('was-validated');
                                    $('#EditModal').modal('hide');
                                }
                            });
                        }
                    }
                });
                
                
                 
            })
            $('#customerdetail tbody').on( 'click', '.edit_btn', function () {  
                $('#EditModal').modal('show');
                var row = table.row($(this).parents('tr')).data();
                $("#cms_id").val(row[1]);
                $("#cms_no").val(row[2]);
                $("#cms_pname").val(row[3]);
                $("#cms_fname").val(row[4]);
                $("#cms_depart").val(row[5]);
                
            } );
            
    
            $('#customerdetail tbody').on( 'click', '.del_btn', function () {  
                var row = table.row($(this).parents('tr')).data();
                Swal.fire({
                    title: 'คุณต้องการลบหรือไม่',
                    text: "คุณต้องการลบหรือไม่",
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
                                type:"deldetailcustomer",
                                cms_id:row[1]
                            },
                            success:function(msg){
                                
                                if(msg == "true"){
                                    Swal.fire(
                                    'ลบสำเร็จ',
                                    'ลบสำเร็จ',
                                    'success'
                                )
                                  
                                }else if(msg == "false"){
                                    Swal.fire(
                                    'ลบไม่สำเร็จ',
                                    'ลบไม่สำเร็จ',
                                    'error'
                                )
                                }
                                loadData();
                            }
                        });
                        
                        
                    }
                });
        
        
        
                
                
        
        
            } );
        }
    </script>
</html>
