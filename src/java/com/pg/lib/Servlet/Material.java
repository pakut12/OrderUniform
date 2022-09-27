/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pg.lib.Servlet;

import com.pg.lib.model.OUMaterialDetail;
import com.pg.lib.service.MaterialService;
import java.io.*;
import java.net.*;

import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author 111525
 */
public class Material extends HttpServlet {
   
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String type = request.getParameter("type") == null ? "" : request.getParameter("type").trim();
        MaterialService s_mat = new MaterialService();
        
        if(type.equals("createnew")){
            String code = request.getParameter("code") == null ? "" : request.getParameter("code");
            String spec = request.getParameter("spec") == null ? "" : request.getParameter("spec");
            //String color = request.getParameter("color") == null ? "" : request.getParameter("color");
            String desc = request.getParameter("desc") == null ? "" : request.getParameter("desc");
            String company = request.getParameter("company") == null ? "" : request.getParameter("company");
            
            if(s_mat.createNewMaterial(code,spec,desc,company)){
                out.print("{\"status\":\"Create Success\"}");
            } else {
                out.print("{\"status\":\"Create Fail\"}");
            }
            
        } else if (type.equals("loaddata")){
            String returnHTML = "";
            List<OUMaterialDetail> listmat = s_mat.getDataMaterial();
            
                returnHTML += "<table id=\"material_detail\">";
                returnHTML += "<thead>";
                returnHTML += "<tr>";
                returnHTML += "<th>รหัสสินค้า</th>";
                returnHTML += "<th>คำอธิบายเพิ่มเติม</th>";
                returnHTML += "<th>เพศ</th>";
                returnHTML += "<th>ชนิดสินค้า</th>";
                returnHTML += "<th>ประเภท</th>";
                returnHTML += "<th>ลายผ้า</th>";
                returnHTML += "<th>Running No.</th>";
                returnHTML += "<th>สี</th>";
                returnHTML += "<th>กลุ่ม</th>";
                returnHTML += "<th>บริษัท</th>";
                returnHTML += "</tr>";
                returnHTML += "</thead>";
                returnHTML += "<tbody>";
                    for(int i=0 ; i<= listmat.size()-1 ; i++){
                        returnHTML += "<tr>";
                        returnHTML += "<td>"+listmat.get(i).getHmat_code()+"</td>";
                        returnHTML += "<td>"+listmat.get(i).getHmat_desc()+"</td>";
                        returnHTML += "<td>"+listmat.get(i).getHmat_gender()+"</td>";
                        returnHTML += "<td>"+listmat.get(i).getHmat_category()+"</td>";
                        returnHTML += "<td>"+listmat.get(i).getHmat_type()+"</td>";
                        returnHTML += "<td>"+listmat.get(i).getHmat_pattern()+"</td>";
                        returnHTML += "<td>"+listmat.get(i).getHmat_rno()+"</td>";
                        returnHTML += "<td>"+listmat.get(i).getHmat_color()+"</td>";
                            if(listmat.get(i).getGroup_name()==null){
                                returnHTML += "<td>ไม่ได้จัดกลุ่ม</td>";
                            } else {
                                returnHTML += "<td>"+listmat.get(i).getGroup_name()+"</td>";
                            }
                        returnHTML += "<td>"+listmat.get(i).getCom_name()+"</td>";
                        returnHTML += "</tr>";
                    }
                returnHTML += "</tbody>";
                returnHTML += "</table>";
                
            out.print(returnHTML);
        }
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
