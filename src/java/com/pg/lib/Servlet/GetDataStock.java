/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pg.lib.Servlet;

import com.pg.lib.model.OUSummaryOrderByCustomer;
import com.pg.lib.model.OUTransactionCustomerDetail;
import com.pg.lib.model.OUTransactionDepartmentDetail;
import com.pg.lib.service.CustomerService;
import com.pg.lib.service.DepartmentService;
import com.pg.lib.service.TransactionCustomerService;
import com.pg.lib.service.TransactionDepartmentService;
import java.io.*;
import java.net.*;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.ls.LSException;

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
        request.setCharacterEncoding("utf8");
        PrintWriter out = response.getWriter();
        try {
            String type = request.getParameter("type");

            if (type.equalsIgnoreCase("getdataformbarcodebag")) {
                String doc_id = request.getParameter("doc_id");
                String cus_no = request.getParameter("cus_no");

                TransactionCustomerService s_trancustomer = new TransactionCustomerService();
                List<OUTransactionCustomerDetail> listdetail = s_trancustomer.getDetailFromBarcode(doc_id, Integer.parseInt(cus_no));

                String HTMLtag = "";

                String status = "";

                if (!listdetail.get(0).getStatus().equalsIgnoreCase("success")) {
                    status = "ยังไม่ได้จัดสินค้า";
                } else {
                    status = "จัดสินค้าเรียบร้อย";
                }

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
                HTMLtag += "<div class=\"row mb-3\">";
                HTMLtag += "<div class=\"col-4 text-end\">สถานะ : </div>";
                HTMLtag += "<div class=\"col-4\">";
                HTMLtag += "<input class=\"form-control form-control-sm text-center\" type=\"text\" id=\"cus_status\" value=\"" + status + "\" readonly></input>";
                HTMLtag += "</div>";
                HTMLtag += "</div>";

                HTMLtag += "<div class=\"row mb-3\">";
                HTMLtag += "<div class=\"col-12 text-center\">";
                HTMLtag += "<input class=\"btn btn-success btn-sm\" type=\"button\" id=\"btn_confirm\" value='ยืนยัน'></input>";
                HTMLtag += "&nbsp&nbsp";
                HTMLtag += "<button id='print_sticker' class=\"btn btn-sm btn-secondary disabled\" >พิมพ์สติ๊กเกอร์</button>";

                HTMLtag += "</div>";

                HTMLtag += "</div>";
                HTMLtag += "<table id=\"listdata\" class=\"table table-bordered w-100 text-center\" >";
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
                HTMLtag += "<th class='text-center'>" + sum + "</th>";
                HTMLtag += "</tr>";
                HTMLtag += "</tfoot>";

                out.print(HTMLtag);
            } else if (type.equalsIgnoreCase("getdatadepart")) {
                String doc_id = request.getParameter("doc_id");
                TransactionCustomerService cms = new TransactionCustomerService();
                List<OUTransactionCustomerDetail> list = cms.GroupDepart(doc_id);

                JSONArray jsarr = new JSONArray();
                JSONObject obj = new JSONObject();
                for (OUTransactionCustomerDetail arrlist : list) {

                    jsarr.put(arrlist.getDepartmentname());
                }
                obj.put("depart", jsarr);
                out.print(obj);
            } else if (type.equalsIgnoreCase("getdataformbarcodedepart")) {
                String doc_id = request.getParameter("doc_id");
                String depart = request.getParameter("cus_department");

                TransactionCustomerService s_trancustomer = new TransactionCustomerService();
                List<OUTransactionCustomerDetail> listdetail = s_trancustomer.getDetailFromBarcodeDepartTable(doc_id, depart);


                String HTMLtag = "";
                HTMLtag += "<div class=\"mb-3 text-end\">";
                HTMLtag += "<a href=\"report/ReportBagDepart.jsp?doc_id=" + doc_id + "&cus_department=" + depart + "\" target=\"_blank\"><button class=\"btn btn-sm btn-secondary\" >พิมพ์สติ๊กเกอร์ทั้งหมด</button></a>";
                HTMLtag += "";
                HTMLtag += "</div>";
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
                HTMLtag += "<th class='text-center'>" + sum + "</th>";
                HTMLtag += "</tr>";
                HTMLtag += "</tfoot>";
                HTMLtag += "</table>";

                out.print(HTMLtag);
            } else if (type.equalsIgnoreCase("getdataformbarcodelistall")) {
                String doc_id = request.getParameter("doc_id");


                TransactionCustomerService s_trancustomer = new TransactionCustomerService();
                List<OUTransactionCustomerDetail> listdetail = s_trancustomer.getDetailTransactionByDocumentId(doc_id);


                String HTMLtag = "";
                HTMLtag += "<div class=\"mb-3 text-end\">";
                HTMLtag += "<a href=\"report/ReportBagAll.jsp?doc_id=" + doc_id + "\" target=\"_blank\"><button class=\"btn btn-sm btn-secondary\" >พิมพ์สติ๊กเกอร์ทั้งหมด</button></a>";
                HTMLtag += "";
                HTMLtag += "</div>";
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
                    HTMLtag += "<td><b>ชื่อ : </b>" + listdetail.get(i).getPrename() + " " + listdetail.get(i).getFname() + "<br>" +
                            "<b>เเผนก : </b>" + listdetail.get(i).getDepartmentname() + "</td>";

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

                out.print(HTMLtag);
            } else if (type.equalsIgnoreCase("getdataformbarcodebox")) {
                try {
                    String doc_id = request.getParameter("doc_id");
                    String num = request.getParameter("num");

                    TransactionCustomerService ts = new TransactionCustomerService();
                    List<OUTransactionCustomerDetail> list = ts.getDetailFromBarcodeDepartAll(doc_id);

                    if (list.size() > 0) {
                        out.print("true");
                    } else {
                        out.print("false");
                    }

                /* 19/12/2565
                int box = (int) Math.ceil((double) list.size() / Integer.parseInt(num));
                
                String HTMLtag = "";
                HTMLtag += "<div class=\"mb-3 text-end\">";
                HTMLtag += "<a href=\"report/ReportBoxAll.jsp?doc_id=" + doc_id + "&num=" + num + "\" target=\"_blank\"><button class=\"btn btn-sm btn-secondary\" >พิมพ์สติ๊กเกอร์ทั้งหมด</button></a>";
                HTMLtag += "";
                HTMLtag += "</div>";
                HTMLtag += "<table id=\"listdata\" class=\"table w-100 \" >";
                HTMLtag += "<thead>";
                HTMLtag += "<tr>";
                HTMLtag += "<th>ลำดับ</th>";
                HTMLtag += "<th>ลำดับ</th>";
                HTMLtag += "<th>ชื่อ</th>";
                HTMLtag += "<th>เเผนก</th>";
                HTMLtag += "</tr>";
                HTMLtag += "</thead>";
                HTMLtag += "<tbody>";
                int x = 1;
                int z = 0;
                for (int i = 0; i <= list.size() - 1; i++) {
                String Name = list.get(i).getFname();
                String DepartmentName = list.get(i).getDepartmentname();
                if (x == Integer.parseInt(num)) {
                HTMLtag += "<tr>";
                HTMLtag += "<td>" + list.get(0).getDocName() + " " + (z + 1) + "/" + (box) + "</td>";
                HTMLtag += "<td>" + (i + 1) + "</td>";
                HTMLtag += "<td>" + Name + "</td>";
                HTMLtag += "<td>" + DepartmentName + "</td>";
                HTMLtag += "</tr>";
                x = 1;
                z++;
                } else {
                HTMLtag += "<tr>";
                HTMLtag += "<td>" + list.get(0).getDocName() + " " + (z + 1) + "/" + (box) + "</td>";
                HTMLtag += "<td>" + (i + 1) + "</td>";
                HTMLtag += "<td>" + Name + "</td>";
                HTMLtag += "<td>" + DepartmentName + "</td>";
                HTMLtag += "</tr>";
                x++;
                }
                }
                HTMLtag += "</tbody>";
                HTMLtag += "</table>";
                
                out.print(HTMLtag);
                 */
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (type.equalsIgnoreCase("getdataformbarcodeboxdepart")) {
                try {
                    String doc_id = request.getParameter("doc_id");
                    String num = request.getParameter("num");
                    String depart = request.getParameter("depart");

                    TransactionCustomerService tms = new TransactionCustomerService();
                    List<OUTransactionCustomerDetail> list = tms.getDetailFromBarcodeDepart(doc_id, depart);
                    int box = (int) Math.ceil((double) list.size() / Integer.parseInt(num));

                    String HTMLtag = "";
                    HTMLtag += "<div class=\"mb-3 text-end\">";
                    HTMLtag += "<a href=\"report/ReportBoxDepart.jsp?doc_id=" + doc_id + "&num=" + num + "&depart=" + depart + "\" target=\"_blank\"><button class=\"btn btn-sm btn-secondary\" >พิมพ์สติ๊กเกอร์ทั้งหมด</button></a>";
                    HTMLtag += "";
                    HTMLtag += "</div>";
                    HTMLtag += "<table id=\"listdata\" class=\"table w-100 \" >";
                    HTMLtag += "<thead>";
                    HTMLtag += "<tr>";
                    HTMLtag += "<th>ลำดับ</th>";
                    HTMLtag += "<th>ลำดับ</th>";
                    HTMLtag += "<th>ชื่อ</th>";
                    HTMLtag += "<th>เเผนก</th>";
                    HTMLtag += "</tr>";
                    HTMLtag += "</thead>";
                    HTMLtag += "<tbody>";
                    int x = 1;
                    int z = 0;
                    for (int i = 0; i <= list.size() - 1; i++) {
                        String Name = list.get(i).getFname();
                        String DepartmentName = list.get(i).getDepartmentname();

                        if (x == Integer.parseInt(num)) {
                            if (DepartmentName.equals(depart)) {
                                HTMLtag += "<tr>";
                                HTMLtag += "<td>" + list.get(0).getDocName() + " " + (z + 1) + "/" + (box) + "</td>";
                                HTMLtag += "<td>" + (i + 1) + "</td>";
                                HTMLtag += "<td>" + Name + "</td>";
                                HTMLtag += "<td>" + DepartmentName + "</td>";
                                HTMLtag += "</tr>";
                                x = 1;
                                z++;
                            }
                        } else {
                            if (DepartmentName.equals(depart)) {
                                HTMLtag += "<tr>";
                                HTMLtag += "<td>" + list.get(0).getDocName() + " " + (z + 1) + "/" + (box) + "</td>";
                                HTMLtag += "<td>" + (i + 1) + "</td>";
                                HTMLtag += "<td>" + Name + "</td>";
                                HTMLtag += "<td>" + DepartmentName + "</td>";
                                HTMLtag += "</tr>";
                                x++;
                            }
                        }


                    }
                    HTMLtag += "</tbody>";
                    HTMLtag += "</table>";

                    out.print(HTMLtag);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            if (type.equalsIgnoreCase("Depratgetdataformbarcodebag")) {
                String doc_id = request.getParameter("doc_id");
                String cus_no = request.getParameter("cus_no");

                TransactionDepartmentService s_trancustomer = new TransactionDepartmentService();
                List<OUTransactionDepartmentDetail> listdetail = s_trancustomer.getDetailTransactionByDocumentIdAndDepariID(doc_id, cus_no);

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
                HTMLtag += "<input class=\"form-control form-control-sm text-center\" type=\"text\" id=\"cus_no\" value=\"" + listdetail.get(0).getDepartID() + "\" readonly></input>";
                HTMLtag += "</div>";
                HTMLtag += "</div>";
                HTMLtag += "<div class=\"row mb-3\">";
                HTMLtag += "<div class=\"col-4 text-end\">หน่วยงานที่ 1 : </div>";
                HTMLtag += "<div class=\"col-4\">";
                HTMLtag += "<input class=\"form-control form-control-sm text-center\" type=\"text\" id=\"cus_no\" value=\"" + listdetail.get(0).getAgency() + "\" readonly></input>";
                HTMLtag += "</div>";
                HTMLtag += "</div>";
                HTMLtag += "<div class=\"row mb-3\">";
                HTMLtag += "<div class=\"col-4 text-end\">หน่วยงานที่ 2 : </div>";
                HTMLtag += "<div class=\"col-4\">";
                HTMLtag += "<input class=\"form-control form-control-sm text-center\" type=\"text\" id=\"cus_no\" value=\"" + listdetail.get(0).getDivision() + "\" readonly></input>";
                HTMLtag += "</div>";
                HTMLtag += "</div>";
                HTMLtag += "<div class=\"row mb-3\">";
                HTMLtag += "<div class=\"col-4 text-end\">หน่วยงานที่ 3 : </div>";
                HTMLtag += "<div class=\"col-4\">";
                HTMLtag += "<input class=\"form-control form-control-sm text-center\" type=\"text\" id=\"cus_no\" value=\"" + listdetail.get(0).getDepartmentname() + "\" readonly></input>";
                HTMLtag += "</div>";
                HTMLtag += "</div>";



                HTMLtag += "</table>";
                HTMLtag += "<div class=\"mb-3 text-end\">";
                HTMLtag += "<a href=\"report/Department/ReportBag.jsp?doc_id=" + doc_id + "&cus_no=" + cus_no + "\" target=\"_blank\"><button class=\"btn btn-sm btn-secondary\" >พิมพ์สติ๊กเกอร์</button></a>";

                HTMLtag += "";
                HTMLtag += "</div>";

                HTMLtag += "<table id=\"listdata\" class=\"table w-100 text-center\" >";
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

                    HTMLtag += "<td>" + listdetail.get(i).getMaterialdesc() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getMaterialname() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getMaterialdesc() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getMaterialsize() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getMaterialfullname() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getBarcode() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getMaterialquantity() + "</td>";
                    HTMLtag += "</tr>";

                    sum += Integer.parseInt(listdetail.get(i).getMaterialquantity());
                }
                HTMLtag += "<tfoot>";
                HTMLtag += "<tr>";
                HTMLtag += "<th colspan=\"7\" class=\"text-end\">รวมทั้งหมด</th>";
                HTMLtag += "<th>" + sum + "</th>";
                HTMLtag += "</tr>";
                HTMLtag += "</tfoot>";

                out.print(HTMLtag);
            } else if (type.equalsIgnoreCase("Departgetdata")) {
                String doc_id = request.getParameter("doc_id");

                DepartmentService ds = new DepartmentService();
                List<OUTransactionDepartmentDetail> list = ds.getdepart(doc_id);
                JSONArray jsarr = new JSONArray();
                JSONObject obj = new JSONObject();
                for (OUTransactionDepartmentDetail arrlist : list) {
                    jsarr.put(arrlist.getAgency());
                }
                obj.put("depart", jsarr);
                out.print(obj);
            } else if (type.equalsIgnoreCase("Departgetdataformbarcodedepart")) {
                String doc_id = request.getParameter("doc_id");
                String depart_agency = request.getParameter("depart_agency");
                System.out.println(doc_id);
                System.out.println(depart_agency);

                TransactionDepartmentService s_trancustomer = new TransactionDepartmentService();
                List<OUTransactionDepartmentDetail> listdetail = s_trancustomer.getDetailTransactionByDocumentIdAndAgency(doc_id, depart_agency);

                String HTMLtag = "";
                HTMLtag += "<div class=\"mb-3 text-end\">";
                HTMLtag += "<input class='btn btn-sm btn-secondary' type='submit' value='พิมพ์สติ๊กเกอร์'></a>";
                HTMLtag += "</div>";
                HTMLtag += "<table id=\"listdata\" class=\"table w-100 text-center\" >";
                HTMLtag += "<thead>";
                HTMLtag += "<tr>";
                HTMLtag += "<th>ลำดับ</th>";
                HTMLtag += "<th>หน่วยงาน</th>";
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
                    HTMLtag += "<td>หน่วยงานที่ 1 : " + listdetail.get(i).getAgency() + "<br>หน่วยงานที่ 2 : " + listdetail.get(i).getDivision() + "<br>หน่วยงานที่ 3 : " + listdetail.get(i).getDepartmentname() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getMaterialdesc() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getMaterialname() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getMaterialcolor() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getMaterialsize() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getMaterialfullname() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getBarcode() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getMaterialquantity() + "</td>";
                    HTMLtag += "</tr>";

                    sum += Integer.parseInt(listdetail.get(i).getMaterialquantity());
                }
                HTMLtag += "<tfoot>";
                HTMLtag += "<tr>";
                HTMLtag += "<th colspan=\"8\" class=\"text-end\">รวมทั้งหมด</th>";
                HTMLtag += "<th>" + sum + "</th>";
                HTMLtag += "</tr>";
                HTMLtag += "</tfoot>";

                out.print(HTMLtag);
            } else if (type.equalsIgnoreCase("Departgetdataformbarcodelistall")) {
                String doc_id = request.getParameter("doc_id");

                TransactionDepartmentService s_trancustomer = new TransactionDepartmentService();
                List<OUTransactionDepartmentDetail> listdetail = s_trancustomer.getDetailTransactionByDocumentId(doc_id);

                String HTMLtag = "";
                HTMLtag += "<div class=\"mb-3 text-end\">";
                HTMLtag += "<a href=\"report/Department/ReportBagAll.jsp?doc_id=" + doc_id + "\" target=\"_blank\"><button class=\"btn btn-sm btn-secondary\" >พิมพ์สติ๊กเกอร์ทั้งหมด</button></a>";
                HTMLtag += "";
                HTMLtag += "</div>";
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
                    HTMLtag += "<td>หน่วยงานที่ 1 : " + listdetail.get(i).getAgency() + "<br>หน่วยงานที่ 2 : " + listdetail.get(i).getDivision() + "<br>หน่วยงานที่ 3 : " + listdetail.get(i).getDepartmentname() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getMaterialdesc() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getMaterialname() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getMaterialcolor() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getMaterialsize() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getMaterialfullname() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getBarcode() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getMaterialquantity() + "</td>";
                    HTMLtag += "</tr>";

                    sum += Integer.parseInt(listdetail.get(i).getMaterialquantity());
                }
                HTMLtag += "<tfoot>";
                HTMLtag += "<tr>";
                HTMLtag += "<th colspan=\"8\" class=\"text-end\">รวมทั้งหมด</th>";
                HTMLtag += "<th>" + sum + "</th>";
                HTMLtag += "</tr>";
                HTMLtag += "</tfoot>";
                HTMLtag += "</table>";

                out.print(HTMLtag);
            } else if (type.equalsIgnoreCase("Departgetdataformbarcodebox")) {
                try {
                    String doc_id = request.getParameter("doc_id");
                    String num = request.getParameter("num");

                    TransactionDepartmentService s_trancustomer = new TransactionDepartmentService();
                    List<OUTransactionDepartmentDetail> list = s_trancustomer.getDepariID(doc_id);

                    int box = (int) Math.ceil((double) list.size() / Integer.parseInt(num));

                    String HTMLtag = "";
                    HTMLtag += "<div class=\"mb-3 text-end\">";
                    HTMLtag += "<a href=\"report/Department/ReportBoxAll.jsp?doc_id=" + doc_id + "&num=" + num + "\" target=\"_blank\"><button class=\"btn btn-sm btn-secondary\" >พิมพ์สติ๊กเกอร์ทั้งหมด</button></a>";
                    HTMLtag += "";
                    HTMLtag += "</div>";
                    HTMLtag += "<table id=\"listdata\" class=\"table w-100 \" >";
                    HTMLtag += "<thead>";
                    HTMLtag += "<tr>";
                    HTMLtag += "<th>ลำดับ</th>";
                    HTMLtag += "<th>ลำดับ</th>";
                    HTMLtag += "<th>ชื่อ</th>";

                    HTMLtag += "</tr>";
                    HTMLtag += "</thead>";
                    HTMLtag += "<tbody>";
                    int x = 1;
                    int z = 0;
                    for (int i = 0; i <= list.size() - 1; i++) {
                        String Name = "หน่วยงานที่ 1 : " + list.get(i).getAgency() + "<br>หน่วยงานที่ 2 : " + list.get(i).getDivision() + "<br>หน่วยงานที่ 3 : " + list.get(i).getDepartmentname();

                        if (x == Integer.parseInt(num)) {
                            HTMLtag += "<tr>";
                            HTMLtag += "<td>" + list.get(0).getDocName() + " " + (z + 1) + "/" + (box) + "</td>";
                            HTMLtag += "<td>" + (i + 1) + "</td>";
                            HTMLtag += "<td>" + Name + "</td>";

                            HTMLtag += "</tr>";
                            x = 1;
                            z++;
                        } else {
                            HTMLtag += "<tr>";
                            HTMLtag += "<td>" + list.get(0).getDocName() + " " + (z + 1) + "/" + (box) + "</td>";
                            HTMLtag += "<td>" + (i + 1) + "</td>";
                            HTMLtag += "<td>" + Name + "</td>";

                            HTMLtag += "</tr>";
                            x++;
                        }
                    }
                    HTMLtag += "</tbody>";
                    HTMLtag += "</table>";

                    out.print(HTMLtag);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (type.equalsIgnoreCase("getdetail")) {
                String doc_id = request.getParameter("doc_id");
                String depart = request.getParameter("cus_department");

                TransactionCustomerService s_trancustomer = new TransactionCustomerService();
                List<OUTransactionCustomerDetail> listdetail = s_trancustomer.getDetailFromBarcodeDepartAll(doc_id);


                String HTMLtag = "";
                HTMLtag += "<table id=\"listdata\" class=\"table table-bordered w-100 \" >";
                HTMLtag += "<thead>";
                HTMLtag += "<tr>";
                HTMLtag += "<th>ลำดับ</th>";
                HTMLtag += "<th>รหัสบาร์โค้ด</th>";
                HTMLtag += "<th>ชื่อ</th>";
                HTMLtag += "<th>สถานะ</th>";
                HTMLtag += "</tr>";
                HTMLtag += "</thead>";
                HTMLtag += "<tbody>";

                int sum = 0;
                for (int i = 0; i <= listdetail.size() - 1; i++) {

                    String status = "";
                    if (listdetail.get(i).getStatus().equals("success")) {
                        status = "<div class='text-success'>จัดสินค้าเรียบร้อย</div>";
                    } else {
                        status = "<div class='text-danger'>ยังไม่ได้จัดสินค้า</div>";
                    }
                    HTMLtag += "<tr>";
                    HTMLtag += "<td>" + (i + 1) + "</td>";
                    HTMLtag += "<td>" + doc_id + "/" + listdetail.get(i).getCustomerID() + "</td>";
                    HTMLtag += "<td>" + listdetail.get(i).getPrename() + " " + listdetail.get(i).getFname() + "</td>";
                    HTMLtag += "<td>" + status + "</td>";
                    HTMLtag += "</tr>";
                }

                HTMLtag += "</table>";
                out.print(HTMLtag);
            } else if (type.equalsIgnoreCase("getnumbydoc")) {
                String doc_id = request.getParameter("doc_id");
                String status_header = "";
                String result = "";
                TransactionCustomerService s_trancustomer = new TransactionCustomerService();
                List<TreeMap> headerDetail = s_trancustomer.getHeaderTransactionwithid(doc_id);
                List<OUTransactionCustomerDetail> listdetail = s_trancustomer.getDetailFromBarcodeDepartAll(doc_id);
                if (headerDetail.get(0).get("h_status").toString().equals("new")) {
                    status_header = "ยังไม่ได้จัดสินค้าลงกล่อง";
                } else {
                    status_header = "จัดสินค้าลงกล่องเรียบร้อยเเล้ว";
                }
                int packsuccess = 0;
                int packerror = 0;
                int packtotal = 0;
                for (int i = 0; i <= listdetail.size() - 1; i++) {
                    if (listdetail.get(i).getStatus().equals("success")) {
                        packsuccess++;
                    } else {
                        packerror++;
                    }
                    packtotal++;
                }
                if (packtotal != packsuccess) {
                    result = "จัดสินค้ายังไม่ครบ";
                } else {
                    result = "จัดสินค้าครบเเล้ว";
                }

                JSONObject obj = new JSONObject();
                obj.put("success", packsuccess);
                obj.put("error", packerror);
                obj.put("total", packtotal);
                obj.put("status_header", status_header);
                obj.put("result", result);
                out.print(obj);
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
