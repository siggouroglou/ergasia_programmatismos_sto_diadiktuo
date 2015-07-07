<%-- 
    Document   : home
    Created on : May 8, 2015, 6:46:06 PM
    Author     : siggouroglou
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:guest>
    <jsp:attribute name="header">
        <script src="https://maps.googleapis.com/maps/api/js"></script>
        <script>
            function initialize() {
                var mapCanvas = document.getElementById('map-canvas');
                var mapOptions = {
                    center: new google.maps.LatLng(44.5403, -78.5463),
                    zoom: 8,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                }
                var map = new google.maps.Map(mapCanvas, mapOptions)
            }
            google.maps.event.addDomListener(window, 'load', initialize);
        </script>
    </jsp:attribute>
    <jsp:attribute name="body">
                <h1>Η σελίδα που ζητήσατε δεν βρέθηκε!</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
</t:guest>