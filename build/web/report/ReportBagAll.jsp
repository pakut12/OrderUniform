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
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>ReportBagAll</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    </head>
    <style>
        
        @media print{
            @page {
                margin: 5px;
            }
            body{
                font-size:1px; 
                page-break-before: always;
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

            //CustomerService getcode = new CustomerService();
            //ArrayList<OUTransactionCustomerDetail> customercode = getcode.GroupCustomerCode(doc_id);
            TransactionCustomerService ts = new TransactionCustomerService();
            List<OUTransactionCustomerDetail> list = ts.getDetailFromBarcodeDepartAll(doc_id);
           
            for (OUTransactionCustomerDetail x : list) {
                TransactionCustomerService s_trancustomer = new TransactionCustomerService();
                List<OUTransactionCustomerDetail> listdetail = s_trancustomer.getDetailFromBarcode(doc_id, x.getCustomerID());

        %>
        
        <table class="table table-bordered border-dark text-center w-100 page-break">
            <thead>
                <tr>
                    <th colspan="7" class="text-center p-0 ">
                        รายชื่อพนักงาน : <%=listdetail.get(0).getCompanyname()%>
                    </th> 
                </tr>
                <tr>
                    <th class="p-0" colspan="7">
                        ชื่อ : <%=listdetail.get(0).getPrename() + " " + listdetail.get(0).getFname()%> 
                        เเผนก : <%=listdetail.get(0).getDepartmentname()%> <br>
                    </th> 
                </tr>
                <tr>
                    <th class="p-0">ลำดับ</th>
                    <th class="p-0">ชื่อสินค้า</th>
                    <th class="p-0">รหัสสินค้า</th> 
                    <th class="p-0">สี</th>
                    <th class="p-0">ไซส์</th>
                    <th class="p-0">รหัสสินค้า 18 หลัก</th> 
                    <th class="p-0">จำนวน</th>
                </tr>
            </thead>
            <tbody>
                <%
            int sum = 0;
            for (int i = 0; i <= listdetail.size() - 1; i++) {
                sum += Integer.parseInt(listdetail.get(i).getQuantity());
                %>
                <tr>
                    <td class="p-0"><%=(i + 1)%></td>
                    <td class="p-0"><%=listdetail.get(i).getDesc()%></td>
                    <td class="p-0"><%=listdetail.get(i).getMaterialname()%></td>
                    <td class="p-0"><%=listdetail.get(i).getColor()%></td>
                    <td class="p-0"><%=listdetail.get(i).getSize()%></td>
                    <td class="p-0"><%=listdetail.get(i).getMatfullname()%></td>
                    <td class="p-0"><%=listdetail.get(i).getQuantity()%></td>
                </tr>
                <%}%>
            </tbody>
            <tfoot>
                <tr>
                    <th colspan="6" class="text-end p-0">
                        รวมทั้งหมด
                    </th> 
                    <th class="p-0">
                        <%=sum%>
                    </th> 
                </tr>
            </tfoot>
        </table>
        
        
        <%
            }
        %>
        <script>
            window.print();
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
    </body>
</html>

