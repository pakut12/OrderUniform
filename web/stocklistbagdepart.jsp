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
                            <div class="">พิมพ์สติ๊กเกอร์ (รายชื่อ)</div>
                        </div>
                        <div class="card-body">
                            <div class="card">
                                <div class="card-header">
                                    <div class="">ค้นหา</div>
                                </div>
                                <div class="card-body">
                                    <div class="row ">
                                        <div class="col-4 text-end">รหัสบาร์โค้ด (รายชื่อ) : </div>
                                        <div class="col-4">
                                            <form class="" id="myform">
                                                <input class="form-control form-control-sm " type="text" id="cus_no" value="" required></input>
                                            </form>
                                        </div>
                                        <div class="col-4">
                                            <button class="btn btn-sm btn-success" id="btn-getdata">ค้นหา</button>
                                            
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
                $("#myform").addClass("was-validated");
                
                if(data != ""){
                    $.ajax({
                        type:"post",
                        url:"GetDataStock",
                        data:{
                            type:"Depratgetdataformbarcodebag",
                            doc_id:data[0],
                            cus_no:data[1]
                        },
                        success:function(msg){
                            $(".viewdata").html(msg);
                            $("#listdata").DataTable();
                        }
                    });
                }
            });
            
        })

    </script>  
</html>
