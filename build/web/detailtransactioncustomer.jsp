<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.pg.lib.model.*" %>
<%@page import="java.util.*" %>

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
        <div class="container" >
            <%
            List<OUTransactionCustomerDetail> detail = (List<OUTransactionCustomerDetail>) request.getAttribute("detaildoc");
            List<OUSummaryOrderByCustomer> summary = (List<OUSummaryOrderByCustomer>) request.getAttribute("listSummaryByCustomer");
            List<OUSummaryOrderCustomerByMaterialAndSize> summaryQuantity = (List<OUSummaryOrderCustomerByMaterialAndSize>) request.getAttribute("listSummaryQuantity");
            HashMap<String, Integer> totalSummaryByMaterailID = (HashMap<String, Integer>) request.getAttribute("totalByMaterialid");
            %> 
            
            <br>
            <div class="shadow-none p-3 mb-5 bg-light rounded">
                <div class="shadow-lg p-3 mb-2 bg-body rounded">
                    <div class="row">
                        <div class="col-10">
                            <h1><u>ประเภทเอกสาร</u> : รูปแบบพนักงาน</h1>
                            <h2>เลขเอกสาร : <%=detail.get(0).getDocID()%> , ชื่อเอกสาร : <%=detail.get(0).getDocName()%> </h2>
                            <h4><%=detail.get(0).getCompanyname()%></h4> 
                        </div>
                        <div class="col-2">
                            <div style="text-align:center">                               
                                <button class="btn btn-outline-secondary w-100" onclick="updateBarcode(<%=detail.get(0).getDocID()%>)">
                                    <img src="css/bootstrap-icons-1.5.0/cloud-download.svg"
                                         alt="Bootstrap"
                                         width="20"
                                         height="20">
                                    รีโหลดบาร์โค๊ด
                                </button>
                                <button class="btn  btn-outline-success mt-3 w-100" value="<%=detail.get(0).getDocID()%>" id="btn-excel">Report</button>
                            </div>
                        </div>
                    </div>
                    
                </div>
                <hr>    
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <li class="nav-item" role="presentation">
                        <button 
                            class="nav-link active" 
                            id="detail-tab" 
                            data-bs-toggle="tab" 
                            data-bs-target="#detail" 
                            type="button" role="tab" 
                            aria-controls="detail" 
                            aria-selected="true">
                            รายละเอียดของเอกสาร
                        </button>
                    </li>
                    <li class="nav-item" role="presentation">
                        <button 
                            class="nav-link" 
                            id="summary-tab" 
                            data-bs-toggle="tab" 
                            data-bs-target="#summary" 
                            type="button" 
                            role="tab" 
                            aria-controls="summary" 
                            aria-selected="false">
                            สรุปยอดเรียงตามรายชื่อ
                        </button>
                    </li>
                    <li class="nav-item" role="presentation">
                        <button
                            class="nav-link"
                            id="summary-size"
                            data-bs-toggle="tab"
                            data-bs-target="#sizesummary"
                            type="button"
                            role="tab"
                            aria-controls="sizesummary"
                            aria-selected="false">
                            สรุปยอดเรียงตามไซส์
                        </button>
                    </li>
                </ul>
                <div class="tab-content" id="myTabContent">
                    <!-- document detail -->
                    <div class="tab-pane fade show active" id="detail" role="tabpanel" aria-labelledby="detail-tab">
                        <br>
                        
                        
                        <br>
                        <table id="documentDetail" class='table table-bordered w-100'>
                            <thead >
                                <tr >
                                    <th> ลำดับ </th>  
                                    <th> ชื่อ-นามสกุล </th>
                                    <th> ชื่อสินค้า</th>
                                    <th> รหัสสินค้า </th>
                                    <th> สี </th>
                                    <th> ไซส์ </th>
                                    <th> รหัสสินค้า 18 หลัก </th>
                                    <th> Barcode </th>
                                    <th> จำนวน </th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (int i = 0; i <= detail.size() - 1; i++) {%>
                                <tr>
                                    <td><%= i + 1%></td>
                                    <td>
                                        <div class="row">
                                            <div class="col-12">
                                                <b>รหัสพนักงาน  : </b><%=detail.get(i).getCustomerCode()%> 
                                                <br>
                                                <b>ชื่อ : </b> <%=detail.get(i).getPrename()%> <%=detail.get(i).getFname()%>
                                                <br> 
                                                <b>แผนก : </b> <%=detail.get(i).getDepartmentname()%>
                                            </div>
                                        </div>
                                        
                                    </td>
                                    <td><%=detail.get(i).getDesc()%></td>
                                    <td><%=detail.get(i).getMaterialname()%></td>
                                    <td><%=detail.get(i).getColor()%></td>
                                    <td><%=detail.get(i).getSize()%></td>
                                    <td style="color:green"><%=detail.get(i).getMatfullname()%></td>
                                    
                                    <% if (detail.get(i).getBarcode() == null || detail.get(i).getBarcode() == "") {%>
                                    <td style="color:red">ยังไม่มี Barcode</td>
                                    <% } else {%>
                                    <td><%=detail.get(i).getBarcode()%></td>
                                    <% }%>
                                    
                                    <td><%=detail.get(i).getQuantity()%> ตัว </td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                    <!-- document detail -->
                    <div class="tab-pane fade" id="summary" role="tabpanel" aria-labelledby="profile-tab">
                        <br>
                        <div>
                            <table class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th scope="col">ลำดับ</th>
                                        <th scope="col">รหัสพนักงาน</th>
                                        <th scope="col">ชื่อ - นามสกุล</th>
                                        <th scope="col">จำนวน</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% int totalsummary = 0;
            for (int j = 0; j <= summary.size() - 1; j++) {
                totalsummary += Integer.parseInt(summary.get(j).getQuantity());%>
                                    <tr>
                                        <td><%=j + 1%></td>
                                        <td><%=summary.get(j).getCustomerno()%></td>
                                        <td><%=summary.get(j).getCustomerprename()%> <%=summary.get(j).getCustomername()%></td>
                                        <td><%=summary.get(j).getQuantity()%> ตัว </td>
                                    </tr>
                                    <% }%>
                                </tbody>
                                <tfooter>
                                    <tr>
                                        <td colspan="3" style="text-align:end"><b>รวมทั้งหมด</b></td>
                                        <td ><%=totalsummary%> ตัว</td>
                                    </tr>
                                </tfooter>
                            </table>
                        </div>
                    </div>
                    <!-- document sizesummary -->
                    <div class="tab-pane fade" id="sizesummary" role="tabpanel" aria-labelledby="profile-tab">
                        <br>
                        <div>
                            <table class='table table-bordered w-100' >
                                <%  String tempmaterial = "";
            for (int k = 0; k <= summaryQuantity.size() - 1; k++) {
                if (!summaryQuantity.get(k).getMaterialid().equals(tempmaterial)) {
                    tempmaterial = summaryQuantity.get(k).getMaterialid();
                                %>
                                <thead>
                                    <tr>
                                        <th colspan="2"  style="background-color:#ddd">
                                            รหัส : <%=summaryQuantity.get(k).getMaterialname()%> 
                                            <br>
                                            ชื่อ : <%=summaryQuantity.get(k).getMaterialdesc()%>
                                            <br> 
                                            <u>ยอดรวมทั้งหมด <%=totalSummaryByMaterailID.get(summaryQuantity.get(k).getMaterialid())%> ตัว</u>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th>ไซส์</th>
                                        <th>จำนวน</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td><%=summaryQuantity.get(k).getMaterialsize()%> </td>
                                        <td><%=summaryQuantity.get(k).getQuantitySummary()%> ตัว </td>
                                    </tr>
                                </tbody>
                                <% } else {%>    
                                <tbody>
                                    <tr>
                                        <td><%=summaryQuantity.get(k).getMaterialsize()%></td>
                                        <td><%=summaryQuantity.get(k).getQuantitySummary()%> ตัว </td>
                                    </tr>
                                </tbody>
                                <% }%>
                                <% }%>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    
    <footer>
        <%@ include file = "share/footer.jsp" %>
    </footer>
    
    <script language=javascript>
        
        JsBarcode(".barcode").init();
        $(document).ready(function(){
            var groupColumn = 1;
            $('#documentDetail').DataTable({
                //scrollY: '45vh',
               
                scrollCollapse: true,
                "columnDefs":[
                    { "visible" : false, "targets": groupColumn }
                ],
                "order": [[0, 'asc'],[0, 'asc']],
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
            

            $("#btn-excel").click(function(){ 
                const queryString = window.location.search;
                const urlParams = new URLSearchParams(queryString);
                const doc_id = urlParams.get('doc_id');
                window.open('report/ReportPdf.jsp?doc_id='+doc_id); 
                
            });


        });
        
        function updateBarcode(docid){
           
            $.post("Barcode",{
                documentID : docid,
                type:"Customer"
            },
            function(result){
                
                if(result == "true"){
                    Swal.fire(
                    'ดึงสำเร็จ',
                    'ดึงสำเร็จ',
                    'success'
                )
                }else if(result == "false"){
                    Swal.fire(
                    'ดึงไม่สำเร็จ',
                    'ดึงไม่สำเร็จ',
                    'error'
                )
                    
                }
                location.reload();
                
            }).done(function(){
                
            }).fail(function(){
                
            })
        }
    </script>
</html>
