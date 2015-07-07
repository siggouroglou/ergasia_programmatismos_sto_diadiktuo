<%-- 
    Document   : home
    Created on : May 8, 2015, 6:46:06 PM
    Author     : siggouroglou
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:guest>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="body">
        <div id="page" class="container">
            <h1>Ένα πρόβλημα παρουσιάστηκε με τη σελίδα που ζητήσετε!</h1>
        </div>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
</t:guest>