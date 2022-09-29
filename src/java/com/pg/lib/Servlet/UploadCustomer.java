/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pg.lib.Servlet;

import com.pg.lib.model.OUCustomerDetail;
import com.pg.lib.model.OUUploadCustomer;
import com.pg.lib.service.CustomerService;
import com.pg.lib.service.ReadExcelService;
import com.pg.lib.service.UploadFileService;
import java.io.*;
import java.net.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author 111525
 */
public class UploadCustomer extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        PrintWriter out = response.getWriter();
        HashMap<String, String> uploadDetail = new HashMap<String, String>();
        String returnResult = "";

        //Upload files to Server
        UploadFileService oupload = new UploadFileService();
        uploadDetail = oupload.checkMultiContent(request, "uploadCustomer");

        //Read xlsx file and save into database;
        ReadExcelService oreadfile = new ReadExcelService();
        List<OUUploadCustomer> customerList = oreadfile.readExcelFileCustomer(uploadDetail);

        //Insert data into database;
        CustomerService ocustomer = new CustomerService();

        if (ocustomer.addCustomer(customerList)) {
            //Load data customer and Generate to HTML
            List<OUCustomerDetail> customerDetail = ocustomer.getDataUpload(uploadDetail.get("comp"));
            returnResult += "<table id=\"uploaddetail\" class=\"table table-striped\" >";
            returnResult += "<thead>";
            returnResult += "<tr>";
            returnResult += "<th scope=\"col\">NO</th>";
            returnResult += "<th scope=\"col\">รหัสพนักงาน</th>";
            returnResult += "<th scope=\"col\">คำนำหน้าชื่อ</th>";
            returnResult += "<th scope=\"col\">ชื่อ-นามสกุล</th>";
            returnResult += "<th scope=\"col\">แผนก</th>";
            returnResult += "<th scope=\"col\">บริษัท</th>";
            returnResult += "</tr>";
            returnResult += "</thead>";
            returnResult += "<tbody>";
            for (int i = 0; i <= customerDetail.size() - 1; i++) {
                returnResult += "<tr>";
                returnResult += "<th scope=\"row\">" + customerDetail.get(i).getCusSeq() + "</th>";
                returnResult += "<td>" + customerDetail.get(i).getCusNo() + "</td>";
                returnResult += "<td>" + customerDetail.get(i).getCusPreName() + "</td>";
                returnResult += "<td>" + customerDetail.get(i).getCusFName() + "</td>";
                returnResult += "<td>" + customerDetail.get(i).getCusDepartmet() + "</td>";
                returnResult += "<td>" + customerDetail.get(i).getCusCompany() + "</td>";
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
