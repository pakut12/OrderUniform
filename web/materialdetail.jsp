<%-- 
    Document   : addmaterial
    Created on : 11 มิ.ย. 2564, 15:08:25
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
        <div >
            <div class="container">
                
                <div class="modal fade" id="EditModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="exampleModalLabel">เเก้ไขข้อมูล</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="row">
                                    <div class="col-4">
                                        <div class="row mb-3">
                                            <label for="addcmsno" class="text-center">รหัสสินค้า</label>
                                            <input class="form-control form-control-sm  w-75 mx-auto text-center" id="Hmat_code" name="Hmat_code" readonly ></input>
                                        </div>
                                        <div class="row mb-3">
                                            <label for="addcmsno" class="text-center">ธุรกิจ TeamOrder</label>
                                            <input class="form-control form-control-sm  w-75 mx-auto text-center" id="Hmat_team" name="Hmat_team"  required></input>
                                        </div>
                                        <div class="row mb-3">
                                            <label for="addpname" class="text-center">คำอธิบายเพิ่มเติม</label>
                                            <input class="form-control form-control-sm  w-75 mx-auto text-center" id="Hmat_desc" name="Hmat_desc" required></input>
                                        </div>
                                        
                                    </div>
                                    <div class="col-4">
                                        <div class="row mb-3">
                                            <label for="addfname" class="text-center">เพศ</label>
                                            <input class="form-control form-control-sm  w-75 mx-auto text-center" id="Hmat_gender" name="Hmat_gender" required></input>
                                        </div>
                                        <div class="row mb-3">
                                            <label for="adddepart" class="text-center">ชนิดสินค้า</label>
                                            <input class="form-control form-control-sm  w-75 mx-auto text-center" id="Hmat_category" name="Hmat_category" required></input>
                                        </div>
                                        <div class="row mb-3">
                                            <label for="addcompany" class="text-center">ประเภท</label>
                                            <input class="form-control form-control-sm  w-75 mx-auto text-center" id="Hmat_type" name="Hmat_type" required></input>
                                        </div>
                                    </div>
                                    <div class="col-4">
                                        <div class="row mb-3">
                                            <label for="addcompany" class="text-center">ลายผ้า</label>
                                            <input class="form-control form-control-sm  w-75 mx-auto text-center" id="Hmat_pattern" name="Hmat_pattern" required></input>
                                        </div>
                                        <div class="row mb-3">
                                            <label for="addcompany" class="text-center">Running No.</label>
                                            <input class="form-control form-control-sm  w-75 mx-auto text-center" id="Hmat_rno" name="Hmat_rno" required></input>
                                        </div>
                                        <div class="row mb-3">
                                            <label for="addcompany" class="text-center">สี</label>
                                            <input class="form-control form-control-sm  w-75 mx-auto text-center" id="Hmat_color" name="Hmat_color" required></input>
                                        </div>
                                    </div>
                                </div>
                                
                                
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary" id="EditSave" >Save</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-1">
                        <img src="css/bootstrap-icons-1.5.0/clipboard-data.svg" 
                             alt="Bootstrap" 
                             width="100%" 
                             height="100%">
                    </div>
                    <div class="col-3">
                        <h1>รายละเอียด<h3>Items</h3></h1>
                    </div>
                    <div class="col-8" style="text-align:right;">
                        <button style="height:100%"class="btn btn-outline-secondary" id="show-input-form">
                            <span>เพิ่ม Item </span>
                            <br>
                            <span>ใหม่</span>
                        </button>
                    </div>
                </div>
                <hr>
                <!--<form id="input_form">-->
                <div class="shadow-lg p-3 mb-5 bg-body rounded" id="input-group">
                    <div class="row">
                        <div class="col-6" >
                            <div class="row">
                                <!--<div class="col">
                                        <div class="input-group mb-3">
                                              <span class="input-group-text" id="basic-addon1">คำอธิบายเพิ่มเติม</span>
                                              <input 
                                                type="text" 
                                                class="form-control" 
                                                placeholder="ตัวอย่าง เสื้อแจ๊คเก็ตพาร์เก้า" 
                                                id="material_desc"
                                                name="material_desc">
                                        </div>
                                    </div>-->
                                <input type="hidden" name="mateiralcode" id="materialcode" value="">
                                <input type="hidden" name="materialspec" id="materialspec" value="">
                            </div>
                            <div class="row" >
                                <div class="input-group mb-3">
                                    <div class="col-4">
                                        <span class="input-group-text" id="basic-addon1">ธุรกิจ TeamOrder</span>
                                    </div>
                                    <input 
                                        type="text" 
                                        class="form-control" 
                                        maxlength="2"
                                        placeholder="ตัวอย่าง : 4T"
                                        id="OrderTeam"
                                        name="OrderTeam">
                                </div>
                            </div>
                            <div class="row" >
                                <div class="input-group mb-3">
                                    <div class="col-4">
                                        <span class="input-group-text" id="basic-addon1">เพศ</span>
                                    </div>
                                    <input 
                                        type="text" 
                                        class="form-control" 
                                        list="genderList" 
                                        placeholder="--- กรุณาเลือกเพศ ---"
                                        id="gender"
                                        name="gender">
                                    <datalist id="genderList">
                                        <option value="MEN">ชาย</option>
                                        <option value="WOMEN">หญิง</option>
                                    </datalist>
                                </div>
                            </div>
                            <div class="row" >
                                <div class="input-group mb-3">
                                    <div class="col-4">
                                        <span class="input-group-text" id="basic-addon1">ชนิดสินค้า</span>
                                    </div>
                                    <input 
                                        type="text" 
                                        class="form-control" 
                                        maxlength="2"
                                        placeholder="ตัวอย่าง : 42"
                                        id="category"
                                        name="category">
                                </div>
                            </div>
                            <div class="row" >
                                <div class="input-group mb-3">
                                    <div class="col-4">
                                        <span class="input-group-text" id="basic-addon1">ประเภท</span>
                                    </div>
                                    <input 
                                        type="text" 
                                        class="form-control" 
                                        maxlength="2"
                                        placeholder="ตัวอย่าง : S"
                                        id="material_type"
                                        name="material_type">
                                </div>
                            </div>
                            <div class="row" >
                                <div class="input-group mb-3">
                                    <div class="col-4">
                                        <span class="input-group-text" id="basic-addon1">ลายผ้า</span>
                                    </div>
                                    <input 
                                        type="text" 
                                        class="form-control" 
                                        maxlength="2"
                                        placeholder="ตัวอย่าง : 6"
                                        id="material_pattern"
                                        name="material_pattern">
                                </div>
                            </div>
                            <div class="row" >
                                <div class="input-group mb-3">
                                    <div class="col-4">
                                        <span class="input-group-text" id="basic-addon1">Running NO.</span>
                                    </div>
                                    <input 
                                        type="text" 
                                        class="form-control" 
                                        maxlength="3"
                                        placeholder="ตัวอย่าง : 125"
                                        id="running_number"
                                        name="running_number">
                                </div>
                            </div>
                            <div class="row" >
                                <div class="input-group mb-3">
                                    <div class="col-4">
                                        <span class="input-group-text" id="basic-addon1">Color</span>
                                    </div>
                                    <input 
                                        type="text" 
                                        class="form-control" 
                                        id="material_color"
                                        maxlength="2"
                                        placeholder="ตัวอย่าง : BL"
                                        name="material_color">
                                </div>
                            </div>
                            <div class="row" >
                                <div class="col-12">
                                    <button style="width:100%;" class="btn btn-outline-secondary" name="submit" onclick="generateMaterialCode()">สร้างรหัสสินค้า</button>
                                </div>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="col-12">
                                <div>
                                    <h5>ตัวอย่างรหัสสินค้า</h5>
                                </div>
                                <div class="input-group mb-3" id="displaymatcode">
                                    <!--<span class="input-group-text" id="basic-addon1">ตัวอย่างรหัสสินค้า</span>-->
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="input-group ">
                                    <span class="input-group-text" id="basic-addon1">คำอธิบายเพิ่มเติม</span>
                                    <input class="form-control form-control-sm" 
                                           autocomplete="off"
                                           name="material_desc" 
                                           id="material_desc"
                                           />
                                </div>
                            </div>
                            <br>
                            <div class="col-12">
                                <div class="input-group ">
                                    <span class="input-group-text" id="basic-addon1">เลือกบริษัท</span>
                                    <input class="form-control form-control-sm" 
                                           list="companylist" 
                                           placeholder="--- กรุณาทำการเลือกบริษัท ---"
                                           autocomplete="off"
                                           name="companyname" 
                                           id="companyname"
                                           />
                                    <datalist id="companylist">
                                        <!-- option generate by js -->
                                    </datalist>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-6">
                                    <button style="width:100%;" class="btn btn-outline-primary" name="submit" onclick="addNewMaterial()">บันทึก</button>
                                </div>
                                <div class="col-6">
                                    <button style="width:100%;" class="btn btn-outline-danger" name="cleartext" onclick="clearInputAll()">เคลียร์ข้อมูล</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--</form>-->
                <div class="shadow-none p-3 mb-5 bg-light rounded">
                    <div id="content-table">
                        <!-- display detail material-->
                    </div>
                </div>
            </div>
        </div>
    </body>
    
    <footer>
        <%@ include file = "share/footer.jsp" %>
    </footer>
    
    <script languge="javascript">
        var statusInputform = false;
        
        $(document).ready(function(){
            $("#input-group").hide();
            getCompanyName();
            loadMaterialDetail();
        });
        
        $("#show-input-form").click(function() {
            if(statusInputform){
                $("#input-group").hide(800);
                statusInputform = false;
            } else {
                $("#input-group").show(800);
                statusInputform = true;
            }
        });
        
        function getCompanyName(){
            $.get("CompanyList",{
                type : "getCompanyName"
            },
            function(result){
                var options = '';
                for(var i = 0 ; i < result.length ;i++){
                    options += '<option value="'+result[i].code+'">'+result[i].name+'</option>';
                }
                document.getElementById('companylist').innerHTML = options;
            }).done(function (){
                //do nothing...
            }).fail(function (){
                console.log("getCompanyName Failure!!!");
            })
        }
        
        function clearInputAll(){
            document.getElementById("material_name").value = "";
            document.getElementById("material_color").value = "";
            document.getElementById("material_desc").value = "";
            document.getElementById("companyname").value="";
        }
        
        function addNewMaterial(){
            
            var matcode = document.getElementById("materialcode").value;
            var matspec = document.getElementById("materialspec").value;
            var matdesc = document.getElementById("material_desc").value;
            var companyname = document.getElementById("companyname").value;
            
            if(matcode === ""){
                alert("กรุณาทำการสร้างรหัสสินค้าก่อนบันทึก!");
                return false;
            } else {
                if(companyname === ""){
                    alert("กรุณาทำการเลือกบริษัทก่อนทำการกดบันทึก!");
                    return false;
                } else {
                    $.post("Material",{
                        type : "createnew",
                        code : matcode,
                        desc : matdesc,
                        spec : matspec,
                        company : document.getElementById("companyname").value
                    },function(data){
                        var resultobj = JSON.parse(data);
                        if(resultobj.status == "Create Success"){
                            location.reload();
                        }
                    }).fail(function (){
                        alert("ไม่สามารถเพิ่มข้อมูลได้ กรุณาลองใหม่อีกครั้ง...");
                    })
                }
            }
        }
        
        function loadMaterialDetail(){
            $.get("Material",{
                type : "loaddata"
            },function(data){
                document.getElementById("content-table").innerHTML = data;
            }).done(function(){
                setupDataTables()
            });
        }
        
        function setupDataTables(){
            var groupColumn = 11;
            var table = $("#material_detail").DataTable({
                scrollY: '24vh',
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
                            '<tr class="group" style="background-color:#ddd"><td colspan="13">'+group+'</td></tr>'
                        );
                            last = group;
                        }
                    });
                }
            });
            
            $('#material_detail tbody').on( 'click', 'tr.group', function() {
                var currentOrder = table.order()[0];
                if ( currentOrder[0] === groupColumn && currentOrder[1] === 'asc' ){
                    table.order( [ groupColumn, 'desc' ] ).draw();
                } else {
                    table.order( [ groupColumn, 'asc' ] ).draw();
                }
            });
            $('#material_detail tbody').on( 'click', '.edit_btn', function () {  
                $('#EditModal').modal('show');
                var row = table.row($(this).parents('tr')).data();
                $("#Hmat_code").val(row[1]);
                $("#Hmat_team").val(row[2]);
                $("#Hmat_desc").val(row[3]);
                $("#Hmat_gender").val(row[4]);
                $("#Hmat_category").val(row[5]);
                $("#Hmat_type").val(row[6]);
                $("#Hmat_pattern").val(row[7]);
                $("#Hmat_rno").val(row[8]);
                $("#Hmat_color").val(row[9]);
               
            } );
            
            $('#material_detail tbody').on( 'click', '.del_btn', function () {  
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
                        console.log(row[0]);                
        
                        
                        $.ajax({
                            type:"post",
                            url:"Material",
                            data:{
                                type:"hmat_del",
                                hmat_id:row[0]
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
                                $.get("Material",{
                                    type : "loaddata"
                                },function(data){
                                    document.getElementById("content-table").innerHTML = data;
                                }).done(function(){
                                    setupDataTables()
                                });
                               
                            }
                        });
                        
                                              
                    }
                }); 
            } );
        }
        
        function generateMaterialCode(){
            // TeamOrder
            const orderTeam = document.getElementById("OrderTeam").value;
            
            // เพศ
            const gender = document.getElementById("gender").value;
            var formatgender ;
            if(gender == ""){
                alert("กรุณาทำการเลือกเพศ")
                return false;
            } else if (gender == "MEN"){
                formatgender = "M"
            } else if (gender == "WOMEN"){
                formatgender = "W"
            }
                                                    
            // ชนิดสินค้า
            const category = document.getElementById("category").value;
            
            //ประเภท
            const matType = document.getElementById("material_type").value;
            
            //ลายผ้า
            const matPat = document.getElementById("material_pattern").value;
            
            //Running NO.
            const runNum = document.getElementById("running_number").value;
            var formatRunNum ;
            if(runNum == ""){
                alert("กรุณาทำการกรอก Running NO.")
                return false;
            } else if(runNum.length == 1){
                formatRunNum = "00"+runNum;
            } else if (runNum.length == 2){
                formatRunNum = "0"+runNum;
            } else if (runNum.length == 3){
                formatRunNum = runNum;
            }
            
            //สี
            const matCol = document.getElementById("material_color").value;
            
                                                    
            const displaymaterialCode = orderTeam+"-"+formatgender+"-"+category+"-"+matType+"-"+matPat+"-"+formatRunNum+"-"+matCol;
                                    
            const materialCode = orderTeam+formatgender+category+matType+matPat+formatRunNum+matCol;
            
            document.getElementById("materialcode").value = materialCode;
            document.getElementById("materialspec").value = displaymaterialCode;
            document.getElementById("displaymatcode").innerHTML = "<h1>"+displaymaterialCode+"</h1>";
        }
    </script>
    
</html>
