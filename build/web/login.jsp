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
        <form action="Chklogin" method=post id="bg-mainpage">
            <div class="container" id="form-login">
                <%if (request.getAttribute("status") != null && !request.getAttribute("status").equals("")) {%>
                <script>
                    Swal.fire({
                        title:"Logout",
                        text:"ออกจากระบบเรียบร้อย",
                        icon:"success"
                    })
                </script>
                <%}%>
                <!-- เงื่อนไขไว้เช็คว่าไอดีที่ล็อคอินเข้ามาถูกต้องหรือไม่ -->
                <%if (request.getAttribute("message-login") != null && !request.getAttribute("message-login").equals("")) {%>
                <p style="color:red"><%=request.getAttribute("message-login")%></p>
                <%}%>
                <!-- จบเงื่อนไขไว้เช็คว่าไอดีที่ล็อคอินเข้ามาถูกต้องหรือไม่ -->
                
                <div class="col-3">
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">
                            <img src="css/bootstrap-icons-1.5.0/person.svg" alt="Bootstrap" width="20" height="20">
                        </span>
                        <input 
                            type="text" 
                            placeholder="username"
                            class="form-control"
                            name="username"
                            aria-label="Username" 
                            aria-describedby="basic-addon1"
                            required>
                    </div>
                </div>
                <div class="col-3">
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">
                            <img src="css/bootstrap-icons-1.5.0/key.svg" alt="Bootstrap" width="20" height="20">
                        </span>
                        <input 
                            type="Password" 
                            placeholder="password"
                            name="password"
                            class="form-control"
                            aria-label="Password" 
                            aria-describedby="basic-addon1"
                            required>
                    </div>
                </div>
                <div class="d-grid gap-2 col-3 ">
                    <button type="submit" name="login" value="login" class="btn btn-outline-success">Login</button>
                </div>
            </div>
        </form>
    </body>
    <footer>
        <%@ include file="share/footer.jsp" %>
    </footer>
</html>
