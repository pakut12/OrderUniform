/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pg.lib.Servlet;

import com.pg.lib.model.OUCustomerDetail;
import com.pg.lib.model.OUUploadCustomer;
import com.pg.lib.service.CustomerService;
import java.io.*;
import java.net.*;

import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author 111525
 */
public class Customer extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf8");

        String type = request.getParameter("type");

        if (type.equalsIgnoreCase("getdetailcustomer")) {
            PrintWriter out = response.getWriter();
            String returnResult = "";
            CustomerService cs = new CustomerService();
            List<OUCustomerDetail> cusobj = cs.getDataALL();

            if (cusobj != null) {
                returnResult += "<table id=\"customerdetail\" >";
                returnResult += "<thead>";
                returnResult += "<tr>";
                returnResult += "<th>ลำดับ</th>";
                returnResult += "<th>เลขที่</th>";
                returnResult += "<th>รหัสพนักงาน</th>";
                returnResult += "<th>คำนำหน้าชื่อ</th>";
                returnResult += "<th>ชื่อ-นามสกุล</th>";
                returnResult += "<th>แผนก</th>";
                returnResult += "<th>บริษัท</th>";
                returnResult += "<th>เเก้ไข</th>";
                returnResult += "<th>ลบ</th>";
                returnResult += "</tr>";
                returnResult += "</thead>";
                returnResult += "<tbody>";
                for (int i = 0; i <= cusobj.size() - 1; i++) {
                    returnResult += "<tr>";
                    returnResult += "<td>" + cusobj.get(i).getCusSeq() + "</td>";
                    returnResult += "<td>" + cusobj.get(i).getCusId() + "</td>";
                    returnResult += "<td>" + cusobj.get(i).getCusNo() + "</td>";
                    returnResult += "<td>" + cusobj.get(i).getCusPreName() + "</td>";
                    returnResult += "<td>" + cusobj.get(i).getCusFName() + "</td>";
                    returnResult += "<td>" + cusobj.get(i).getCusDepartmet() + "</td>";
                    returnResult += "<td>" + cusobj.get(i).getCusCompany() + "</td>";
                    returnResult += "<td><button type='button' class='btn btn-warning btn-sm edit_btn'>เเก้ไข</button></td>";
                    returnResult += "<td><button type='button' class='btn btn-danger btn-sm del_btn'>ลบ</button></td></td>";
                    returnResult += "</tr>";
                }
                returnResult += "</tbody>";
                returnResult += "</table>";
            }

            out.print(returnResult);
            out.close();
        } else if (type.equalsIgnoreCase("editdetailcustomer")) {
            PrintWriter out = response.getWriter();
            String cms_id = request.getParameter("cms_id");
            String cms_no = request.getParameter("cms_no");
            String cms_pname = request.getParameter("cms_pname");
            String cms_fname = request.getParameter("cms_fname");
            String cms_depart = request.getParameter("cms_depart");

            OUCustomerDetail data = new OUCustomerDetail();
            data.setCusId(cms_id);
            data.setCusNo(cms_no);
            data.setCusPreName(cms_pname);
            data.setCusFName(cms_fname);
            data.setCusDepartment(cms_depart);

            CustomerService cs = new CustomerService();
            out.print(cs.InsertDataOneRow(data));

            out.close();

        } else if (type.equalsIgnoreCase("deldetailcustomer")) {
            PrintWriter out = response.getWriter();
            String cms_id = request.getParameter("cms_id");

            OUCustomerDetail data = new OUCustomerDetail();
            data.setCusId(cms_id);

            CustomerService cs = new CustomerService();
            out.print(cs.DeleteDataOneRow(data));

            out.close();

        } else if (type.equalsIgnoreCase("adddetailcustomer")) {
            PrintWriter out = response.getWriter();
            String addcmsno = request.getParameter("addcmsno");
            String addpname = request.getParameter("addpname");
            String addfname = request.getParameter("addfname");
            String adddepart = request.getParameter("adddepart");
            String addcompany = request.getParameter("addcompany");

            OUUploadCustomer upc = new OUUploadCustomer();
            upc.setCustomerID(addcmsno);
            upc.setCompany(addcompany);
            upc.setDepartment(adddepart);
            upc.setFullname(addfname);
            upc.setPrename(addpname);

            CustomerService cms = new CustomerService();
            boolean status = cms.addCustomerOneRow(upc);

            if (status) {
                out.print("true");
            } else {
                out.print("false");
            }

            out.close();

        } else if (type.equalsIgnoreCase("updatestatuscustomer")) {
            PrintWriter out = response.getWriter();
            String cm_id = request.getParameter("cm_id");

            CustomerService cs = new CustomerService();
            boolean stutus = cs.UpdateStatus(cm_id);


            out.print(stutus);
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
