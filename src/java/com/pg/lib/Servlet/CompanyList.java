/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pg.lib.Servlet;

import com.pg.lib.model.OUCompany;
import com.pg.lib.service.CompanyService;
import java.io.*;
import java.net.*;

import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author 111525
 */
public class CompanyList extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {

        request.setCharacterEncoding("UTF-8");
        String type = (String) request.getParameter("type").trim() == null ? "" : request.getParameter("type").trim();
        JSONArray jarr = new JSONArray();
        HashMap<Integer, OUCompany> hmCompany = new HashMap<Integer, OUCompany>();

        if (type.equals("getCompanyName")) {
            String content = request.getParameter("content") == null ? "" : request.getParameter("content").trim();
            hmCompany = CompanyService.getCompanyDetail(content);
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            for (int i = 0; i <= hmCompany.size() - 1; i++) {
                JSONObject jobj = new JSONObject();
                jobj.put("code", hmCompany.get(i).getCode());
                jobj.put("name", hmCompany.get(i).getName());
                jarr.put(jobj);
            }
            out.print(jarr);
            out.close();
        } else if (type.equals("getDetailCompany")) {
            hmCompany = CompanyService.getCompanyDetail("all");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String returnResult = "";
            returnResult += "<table id=\"table_company\" class='table table-bordered w-100'>";
            returnResult += "<thead>";
            returnResult += "<tr>";
            returnResult += "<th scope=\"col\">รหัสบริษัท</th>";
            returnResult += "<th scope=\"col\">ชื่อบริษัท</th>";
            returnResult += "<th scope=\"col\">ใช้กับข้อมูลประเภท</th>";
            returnResult += "<th scope=\"col\">เเก้ไข</th>";
            returnResult += "<th scope=\"col\">ลบ</th>";
            returnResult += "</tr>";
            returnResult += "</thead>";
            returnResult += "<tbody>";
            for (int i = 0; i <= hmCompany.size() - 1; i++) {
                returnResult += "<tr>";
                returnResult += "<td>" + hmCompany.get(i).getCode() + "</td>";
                returnResult += "<td>" + hmCompany.get(i).getName() + "</td>";
                if (hmCompany.get(i).getContent_type().equals("customer")) {
                    returnResult += "<td>แบบรายชื่อ</td>";
                } else if (hmCompany.get(i).getContent_type().equals("department")) {
                    returnResult += "<td>แบบแผนก</td>";
                }
                returnResult += "<td><button type='button' class='btn btn-warning edit_btn' data-bs-toggle='modal' data-bs-target='#EditCompanyModal' >เเก้ไข</button></td>";
                returnResult += "<td><button type='button' id='DelCompany' class='btn btn-danger del_btn'>ลบ</button></td>";
                returnResult += "</tr>";
            }
            returnResult += "</tbody>";
            returnResult += "</table>";
            out.print(returnResult);
            out.close();
        } else if (type.equals("AddNew")) {
            String prename = request.getParameter("prename") != null ? request.getParameter("prename") : "";
            String companyname = request.getParameter("companyname") != null ? request.getParameter("companyname") : "";
            String endname = request.getParameter("endname") != null ? request.getParameter("endname") : "";
            String extensionname = request.getParameter("extensionname") != null ? request.getParameter("extensionname") : "";
            String contenttype = request.getParameter("cotent_name") != null ? request.getParameter("cotent_name") : "";

            String statusCreate = CompanyService.addNewCompany(prename + " " + companyname + " " + endname + " " + extensionname, contenttype);
            String url = "/managecompany.jsp";

            if (statusCreate.equals("Success")) {

                request.setAttribute("statuscreatenew", "createsuccess");
            } else {

                request.setAttribute("statuscreatenew", "createfail");
            }

            getServletContext().getRequestDispatcher(url).forward(request, response);
        } else if (type.equals("EditCompany")) {
            PrintWriter out = response.getWriter();
            String comp_name = request.getParameter("comp_name") != null ? request.getParameter("comp_name") : "";
            String comp_code = request.getParameter("comp_code") != null ? request.getParameter("comp_code") : "";
            String content_type = request.getParameter("content_type") != null ? request.getParameter("content_type") : "";

            String status = CompanyService.EditCompany(comp_name, content_type, comp_code);

            response.setContentType("application/json;charset=UTF-8");

            out.print(status);
            out.close();

        } else if (type.equals("DeleteCompany")) {
            PrintWriter out = response.getWriter();

            String comp_code = request.getParameter("comp_code") != null ? request.getParameter("comp_code") : "";

            String status = CompanyService.DeleteCompany(comp_code);

            response.setContentType("application/json;charset=UTF-8");
           
            out.print(status);
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
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            ex.printStackTrace();
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
            ex.printStackTrace();
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
