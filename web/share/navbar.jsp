
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">
    
    /* ============ desktop view ============ */
    @media all and (min-width: 992px) {
        
        .dropdown-menu li{
            position: relative;
        }
        .dropdown-menu .submenu{ 
            display: none;
            position: absolute;
            left:100%; top:-7px;
        }
        .dropdown-menu .submenu-left{ 
            right:100%; left:auto;
        }
        
        .dropdown-menu > li:hover{ background-color: #f1f1f1 }
        .dropdown-menu > li:hover > .submenu{
            display: block;
        }
    }	
    /* ============ desktop view .end// ============ */
    
    /* ============ small devices ============ */
    @media (max-width: 991px) {
        
        .dropdown-menu .dropdown-menu{
            margin-left:0.7rem; margin-right:0.7rem; margin-bottom: .5rem;
        }
        
    }	
    /* ============ small devices .end// ============ */
    
</style>
<script type="text/javascript">
    //	window.addEventListener("resize", function() {
    //		"use strict"; window.location.reload(); 
    //	});


    document.addEventListener("DOMContentLoaded", function(){
        

        /////// Prevent closing from click inside dropdown
        document.querySelectorAll('.dropdown-menu').forEach(function(element){
            element.addEventListener('click', function (e) {
                e.stopPropagation();
            });
        })



        // make it as accordion for smaller screens
        if (window.innerWidth < 992) {

            // close all inner dropdowns when parent is closed
            document.querySelectorAll('.navbar .dropdown').forEach(function(everydropdown){
                everydropdown.addEventListener('hidden.bs.dropdown', function () {
                    // after dropdown is hidden, then find all submenus
                    this.querySelectorAll('.submenu').forEach(function(everysubmenu){
                        // hide every submenu as well
                        everysubmenu.style.display = 'none';
                    });
                })
            });
                        
            document.querySelectorAll('.dropdown-menu a').forEach(function(element){
                element.addEventListener('click', function (e) {
                    var nextEl = this.nextElementSibling;
                    if(nextEl && nextEl.classList.contains('submenu')) {	
                        // prevent opening link if link needs to open dropdown
                        e.preventDefault();
                        console.log(nextEl);
                        if(nextEl.style.display == 'block'){
                            nextEl.style.display = 'none';
                        } else {
                            nextEl.style.display = 'block';
                        }

                    }
                });
            })
        }
        // end if innerWidth

    }); 
    // DOMContentLoaded  end
</script>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
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
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle"
                       href="#"
                       id="navbarDropdown"
                       role="button"
                       data-bs-toggle="dropdown"
                       aria-expanded="false">
                        <span class="font-menu-style">
                            <img src="css/bootstrap-icons-1.5.0/building.svg"
                                 alt="Bootstrap"
                                 width="20"
                                 height="20">
                            ระบบจัดการออเดอร์ (รายชื่อพนักงาน)
                        </span>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <div class="dropdown-header">ควบคุม</div>
                        <li>
                            <a class="dropdown-item" href="#"> 
                                <img src="css/bootstrap-icons-1.5.0/download.svg"
                                     alt="Bootstrap"
                                     width="20"
                                     height="20">&nbsp;
                            ดาวน์โหลดฟอร์ม </a>
                            <ul class="submenu dropdown-menu">
                                <!--<li>
                                        <a class="dropdown-item" href="attachfile/download/form/Department.xls">
                                            <span>
                                                <img src="css/bootstrap-icons-1.5.0/info-square.svg" 
                                                     alt="Bootstrap" 
                                                     width="20" 
                                                     height="20">&nbsp;
                                                แบบแผนก
                                            </span>
                                        </a>
                                    </li>-->
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
                        <li>
                            <a class="dropdown-item" href="#"> 
                                <img src="css/bootstrap-icons-1.5.0/people.svg"
                                     alt="Bootstrap"
                                     width="20"
                                     height="20">&nbsp;
                            จัดการข้อมูลมาสเตอร์</a>
                            <ul class="submenu dropdown-menu">
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
                                <li>
                                    <a class="dropdown-item" href="manageorder.jsp">
                                        <span>
                                            <img src="css/bootstrap-icons-1.5.0/file-earmark-arrow-down.svg" 
                                                 alt="Bootstrap" 
                                                 width="20" 
                                                 height="20">&nbsp;
                                            ดาวน์โหลดออเดอร์
                                        </span>
                                    </a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="uploadtransaction.jsp">
                                        <span>
                                            <img src="css/bootstrap-icons-1.5.0/file-earmark-arrow-up.svg" 
                                                 alt="Bootstrap" 
                                                 width="20" 
                                                 height="20">&nbsp;
                                            อัพโหลดออเดอร์
                                        </span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a class="dropdown-item" href="#"> 
                                <img src="css/bootstrap-icons-1.5.0/clipboard-data.svg"
                                     alt="Bootstrap"
                                     width="20"
                                     height="20">&nbsp;
                            จัดการข้อมูล Transaction</a>
                            <ul class="submenu dropdown-menu">
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
                                <!-- <li>
                                        <a class="dropdown-item" href="listtransactiondepartment.jsp">
                                            <span>
                                                <img src="css/bootstrap-icons-1.5.0/briefcase-fill.svg" 
                                                     alt="Bootstrap" 
                                                     width="20" 
                                                     height="20">&nbsp;
                                                รูปแบบแผนก
                                            </span>
                                        </a>
                                    </li>-->
                            </ul>
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
                            ระบบจัดการสต็อก (รายชื่อพนักงาน)
                        </span>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <div class="dropdown-header">ควบคุม</div>
                        <li>
                            <a class="dropdown-item" href="listtransactioncustomer.jsp">
                                <span>
                                    <img src="css/bootstrap-icons-1.5.0/file-earmark-check.svg" 
                                         alt="Bootstrap" 
                                         width="20" 
                                         height="20">&nbsp;
                                    โปรเเกรมค้นหาเอกสาร
                                </span>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item" href="stocklistbag.jsp">
                                <span>
                                    <img src="css/bootstrap-icons-1.5.0/bag-check.svg" 
                                         alt="Bootstrap" 
                                         width="20" 
                                         height="20">&nbsp;
                                    โปรเเกรมจัดสินค้าลงถุง
                                </span>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item" href="stockboxall.jsp">
                                <span>
                                    <img src="css/bootstrap-icons-1.5.0/box.svg" 
                                         alt="Bootstrap" 
                                         width="20" 
                                         height="20">&nbsp;
                                    โปรเเกรมจัดสินค้าลงกล่อง
                                </span>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item" href="stockconfirm.jsp">
                                <span>
                                    <img src="css/bootstrap-icons-1.5.0/clipboard-check.svg" 
                                         alt="Bootstrap" 
                                         width="20" 
                                         height="20">&nbsp;
                                    โปรเเกรมสรุปผลการจัดสินค้าลงกล่อง
                                </span>
                            </a>
                        </li>
                        <li><hr class="dropdown-divider"></li>
                        <li>
                            <a class="dropdown-item" href="stockcustomeviewstatus.jsp">
                                <span>
                                    <img src="css/bootstrap-icons-1.5.0/book.svg" 
                                         alt="Bootstrap" 
                                         width="20" 
                                         height="20">&nbsp;
                                    โปรเเกรมดูรายละเอียดการจัดสินค้า
                                </span>
                            </a>
                        </li>
                        <!--<li>
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
                            </li>-->
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
                            <img src="css/bootstrap-icons-1.5.0/journal-text.svg"
                                 alt="Bootstrap"
                                 width="20"
                                 height="20">
                            คู่มือการใช้งานโปรแกรม
                        </span>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <div class="dropdown-header">ควบคุม</div>
                        <li>
                            <a class="dropdown-item" href="manualorder.jsp">
                                <span>
                                    <img src="css/bootstrap-icons-1.5.0/journal-bookmark.svg" 
                                         alt="Bootstrap" 
                                         width="20" 
                                         height="20">&nbsp;
                                    ระบบจัดการออเดอร์ 
                                </span>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item" href="manualstock.jsp">
                                <span>
                                    <img src="css/bootstrap-icons-1.5.0/journal-bookmark-fill.svg" 
                                         alt="Bootstrap" 
                                         width="20" 
                                         height="20">&nbsp;
                                    ระบบจัดการสต็อก
                                </span>
                            </a>
                        </li>
                        <!--<li>
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
                            </li>-->
                    </ul>
                </li>
                <!-- <li class="nav-item dropdown">
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
                                ระบบสต็อก (เเผนก)
                            </span>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li class="dropdown-header">เเบบถุง</li>
                            <li>
                                <a class="dropdown-item" href="stocklistbagdepart.jsp">
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
                                <a class="dropdown-item" href="stockdepartmentbagdepart.jsp">
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
                                <a class="dropdown-item" href="stockalllistbagdepart.jsp">
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
                                <a class="dropdown-item" href="stockboxalldepart.jsp">
                                    <span>
                                        <img src="css/bootstrap-icons-1.5.0/printer-fill.svg" 
                                             alt="Bootstrap" 
                                             width="20" 
                                             height="20">&nbsp;
                                        พิมพ์สติ๊กเกอร์ (ทั้งหมด)
                                    </span>
                                </a>
                                <a class="dropdown-item" href="stockdepartmentboxdepart.jsp">
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
                    </li>-->
            </ul>
            <div class="d-flex">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle"
                           href="#"
                           id="navbarDropdown"
                           role="button"
                           data-bs-toggle="dropdown"
                           aria-expanded="false">
                            <span class="font-menu-style">
                                <img src="css/bootstrap-icons-1.5.0/person-badge.svg"
                                     alt="Bootstrap"
                                     width="20"
                                     height="20">
                                <%=request.getSession().getAttribute("name")%>
                            </span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item  text-center " href="Chklogout">
                                    <img src="css/bootstrap-icons-1.5.0/door-closed.svg"
                                         alt="Bootstrap"
                                         width="20"
                                         height="20">  ออกจากระบบ
                            </a></li>
                        </ul>
                    </li>
                </ul>
                
            </div>
        </div>
    </div>
</nav>