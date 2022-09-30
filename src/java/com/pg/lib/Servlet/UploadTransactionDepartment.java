package com.pg.lib.Servlet;

import com.pg.lib.model.OUOrderListDepartment;
import com.pg.lib.model.OUUDepartmentOrder;
import com.pg.lib.service.ReadExcelService;
import com.pg.lib.service.TransactionDepartmentService;
import com.pg.lib.service.UploadFileService;
import java.io.*;

import java.util.HashMap;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;


public class UploadTransactionDepartment extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        UploadFileService s_upload  = new UploadFileService();
        HashMap<String,String> detailFile = s_upload.checkMultiContent(request,"uploadtransactiondepartment");
        
        ReadExcelService s_readxls = new ReadExcelService();
//**Display list datalist
        List<OUUDepartmentOrder> datalist = s_readxls.readExcelFileTransactionDepartment(detailFile);
//        for(int i = 0 ; i <= datalist.size()-1 ; i++){
//                System.out.println("Id : "+datalist.get(i).getId());
//                System.out.println("Agency : "+datalist.get(i).getAgency());
//                System.out.println("Division : "+datalist.get(i).getDivision());
//                System.out.println("Department : "+datalist.get(i).getDepartment());
//            for(int j = 0 ; j<= datalist.get(i).getItem().size()-1; j++) {
//                System.out.println("item_id : "+datalist.get(i).getItem().get(j).getCode());
//                System.out.println("Size : "+datalist.get(i).getItem().get(j).getSize());
//                System.out.println("Quantity : "+datalist.get(i).getItem().get(j).getQty());
//            }
//                System.out.println("====================================");
//        }
        
        TransactionDepartmentService s_transaction = new TransactionDepartmentService();
//**Display list orderListDepart;
        List<OUOrderListDepartment> orderListDepartment = s_transaction.processData(datalist);
//        for(int j = 0 ; j <= orderListDepartment.size()-1 ; j++){
//            System.out.println("depart_id : "+orderListDepartment.get(j).getDepart_id());
//            System.out.println("company_id : "+orderListDepartment.get(j).getCompanyID());
//            System.out.println("material_id : "+orderListDepartment.get(j).getMaterialID());
//            System.out.println("size : "+orderListDepartment.get(j).getSize());
//            System.out.println("quantity : "+orderListDepartment.get(j).getQuantity());
//            System.out.println("====================================================");
//        }
            int headerid = s_transaction.createNewTransaction(orderListDepartment,detailFile);
            
                if(headerid != 0){
                     boolean statusUpdateItem = s_transaction.updateStatusItems(orderListDepartment);
                     boolean statusUpdateDepart = s_transaction.updateStatusDepartment(orderListDepartment);
                        if(statusUpdateItem && statusUpdateDepart){
                            out.print("{\"doc_id\":"+headerid+"}");
                            System.out.println("Update status item and department success...");
                        } else {
                            System.out.println("Update status item and department failure...");
                        }
                } else {
                     System.out.println("create transaction failure ...");
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
