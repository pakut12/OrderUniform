<%-- 
    Document   : ReportBag
    Created on : 15 พ.ย. 2565, 9:45:37
    Author     : pakutsing
--%>
<%@page import="com.pg.lib.service.*" %>
<%@page import="com.pg.lib.model.*" %>
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
            String depart = request.getParameter("depart");



            TransactionDepartmentService dx = new TransactionDepartmentService();
            List<OUTransactionDepartmentDetail> listdetail = dx.getDepariID(doc_id);
            int a = 0;
            int b = 0;
            int n = 0;
            int box = 3;

            String HTMLtag = "";

            for (int i = 0; i < box; i++) {
                HTMLtag += "<table id=\"listdata\" class=\"table table-bordered border-dark text-center w-100 page-break \" >";
                HTMLtag += "<thead>";
                HTMLtag += "<tr>";
                HTMLtag += "<th class='p-0' colspan=\"3\">" + listdetail.get(0).getDocName() + " / " + listdetail.get(a).getAgency() + " / Box No : " + (i + 1) + "/" + (box) + "</th>";
                HTMLtag += "</tr>";
                HTMLtag += "<tr>";
                HTMLtag += "<th class='p-0'>ลำดับ</th>";
                HTMLtag += "<th class='p-0'>ชื่อ</th>";
                HTMLtag += "</tr>";
                HTMLtag += "</thead>";
                HTMLtag += "<tbody>";

                String Name = "หน่วยงานที่ 1 : " + listdetail.get(i).getAgency() + "<br>หน่วยงานที่ 2 : " + listdetail.get(i).getDivision() + "<br>หน่วยงานที่ 3 : " + listdetail.get(i).getDepartmentname();
                HTMLtag += "<tr>";
                HTMLtag += "<td class='p-0'>" + (n + 1) + "</td>";
                HTMLtag += "<td class='p-0'>" + Name + "</td>";
                HTMLtag += "</tr>";
            }

            HTMLtag += "</tbody>";
            HTMLtag += "</table>";

            out.print(HTMLtag);

        %>
        
        
        <script>
            // window.print();
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
    </body>
</html>

