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
        <div id="default-layout">
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
                                    '<tr class="group" style="background-color:#ddd"><td colspan="5">'+group+'</td></tr>'
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
        }
    </script>
</html>
