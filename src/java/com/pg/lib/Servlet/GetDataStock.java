/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pg.lib.Servlet;

import com.pg.lib.model.OUSummaryOrderByCustomer;
import com.pg.lib.model.OUTransactionCustomerDetail;
import com.pg.lib.service.CustomerService;
import com.pg.lib.service.TransactionCustomerService;
import java.io.*;
import java.net.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author pakutsing
 */
public class GetDataStock extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String type = request.getParameter("type");

            if (type.equalsIgnoreCase("getdataformbarcodebag")) {
                String doc_id = request.getParameter("doc_id");
                String cus_no = request.getParameter("cus_no");

                TransactionCustomerService s_trancustomer = new TransactionCustomerService();
                List<OUTransactionCustomerDetail> listdetail = s_trancustomer.getDetailFromBarcode(doc_id, cus_no);

                String HTMLtag = "";

                HTMLtag += "<div class=\"row mb-3\">";
                HTMLtag += "<div class=\"col-4 text-end\">เลขที่เอกสาร : </div>";
                HTMLtag += "<div class=\"col-4\">";
                HTMLtag += "<input class=\"form-control form-control-sm text-center\" type=\"text\" id=\"cus_no\" value=\"" + listdetail.get(0).getDocID() + "\" readonly></input>";
                HTMLtag += "</div>";
                HTMLtag += "</div>";
                HTMLtag += "<div class=\"row mb-3\">";
                HTMLtag += "<div class=\"col-4 text-end\">รหัสพนักงาน : </div>";
                HTMLtag += "<div class=\"col-4\">";
                HTMLtag += "<input class=\"form-control form-control-sm text-center\" type=\"text\" id=\"cus_no\" value=\"" + listdetail.get(0).getCustomerCode() + "\" readonly></input>";
                HTMLtag += "</div>";
                HTMLtag += "</div>";
                HTMLtag += "<div class=\"row mb-3\">";
                HTMLtag += "<div class=\"col-4 text-end\">ชื่อ : </div>";
                HTMLtag += "<div class=\"col-4\">";
                HTMLtag += "<input class=\"form-control form-control-sm text-center\" type=\"text\" id=\"cus_no\" value=\"" + listdetail.get(0).getPrename() + " " + listdetail.get(0).getFname() + "\" readonly></input>";
                HTMLtag += "</div>";
                HTMLtag += "</div>";
                HTMLtag += "<div class=\"row mb-3\">";
                HTMLtag += "<div class=\"col-4 text-end\">เเผนก : </div>";
                HTMLtag += "<div class=\"col-4\">";
                HTMLtag += "<input class=\"form-control form-control-sm text-center\" type=\"text\" id=\"cus_no\" value=\"" + listdetail.get(0).getDepartmentname() + "\" readonly></input>";
                HTMLtag += "</div>";
                HTMLtag += "</div>";



                HTMLtag += "<table id=\"listdata\" class=\"table w-100 table-bordered text-center\" >";
                HTMLtag += "<thead>";
                HTMLtag += "<tr>";
                HTMLtag += "<th>ลำดับ</th>";
                HTMLtag += "<th>ชื่อสินค้า</th>";
                HTMLtag += "<th>รหัสสินค้า</th>";
                HTMLtag += "<th>สี</th>";
                HTMLtag += "<th>ไซส์</th>";
                HTMLtag += "<th>รหัสสินค้า 18 หลัก</th>";
                HTMLtag += "<th>Barcode</th>";
                HTMLtag += "<th>จำนวน</th>";
                HTMLtag += "</tr>";
                HTMLtag += "</thead>";
                HTMLtag += "<tbody>";

                int sum = 0;
                for (int i = 0; i <= listdetail.size() - 1; i++) {
                    HTMLtag += "<tr>";
                    HTMLtag += "<td>" + (i + 1) + "</td>";

                    HTMLtag += "<td>" + listdetail.get(i).getDesc() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getMaterialname() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getColor() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getSize() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getMatfullname() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getBarcode() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getQuantity() + "</td>";
                    HTMLtag += "</tr>";

                    sum += Integer.parseInt(listdetail.get(i).getQuantity());
                }
                HTMLtag += "<tfoot>";
                HTMLtag += "<tr>";
                HTMLtag += "<th colspan=\"7\" class=\"text-end\">รวมทั้งหมด</th>";
                HTMLtag += "<th>" + sum + "</th>";
                HTMLtag += "</tr>";
                HTMLtag += "</tfoot>";
                HTMLtag += "</table>";
                HTMLtag += "<div class=\"mt-3 text-end\">";
                HTMLtag += "<a href=\"report/ReportBag.jsp?doc_id=" + doc_id + "&cus_no=" + cus_no + "\" target=\"_blank\"><button class=\"btn btn-sm btn-secondary\" >พิมพ์สติ๊กเกอร์</button></a>";
                HTMLtag += "&nbsp";
                HTMLtag += "<a href=\"report/ReportBagAll.jsp?doc_id=" + doc_id + "&cus_no=" + cus_no + "\" target=\"_blank\"><button class=\"btn btn-sm btn-secondary\" >พิมพ์สติ๊กเกอร์ทั้งหมด</button></a>";
                HTMLtag += "";
                HTMLtag += "</div>";
                out.print(HTMLtag);
            } else if (type.equalsIgnoreCase("getdatadepart")) {
                String doc_id = request.getParameter("doc_id");


                CustomerService cms = new CustomerService();
                ArrayList<OUTransactionCustomerDetail> listcms = cms.GroupCustomerDepart(doc_id);
                JSONArray jsarr = new JSONArray();
                JSONObject obj = new JSONObject();
                for (OUTransactionCustomerDetail op : listcms) {
                    jsarr.put(op.getDepartmentname());
                }
                obj.put("depart", jsarr);
                out.print(obj);
            } else if (type.equalsIgnoreCase("getdataformbarcodedepart")) {
                String doc_id = request.getParameter("doc_id");
                String depart = request.getParameter("cus_department");

                TransactionCustomerService s_trancustomer = new TransactionCustomerService();
                List<OUTransactionCustomerDetail> listdetail = s_trancustomer.getDetailFromBarcodeDepart(doc_id, depart);


                String HTMLtag = "";

                HTMLtag += "<table id=\"listdata\" class=\"table w-100 \" >";
                HTMLtag += "<thead>";
                HTMLtag += "<tr>";
                HTMLtag += "<th>ลำดับ</th>";
                HTMLtag += "<th>ชื่อ</th>";
                HTMLtag += "<th>ชื่อสินค้า</th>";
                HTMLtag += "<th>รหัสสินค้า</th>";
                HTMLtag += "<th>สี</th>";
                HTMLtag += "<th>ไซส์</th>";
                HTMLtag += "<th>รหัสสินค้า 18 หลัก</th>";
                HTMLtag += "<th>Barcode</th>";
                HTMLtag += "<th>จำนวน</th>";
                HTMLtag += "</tr>";
                HTMLtag += "</thead>";
                HTMLtag += "<tbody>";

                int sum = 0;
                for (int i = 0; i <= listdetail.size() - 1; i++) {
                    HTMLtag += "<tr>";
                    HTMLtag += "<td>" + (i + 1) + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getPrename() + " " + listdetail.get(i).getFname() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getDesc() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getMaterialname() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getColor() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getSize() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getMatfullname() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getBarcode() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getQuantity() + "</td>";
                    HTMLtag += "</tr>";

                    sum += Integer.parseInt(listdetail.get(i).getQuantity());
                }
                HTMLtag += "<tfoot>";
                HTMLtag += "<tr>";
                HTMLtag += "<th colspan=\"8\" class=\"text-end\">รวมทั้งหมด</th>";
                HTMLtag += "<th>" + sum + "</th>";
                HTMLtag += "</tr>";
                HTMLtag += "</tfoot>";
                HTMLtag += "</table>";
                HTMLtag += "<div class=\"mt-3 text-end\">";
                HTMLtag += "<a href=\"report/ReportBag.jsp?doc_id=" + doc_id + "&cus_no=" + depart + "\" target=\"_blank\"><button class=\"btn btn-sm btn-secondary\" >พิมพ์สติ๊กเกอร์</button></a>";
                HTMLtag += "&nbsp";
                HTMLtag += "<a href=\"report/ReportBagAll.jsp?doc_id=" + doc_id + "&cus_no=" + depart + "\" target=\"_blank\"><button class=\"btn btn-sm btn-secondary\" >พิมพ์สติ๊กเกอร์ทั้งหมด</button></a>";
                HTMLtag += "";
                HTMLtag += "</div>";
                out.print(HTMLtag);
            }

        } finally {
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
            Logger.getLogger(GetDataStock.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GetDataStock.class.getName()).log(Level.SEVERE, null, ex);
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
