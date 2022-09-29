<%-- 
    Document   : managematerial
    Created on : 9 มิ.ย. 2564, 15:29:55
    Author     : 111525
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.pg.lib.model.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <%
            OUPermission pm = (OUPermission) request.getSession().getAttribute("role");
            if(pm == null || !pm.getRole().equals("1")){
                RequestDispatcher rdp = getServletContext().getRequestDispatcher("/login.jsp");
                rdp.forward(request, response);
            }
    %>
    <head>
        <%@ include file = "share/header.jsp" %>
    </head>
    <body>
        <!-- nav -->
        <%@ include file = "share/navbar.jsp" %>
        <!-- end nav -->
        <div id="default-layout">
            <div class="container">
                <div class="row">
                    <div class="col-1">
                        <img src="css/bootstrap-icons-1.5.0/clipboard.svg" 
                                 alt="Bootstrap" 
                                 width="100%" 
                                 height="100%">
                    </div>
                    <div class="col">
                        <h3>จัดการข้อมูล<h5>(รายการ Items)</h5></h3>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-12" style="height:30vh;padding-bottom:3px">
                         <a  href="materialdetail.jsp" 
                             class="btn btn-outline-success" 
                             style=" width:100%;
                                    height:100%;
                                    font-size:50px;
                                    text-align:end;
                                    border-top-width:5px;
                                    border-left-width:5px;
                                    border-right-width:5px;
                                    border-bottom-width:5px;"
                             role="button">
                             ดูรายละเอียด
                         </a>
                    </div>
                </div>
                <div class="row">
                    <div class="col-9" style="height:30vh;padding-right:3px">
                        <a  href="#" 
                            class="btn btn-outline-secondary" 
                            style=" width:100%;
                                    height:100%;
                                    font-size:50px;
                                    text-align:end;
                                    border-top-width:5px;
                                    border-left-width:5px;
                                    border-right-width:5px;
                                    border-bottom-width:5px;"
                            role="button">
                            
                        </a>
                    </div>
                    <div class="col-3" style="height:30vh;padding-left:3px">
                        <a  href="#" 
                            class="btn btn-outline-dark" 
                            style=" width:100%;
                                    height:100%;
                                    font-size:50px;
                                    text-align:end;
                                    border-top-width:5px;
                                    border-left-width:5px;
                                    border-right-width:5px;
                                    border-bottom-width:5px;"
                            role="button">
                            
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <footer>
        <%@ include file = "share/footer.jsp" %>
    </footer>
</html>
