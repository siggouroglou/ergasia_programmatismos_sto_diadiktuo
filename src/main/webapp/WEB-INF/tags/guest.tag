<%@tag description="Guest Common Page" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="body" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%-- 
    Document   : home
    Created on : May 8, 2015, 6:46:06 PM
    Author     : siggouroglou
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
Design by Free CSS Templates
http://www.freecsstemplates.org
Released for free under a Creative Commons Attribution 2.5 License

Name       : Good-Natured
   
Description: A two-column, fixed-width design with dark color scheme.
Version    : 1.0
Released   : 20120817
-->
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <base href="/"/>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        
        <title>ΠΑ.ΠΕΙ. - Τμήμα Πληροφορικής - Εργασία στο μάθημα Προγραμματισμός Στο Διαδίκτυο Και Στον Παγκόσμιο Ιστό</title>
        <meta name="keywords" content="ΠΑ.ΠΕΙ. - Τμήμα Πληροφορικής - Εργασία στο μάθημα Προγραμματισμός Στο Διαδίκτυο Και Στον Παγκόσμιο Ιστό" />
        <meta name="description" content="ΠΑ.ΠΕΙ. - Τμήμα Πληροφορικής - Εργασία στο μάθημα Προγραμματισμός Στο Διαδίκτυο Και Στον Παγκόσμιο Ιστό" />
        
        <link href="http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300&subset=latin,greek" rel="stylesheet" type="text/css"/>
        <link href="style.css?ver=0.1" rel="stylesheet" type="text/css" media="screen" />

        <jsp:invoke fragment="header"/>
    </head>
    <body>
        <div id="header-wrapper" class="container">
            <div id="header" class="container">
                <div id="logo">
                    <h1><a href="#">ΠΑ.ΠΕΙ. - ΤΜΗΜΑ ΠΛΗΡΟΦΟΡΙΚΗΣ</a></h1>
                    <p>Σύστημα διαχείρισης κρατήσεων σε κινηματογράφο</p>
                </div>
                <div id="menu">
                    <ul>
                        <li class="current_page_item"><a href="web/home">Αρχική Σελίδα</a></li>
                        <li><a href="web/search-movie">Αναζήτηση Ταινίας</a></li>
                        <li><a href="web/signIn">Σύνδεση</a></li>
                    </ul>
                </div>
            </div>
            <div>
                <img src="images/logo.jpg?ver=0.0" alt="" style="border: 15px solid #F4F4F4; width:970px; height:290px;" />
            </div>
        </div>
        <div id="main-wrapper">
            <jsp:invoke fragment="body"/>
        </div>
        <div id="footer-wrapper" class="container">
            <div id="footer-bg">
                <div class="footer-subblock">
                    <h2>Μενού</h2>
                    <ul class="list-style2">
                        <li class="first"><a href="web/home">Αρχική</a></li>
                        <li><a href="web/search-movie">Αναζήτηση Ταινίας</a></li>
                        <li><a href="web/signIn">Σύνδεση</a></li>
                    </ul>
                </div>
                <div class="footer-subblock" style="margin-right:0;">
                    <h2>προτεινόμενοι σύνδεσμοι</h2>
                    <ul class="list-style2">
                        <li class="first"><a href="http://www.unipi.gr/" target="_blank">Πανεπιστήμιο Πειραιά</a></li>
                        <li><a href="http://gunet2.cs.unipi.gr/" target="_blank">Open Eclass</a></li>
                        <li><a href="https://students.cs.unipi.gr/" target="_blank">Students</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div id="footer" class="container">
            <p>© 2013 All rights reserved. Designed by <a href="mailto:siggouroglou@gmail.com?subject=Project Unipi">George Siggouroglou</a></p>
        </div>
        <!-- end #footer -->

        <jsp:invoke fragment="footer"/>
    </body>
</html>