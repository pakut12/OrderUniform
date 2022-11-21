<%-- 
    Document   : ReportBag
    Created on : 15 พ.ย. 2565, 9:45:37
    Author     : pakutsing
--%>
<%@page import="com.pg.lib.service.TransactionCustomerService" %>
<%@page import="com.pg.lib.model.OUTransactionCustomerDetail" %>
<%@page import="com.pg.lib.model.OUSummaryOrderByCustomer" %>
<%@page import="com.pg.lib.service.CustomerService" %>
<%@page import="java.util.*" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <!--<link rel="icon" href="image/PG.gif" type="image/gif" sizes="16x16">-->
        <script type="text/javascript" src="../js/jquery-1.7.1.js"></script>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css">
        <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
        <script src="https://cdn.datatables.net/buttons/2.2.3/js/dataTables.buttons.min.js"></script>
        <script src="https://cdn.datatables.net/buttons/2.2.3/js/buttons.bootstrap5.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
        <script src="https://cdn.datatables.net/buttons/2.2.3/js/buttons.html5.min.js"></script>
        <script src="https://cdn.datatables.net/buttons/2.2.3/js/buttons.print.min.js"></script>
        <script src="https://cdn.datatables.net/buttons/2.2.3/js/buttons.colVis.min.js"></script>
        <script src="https://cdn.datatables.net/responsive/2.3.0/js/dataTables.responsive.min.js"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.3.0/css/responsive.dataTables.min.css">
        <link type="text/css" href="//gyrocode.github.io/jquery-datatables-checkboxes/1.2.12/css/dataTables.checkboxes.css" rel="stylesheet" />
        <script type="text/javascript" src="//gyrocode.github.io/jquery-datatables-checkboxes/1.2.12/js/dataTables.checkboxes.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/select/1.4.0/js/dataTables.select.min.js"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/select/1.4.0/css/select.dataTables.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/fixedcolumns/4.1.0/css/fixedColumns.dataTables.min.css">
        <script type="text/javascript" src="https://cdn.datatables.net/fixedcolumns/4.1.0/js/dataTables.fixedColumns.min.js"></script>  
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
        
        <script src="https://cdn.jsdelivr.net/npm/jsbarcode@3.11.0/dist/JsBarcode.all.min.js"></script>
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        
        
    </head>
    <style>
        @media screen {
            div.divFooter {
                display: none;
            }
            div.divHeader  {
                display: none;
            }
        }
        @media print{
            
            .page-number {
                content: "Page " counter(page) " of " counter(pages);
            }
            body{
                font-size: 10px;
            }
            .code{
                width:"50px",
                height"25px"
            }
            table.report-container {
                page-break-after:always;
                
            }
            thead.report-header {
                display:table-header-group;
                
            }
            tfoot.report-footer {
                display:table-footer-group;
            } 
            
            
        }
        .page-break
        {  
            page-break-after:always;
        }
    }
        
        
    </style>
    <body>
        <%
            String doc_id = request.getParameter("doc_id");
            String cus_no = request.getParameter("cus_no");

            TransactionCustomerService s_trancustomer = new TransactionCustomerService();
            List<OUTransactionCustomerDetail> detail = s_trancustomer.getDetailTransactionByDocumentId(doc_id);
        %>
        <table id="listdata" class="table table-bordered text-center w-100   border-dark">
            <thead >
                <tr >
                    <th colspan="8" class="h3"> 
                        <b>รายชื่อพนักงาน :    </b><%=detail.get(0).getCompanyname()%> 
                    </th>  
                    <th class="text-center"> 
                        <img id="barcode" 
                             width="100px"
                             height="30px"
                             jsbarcode-value="<%=doc_id%>"
                             </img>
                    </th>  
                </tr>
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
                <%
            int sum = 0;
            for (int i = 0; i <= detail.size() - 1; i++) {
                sum += Integer.valueOf(detail.get(i).getQuantity().toString());
                %>
                <tr>
                    <td class="p-0 pt-1"><%= i + 1%></td>
                    <td class="p-0 pt-1">
                        <div class="row">
                            <div class="col-6 text-start ">
                                <b>รหัสพนักงาน  : </b><%=detail.get(i).getCustomerCode()%> 
                                <br>
                                <b>ชื่อ : </b> <%=detail.get(i).getPrename()%> <%=detail.get(i).getFname()%>
                                <br> 
                                <b>แผนก : </b> <%=detail.get(i).getDepartmentname()%>
                            </div>
                            <div class="col-6 text-end">
                                <img id="barcode" 
                                     width="100px"
                                     height="30px"
                                     jsbarcode-value="<%=doc_id%>/<%=detail.get(i).getCustomerCode()%>"
                                     </img>
                            </div>
                        </div> 
                    </td>
                    <td class="p-0 pt-1"><%=detail.get(i).getDesc()%></td>
                    <td class="p-0 pt-1"><%=detail.get(i).getMaterialname()%></td>
                    <td class="p-0 pt-1"><%=detail.get(i).getColor()%></td>
                    <td class="p-0 pt-1"><%=detail.get(i).getSize()%></td>
                    <td style="color:green" class="p-0 pt-1"><%=detail.get(i).getMatfullname()%></td>
                    
                    <% if (detail.get(i).getBarcode() == null || detail.get(i).getBarcode() == "") {%>
                    <td style="color:red" class="p-0 pt-1"> 
                        <img id="barcode" 
                             width="100px"
                             height="20px"
                             jsbarcode-value="<%=detail.get(i).getMatfullname()%>"
                             </img>
                    </td>
                    <% } else {%>
                    <td><%=detail.get(i).getBarcode()%></td>
                    <% }%>
                    
                    <td><%=detail.get(i).getQuantity()%> ตัว </td>
                </tr>
                
                <%}%>
            </tbody>
            
        </table>
        
        <script>
            JsBarcode("#barcode").init();
            window.print();
        </script>
        <script>
            $(document).ready(function(){
                var groupColumn = 1;
                var table = $('#listdata').DataTable({
                    columnDefs: [
                        { 
                            visible: false, 
                            targets: groupColumn
                        }
                    ],
                    order: [[groupColumn, 'asc']],
                    paging: false,
                    ordering: false,
                    info: false,
                    searching: false,
                    drawCallback: function (settings) {
                        var api = this.api();
                        var rows = api.rows({ page: 'current' }).nodes();
                        var last = null;
 
                        api
                        .column(groupColumn, { page: 'current' })
                        .data()
                        .each(function (group, i) {
                            if (last !== group) {
                                $(rows)
                                .eq(i)
                                .before('<tr class="group  border-dark p-0"><td colspan="8" >' + group + '</td></tr>');
                                last = group;
                            }
                        });
                    }
                });
                $('#listdata tbody').on('click', 'tr.group', function () {
                    var currentOrder = table.order()[0];
                    if (currentOrder[0] === groupColumn && currentOrder[1] === 'asc') {
                        table.order([groupColumn, 'desc']).draw();
                    } else {
                        table.order([groupColumn, 'asc']).draw();
                    }
                });
                
            })
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
    </body>
</html>

