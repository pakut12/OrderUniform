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
        <div class="container">
            <%
            List<OUTransactionDepartmentDetail> detail = (List<OUTransactionDepartmentDetail>) request.getAttribute("detail_doc");
            List<OUSummaryOrderByDepartment> summarybydepartment = (List<OUSummaryOrderByDepartment>) request.getAttribute("summaryByDepartment");
            List<OUSummaryOrderDepartmentByMaterialAndSize> summarybymaterialsize = (List<OUSummaryOrderDepartmentByMaterialAndSize>) request.getAttribute("summarybysize");
            HashMap<String, Integer> totalbysize = (HashMap<String, Integer>) request.getAttribute("totalbysize");
            %>
            <br>
            <div class="shadow-none p-3 mb-5 bg-light rounded">
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
                            สรุปยอดเรียงตามรายชื่อแผนก
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
                        <div class="tab-pane fade show active" id="detail" role="tabpanel" aria-labelledby="detail-tab">
                            <br>
                            <table id="documentdetail">
                                <thead>
                                    <tr>
                                        <th>ลำดับ</th>
                                        <th>หน่วยงาน</th>
                                        <th>ชื่อสินค้า</th>
                                        <th>รหัสสินค้า</th>
                                        <th>สี</th>
                                        <th>ไซส์</th>
                                        <th>รหัสสินค้า 18 หลัก</th>
                                        <th>barcode</th>
                                        <th>จำนวน</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for (int i = 0; i <= detail.size() - 1; i++) {%>
                                    <tr>
                                        <td><%= i + 1%></td>
                                        <td>
                                            <b>หน่วยงาน 1 : </b><%=detail.get(i).getAgency()%>
                                            <br>
                                            <b>หน่วยงาน 2 : </b><%=detail.get(i).getDivision()%>
                                            <br>
                                            <b>หน่วยงาน 3 : </b><%=detail.get(i).getDepartmentname()%>
                                        </td>
                                        <td><%=detail.get(i).getMaterialdesc()%></td>
                                        <td><%=detail.get(i).getMaterialname()%></td>
                                        <td><%=detail.get(i).getMaterialcolor()%></td>
                                        <td><%=detail.get(i).getMaterialsize()%></td>
                                        <td style="color:green"><%=detail.get(i).getMaterialfullname()%></td>
                                        
                                        <% if (detail.get(i).getBarcode() == null || detail.get(i).getBarcode() == "") {%>
                                        <td style="color:red">ยังไม่มี Barcode</td>
                                        <% } else {%>
                                        <td><%=detail.get(i).getBarcode()%></td>
                                        <% }%>
                                        
                                        <td><%=detail.get(i).getMaterialquantity()%> ตัว</td>
                                    </tr>
                                    <% }%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- document detail -->
                    <div class="tab-pane fade" id="summary" role="tabpanel" aria-labelledby="profile-tab">
                        <br>
                        <div>
                            <table class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th scope="col">ลำดับ</th>
                                        <th scope="col">ฝ่าย</th>
                                        <th scope="col">จำนวน</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
            int totalQuantity = 0;
            for (int j = 0; j <= summarybydepartment.size() - 1; j++) {
                totalQuantity += Integer.parseInt(summarybydepartment.get(j).getSummary());
                                    %>
                                    <tr>
                                        <td><%=j + 1%></td>
                                        <td>
                                            หน่วยงาน 1 : <%=summarybydepartment.get(j).getAgency()%>
                                            <br>
                                            หน่วยงาน 2 :  <%=summarybydepartment.get(j).getDivision()%>
                                            <br>
                                            หน่วยงาน 3 :  <%=summarybydepartment.get(j).getDepartname()%>
                                        </td>
                                        <td><%=summarybydepartment.get(j).getSummary()%> ตัว </td>
                                    </tr>
                                    <%}%>
                                </tbody>
                                <tfooter>
                                    <tr>
                                        <td colspan = "2" style="text-align:end">รวมทั้งหมด</td>
                                        <td ><%=totalQuantity%> ตัว </td>
                                    </tr>
                                </tfooter>
                            </table>
                        </div>
                    </div>
                    <!-- document sizesummary -->
                    <div class="tab-pane fade" id="sizesummary" role="tabpanel" aria-labelledby="profile-tab">
                        <br>
                        <div>
                            <table class="table  table-bordered">
                                <%
            String tempMaterialID = "";
            for (int k = 0; k <= summarybymaterialsize.size() - 1; k++) {
                if (!summarybymaterialsize.get(k).getMaterialname().equals(tempMaterialID)) {
                    tempMaterialID = summarybymaterialsize.get(k).getMaterialname();
                                %>
                                <thead>
                                    <tr>
                                        <th colspan="2" style="background-color:#ddd">
                                            รหัส : <%=summarybymaterialsize.get(k).getMaterialname()%> 
                                            <br>
                                            ชื่อ : <%=summarybymaterialsize.get(k).getMaterialdesc()%>
                                            <br>
                                            <u>ยอดรวมทั้งหมด</u> : <%=totalbysize.get(summarybymaterialsize.get(k).getMaterialname())%>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th>ไซส์</th>
                                        <th>จำนวน</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td><%=summarybymaterialsize.get(k).getMaterialsize()%></td>
                                        <td><%=summarybymaterialsize.get(k).getSummary()%> ตัว</td>
                                    </tr>
                                </tbody>
                                <%} else {%>
                                <tbody>
                                    <tr>
                                        <td><%=summarybymaterialsize.get(k).getMaterialsize()%></td>
                                        <td><%=summarybymaterialsize.get(k).getSummary()%> ตัว</td>
                                    </tr>
                                </tbody>    
                                <%}%>
                                <%}%>
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
        $(document).ready(function(){
              $("#btn-excel").click(function(){ 
                const queryString = window.location.search;
                const urlParams = new URLSearchParams(queryString);
                const doc_id = urlParams.get('doc_id');
                window.open('report/Department/ReportPdf.jsp?doc_id='+doc_id); 
                
            });
            var groupColumn = 1;
            $('#documentdetail').DataTable({
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
            

        });
        function updateBarcode(docid){
           
            $.post("Barcode",{
                documentID : docid,
                type:"Department"
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
