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

<!DOCTYPE html>
<html>
    <head>
        <title>Πανεπιστήμιο Πειραιά - Τμήμα Πληροφορικής - Γεωγραφικά Πληροφοριακά Συστήματα 2015</title>
        <link href="http://www.unipi.gr/unipi/templates/universityofpiraeus_2012/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon" />
        <style>
            body{
                margin:0;
                border:0;
                padding: 0;
            }
            .container-block {
            }
            .inner-block {
                width:1000px;
                margin:0 auto;
                text-align:center;
            }
            a:hover,
            a:visited,
            a {
                text-decoration: none;
                color: #000;
            }
            #map-canvas {
                width: 500px;
                height: 400px;
            }
        </style>
        <jsp:invoke fragment="header"/>
    </head>
    <body>
        <div class="inner-block" style="margin-top: 10px;">
            <img src="http://www.wideservices.gr/images/logos/unipi_logo.jpg" alt="unipi logo" style="height: 100px;"/>
        </div>

        <div class="inner-block" style="margin: 5px auto 30px auto;">
            <a href="/home">
                ΠΑΝΕΠΙΣΤΗΜΙΟ ΠΕΙΡΑΙΑ
                <br/>
                ΓΕΩΓΡΑΦΙΚΑ ΠΛΗΡΟΦΟΡΙΑΚΑ ΣΥΣΤΗΜΑΤΑ
            </a>
        </div>

        <div class="inner-block">
            <div style="display:table; width: 1000px; height: 30px;">
                <div style="display:table-cell; text-align: center; vertical-align: middle;"><a href="/er1">Ερώτημα 1o</a></div>
                <div style="display:table-cell; text-align: center; vertical-align: middle;"><a href="/er2">Ερώτημα 2o</a></div>
                <div style="display:table-cell; text-align: center; vertical-align: middle;"><a href="/er3">Ερώτημα 3o</a></div>
            </div>
        </div>
        <div style="height:410px; background-image: url('http://www.containerstore.com/catalogimages/168786/WrapStripe10060886_x.jpg')">
            <div class="inner-block">
                <div id="map-canvas" style="width:1000px; height:420px; margin: 0; border:0; border-top: 1px solid #ccc; border-bottom: 1px solid #ccc; padding: 0; text-align: center;"></div>
            </div>
            <div class="inner-block">
                <img src="http://eshop.thevsociety.com/files/images/shadow.png"/>
            </div>
        </div>

        <div style="padding:30px 0 10px 0; background-image: url('files/images/stripe.png');">
            <div class="inner-block" style="height: 500px; border:1px solid #EEE; padding: 10px; background: #FFF;">
        <jsp:invoke fragment="body"/>
            </div>
        </div>

        <div style="border-top:2px solid #CCC;">
            <div class="inner-block" style="margin: 10px auto 0px auto;">
                <div style="display: table; width:1000px;">
                    <div style="display:table-cell; text-align: left; vertical-align: top;">
                        <a href="http://www.postgresql.org/">
                            <img src="http://2.bp.blogspot.com/-xiBNHcpxjWE/TndsywouDGI/AAAAAAAAAoY/X3SPSn0bhcE/s1600/postgresql_logo-555px.png" style="height: 50px;"/></a>
                        <a href="http://postgis.net/">
                            <img src="http://upload.wikimedia.org/wikipedia/en/6/60/PostGIS_logo.png" style="height: 50px;"/></a>
                        <a href="https://java.com/en/">
                            <img src="https://redcircle2oracle.files.wordpress.com/2012/10/logo-java.jpg" style="height: 50px;"/></a>
                        <a href="https://netbeans.org/">
                            <img src="http://peterfeatherstone.com/wp-content/uploads/netbeans-logo.png" style="height: 50px;"/></a>
                    </div>
                    <div style="display:table-cell; text-align: right; vertical-align: top;">
                        <b>Φοιτητές</b>
                        <br/>
                        Συγγούρογλου Γέωργιος Π08138
                        <br/>
                        Ναλπαντίσης Κων/νος Π?????
                    </div>
                </div>
                <div style="margin-top:30px; color: #CCC;">Πανεπιστήμιο Πειραιά - Τμήμα Πληροφορικής - Γεωγραφικά Πληροφοριακά Συστήματα 2015</div>
            </div>
        </div>
        <jsp:invoke fragment="footer"/>
    </body>
</html>