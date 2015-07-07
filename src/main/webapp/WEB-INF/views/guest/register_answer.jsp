<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:guest>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="body">
        <div id="page-title" class="container">Εγγραφή Χρήστη</div>
        <div id="page" class="container">

            <h3 style="margin:0 auto; text-align: center;">
                <c:if test="${hasError}">
                    Ένα πρόβλημα προέκυψε με την εγγραφή σας. Παρακαλούμε ελέγξτε τα στοιχεία που δώσατε και προσπαθήστε ξανά.
                </c:if>
                <c:if test="${!hasError}">
                    Η εγγραφή σας ολοκληρώθηκε με επιτυχία.
                </c:if>
                <br/>
                <a href="web/register" class="btn btn-default" style="margin-top: 60px;">Πίσω</a>
            </h3>
        </div>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
</t:guest>