<%-- 
    Document   : addcompany
    Created on : 8 มิ.ย. 2564, 15:03:20
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
            <div class="container">
                <div class="row">
                    <div class="col-1">
                        <img src="css/bootstrap-icons-1.5.0/building.svg" 
                             alt="Bootstrap" 
                             width="100%" 
                             height="100%">
                    </div>
                    <div class="col">
                        <h1>จัดการข้อมูล<h3>(รายชื่อบริษัท)</h3></h1>
                    </div>
                </div>
                <% if (request.getAttribute("statuscreatenew") != null && !request.getAttribute("statuscreatenew").equals("")) {
                if (request.getAttribute("statuscreatenew").equals("createsuccess")) {%>
                <div class="statusCreateSuccess">
                    <h3>เพิ่มข้อมูลบริษัทสำเร็จ...</h3>
                </div>
                <% } else if (request.getAttribute("statuscreatenew").equals("createfail")) {%>
                <div class="statusCreateFail">
                    <h3>เพิ่มข้อมูลบริษัทไม่สำเร็จ!! กรุณาตรวจสอบข้อมูลว่ามีอยู่แล้วหรือไม่</h3>
                </div>
                <% }%>
                <% }%>
                <hr>
                <div>
                    <div class="modal fade" id="EditCompanyModal" tabindex="-1" aria-labelledby="EditCompanyModal" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">เเก้ไข</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <form class="myform1">
                                            <div class="col-10 mx-auto">
                                                <div class="row align-items-center mb-3 ">
                                                    <label for="IDText" class="col-md-3 col-form-label text-end">รหัสบริษัท</label>
                                                    <div class="col-sm-12 col-md-9  "><input class="form-control form-control-sm text-center" id="IDText" value="" readonly></input></div>
                                                </div>
                                                <div class="row align-items-center mb-3">
                                                    <label for="IDText" class="col-md-3 col-form-label text-end">ชื่อบริษัท</label>
                                                    <div class="col-sm-12 col-md-9"><input class="form-control form-control-sm text-center" id="CompanyText" value="" ></input></div>
                                                </div>
                                                <div class="row align-items-center mb-3">
                                                    <label for="IDText" class="col-md-3 col-form-label text-end ">ประเภท</label>
                                                    <div class="col-sm-12 col-md-9">
                                                        <select class="form-select form-select-sm text-center" id="content_type">
                                                            <option value="customer">เเบบรายชื่อ</option>
                                                            <option value="department">เเบบเเผนก</option>
                                                        </select>
                                                    </div>
                                                </div>   
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary" id="bt-save">Save</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-primary " data-bs-toggle="modal" data-bs-target="#addCompanyModal">
                        เพิ่มข้อมูลบริษัท
                    </button>
                    
                    <!-- Modal -->
                    <div class="modal fade" id="addCompanyModal" tabindex="-1" aria-labelledby="addCompanyModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <form action="CompanyList" method="post" id="myform" class="needs-validation" novalidate>
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="addCompanyModalLabel">เพิ่มข้อมูลบริษัท</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="input-group">
                                            <input readonly type="text" class="form-control" name="prename" id="prename" autocomplete="off" id="companyname" value="บริษัท" >
                                        </div>
                                        
                                        <br>
                                        
                                        <div class="input-group">
                                            <span class="input-group-text" id="">ป้อนชื่อบริษัท</span>
                                            <input type="text" class="form-control" name="companyname" autocomplete="off" id="companyname" placeholder="ตัวอย่าง ประชาอาภรณ์ ฯลฯ" required>
                                            <input type="hidden" value="AddNew" name="type">
                                        </div>
                                        
                                        <br>
                                        
                                        <div class="input-group">
                                            <input readonly type="text" class="form-control" name="endname" id="endname" autocomplete="off" id="companyname" value="จำกัด">
                                        </div>
                                        
                                        <br>
                                        
                                        <span style="color:red">** เลือกกรณีเพิ่มชื่อเสริม</span>
                                        <div class="input-group">
                                            <div class="input-group-text">
                                                <input class="form-check-input mt-0" type="checkbox" value="" name="chkexten" id="chkexten" aria-label="Checkbox for following text input">
                                            </div>
                                            <input disabled type="text" class="form-control" value="" name="extensionname" id="extensionname" placeholder= "ตัวอย่าง (มหาชน),(แห่งประเทศไทย) ฯลฯ " aria-label="Text input with checkbox">
                                        </div>
                                        
                                        <br>
                                        
                                        <div class="btn-group" id="filtercontent">
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" name="content" value="customer" required>
                                                <label class="form-check-label" for="inlineRadio1">ใช้กับข้อมูล <br>(รูปแบบรายชื่อพนักงาน)</label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" name="content" value="department" required>
                                                <label class="form-check-label" for="inlineRadio2">ใช้กับข้อมูล <br>(รูปแบบแผนก)</label>
                                            </div>
                                            <input type="hidden" id="cotent_name" name="cotent_name" value="">
                                        </div>
                                        
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">ยกเลิก</button>
                                        <button type="submit" class="btn btn-primary" >ยืนยัน</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>    
                <br>
                <div id="contentTable">
                    <!-- display table -->
                </div>
            </div>
        </div>
    </body>
    <footer>
        <%@ include file = "share/footer.jsp" %>
    </footer>
    <script>
        // Example starter JavaScript for disabling form submissions if there are invalid fields
        (function () {
            'use strict'

            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.querySelectorAll('.needs-validation')

            // Loop over them and prevent submission
            Array.prototype.slice.call(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }

                    form.classList.add('was-validated')
                }, false)
            })
        })()
    </script>
    
    <script language=javascript>
        
        
        $( document ).ready(function() {
            
            $("#bt-save").click(function(){
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
                        $(".myform1").addClass("was-validated") ;       
                        var comp_code = $("#IDText").val();
                        var comp_name = $("#CompanyText").val();
                        var content_type = $("#content_type").val(); 
                
                        $.ajax({
                            type:"post",
                            url:"CompanyList",
                            data:{
                                type:"EditCompany",
                                comp_code:comp_code,
                                comp_name:comp_name,
                                content_type:content_type
                            },
                            success:function(msg){
                                if(msg.toString() == 'true'){
                                    Swal.fire({
                                        icon: 'success',
                                        title: 'บันทึกสำเร็จ',
                                        text: 'บันทึกสำเร็จ'
                                    }) 
                                }else if(msg.toString() == "false"){
                                    Swal.fire({
                                        icon: 'error',
                                        title: 'บันทึกไม่สำเร็จ',
                                        text: 'บันทึกไม่สำเร็จ'
                                    })  
                                }
                                getDetailCompany();
                            }
                        });
                        
                    }
                });
                
                 
               
            });
            
            getDetailCompany();
            
            $("#chkexten").on("click",function(){
                if($(this).is(':checked')){
                    $("#extensionname").prop('disabled',false);
                } else {
                    $("#extensionname").prop('disabled',true);
                }
            })
            
            $("#filtercontent input:radio").on("click",function(){
                var content = $(this).val();
                document.getElementById("cotent_name").value = content ;
            })
        });
        
        function getDetailCompany(){
            $.get("CompanyList",{
                type : "getDetailCompany"
            },
            function(result){
                document.getElementById("contentTable").innerHTML = result;
            }).done(function (){
                setDataTable();
              
            
            }).fail(function (){
                console.log("getCompanyName Failure!!!");
            })
        }
        
        function setDataTable(){
            var groupColumn = 2;
            var table = $('#table_company').DataTable({
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
                            '<tr class="group" style="background-color:#ddd"><td colspan="5">'+group+'</td></tr>'
                        );
                            last = group;
                        }
                    });
                }
            });
            
            $('#table_company tbody').on( 'click', 'tr.group', function() {
                var currentOrder = table.order()[0];
                if ( currentOrder[0] === groupColumn && currentOrder[1] === 'asc' ){
                    table.order( [ groupColumn, 'desc' ] ).draw();
                } else {
                    table.order( [ groupColumn, 'asc' ] ).draw();
                }
            });
           
            $('#table_company tbody').on( 'click', '.edit_btn', function () {  
                var row = table.row($(this).parents('tr')).data();
                
              
                $("#IDText").val(row[0]);
                $("#CompanyText").val(row[1]);
        
            } );
            
            $('#table_company tbody').on( 'click', '.del_btn', function () {  
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
                            url:"CompanyList",
                            data:{
                                type:"DeleteCompany",
                                comp_code:row[0]  
                            },
                            success:function(msg){ 
                               
                                if(msg.toString() == 'true'){
                                    Swal.fire({
                                        icon: 'success',
                                        title: 'บันทึกสำเร็จ',
                                        text: 'บันทึกสำเร็จ'
                                    }) 
                                }else if(msg.toString() == "false"){
                                    Swal.fire({
                                        icon: 'error',
                                        title: 'บันทึกไม่สำเร็จ',
                                        text: 'บันทึกไม่สำเร็จ'
                                    })  
                                }
                                getDetailCompany();
                                 
                            }
                        });
                        
                    }
                });
                  


            } );
    
           
        }
    </script>
</html>
