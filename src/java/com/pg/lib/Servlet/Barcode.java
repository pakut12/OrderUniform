/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pg.lib.Servlet;

import com.pg.lib.model.OUTransactionCustomerDetail;
import com.pg.lib.service.BarcodeService;
import com.pg.lib.service.TransactionCustomerService;
import java.io.*;
import java.net.*;

import java.util.HashMap;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author 111525
 */
public class Barcode extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String docid = request.getParameter("documentID") == null ? "" : request.getParameter("documentID");

        TransactionCustomerService s_cus = new TransactionCustomerService();
        BarcodeService s_barcode = new BarcodeService();

        List<OUTransactionCustomerDetail> detailDoc = s_cus.getDetailTransactionByDocumentId(docid);
        HashMap<String, String> item = s_barcode.getBarcodeFindByMaterialCode(detailDoc);
     
        try {
            for(OUTransactionCustomerDetail s : detailDoc ){
                out.print(s.getCustomerID());
            }

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
