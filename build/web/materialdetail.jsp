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
            if(pm == null || !pm.getRole().equals("1")){
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
            var groupColumn = 9;
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
                                    '<tr class="group" style="background-color:#ddd"><td colspan="9">'+group+'</td></tr>'
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
