<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:guest>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="body">
        <div id="page-title" class="container">Εγγραφή Χρήστη</div>
        <div id="page" class="container">

            <h3 style="margin:0 auto; text-align: center;">
                Η κράτησή σας καταχωρήθηκε.
                <br/>
                Σας ευχαριστούμε για την προτίμηση.
            </h3>
            <button onclick="javascript:window.history.back();" class="btn btn-default" style="margin: 60px auto 0 auto; text-align: center;">Πίσω</button>
        </div>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
</t:guest>