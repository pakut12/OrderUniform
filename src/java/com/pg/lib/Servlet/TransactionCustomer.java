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


        if (type.equals("updatestatusheadertransaction")) {
            String id = request.getParameter("h_id");
            boolean status = s_trancustomer.UpdateStatusHeader(id);
            if (status) {
                out.print("true");
            } else {
                out.print("false");
            }
            out.close();
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
            out.close();
        } else if (type.equals("getheadertransactionwihtid")) {
            String id = request.getParameter("h_id");
            List<TreeMap> headerDetail = s_trancustomer.getHeaderTransactionwithid(id);
            String status = "";

            if (headerDetail.get(0).get("h_status").toString().equals("new")) {
                status = "0";
            } else {
                status = "1";
            }

            JSONObject obj = new JSONObject();
            obj.put("id", headerDetail.get(0).get("h_id"));
            obj.put("name", headerDetail.get(0).get("h_name"));
            obj.put("filename", headerDetail.get(0).get("h_filename"));
            obj.put("date", headerDetail.get(0).get("h_create_date"));
            obj.put("status", status);
            out.print(obj);
            out.close();
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
