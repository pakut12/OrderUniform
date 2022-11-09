package com.pg.lib.Servlet;

import com.pg.lib.model.OUSummaryOrderByCustomer;
import com.pg.lib.model.OUSummaryOrderCustomerByMaterialAndSize;
import com.pg.lib.model.OUTransactionCustomerDetail;
import com.pg.lib.service.TransactionCustomerService;
import java.io.*;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class TransactionCustomer extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String doc_id = request.getParameter("doc_id") == null ? "" : request.getParameter("doc_id");
        String type = request.getParameter("type") == null ? "" : request.getParameter("type");
        TransactionCustomerService s_trancustomer = new TransactionCustomerService();


        if (type.equals("generateXLSFileSummarizeCustomer")) {
            //ค้นหาข้อมูล transaction
            List<OUTransactionCustomerDetail> listdetail = s_trancustomer.getDetailTransactionByDocumentId(doc_id);
            //คำนวนยอดสั่งสินค้าตามรายชื่อสาขา
            List<OUSummaryOrderByCustomer> listSummaryByCustomer = s_trancustomer.summaryOrderByCustomer(doc_id);
            //คำนวนยอดสั่งสินค้าตามรหัสสินค้า และ size สินค้า
            List<OUSummaryOrderCustomerByMaterialAndSize> listSummaryQuantityByMaterialAndSize = s_trancustomer.summaryOrderByMaterialAndSize(doc_id);
            //คำนวนยอดรวมสั่งสินค้าตามไซส์
            HashMap<String, Integer> totalByMaterial = s_trancustomer.calculateTotalByMaterial(listSummaryQuantityByMaterialAndSize);

            try {



                String concatpath = "C:/Users/pakutsing/Desktop/Github/OU/web/attachfile/Test.xls";
                HSSFWorkbook wb = new HSSFWorkbook();
                HSSFSheet s = wb.createSheet();

                String CustomerCodeNew = "";
                String NameNew = "";


                HSSFFont font = wb.createFont();
                font.setFontName("3 of 9 Barcode");
                font.setFontHeightInPoints((short) 24);

                HSSFFont font1 = wb.createFont();
                font1.setFontName("Arial");
                font1.setFontHeightInPoints((short) 24);

                HSSFCellStyle stylebarcode = wb.createCellStyle();
                stylebarcode.setFont(font);
                stylebarcode.setBorderBottom(stylebarcode.BORDER_THIN);
                stylebarcode.setBorderLeft(stylebarcode.BORDER_THIN);
                stylebarcode.setBorderRight(stylebarcode.BORDER_THIN);
                stylebarcode.setBorderTop(stylebarcode.BORDER_THIN);
                stylebarcode.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                stylebarcode.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);


                HSSFCellStyle topic = wb.createCellStyle();
                topic.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                topic.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                topic.setBorderRight(HSSFCellStyle.BORDER_THIN);
                topic.setBorderTop(HSSFCellStyle.BORDER_THIN);
                topic.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                topic.setAlignment(HSSFCellStyle.ALIGN_CENTER);

                HSSFCellStyle details = wb.createCellStyle();
                details.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                details.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                details.setBorderRight(HSSFCellStyle.BORDER_THIN);
                details.setBorderTop(HSSFCellStyle.BORDER_THIN);
                details.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                details.setAlignment(HSSFCellStyle.ALIGN_CENTER);

                //หัวข้อ
                Row rowindex = s.createRow(0);

                Cell index1 = rowindex.createCell(0);
                index1.setCellValue("รหัสพนักงาน");
                index1.setCellStyle(topic);

                Cell index2 = rowindex.createCell(1);
                index2.setCellValue("ชื่อ");
                index2.setCellStyle(topic);

                Cell index3 = rowindex.createCell(2);
                index3.setCellValue("แผนก");
                index3.setCellStyle(topic);


                int x = 1;
                for (int n = 0; n < listdetail.size(); n++) {

                    if (!CustomerCodeNew.equals(listdetail.get(n).getCustomerCode()) && !NameNew.equals(listdetail.get(n).getPrename() + listdetail.get(n).getFname())) {
                        Row row = s.createRow(x);

                        s.autoSizeColumn(0);
                        s.autoSizeColumn(1);
                        s.autoSizeColumn(2);


                        Cell CustomerCode = row.createCell(0);
                        CustomerCode.setCellValue(listdetail.get(n).getCustomerCode());

                        Cell Name = row.createCell(1);
                        Name.setCellValue(listdetail.get(n).getPrename() + listdetail.get(n).getFname());

                        Cell Barcode = row.createCell(2);
                        Barcode.setCellValue("*" + listdetail.get(n).getCustomerCode() + "*");
                        
                        
                        row.getCell(0).setCellStyle(details);
                        row.getCell(1).setCellStyle(details);
                        row.getCell(2).setCellStyle(stylebarcode);
                        x++;
                    }
                    CustomerCodeNew = listdetail.get(n).getCustomerCode();
                    NameNew = listdetail.get(n).getPrename() + listdetail.get(n).getFname();
                }



                FileOutputStream outputStream = new FileOutputStream(concatpath);
                wb.write(outputStream);
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }



            out.print("asd");



        } else if (type.equals("getheadertransaction")) {
            String HTMLtag = "";
            List<TreeMap> headerDetail = s_trancustomer.getHeaderTransaction();
            HTMLtag += "<table id=\"list-transaction\" >";
            HTMLtag += "<thead>";
            HTMLtag += "<tr>";
            HTMLtag += "<th>หัวข้อ</th>";
            HTMLtag += "<th>ชื่อไฟล์ที่อัพโหลด</th>";
            HTMLtag += "<th>เวลา</th>";
            HTMLtag += "</tr>";
            HTMLtag += "</thead>";
            HTMLtag += "<tbody>";
            for (int i = 0; i <= headerDetail.size() - 1; i++) {
                HTMLtag += "<tr>";
                HTMLtag += "<td> <a href=\"TransactionCustomer?doc_id=" + headerDetail.get(i).get("h_id") + " \" >" + headerDetail.get(i).get("h_name") + "</a></td>";
                HTMLtag += "<td>" + headerDetail.get(i).get("h_filename") + "</td>";
                HTMLtag += "<td>" + headerDetail.get(i).get("h_create_date") + "</td>";
                HTMLtag += "</tr>";
            }
            HTMLtag += "</tbody>";
            HTMLtag += "</table>";
            out.print(HTMLtag);
        } else {
            //ค้นหาข้อมูล transaction
            List<OUTransactionCustomerDetail> listdetail = s_trancustomer.getDetailTransactionByDocumentId(doc_id);
            //คำนวนยอดสั่งสินค้าตามรายชื่อสาขา
            List<OUSummaryOrderByCustomer> listSummaryByCustomer = s_trancustomer.summaryOrderByCustomer(doc_id);
            //คำนวนยอดสั่งสินค้าตามรหัสสินค้า และ size สินค้า
            List<OUSummaryOrderCustomerByMaterialAndSize> listSummaryQuantityByMaterialAndSize = s_trancustomer.summaryOrderByMaterialAndSize(doc_id);
            //คำนวนยอดรวมสั่งสินค้าตามไซส์
            HashMap<String, Integer> totalByMaterial = s_trancustomer.calculateTotalByMaterial(listSummaryQuantityByMaterialAndSize);

            request.setAttribute("detaildoc", listdetail);
            request.setAttribute("listSummaryByCustomer", listSummaryByCustomer);
            request.setAttribute("listSummaryQuantity", listSummaryQuantityByMaterialAndSize);
            request.setAttribute("totalByMaterialid", totalByMaterial);

            getServletContext().getRequestDispatcher("/detailtransactioncustomer.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
