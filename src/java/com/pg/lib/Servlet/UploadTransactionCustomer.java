/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pg.lib.Servlet;

import com.pg.lib.model.OUOrderList;
import com.pg.lib.model.OUUCustomerOrder;
import com.pg.lib.service.ReadExcelService;
import com.pg.lib.service.TransactionCustomerService;
import com.pg.lib.service.UploadFileService;
import java.io.*;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.JSONException;
import org.json.JSONObject;

public class UploadTransactionCustomer extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        UploadFileService s_upload = new UploadFileService();
        HashMap<String, String> detailFile = s_upload.checkMultiContent(request, "uploadtransactioncustomer");

        ReadExcelService s_readxls = new ReadExcelService();
        List<OUUCustomerOrder> listdata = s_readxls.readExcelFileTransactionCustomer(detailFile);

        if (listdata.size() != 0) {
            TransactionCustomerService s_transaction = new TransactionCustomerService();
            List<OUOrderList> Orderlist = s_transaction.processData(listdata);

            int headerid = s_transaction.createNewTransaction(Orderlist, detailFile);

            if (headerid != 0) {
                boolean statusUpdateItem = s_transaction.updateStatusItems(Orderlist);
                boolean statusUpdateCustomer = s_transaction.updateStatusCustomers(Orderlist);

                if (statusUpdateItem && statusUpdateCustomer) {
                    JSONObject obj = new JSONObject();
                    obj.put("doc_id", headerid);
                    obj.put("status", "true");
                    out.print(obj);
                    System.out.println("Update status item and customer successful...");
                    out.close();
                } else {
                    System.out.println("Update status item and customer failure...");
                }

            } else {
                System.out.println("Insert transaction Error...");
            }
        } else {
            out.print("{\"status\":\"false\"}");
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
            Logger.getLogger(UploadTransactionCustomer.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UploadTransactionCustomer.class.getName()).log(Level.SEVERE, null, ex);
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
