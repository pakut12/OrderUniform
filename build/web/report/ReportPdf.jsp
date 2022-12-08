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
        <title>JSP </title>
        <script src="../js/pdfmake.min.js"></script>
        <script src="../js/vfs_fonts.js"></script>
        
        <script src="https://cdn.jsdelivr.net/jsbarcode/3.6.0/JsBarcode.all.min.js"></script>
    </head>
    <body>
        
        
        <script>
            <%
            String doc_id = request.getParameter("doc_id");

            String cus_no = request.getParameter("cus_no");

            TransactionCustomerService s_trancustomer = new TransactionCustomerService();
            List<OUTransactionCustomerDetail> detail = s_trancustomer.getDetailTransactionByDocumentId(doc_id);
            int sum = 0;

            %>
                function today(){
                    var date = new Date();
                    var d =date.getDate()
                    var m = date.getMonth()+1;
                    var y = date.getFullYear();
                    var h = date.getHours();
                    var i = date.getMinutes()
                    var s = date.getSeconds();
                    if(m <10){
                        m = "0"+m;
                    }
                    if(i<10){
                        i = "0"+i;
                    }

                    if(d<10){
                        d = "0"+d;
                    }
                    var today = d+"/"+m+"/"+y+" "+h+":"+i+":"+s;
                    return today;
                }
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
                    pageMargins: [ 40, 70,40, 30 ],
                    footer: function(currentPage, pageCount) { 
                        return []
                    },
                    header: function(currentPage, pageCount, pageSize) {
                        // you can apply any logic and return any valid pdfmake element
                            
                        return [  
                            {
                                columns: [
                                    {
                                        width: 'auto',
                                        text: [
                                            { 
                                                text: 'วันที่ : '+today()+' \nรายชื่อพนักงานบริษัท  : <%=detail.get(0).getCompanyname()%>',
                                                bold:true
                                            }
                                        ],
                                        fontSize: 14,
                                        alignment: 'left',
                                        margin: [45,25, 0, 0]
                                    },
                                    {
                                        image: textToBase64Barcode('<%=doc_id%>'),
                                        width: 200,
                                        height: 40,
                                        alignment: 'left',
                                        margin: [20,25, 0, 0]
                                    },
                                    {
                                        width: '*',
                                        text: [{ text: 'หน้า : ', bold:true },{ text: currentPage ,color:'#1700FF',bold:true  }],
                                        fontSize: 14,
                                        alignment: 'right',
                                        margin: [0,25,45, 0]
                                    }
                            
                                ]
                            }
                       
                        ]
                    },
                    background: [
                        {
                            canvas: [
                                {
                                    type: 'rect',
                                    x: 40,
                                    y: 20,
                                    w: 520,
                                    h: 800,
                                    r: 0,
                                    lineWidth: 1,
                                    lineColor: '#000000'
                                }
                            ]
                        }
                    ],
                    content: [
                        {
                            style: 'tableExample',// optional
                            table: {
                                // headers are automatically repeated if the table spans over multiple pages
                                // you can declare how many rows should be treated as headers
                                headerRows: 1,
                                widths: [  '14%', '14%' ,'5%', '5%', '28%', '30%','5%'],
                                body: [
                                    [ 'ชื่อสินค้า', 'รหัสสินค้า', 'สี', 'ไซส์', 'รหัสสินค้า 18 หลัก' , 'Barcode', 'จำนวน' ],
                                <%
            int id = 0;
            String name = "";
            String depart = "";
            for (int i = 0; i <= detail.size() - 1; i++) {
                sum += Integer.valueOf(detail.get(i).getQuantity().toString());
                if (id != detail.get(i).getCustomerID()) {
                                %>
                                                        [ 
                                                            {
                                                                image: textToBase64Barcode('<%=doc_id%>/<%=detail.get(i).getCustomerID()%>'),
                                                                width: 140,
                                                                height: 25,
                                                                border: [false, false, false, false], 
                                                                alignment:'left' 
                                                                                                                             
                                                            },
                                                            {
                                                                border: [false, false, true, false],  
                                                                text:''
                                                            },
                                                            {
                                                                border: [false, false, false, false], 
                                                                text: 'รหัสพนักงาน <%=detail.get(i).getCustomerCode()%> \n ชื่อ-นามสกุล : <%=detail.get(i).getPrename()%> <%=detail.get(i).getFname()%> \n เเผนก : <%=detail.get(i).getDepartmentname()%>', 
                                                                alignment:'center' ,
                                                                colSpan: 5
                                                            }
                                                            
                                                        ],
                 <%                }

                %>
                      
                                        [ 
                                            
                                            '<%=detail.get(i).getDesc()%>',
                                            '<%=detail.get(i).getMaterialname()%>',
                                            '<%=detail.get(i).getColor()%>', 
                                            '<%=detail.get(i).getSize()%>', 
                                            '<%=detail.get(i).getMatfullname()%>' , 
                                            {
                                                
                                                image: textToBase64Barcode('<%=detail.get(i).getBarcode()%>'),
                                                width: 100,
                                                height: 20
                                            }, 
                                            '<%=detail.get(i).getQuantity()%>'
                                                            
                                        ],
                    
                    
         <%
                id = detail.get(i).getCustomerID();
                name = detail.get(i).getPrename() + " " + detail.get(i).getFname();
                depart = detail.get(i).getDepartmentname();

            }
            %>
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