<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:guest>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="body">
        <div id="page-title" class="container">Αναπληρωτής Καθηγητής, <span>ΒΑΡΣΑΜΗΣ ΧΡΗΣΤΟΣ-ΠΛΑΤΩΝ</span></div>
        <div id="page" class="container">
            <table id="tutorInfo" cellspacing="10">
                <tr>
                    <td style="width:200px;">Γραφείο11111111:</td>
                    <td style="width:600px;">Β219Α1111</td>
                </tr>
                <tr>
                    <td>Τηλ. Επικοινωνίας:</td>
                    <td>210 5381594</td>
                </tr>
                <tr>
                    <td>Ε-mail:</td>
                    <td><a href="mailto:cvars@teipir.gr">cvars@teipir.gr</a></td>
                </tr>
                <tr>
                    <td>Γνωστικό Αντικείμενο:</td>
                    <td>Φυσικός με ειδίκευση στη φυσική στερεάς κατάστασης και στην επιστήμη υλικών</td>
                </tr>
                <tr>
                    <td>Αρμοδιότητες:</td>
                    <td>Διευθυντής Τομέα Φυσικής</td>
                </tr>
                <tr>
                    <td>Μαθήματα:</td>
                    <td>Φυσική</td>
                </tr>
                <tr>
                    <td>Ώρες Γραφείου:</td>
                    <td>Πέμπτη 9-11, Παρασκευή 11-13</td>
                </tr>
                <tr>
                    <td>Βιογραφικό:</td>
                    <td><a href="cv_barsamis.pdf" target="_blank"><img src="images/pdf.jpg" alt=""/> Βιογραφικό</a></td>
                </tr>
            </table>
        </div>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
</t:guest>