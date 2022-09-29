<%-- 
    Document   : manageorder
    Created on : 8 มิ.ย. 2564, 14:47:47
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
                <strong>กำลังทำการสร้างไฟล์ Excel กรุณารอซักครู่...</strong>
            </div>
        </div>
        <div id="default-layout">
            <div class="container">
                <div class="row" style="height:100%">
                    <div class="col-3">
                            <div class="row">
                                <div class="col" style="text-align:center">
                                    <div class="btn-group" role="group" aria-label="Basic example">
                                      <button type="button" 
                                              class="btn btn-secondary" 
                                              onclick="selectContent(this)" 
                                              value="customer">
                                              รูปแบบพนักงาน
                                      </button>
                                      &nbsp;
                                      <button 
                                              type="button" 
                                              class="btn btn-secondary" 
                                              onclick="selectContent(this)" 
                                              value="department">
                                              รูปแบบแผนก
                                      </button>
                                    </div>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col" style="height:60vh">
                                        <div class="col">
                                        <div class="list-group" id="list-tab" role="tablist">

                                        </div>
                                      </div>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col">
                                    <button style="width:100%" 
                                            id="btn-generate" 
                                            class="btn btn-success" 
                                            disabled 
                                            onclick="generateExcelFile()">
                                        Generate XLS File.
                                    </button>
                                </div>
                            </div>
                    </div>
                    <div class="col-9">
                        <div class="shadow-lg p-3 mb-5 bg-body rounded">
                            <!--<iframe id="displayXLS" width="100%" height="100%"> </iframe>-->
                            <div id="display_generate_input_table">
                                
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
        
        function setupOrderDetail(event){
            $('#btn-generate').attr("disabled",false);
            $.get("Order",{
                type : "setupOrderDetail",
                companyid : event.value
            },function(result){
                document.getElementById("display_generate_input_table").innerHTML = result;
            }).done(function(){
                
            }).fail(function(){
                console.log("เกิดข้อผิดพลาด...");
            })
        }                                
                                        
        function testsendtoIframe(event){
            var iframe = document.getElementById("displayXLS");
            iframe.contentWindow.document.body.innerHTML = event.value;
        }
        
        function selectContent(event){
            $.get("Order",{
                type : "selectedcontent",
                contentname : event.value
            },function(result){
                document.getElementById("list-tab").innerHTML = result;
            })
        }
        
        function generateExcelFile(){
            var filename , link;
            $.ajax({
                url : "Order",
                type : "get",
                data : { type : "generatexls"},
                async : false,
                beforeSend : function (){
                    $("#loadscreen").removeAttr("id");
                },
                success : function(path){
                    if(path === "data not found"){
                        alert("ไม่มีข้อมูลให้ดาวน์โหลด!! กรุณาทำการเพิ่มข้อมูลมาสเตอร์ก่อน");
                        $('div.d-flex.flex-column.align-items-center.justify-content-center').attr('id', 'loadscreen');
                    } else {
                        filename = path.split("/");
                        link = document.createElement("a");
                        link.href = path;
                        link.download = filename[5];
                        document.body.appendChild(link);

                        setTimeout(function(){
                            $('div.d-flex.flex-column.align-items-center.justify-content-center').attr('id', 'loadscreen');
                            link.click();
                            document.body.removeChild(link);
                            delete link; 
                        },10000);
                    }
                },
                error: function(){
                    $('div.d-flex.flex-column.align-items-center.justify-content-center').attr('id', 'loadscreen');
                    console.log("Generate XLS Failure...");
                }
            })
  
        }
    </script>
</html>
