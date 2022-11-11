/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pg.lib.Servlet;

import com.pg.lib.model.OUDepartmentDetail;
import com.pg.lib.service.CompanyService;
import java.io.*;
import java.net.*;

import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author 111525
 */
public class Department extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String returnResult = "";

        CompanyService s_company = new CompanyService();
        List<OUDepartmentDetail> listDepartment = s_company.getDataALL();

        if (listDepartment != null) {
            returnResult += "<table id=\"departmentDetail\">";
            returnResult += "<thead>";
            returnResult += "<tr>";
            returnResult += "<th>ลำดับ</th>";
            returnResult += "<th>หน่วยงาน 1 ฝ่าย</th>";
            returnResult += "<th>หน่วยงาน 2 กอง</th>";
            returnResult += "<th>สาขา</th>";
            returnResult += "<th>บริษัท</th>";
            returnResult += "<th>เเก้ไข</th>";
            returnResult += "<th>ลบ</th>";
            returnResult += "</tr>";
            returnResult += "</thead>";
            returnResult += "<tbody>";
            for (int i = 0; i <= listDepartment.size() - 1; i++) {
                returnResult += "<tr>";
                returnResult += "<td>" + listDepartment.get(i).getDepart_seq() + "</td>";
                returnResult += "<td>" + listDepartment.get(i).getDepart_agency() + "</td>";
                returnResult += "<td>" + listDepartment.get(i).getDepart_division() + "</td>";
                returnResult += "<td>" + listDepartment.get(i).getDepart_name() + "</td>";
                returnResult += "<td>" + listDepartment.get(i).getDepart_company() + "</td>";
                returnResult += "<td><button type='button' class='btn btn-warning btn-sm edit_btn'>เเก้ไข</button></td>";
                returnResult += "<td><button type='button' class='btn btn-danger btn-sm del_btn'>ลบ</button></td></td>";
                returnResult += "</tr>";
            }
            returnResult += "</tbody>";
            returnResult += "</table>";
        }

        out.print(returnResult);
        out.close();
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
