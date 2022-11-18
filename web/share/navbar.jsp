<%-- 
    Document   : navbar
    Created on : 1 มิ.ย. 2564, 14:17:04
    Author     : 111525
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <nav class="navbar navbar-expand-md navbar-light bg-light">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" 
                 id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link "
                           href="main.jsp"
                           id="navbarDropdown">
                            <span class="font-menu-style">
                                <img src="css/bootstrap-icons-1.5.0/house-door.svg"
                                     alt="Bootstrap"
                                     width="20"
                                     height="20">
                                หน้าแรก
                            </span>
                        </a>
                    </li>
                    
                    &nbsp;
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle"
                           href="#"
                           id="navbarDropdown"
                           role="button"
                           data-bs-toggle="dropdown"
                           aria-expanded="false">
                            <span class="font-menu-style">
                                <img src="css/bootstrap-icons-1.5.0/download.svg"
                                     alt="Bootstrap"
                                     width="20"
                                     height="20">
                                ดาวน์โหลดฟอร์ม
                            </span>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li>
                                <a class="dropdown-item" href="attachfile/download/form/Department.xls">
                                    <span>
                                        <img src="css/bootstrap-icons-1.5.0/info-square.svg" 
                                             alt="Bootstrap" 
                                             width="20" 
                                             height="20">&nbsp;
                                        แบบแผนก
                                    </span>
                                </a>
                            </li>
                            <li><hr class="dropdown-divider"></li>
                            <li>
                                <a class="dropdown-item" href="attachfile/download/form/OrderUniform_Customer.xls">
                                    <span>
                                        <img src="css/bootstrap-icons-1.5.0/info-square-fill.svg" 
                                             alt="Bootstrap" 
                                             width="20" 
                                             height="20">&nbsp;
                                        แบบรายชื่อพนักงาน
                                    </span>
                                </a>
                            </li>
                        </ul>
                    </li>
                    &nbsp;
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" 
                           href="#" 
                           id="navbarDropdown" 
                           role="button" 
                           data-bs-toggle="dropdown" 
                           aria-expanded="false">
                            <span class="font-menu-style">
                                <img src="css/bootstrap-icons-1.5.0/list-ul.svg" 
                                     alt="Bootstrap" 
                                     width="20" 
                                     height="20">
                                จัดการข้อมูลมาสเตอร์
                            </span>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li>
                                <a class="dropdown-item" href="managecompany.jsp">
                                    <span>
                                        <img src="css/bootstrap-icons-1.5.0/building.svg" 
                                             alt="Bootstrap" 
                                             width="20" 
                                             height="20">&nbsp;
                                        รายชื่อบริษัท
                                    </span>
                                </a>
                            </li>
                            <li><hr class="dropdown-divider"></li>
                            <li>
                                <a class="dropdown-item" href="managematerial.jsp">
                                    <span>
                                        <img src="css/bootstrap-icons-1.5.0/clipboard.svg" 
                                             alt="Bootstrap" 
                                             width="20" 
                                             height="20">&nbsp;
                                        รายการ Items
                                    </span>
                                </a>
                            </li>
                            <li><hr class="dropdown-divider"></li>
                            <!--<li>
                                <a class="dropdown-item" href="uploadmastercustomer.jsp">
                                    <span>
                                        <img src="css/bootstrap-icons-1.5.0/person-plus.svg" 
                                         alt="Bootstrap" 
                                         width="20" 
                                         height="20">&nbsp;
                                        รูปแบบพนักงาน
                                    </span>
                                </a>
                            </li>
                            <li><hr class="dropdown-divider"></li>
                            <li>
                                <a class="dropdown-item" href="uploadmasterdepartment.jsp">
                                    <span>
                                        <img src="css/bootstrap-icons-1.5.0/building.svg" 
                                         alt="Bootstrap" 
                                         width="20" 
                                         height="20">&nbsp;
                                        รูปแบบแผนก
                                    </span>
                                </a>
                            </li>
                            <li><hr class="dropdown-divider"></li>-->
                            <li>
                                <a class="dropdown-item" href="managecustomer.jsp">
                                    <span>
                                        <img src="css/bootstrap-icons-1.5.0/person.svg" 
                                             alt="Bootstrap" 
                                             width="20" 
                                             height="20">&nbsp;
                                        ลูกค้า
                                    </span>
                                </a>
                            </li>
                        </ul>
                    </li>
                    &nbsp;
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" 
                           href="#" 
                           id="navbarDropdown" 
                           role="button" 
                           data-bs-toggle="dropdown" 
                           aria-expanded="false">
                            <span class="font-menu-style">
                                <img src="css/bootstrap-icons-1.5.0/layout-text-sidebar.svg" 
                                     alt="Bootstrap" 
                                     width="20" 
                                     height="20">
                                จัดการข้อมูล Transaction 
                            </span>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li>
                                <a class="dropdown-item" href="listtransactioncustomer.jsp">
                                    <span>
                                        <img src="css/bootstrap-icons-1.5.0/file-earmark-person.svg" 
                                             alt="Bootstrap" 
                                             width="20" 
                                             height="20">&nbsp;
                                        รูปแบบพนักงาน
                                    </span>
                                </a>
                            </li>
                            <li><hr class="dropdown-divider"></li>
                            <li>
                                <a class="dropdown-item" href="listtransactiondepartment.jsp">
                                    <span>
                                        <img src="css/bootstrap-icons-1.5.0/briefcase-fill.svg" 
                                             alt="Bootstrap" 
                                             width="20" 
                                             height="20">&nbsp;
                                        รูปแบบแผนก
                                    </span>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li>
                                <a class="dropdown-item" href="attachfile/download/form/Department.xls">
                                    <span>
                                        <img src="css/bootstrap-icons-1.5.0/info-square.svg" 
                                             alt="Bootstrap" 
                                             width="20" 
                                             height="20">&nbsp;
                                        แบบแผนก
                                    </span>
                                </a>
                            </li>
                            <li><hr class="dropdown-divider"></li>
                            <li>
                                <a class="dropdown-item" href="attachfile/download/form/OrderUniform_Customer.xls">
                                    <span>
                                        <img src="css/bootstrap-icons-1.5.0/info-square-fill.svg" 
                                             alt="Bootstrap" 
                                             width="20" 
                                             height="20">&nbsp;
                                        แบบรายชื่อพนักงาน
                                    </span>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle"
                           href="#"
                           id="navbarDropdown"
                           role="button"
                           data-bs-toggle="dropdown"
                           aria-expanded="false">
                            <span class="font-menu-style">
                                <img src="css/bootstrap-icons-1.5.0/basket.svg"
                                     alt="Bootstrap"
                                     width="20"
                                     height="20">
                                ระบบสต็อก
                            </span>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li class="dropdown-header">เเบบถุง</li>
                            <li>
                                <a class="dropdown-item" href="stocklistbag.jsp">
                                    <span>
                                        <img src="css/bootstrap-icons-1.5.0/printer.svg" 
                                             alt="Bootstrap" 
                                             width="20" 
                                             height="20">&nbsp;
                                        พิมพ์สติ๊กเกอร์ (รายชื่อ)
                                    </span>
                                </a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="stockdepartmentbag.jsp">
                                    <span>
                                        <img src="css/bootstrap-icons-1.5.0/printer.svg" 
                                             alt="Bootstrap" 
                                             width="20" 
                                             height="20">&nbsp;
                                        พิมพ์สติ๊กเกอร์ (เเผนก)
                                    </span>
                                </a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="stockalllistbag.jsp">
                                    <span>
                                        <img src="css/bootstrap-icons-1.5.0/printer.svg" 
                                             alt="Bootstrap" 
                                             width="20" 
                                             height="20">&nbsp;
                                        พิมพ์สติ๊กเกอร์ (ทั้งหมด)
                                    </span>
                                </a>
                            </li>
                            <li><hr class="dropdown-divider"></li>
                            <li>
                                <li class="dropdown-header">เเบบกล่อง</li>
                                <a class="dropdown-item" href="stockboxall.jsp">
                                    <span>
                                        <img src="css/bootstrap-icons-1.5.0/printer-fill.svg" 
                                             alt="Bootstrap" 
                                             width="20" 
                                             height="20">&nbsp;
                                        พิมพ์สติ๊กเกอร์ (ทั้งหมด)
                                    </span>
                                </a>
                                <a class="dropdown-item" href="stockdepartmentbox.jsp">
                                    <span>
                                        <img src="css/bootstrap-icons-1.5.0/printer-fill.svg" 
                                             alt="Bootstrap" 
                                             width="20" 
                                             height="20">&nbsp;
                                        พิมพ์สติ๊กเกอร์ (เเผนก)
                                    </span>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <a class="btn btn-secondary" href="manageorder.jsp">
                    ดาวน์โหลดออเดอร์ 
                </a>&nbsp;
                <a class="btn btn-success" href="uploadtransaction.jsp">
                    อัพโหลดออเดอร์
                </a>&nbsp;
            </div>
        </div>
    </nav>
</html>
