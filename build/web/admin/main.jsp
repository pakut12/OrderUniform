<%-- 
    Document   : login
    Created on : 28 พ.ค. 2564, 15:22:47
    Author     : 111525
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <header>
        <%@ include file="share/header.jsp" %>
    </header>
    <body>
        <%@ include file="share/navbar.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-6 mx-auto">
                    <br>
                    <br>
                    <br>
                    <br>
                    <div class="card mb-3 shadow-lg ">
                        <div class="card-header">
                            Admin
                        </div>
                        <div class="card-body">
                            <div class="row ">
                                <div class="col-sm-12 col-md-6 mx-auto">
                                    <form id="myform">
                                        <label class="form-label" for="user">User</label>
                                        <input class="form-control form-control-sm mb-3" id="user" type="text" >
                                        <label class="form-label" for="pass">Password</label>
                                        <input class="form-control form-control-sm mb-3" id="pass" type="password" >
                                        <div class="text-center">
                                            <button class="btn btn-sm btn-outline-success" id="btn_login" type="button">Login</button>&nbsp;
                                            <button class="btn btn-sm btn-outline-danger" id="btn_clear" type="button">Clear</button>&nbsp;
                                        </div>
                                    </form>
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function(){
               
                $("#btn_clear").click(function(){
                    alert("asd");
                });
            })
        </script>
        
        
        
    </body>
    <footer>
        <%@ include file="share/footer.jsp" %>
    </footer>
</html>
