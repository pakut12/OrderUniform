/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pg.lib.Servlet;

import com.pg.lib.model.OUSummaryOrderByCustomer;
import com.pg.lib.model.OUTransactionCustomerDetail;
import com.pg.lib.service.TransactionCustomerService;
import java.io.*;
import java.net.*;

import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author pakutsing
 */
public class GetDataStock extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            String doc_id = request.getParameter("doc_id");
            String cus_no = request.getParameter("cus_no");



            TransactionCustomerService s_trancustomer = new TransactionCustomerService();
            List<OUTransactionCustomerDetail> listdetail = s_trancustomer.getDetailFromBarcode(doc_id, cus_no);

            String HTMLtag = "";

            HTMLtag += "<table id=\"list-transaction\" class=\"table\" >";
            HTMLtag += "<thead>";
            HTMLtag += "<tr>";
            HTMLtag += "<th>ลำดับ</th>";
            HTMLtag += "<th>ชื่อ-นามสกุล</th>";
            HTMLtag += "<th>ชื่อสินค้า</th>";
            HTMLtag += "<th>รหัสสินค้า</th>";
            HTMLtag += "<th>สี</th>";
            HTMLtag += "<th>ไซส์</th>";
            HTMLtag += "<th>รหัสสินค้า 18 หลัก</th>";
            HTMLtag += "<th>Barcode</th>";
            HTMLtag += "<th>จำนวน</th>";
            HTMLtag += "</tr>";
            HTMLtag += "</thead>";
            HTMLtag += "<tbody>";
            for (int i = 0; i <= listdetail.size() - 1; i++) {
                HTMLtag += "<tr>";
                HTMLtag += "<td>" + (i + 1) + "</td>";
                HTMLtag += "<td>" + listdetail.get(i).getPrename() + " " + listdetail.get(i).getFname() + "</td>";
                HTMLtag += "<td>" + listdetail.get(i).getDesc() + "</td>";
                HTMLtag += "<td>" + listdetail.get(i).getMaterialname() + "</td>";
                HTMLtag += "<td>" + listdetail.get(i).getColor() + "</td>";
                HTMLtag += "<td>" + listdetail.get(i).getSize() + "</td>";
                HTMLtag += "<td>" + listdetail.get(i).getMaterialdesc() + "</td>";
                HTMLtag += "<td>" + listdetail.get(i).getBarcode() + "</td>";
                HTMLtag += "<td>" + listdetail.get(i).getQuantity() + "</td>";

                HTMLtag += "</tr>";
            }
            HTMLtag += "</tbody>";
            HTMLtag += "</table>";
            out.print(HTMLtag);
        } finally {
            out.close();
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
