/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pg.lib.Servlet;

import com.pg.lib.model.OUPermission;
import com.pg.lib.service.AuthenticationService;
import com.pg.lib.service.AuthorizedService;
import com.pg.lib.service.EmployeeService;
import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author 111525
 */
public class Chklogin extends HttpServlet {
   
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException,NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String url = "";
        String username = request.getParameter("username")!=null ? request.getParameter("username").trim() : "";
        String password = request.getParameter("password")!=null ? request.getParameter("password").trim() : "";
        String actionSubmit = request.getParameter("login").equals("login") ? request.getParameter("login") : "";
       
        if(actionSubmit.equals("login")){
            if(!username.equals("") && !password.equals("")){
                if(AuthenticationService.checkStatusWorking(username)){
                   MessageDigest md = MessageDigest.getInstance("MD5");
                   md.reset();
                   md.update(password.getBytes());
                   String digestpass = new BigInteger(1,md.digest()).toString(16).toUpperCase();
                       if(AuthenticationService.Checklogin(username,digestpass)){
                            session.setAttribute("userid",username);
                            String detailUser = EmployeeService.queryName(username);
                            session.setAttribute("name",detailUser);
                            OUPermission pm = AuthorizedService.definePermission(EmployeeService.getRole(username));
                            session.setAttribute("role",pm);
                            url = "/main.jsp";
                       } else {
                            request.setAttribute("message-login", "Username หรือ Password ไม่ถูกต้อง กรุณาลองใหม่อีกครั้ง..");
                            url = "/login.jsp";
                       }
                } else {
                    request.setAttribute("message-login", "Username หรือ Password ไม่ถูกต้อง กรุณาลองใหม่อีกครั้ง..");
                    url = "/login.jsp";
                }
            }
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
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
        } catch(NoSuchAlgorithmException ex) {
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
        } catch(NoSuchAlgorithmException ex) {
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
