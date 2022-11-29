<%-- 
    Document   : Test
    Created on : 29 พ.ย. 2565, 10:29:10
    Author     : pakutsing
--%>
<%@page import="com.pg.lib.service.TransactionCustomerService"%>
<%@page import="com.pg.lib.model.OUTransactionCustomerDetail"%>
<%@page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Pagdasdasde</title>
        <script src="../js/pdfmake.min.js"></script>
        <script src="../js/vfs_fonts.js"></script>
        
        <script src="https://cdn.jsdelivr.net/jsbarcode/3.6.0/JsBarcode.all.min.js"></script>
    </head>
    <body>
        
        
        <script>
            function textToBase64Barcode(text){
                var canvas = document.createElement("canvas");
                JsBarcode(canvas, text, {format: "CODE39"});
                return canvas.toDataURL("image/png");
            }
            pdfMake.fonts = {
                THSarabunNew: {
                    normal: 'THSarabunNew.ttf',
                    bold: 'THSarabunNew-Bold.ttf',
                    italics: 'THSarabunNew-Italic.ttf',
                    bolditalics: 'THSarabunNew-BoldItalic.ttf'
                },
                Roboto: {
                    normal: 'Roboto-Regular.ttf',
                    bold: 'Roboto-Medium.ttf',
                    italics: 'Roboto-Italic.ttf',
                    bolditalics: 'Roboto-MediumItalic.ttf'
                }
            }
            
            var dd = {
                pageSize: "A4",
                pageMargins: [ 30, 60, 30, 60 ],
                footer: function(currentPage, pageCount) { 
                    return [
                        {
                            columns: [
                                {
                                    // auto-sized columns have their widths based on their content
                                    width: 'auto',
                                    text: 'First column'
                                },
                                {
                                    // star-sized columns fill the remaining space
                                    // if there's more than one star-column, available width is divided equally
                                    width: '*',
                                    text: 'Second column'
                                }
                            ]
                        }
                    ]
                },
                header: function(currentPage, pageCount, pageSize) {
                    // you can apply any logic and return any valid pdfmake element
                            
                    return [
                                
                        {
                            columns: [
                                {
                                    width: '*',
                                    text: "",
                                    fontSize: 14,
                                    alignment: 'left',
                                    margin: [20,20, 0, 0]
                                             
                                },
                                {
                                    width: '*',
                                    text: [{ text: 'รายชื่อพนักงานบริษัท  : ', bold:true },{ text: 'asdasd' }],
                                    fontSize: 18,
                                    alignment: 'center',
                                   
                                    margin: [0,20, 0, 0]
                                },
                                {
                                    width: '*',
                                    text: [{ text: 'หน้า : ', bold:true },{ text: currentPage ,color:'#1700FF',bold:true  }],
                                    fontSize: 18,
                                    alignment: 'right',
                                    margin: [0,20, 20, 0]
                                }
                            
                            ]
                        }
                       
                        
                       
                    ]
                },
                content: [
                    {
                        
                        style: 'tableExample',// optional
                        table: {
                            // headers are automatically repeated if the table spans over multiple pages
                            // you can declare how many rows should be treated as headers
                            headerRows: 1,
                            widths: [ 'auto', 'auto', 'auto', 'auto' ,'auto', 'auto', 'auto', 'auto','auto','auto'],
                            body: [
                                [ 'ลำดับ', 'ชื่อ-นามสกุล', 'ชื่อสินค้า', 'รหัสสินค้า', 'รหัสสินค้า' , 'สี', 'ไซส์', 'รหัสสินค้า 18 หลัก' , 'Barcode', 'จำนวน' ],
                                <%
            String doc_id = "100";//request.getParameter("doc_id");

            String cus_no = request.getParameter("cus_no");

            TransactionCustomerService s_trancustomer = new TransactionCustomerService();
            List<OUTransactionCustomerDetail> detail = s_trancustomer.getDetailTransactionByDocumentId(doc_id);
            int sum = 0;
            for (int i = 0; i <= detail.size() - 1; i++) {
                sum += Integer.valueOf(detail.get(i).getQuantity().toString());
                                %>
                                                        [ 
                                                            '<%=detail.get(i).getCustomerCode()%>\nasdasd',
                                                            '<%=detail.get(i).getPrename()%> <%=detail.get(i).getFname()%>',
                                                            '<%=detail.get(i).getDepartmentname()%>',
                                                            '<%=detail.get(i).getDesc()%>',
                                                            '<%=detail.get(i).getMaterialname()%>',
                                                            '<%=detail.get(i).getColor()%>', 
                                                            '<%=detail.get(i).getSize()%>', 
                                                            '<%=detail.get(i).getMatfullname()%>' , 
                                                            {
                                                                image: textToBase64Barcode('<%=detail.get(i).getMatfullname()%>'),
                                                                width: 10,
                                                                height: 10
                                                                
                                                            }, 
                                                        
                                                            '<%=detail.get(i).getQuantity()%>' 
                                                        ],
                                <%}%>
                            
                                                    ]
                                                }
                                            }
                    
                                        ]
                                        ,
                                        styles: {
                                            footer:{
                                                margin: [0, 0, 0, 15]
                                            },
                                            tableExample: {
                                                margin: [0, 0, 0, 0],
                                                fontSize: 10,
                                                alignment: 'center',
                                                bold:true
                                            }
                    
                                        },
                                        defaultStyle: {
                                            font: 'THSarabunNew'
                                        }
               
                                    }
                                    pdfMake.createPdf(dd).open({}, window); 
            
        </script>
    </body>
</html>
