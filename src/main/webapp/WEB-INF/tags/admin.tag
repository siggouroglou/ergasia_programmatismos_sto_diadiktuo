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

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"></link>
        <!--Font-->
        <!--<link href="http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300&subset=latin,greek" rel="stylesheet" type="text/css"/>-->
        <!--Custom Css-->
        <link href="style.css?ver=0.1" rel="stylesheet" type="text/css" media="screen" />
        <style type="text/css">
            .images-container {
                display: block;
            }
            .images-container img {
                display: inline-block;
            }
            .menu-item {
                width: 155px; 
                padding: 10px; 
                text-align: center;
            }
            .menu-item a {
                text-decoration: none;
                color: #999;
            }
            .menu-item a:hover {
                text-decoration: none;
                color: #000;
            }
        </style>

        <jsp:invoke fragment="header"/>
    </head>
    <body>
        <div id="header-wrapper-mine" class="container">
            <div id="header" class="container">
                <div id="logo">
                    <h3><a href="#">ΠΑ.ΠΕΙ. - ΤΜΗΜΑ ΠΛΗΡΟΦΟΡΙΚΗΣ</a></h3>
                    <h4><a href="#">Σύστημα διαχείρισης κρατήσεων σε κινηματογράφο</a></h4>
                </div>
                <div id="menu" style="margin-top: 40px;">
                    <ul>
                        <li class="current_page_item"><a href="web/home">Αρχική Σελίδα</a></li>
                        <li><a href="web/search-movie">Αναζήτηση Ταινίας</a></li>
                        <li><a href="web/signIn">Σύνδεση</a></li>
                        <li><a href="web/register">Εγγραφή</a></li>
                    </ul>
                </div>
            </div>
            <div>
                <img src="images/logo.jpg?ver=0.0" alt="" style="border: 15px solid #F4F4F4; width:970px; height:310px;" />
            </div>
        </div>
        <div id="main-wrapper">
            <div id="page" class="container" style="display:table;">
                <div style="display:table-cell; width:160px;">
                    <div style="display:block; width:160px; border:1px solid #BBB;">
                        <div class="menu-item">
                            <a href="admin/home">Αρχική Σελίδα</a>
                        </div>
                        <div class="menu-item">
                            <a href="admin/film_list">Ταινίες</a>
                        </div>
                        <div class="menu-item">
                            <a href="admin/cinemaRoom_list">Αίθουσες</a>
                        </div>
                        <div class="menu-item">
                            <a href="admin/home">Προβολές</a>
                        </div>
                        <div class="menu-item">
                            <a href="admin/contentAdmin_list">Διαχειριστές Περιεχομένου</a>
                        </div>
                        <div class="menu-item">
                            <a href="admin/home">Πελάτες</a>
                        </div>
                    </div>
                </div>
                <div style="display:table-cell; width:800px;">
                    <div style="display:block; width:790px; min-height:400px; margin-left: 10px; padding: 10px; border:1px solid #BBB;">
                        <jsp:invoke fragment="body"/>
                    </div>
                </div>
            </div>
        </div>
        <div id="footer-wrapper" class="container">
            <div id="footer-bg">
                <div class="footer-subblock">
                    <h2>Μενού</h2>
                    <ul class="list-style2">
                        <li class="first"><a href="web/home">Αρχική</a></li>
                        <li><a href="web/search-movie">Αναζήτηση Ταινίας</a></li>
                        <li><a href="web/signIn">Σύνδεση</a></li>
                        <li><a href="web/register">Εγγραφή</a></li>
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
            <p>© 2013 All rights reserved. Designed by <a href="mailto:siggouroglou@gmail.com?subject=Project Unipi">our team</a></p>
        </div>
        <!-- end #footer -->

        <jsp:invoke fragment="footer"/>
    </body>
</html>