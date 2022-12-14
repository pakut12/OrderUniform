package com.pg.lib.Servlet;

import com.pg.lib.model.OUSummaryOrderByCustomer;
import com.pg.lib.model.OUSummaryOrderCustomerByMaterialAndSize;
import com.pg.lib.model.OUTransactionCustomerDetail;
import com.pg.lib.service.TransactionCustomerService;
import java.io.*;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFRegionUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONException;
import org.json.JSONObject;

public class TransactionCustomer extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {
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
                Header oddH = s.getHeader();

                oddH.setRight("Page " + s.getHeader().page() + " of " + s.getHeader().numPages());

                HSSFFont font = wb.createFont();
                font.setFontName("3 of 9 Barcode");
                font.setFontHeightInPoints((short) 16);

                HSSFFont font1 = wb.createFont();
                font1.setFontName("Arial");
                font1.setFontHeightInPoints((short) 16);

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

                CellStyle Company = wb.createCellStyle();
                Company.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                Company.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                Company.setBorderRight(HSSFCellStyle.BORDER_THIN);
                Company.setBorderTop(HSSFCellStyle.BORDER_THIN);
                Company.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                Company.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                Company.setFont(font1);


                Row rowtopic = s.createRow(0);

                CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 5);
                s.addMergedRegion(cellRangeAddress);
                HSSFRegionUtil.setBorderTop(CellStyle.BORDER_THIN, cellRangeAddress, s, wb);
                HSSFRegionUtil.setBorderLeft(CellStyle.BORDER_THIN, cellRangeAddress, s, wb);
                HSSFRegionUtil.setBorderRight(CellStyle.BORDER_THIN, cellRangeAddress, s, wb);
                HSSFRegionUtil.setBorderBottom(CellStyle.BORDER_THIN, cellRangeAddress, s, wb);


                Cell index0 = rowtopic.createCell(0);
                index0.setCellValue(listdetail.get(0).getCompanyname());
                index0.setCellStyle(Company);


                //หัวข้อ
                Row rowindex = s.createRow(1);

                Cell index6 = rowindex.createCell(0);
                index6.setCellValue("ลำดับ");
                index6.setCellStyle(topic);

                Cell index1 = rowindex.createCell(2);
                index1.setCellValue("รหัสพนักงาน");
                index1.setCellStyle(topic);

                Cell index2 = rowindex.createCell(3);
                index2.setCellValue("ชื่อ");
                index2.setCellStyle(topic);

                Cell index3 = rowindex.createCell(4);
                index3.setCellValue("แผนก");
                index3.setCellStyle(topic);

                Cell index4 = rowindex.createCell(1);
                index4.setCellValue("Barcode");
                index4.setCellStyle(topic);

                Cell index5 = rowindex.createCell(5);
                index5.setCellValue("จำนวน");
                index5.setCellStyle(topic);

                int x = 2;
                for (int n = 0; n < listSummaryByCustomer.size(); n++) {


                    Row row = s.createRow(x);

                    s.setColumnWidth(0, 2000);
                    s.setColumnWidth(1, 7000);
                    s.setColumnWidth(2, 3000);
                    s.setColumnWidth(3, 5500);
                    s.setColumnWidth(4, 3000);
                    s.setColumnWidth(5, 2500);


                    Cell Num = row.createCell(0);
                    Num.setCellValue(n + 1);

                    Cell CustomerCode = row.createCell(2);
                    CustomerCode.setCellValue(listSummaryByCustomer.get(n).getCustomerno());

                    Cell Name = row.createCell(3);
                    Name.setCellValue(listSummaryByCustomer.get(n).getCustomerprename() + listSummaryByCustomer.get(n).getCustomername());

                    Cell Depart = row.createCell(4);
                    Depart.setCellValue(listSummaryByCustomer.get(n).getDepartmentname());

                    Cell Quantity = row.createCell(5);
                    Quantity.setCellValue(listSummaryByCustomer.get(n).getQuantity());

                    Cell Barcode = row.createCell(1);
                    Barcode.setCellValue("*" + doc_id + "/" + listSummaryByCustomer.get(n).getCustomerno() + "*");

                    row.getCell(2).setCellStyle(details);
                    row.getCell(3).setCellStyle(details);
                    row.getCell(4).setCellStyle(details);
                    row.getCell(5).setCellStyle(details);
                    row.getCell(0).setCellStyle(details);
                    row.getCell(1).setCellStyle(stylebarcode);
                    x++;


                }

                FileOutputStream outputStream = new FileOutputStream(concatpath);
                wb.write(outputStream);
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

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
        } else if (type.equals("getheadertransactionwihtid")) {
            String id = request.getParameter("h_id");
            List<TreeMap> headerDetail = s_trancustomer.getHeaderTransactionwithid(id);
            JSONObject obj = new JSONObject();
            obj.put("id", headerDetail.get(0).get("h_id"));
            obj.put("name", headerDetail.get(0).get("h_name"));
            obj.put("filename", headerDetail.get(0).get("h_filename"));
            obj.put("date", headerDetail.get(0).get("h_create_date"));
            
            out.print(obj);
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
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(TransactionCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(TransactionCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
