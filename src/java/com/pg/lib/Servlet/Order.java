package com.pg.lib.Servlet;

import com.pg.lib.model.OUCustomerDetailWithID;
import com.pg.lib.model.OUDepartmentDetailWithID;
import com.pg.lib.model.OUMaterialDetailWithID;
import com.pg.lib.service.OrderService;
import com.pg.lib.service.WriteExcelService;
import java.io.*;
import java.net.*;

import java.util.List;
import java.util.TreeMap;
import javax.servlet.*;
import javax.servlet.http.*;

public class Order extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String type = request.getParameter("type") == null ? "" : request.getParameter("type");

        OrderService s_order = new OrderService();

        //กดเลือกรูปแบบที่จะดูข้อมูล
        if (type.equals("selectedcontent")) {
            String contentname = (String) request.getParameter("contentname") == null ? "" : request.getParameter("contentname");
            String textHTML = "";
            List<TreeMap> result = s_order.getCompanyList(contentname);

            for (int i = 0; i <= result.size() - 1; i++) {
                textHTML += "<button class=\"list-group-item list-group-item-action list-group-item-success\" " +
                        " onclick=\"setupOrderDetail(this)\" " +
                        " data-bs-toggle=\"list\" " +
                        " role=\"tab\" " +
                        " value=\"" + result.get(i).keySet().toArray()[0] + "\">" +
                        result.get(i).values().toArray()[0] + "</button>\n";
            }
            out.print(textHTML);
            out.close();
        //โหลดหน้าจอแสดงข้อมูลตัวอย่างที่จะโหลดออกมาเป็น excel
        } else if (type.equals("setupOrderDetail")) {
            String companyID = request.getParameter("companyid") == null ? "" : request.getParameter("companyid");
            String textHTML = "";
            List<String> companydetail = s_order.getDetailOfCompany(companyID);
            List<OUMaterialDetailWithID> listmat = s_order.getMaterialOfCompany(companydetail);

            //ดู type ว่าบริษัทที่รับเข้ามานั้นเป็นรูปแบบไหน 
            //if (พนักงาน)
            if (companydetail.get(3).equals("customer")) {

                // ไปโหลดรายชื่อมาว่าบริษัทนี้มีพนักงานชื่ออะไรบ้าง
                List<OUCustomerDetailWithID> listcus = s_order.getCustomerOfComapany(companydetail);

                if (listmat.size() == 0 && listcus.size() == 0) {
                    textHTML += "<h5>ไม่มีข้อมูลสำหรับการแสดงผล</h5>";
                    session.setAttribute("statusdownload", false);
                } else {
                    // Generate HTML เพื่อเอาไปแสดงผล
                    textHTML += "<table class=\"table table-bordered table-light table-responsive-md table-hover table-striped\">";
                    textHTML += "<thead>";
                    textHTML += "<tr>";
                    textHTML += "<th colspan=\"4\" rowspan=\"3\" style=\"text-align:center;min-width:300px\">ข้อมูลพนักงาน</th>";

                    //Column ชื่อ item
                    for (int i = 0; i <= listmat.size() - 1; i++) {
                        textHTML += "<th colspan=\"2\" style=\"font-size:13px;text-align:center\">" + listmat.get(i).getHmat_code() +
                                "<br>" + listmat.get(i).getHmat_desc() +
                                "<br>" + listmat.get(i).getHmat_color() +
                                "</th>";
                    }
                    textHTML += "</tr>";
                    textHTML += "<tr>";
                    for (int l = 0; l <= listmat.size() - 1; l++) {
                        textHTML += "<th colspan=\"2\" style=\"font-size:13px;text-align:center\">" + listmat.get(l).getHmat_id() +
                                "</th>";
                    }
                    textHTML += "</tr>";
                    textHTML += "<tr>";

                    //Loop Column size และ จำนวน
                    for (int l = 0; l <= listmat.size() - 1; l++) {
                        textHTML += "<th style=\"font-size:13px;text-align:center\">size</th>";
                        textHTML += "<th style=\"font-size:13px;text-align:center\">จำนวน</th>";
                    }
                    textHTML += "</tr>";
                    textHTML += "</thead>";
                    textHTML += "<tbody>";

                    //Loop ข้อมูลพนักงาน
                    for (int j = 0; j <= listcus.size() - 1; j++) {
                        textHTML += "<tr>";
                        textHTML += "<td>" + listcus.get(j).getCus_seq() + "</td>";
                        textHTML += "<td>" + listcus.get(j).getCus_no() + "</td>";
                        textHTML += "<td>" + listcus.get(j).getCus_prename() + " " + listcus.get(j).getCus_fname() + "</td>";
                        textHTML += "<td>" + listcus.get(j).getCompany_id() + "</td>";
                        //Loop เพิ่มช่องกรอกข้อมูล ตามจำนวน item
                        for (int k = 0; k <= listmat.size() - 1; k++) {
                            textHTML += "<td></td>";
                            textHTML += "<td></td>";
                        }
                        textHTML += "</tr>";
                    }
                    textHTML += "</tbody>";
                    textHTML += "</table>";
                    //จบ Generate HTML เอาไปแสดงผล

                    //set ข้อมูลที่ค้นมาลง session
                    session.setAttribute("Company", companydetail);
                    session.setAttribute("Item", listmat);
                    session.setAttribute("customer", listcus);
                    session.setAttribute("type", "customer");
                    session.setAttribute("statusdownload", true);

                }
                //ส่งค่ากลับไป JSP ไฟล์
                out.print(textHTML);
                out.close();
            //แบบแผนก
            } else if (companydetail.get(3).equals("department")) {


                //ไปโหลดรายชื่อมาว่าบริษัทนี้มีแผนกอะไรบ้าง
                List<OUDepartmentDetailWithID> listdepart = s_order.getDepartmentOfCompany(companydetail);

                if (listmat.size() == 0 && listdepart.size() == 0) {
                    textHTML += "<h5>ไม่มีข้อมูลสำหรับการแสดงผล</h5>";
                    session.setAttribute("statusdownload", false);
                } else {
                    textHTML += "<table class=\"table table-bordered table-light table-responsive-md table-hover table-striped\">";
                    textHTML += "<thead>";
                    textHTML += "<tr>";
                    textHTML += "<th colspan = \"5\" rowspan=\"3\" style=\"text-align:center;min-width:600px\">รายชื่อแผนก</th>";
                    for (int i = 0; i <= listmat.size() - 1; i++) {
                        textHTML += "<th colspan=\"2\" style=\"font-size:13px;text-align:center\">" + listmat.get(i).getHmat_code() +
                                "<br>" + listmat.get(i).getHmat_desc() +
                                "<br>" + listmat.get(i).getHmat_color() +
                                "</th>";
                    }

                    textHTML += "</tr>";
                    textHTML += "<tr>";
                    for (int l = 0; l <= listmat.size() - 1; l++) {
                        textHTML += "<th colspan=\"2\" style=\"font-size:13px;text-align:center\">" + listmat.get(l).getHmat_id() +
                                "</th>";
                    }
                    textHTML += "</tr>";
                    textHTML += "<tr>";
                    for (int l = 0; l <= listmat.size() - 1; l++) {
                        textHTML += "<td style=\"font-size:13px;text-align:center\">size</td>";
                        textHTML += "<td style=\"font-size:13px;text-align:center\">จำนวน</td>";
                    }
                    textHTML += "</tr>";
                    textHTML += "</thead>";
                    textHTML += "<tbody>";
                    for (int j = 0; j <= listdepart.size() - 1; j++) {
                        textHTML += "<tr>";
                        textHTML += "<td>" + listdepart.get(j).getDepart_seq() + "</td>";
                        textHTML += "<td>" + listdepart.get(j).getDepart_agency() + "</td>";
                        textHTML += "<td>" + listdepart.get(j).getDepart_division() + "</td>";
                        textHTML += "<td>" + listdepart.get(j).getDepart_name() + "</td>";
                        textHTML += "<td>" + listdepart.get(j).getCompany_id() + "</td>";
                        for (int k = 0; k <= listmat.size() - 1; k++) {
                            textHTML += "<td></td>";
                            textHTML += "<td></td>";
                        }
                        textHTML += "</tr>";
                    }
                    textHTML += "</tbody>";
                    textHTML += "</table>";

                    session.setAttribute("Company", companydetail);
                    session.setAttribute("Item", listmat);
                    session.setAttribute("Department", listdepart);
                    session.setAttribute("type", "department");
                    session.setAttribute("statusdownload", true);
                }
                out.print(textHTML);
                out.close();
            }
        } else if (type.equals("generatexls")) {
            WriteExcelService writeXLS_service = new WriteExcelService();
            List<String> Company = (List<String>) request.getSession().getAttribute("Company");
            List<OUMaterialDetailWithID> item = (List<OUMaterialDetailWithID>) request.getSession().getAttribute("Item");

            if ((Boolean) request.getSession().getAttribute("statusdownload")) {
                if (request.getSession().getAttribute("type").equals("customer")) {
                    List<OUCustomerDetailWithID> customer = (List<OUCustomerDetailWithID>) request.getSession().getAttribute("customer");
                    String pathdownload = writeXLS_service.generateXLSFileCustomer(Company, item, customer);

                    // return to js
                    out.print(pathdownload);
                } else if (request.getSession().getAttribute("type").equals("department")) {
                    List<OUDepartmentDetailWithID> department = (List<OUDepartmentDetailWithID>) request.getSession().getAttribute("Department");
                    String pathdownload = writeXLS_service.generateXLSFileDepartment(Company, item, department);

                    // return to js
                    out.print(pathdownload);
                }
            } else {
                // return to js
                out.print("data not found");
            }
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
