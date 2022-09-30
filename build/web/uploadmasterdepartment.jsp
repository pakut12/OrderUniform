<%-- 
    Document   : uploadmasterdepartment
    Created on : 1 มิ.ย. 2564, 14:45:49
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
        <div class="d-flex flex-column align-items-center justify-content-center" id="loadscreen">
            <div class="row">
              <div class="spinner-border" role="status">
                <span class="visually-hidden">Loading...</span>
              </div>
            </div>
            <br>
            <div class="row">
                <strong>กำลังทำการอัพโหลดข้อมูล กรุณารอซักครู่...</strong>
            </div>
        </div>
        <div id="default-layout">
            <form id="formUploadDepartment">
                <div class="container">
                    <div class="row">
                        <div class="col-1">
                            <img src="css/bootstrap-icons-1.5.0/people.svg" 
                                     alt="Bootstrap" 
                                     width="100%" 
                                     height="100%">
                        </div>
                        <div class="col">
                            <h3>อัพโหลดข้อมูล<h5>(รูปแบบรายชื่อแผนก)</h5></h3>
                        </div>
                    </div>
                    <hr>
                    <p class="warning-text">*** กรุณาทำการเลือกบริษัทก่อนทำงานอัพโหลดไฟล์</p>
                    <p class="warning-text">** ไฟล์ที่ทำงานอัพโหลดนั้น "ห้ามเป็นชื่อภาษาไทย และมีอักษรพิเศษภาษาไทย"</p>
                    <div class="input-group mb-3">
                        <h6 for="searchCompany" style="margin-bottom:0px; padding-top:8px">เลือกบริษัท :&nbsp;</h4>
                        <br>
                        <input class="form-control form-control-sm" 
                               list="companylist" 
                               autocomplete="off"
                               name="companyname" 
                               id="companyname"
                               />
                            <datalist id="companylist">
                                <!-- Gennerate by function getCompanyName -->
                            </datalist>
                       &nbsp;
                        <input type="file" class="form-control" name="file2" id="file2" accept=".xls">
                           <button 
                                class="btn btn-outline-secondary" 
                                type="button" 
                                id="btnUpload"
                                onclick="uploadFileDepartment()">
                                อัพโหลด
                           </button>
                    </div>
                    <div id="tableDisplay">
                        <!-- space for display data upload -->
                    </div>
                </div>
            </form>
        </div>
    </body>
    
    <footer>
        <%@ include file = "share/footer.jsp" %>
    </footer>
    
    <script language=javascript>
        $(document).ready(function(){
            getCompanyName();
        });
        
        function getCompanyName(){
           $.get("CompanyList",{
                type : "getCompanyName",
                content : "department"
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
        
        function uploadFileDepartment(){
            var dataformdepartment = new FormData(document.getElementById("formUploadDepartment"));
            var validCompany = document.getElementById("companyname").value;
            var filename = document.getElementById("file2").value;
            
                //เลือกบริษัท
                if(validCompany === ""){
                    alert("กรุณาเลือกบริษัทก่อนทำการอัพโหลดไฟล์");
                    return false;
                } else {
                    //เลือกไฟล์
                    if(filename === ""){
                        alert("กรุณาเลือกไฟล์ก่อนทำการอัพโหลดไฟล์");
                        return false;
                    } else {
                        //เช็คชื่อไฟล์ภาษาไทย
                        if(/([\u0E00-\u0E7F]+)/.test(filename)){
                            alert("ชื่อไฟล์มีภาษาไทย กรุณาทำการตรวจสอบก่อนอัพโหลดอีกครั้ง");
                            return false;
                        } else {
                             $.ajax({
                                    type : "POST",
                                    encType : "multipart/form-data",
                                    url : "UploadDepartment",
                                    cache : false,
                                    processData : false,
                                    contentType : false,
                                    data : dataformdepartment,
                                    success: function(data){
                                         document.getElementById("tableDisplay").innerHTML = data;
                                         setupDatatable();
                                         document.getElementById("btnUpload").disabled = true;
                                         alert("อัพโหลดสำเร็จ...");
                                    },
                                    error: function(){
                                        alert("เกิดข้อผิดพลาดในการอัพโหลด!! กรุณาลองใหม่อีกครั้ง");
                                        $('div.d-flex.flex-column.align-items-center.justify-content-center').attr('id', 'loadscreen');
                                    }
                                });
                        }//ชื่อภาษาไทย
                    }//เลือกไฟล์
                }//เลือกบริษัท
        }
        
        function setupDatatable(){
            $('#btnUpload').attr("disabled",true);
            //disable loadscreen
            $('div.d-flex.flex-column.align-items-center.justify-content-center').attr('id', 'loadscreen');
            
            $('#uploaddetail').DataTable();
        }
    </script>
</html>
