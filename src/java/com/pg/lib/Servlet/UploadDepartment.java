/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pg.lib.Servlet;

import com.pg.lib.model.OUDepartmentDetail;
import com.pg.lib.model.OUUploadDepartment;
import com.pg.lib.service.DepartmentService;
import com.pg.lib.service.ReadExcelService;
import com.pg.lib.service.UploadFileService;
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
public class UploadDepartment extends HttpServlet {
   
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HashMap<String,String> UploadDetail = new HashMap<String,String>();
        String returnResult = "";
        
        //Upload files to Server
        UploadFileService oupload = new UploadFileService();
        UploadDetail = oupload.checkMultiContent(request, "uploaddepartment");
        
        //Read xlsx file and save into database;
        ReadExcelService oreadExcel = new ReadExcelService();
        List<OUUploadDepartment> listDepart = oreadExcel.readExcelFileDepartment(UploadDetail);
        
        //insert data to database;
        DepartmentService s_department = new DepartmentService();
        if(s_department.addDepartment(listDepart)){
            List<OUDepartmentDetail> depart = s_department.getDataUpload(UploadDetail.get("comp"));
            returnResult += "<table id=\"uploaddetail\" class=\"table table-striped\" >";
            returnResult += "<thead>";
            returnResult += "<tr>";
            returnResult += "<th scope=\"col\">NO</th>";
            returnResult += "<th scope=\"col\">หน่วยงาน 1 (ฝ่าย)</th>";
            returnResult += "<th scope=\"col\">หน่วยงาน 2 (กอง)</th>";
            returnResult += "<th scope=\"col\">สาขา</th>";
            returnResult += "<th scope=\"col\">บริษัท</th>";
            returnResult += "</tr>";
            returnResult += "</thead>";
            returnResult += "<tbody>";
                for(int i = 0 ; i <= depart.size()-1 ; i++){
                    returnResult += "<tr>";
                    returnResult += "<th scope=\"row\">"+depart.get(i).getDepart_seq()+"</th>";
                    returnResult += "<td>"+depart.get(i).getDepart_agency()+"</td>";
                    returnResult += "<td>"+depart.get(i).getDepart_division()+"</td>";
                    returnResult += "<td>"+depart.get(i).getDepart_name()+"</td>";
                    returnResult += "<td>"+depart.get(i).getDepart_company()+"</td>";
                    returnResult += "</tr>";
                }
            returnResult += "</tbody>";
            returnResult += "</table>";
            out.print(returnResult);
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
