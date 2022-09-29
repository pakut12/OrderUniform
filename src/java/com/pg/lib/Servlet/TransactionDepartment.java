package com.pg.lib.Servlet;

import com.pg.lib.model.OUSummaryOrderByDepartment;
import com.pg.lib.model.OUSummaryOrderDepartmentByMaterialAndSize;
import com.pg.lib.model.OUTransactionDepartmentDetail;
import com.pg.lib.service.TransactionDepartmentService;
import java.io.*;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import javax.servlet.*;
import javax.servlet.http.*;


public class TransactionDepartment extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String doc_id = request.getParameter("doc_id") == null ? "" : request.getParameter("doc_id");
        String type = request.getParameter("type") == null ? "" : request.getParameter("type");
        TransactionDepartmentService s_trandepart = new TransactionDepartmentService();
        
        if(type.equals("getheadertransaction")){
            String HTMLtag = "";
            List<TreeMap> headerdetail = s_trandepart.getHeaderTransaction();
                HTMLtag += "<table id=\"list-transaction\" >";
                HTMLtag += "<thead>";
                HTMLtag += "<tr>";
                HTMLtag += "<th>หัวข้อ</th>";
                HTMLtag += "<th>ชื่อไฟล์ที่อัพโหลด</th>";
                HTMLtag += "<th>เวลา</th>";
                HTMLtag += "</tr>";
                HTMLtag += "</thead>";
                HTMLtag += "<tbody>";
                    for(int i = 0 ; i <= headerdetail.size()-1 ; i++){
                        HTMLtag += "<tr>";
                            HTMLtag += "<td> <a href=\"TransactionDepartment?doc_id="+headerdetail.get(i).get("h_id")+" \" >"+ headerdetail.get(i).get("h_name")+"</a></td>";
                            HTMLtag += "<td>"+headerdetail.get(i).get("h_filename")+"</td>";
                            HTMLtag += "<td>"+headerdetail.get(i).get("h_create_date")+"</td>";
                        HTMLtag += "</tr>";
                    }
                HTMLtag += "</tbody>";
                HTMLtag += "</table>";
            out.print(HTMLtag);
        } else {
            //ค้นหาข้อมูล transaction
            List<OUTransactionDepartmentDetail> listdetail = s_trandepart.getDetailTransactionByDocumentId(doc_id);
            //คำนวนยอดสั่งสินค้าตามรายชื่อสาขา
            List<OUSummaryOrderByDepartment> summaryBydepartment = s_trandepart.summaryOrderByDepartment(doc_id);
            //คำนวนยอดสั่งสินค้าตามรหัสสินค้า และ size สินค้า
            List<OUSummaryOrderDepartmentByMaterialAndSize> summarybysize = s_trandepart.summaryOrderByMaterialandSize(doc_id);
            //คำนวนยอดรวมสั่งสินค้าตามไซส์
            HashMap<String,Integer> totalbysize = s_trandepart.calculateTotalBySize(summarybysize);

                request.setAttribute("detail_doc", listdetail);
                request.setAttribute("summaryByDepartment", summaryBydepartment);
                request.setAttribute("summarybysize", summarybysize);
                request.setAttribute("totalbysize", totalbysize);
                
            getServletContext().getRequestDispatcher("/detailtransactiondepartment.jsp").forward(request, response);
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
