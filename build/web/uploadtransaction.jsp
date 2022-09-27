<%-- 
    Document   : uploadtransaction
    Created on : 22 มิ.ย. 2564, 8:33:38
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
            <div class="container">
                <div class="btn-group" style="width:100%;margin-bottom:20px" role="group" aria-label="Basic radio toggle button group">
                  <input type="radio" class="btn-check" name="radiocontent" id="btnradio1" autocomplete="off" value="customer" onclick="togglemenu(this)">
                  <label class="btn btn-outline-success" for="btnradio1">อัพโหลดออเดอร์ รูปแบบพนักงาน</label>

                  <input type="radio" class="btn-check" name="radiocontent" id="btnradio3" autocomplete="off" value="department" onclick="togglemenu(this)">
                  <label class="btn btn-outline-success" for="btnradio3">อัพโหลดออเดอร์ รูปแบบสาขา</label>
                </div>
                
                <!-- Card 1 -->
                <div class="shadow p-3 mb-5 bg-body rounded"  id="card-customer">
                    <div class="card text-start">
                      <div class="card-header">
                          <h3>อัพโหลดออเดอร์ (รูปแบบพนักงาน)</h3>
                          <span style="color:red">คำแนะนำ..</span>
                          <br>
                          <span style="color:red">1. กรุณาอัพโหลดไฟล์ที่เป็นนามสกุล XLS เท่านั้น</span>
                          <br>
                          <span style="color:red">2. ไฟล์ที่อัพโหลดควรจะเป็นรูปแบบที่ดาวน์โหลดออกไปจากโปรแกรมนี้เท่านั้น</span>
                          <br>
                          <span style="color:red">3. ถ้าอัพโหลดไม่สำเร็จกรุณาติดต่อ Support</span>
                      </div>
                      <div>
                          
                      </div>
                      <div class="card-body" >
                        <form id="form-upload-transaction-customer" accept-charset="utf-8">
                            <div class="input-group mb-3">
                              <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1">ป้อนชื่อสำหรับการอัพโหลด</span>
                              </div>
                              <input type="text" 
                                     class="form-control" 
                                     placeholder="" 
                                     name="topiccustomer"
                                     id="topiccustomer"
                                     aria-describedby="basic-addon1">
                            </div>
                            <div class="input-group">
                                  <input 
                                    type="file" 
                                    class="form-control" 
                                    aria-describedby="inputGroupFileAddon04" 
                                    aria-label="Upload"
                                    name="transactionCustomer"
                                    id="transactionCustomer"
                                    accept=".xls">
                                  <button 
                                    class="btn btn-outline-primary" 
                                    type="button" 
                                    style="width:200px"
                                    id="btn-upload-transaction-customer"
                                    onclick="uploadFileCustomer()">
                                        อัพโหลด
                                  </button>
                            </div>
                        </form>
                      </div>
                    </div>
                </div>
                
                <!-- Card 2 -->
                <div class="shadow p-3 mb-5 bg-body rounded" id="card-department">
                    <div class="card text-start">
                      <div class="card-header">
                          <h3>อัพโหลดออเดอร์ (รูปแบบสาขา)</h3>
                          <span style="color:red">คำแนะนำ..</span>
                          <br>
                          <span style="color:red">1. กรุณาอัพโหลดไฟล์ที่เป็นนามสกุล XLS เท่านั้น</span>
                          <br>
                          <span style="color:red">2. ไฟล์ที่อัพโหลดควรจะเป็นรูปแบบที่ดาวน์โหลดออกไปจากโปรแกรมนี้เท่านั้น</span>
                          <br>
                          <span style="color:red">3. ถ้าอัพโหลดไม่สำเร็จกรุณาติดต่อ Support</span>
                      </div>
                      <div>
                          
                      </div>
                      <div class="card-body">
                        <form id="form-upload-transaction-department" accept-charset="utf-8">
                            <div class="input-group mb-3">
                              <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1">ป้อนชื่อสำหรับการอัพโหลด</span>
                              </div>
                              <input type="text" 
                                     class="form-control" 
                                     placeholder="" 
                                     name="topicdepartment"
                                     id="topicdepartment"
                                     aria-describedby="basic-addon1"
                                     >
                            </div>
                            <div class="input-group">
                                  <input 
                                    type="file" 
                                    class="form-control" 
                                    aria-describedby="inputGroupFileAddon04" 
                                    aria-label="Upload"
                                    name="transactionDepartment"
                                    id="transactionDepartment"
                                    accept=".xls">
                                  <button 
                                    class="btn btn-outline-primary" 
                                    type="button" 
                                    style="width:200px"
                                    id="btn-upload-transaction-department"
                                    onclick="uploadFileDepartment()">
                                        อัพโหลด
                                  </button>
                            </div>
                        </form>
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
            togglemenu("");
        })
        
        function uploadFileCustomer(){
            var transactionForm = new FormData(document.getElementById("form-upload-transaction-customer"));
            var filename = document.getElementById("transactionCustomer").value;
            var check = confirm("กรุณาตรวจสอบไฟล์ที่ทำการอัพโหลดตามคำแนะนำ\nหากทำการตรวจสอบแล้วให้กด ตกลง ");
            
            if(check == true){
                if(filename === ""){
                    alert("กรุณาเลือกไฟล์ก่อนทำการอัพโหลดไฟล์");
                        return false;
                } else {
                    if(/([\u0E00-\u0E7F]+)/.test(filename)){
                        alert("ชื่อไฟล์มีภาษาไทย กรุณาทำการตรวจสอบก่อนอัพโหลดอีกครั้ง");
                                return false;
                    } else {
                        $.ajax({
                            type : "POST",
                            encType : "multipart/form-data",
                            url : "UploadTransactionCustomer",
                            cache : false,
                            processData : false,
                            contentType : false,
                            data : transactionForm,
                            success : function(data){
                                const json = JSON.parse(data);
                                link = document.createElement("a");
                                link.href = "TransactionCustomer?doc_id="+json.doc_id;
                                document.body.appendChild(link);
                                link.click();
                            },
                            error : function(){

                            },
                            done:function(){
                                document.body.removeChild(link);
                                delete link;
                            }
                        })
                    }
                }
            } else {
               return false;
            }
        }
        
        function uploadFileDepartment(){
            var transactionForm = new FormData(document.getElementById("form-upload-transaction-department"));
            var filename = document.getElementById("transactionDepartment").value;
            var confirmbox = confirm("กรุณาตรวจสอบไฟล์ที่ทำการอัพโหลดตามคำแนะนำ\nหากทำการตรวจสอบแล้วให้กด ตกลง ");
            
            if(confirmbox == true){
                if(filename === ""){
                    alert("กรุณาเลือกไฟล์ก่อนทำการอัพโหลดไฟล์");
                    return false;
                } else {
                    if(/([\u0E00-\u0E7F]+)/.test(filename)){
                        alert("ชื่อไฟล์มีภาษาไทย กรุณาทำการตรวจสอบก่อนอัพโหลดอีกครั้ง");
                        return false;
                    } else {
                            $.ajax({
                                type : "POST",
                                encType : "multipart/form-data",
                                url : "UploadTransactionDepartment",
                                cache : false,
                                processData : false,
                                contentType : false,
                                data : transactionForm,
                                success : function(data){
                                    const json = JSON.parse(data);
                                    link = document.createElement("a");
                                    link.href = "TransactionDepartment?doc_id="+json.doc_id;
                                    document.body.appendChild(link);
                                    link.click();
                                },
                                error: function(){

                                },
                                done:function(){
                                    document.body.removeChild(link);
                                    delete link;
                                }
                            })
                    }
                }
            } else {
                return false;
            }
        }

        function togglemenu(event){
            var content = event.value;
                if(content==="customer"){
                    $("#transactionDepartment").attr("disabled",true);
                    $("#topicdepartment").attr("disabled",true);
                    $("#btn-upload-transaction-department").attr("disabled",true);
                    $("#card-customer").attr('style','background-color: #198754 !important');
                    $("#transactionCustomer").attr("disabled",false);
                    $("#topiccustomer").attr("disabled",false);
                    $("#btn-upload-transaction-customer").attr("disabled",false);
                    $("#card-department").removeAttr("style");
                } else if (content==="department"){
                    $("#transactionCustomer").attr("disabled",true);
                    $("#topiccustomer").attr("disabled",true);
                    $("#btn-upload-transaction-customer").attr("disabled",true);
                    $("#card-department").attr('style','background-color: #198754 !important');
                    $("#transactionDepartment").attr("disabled",false);
                    $("#topicdepartment").attr("disabled",false);
                    $("#btn-upload-transaction-department").attr("disabled",false);
                    $("#card-customer").removeAttr("style");
                } else {
                    $("#transactionDepartment").attr("disabled",true);
                    $("#topicdepartment").attr("disabled",true);
                    $("#btn-upload-transaction-department").attr("disabled",true);
                    $("#transactionCustomer").attr("disabled",true);
                    $("#topiccustomer").attr("disabled",true);
                    $("#btn-upload-transaction-customer").attr("disabled",true);
                }
        }
    </script>
</html>
