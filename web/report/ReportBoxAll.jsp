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
            String num = request.getParameter("num");

            //CustomerService cms = new CustomerService();
            //ArrayList<OUTransactionCustomerDetail> list = cms.GroupCustomerCode(doc_id);

            TransactionCustomerService ts = new TransactionCustomerService();
            List<OUTransactionCustomerDetail> list = ts.getDetailFromBarcodeDepartAll(doc_id);
            int box = (int) Math.ceil((double) list.size() / Integer.parseInt(num));
            String Name = "";
            String DepartmentName = "";
            String HTMLtag = "";
            int a = 0;
            int n = 0;
            for (int i = 0; i < box; i++) {

                HTMLtag += "<table id=\"listdata\" class=\"table table-bordered border-dark text-center w-100 page-break \" >";
                HTMLtag += "<thead>";
                HTMLtag += "<tr>";
                HTMLtag += "<th colspan='3' class='p-0'>" + list.get(0).getDocName() + " " + (i + 1) + "/" + (box) + "</th>";
                HTMLtag += "</tr>";
                HTMLtag += "<tr>";
                HTMLtag += "<th  class='p-0'>ลำดับ</th>";
                HTMLtag += "<th  class='p-0'>ชื่อ</th>";
                HTMLtag += "<th  class='p-0'>เเผนก</th>";
                HTMLtag += "</tr>";
                HTMLtag += "</thead>";
                HTMLtag += "<tbody>";

                for (int x = 0; x < Integer.parseInt(num); x++) {

                    if (a != list.size()) {
                        HTMLtag += "<tr>";
                        HTMLtag += "<td  class='p-0'>" + (x + 1) + "</td>";
                        HTMLtag += "<td  class='p-0'>" + list.get(a).getPrename() + " " + list.get(a).getFname() + "</td>";
                        HTMLtag += "<td  class='p-0'>" + list.get(a).getDepartmentname() + "</td>";
                        HTMLtag += "</tr>";

                        Name = list.get(a).getPrename() + " " + list.get(a).getFname();
                        DepartmentName = list.get(a).getDepartmentname();
                        a++;
                    }
                }
                HTMLtag += "</tbody>";
                HTMLtag += "</table>";
            }

            out.print(HTMLtag);
        %>
        
        
        <script>
            window.print();
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
    </body>
</html>
