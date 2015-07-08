<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:contentAdmin>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="body">
        <h3>Λίστα Προβολών</h3>
        <p>Διαχειριστείτε τις προβολές του συστήματος. Δημιουργήστε μια προβολή, επεξεργαστείτε μια υπάρχουσα ή διαγράψτε την.</p>
        
        <table style="width:780px; margin:20px auto 10px auto;" class="table table-striped">
            <tr>
                <th>Κωδικός</th>
                <th>Ταινία</th>
                <th>Αίθουσα</th>
                <th>Έναρξη</th>
                <th>Λήξη</th>
                <th>Κρατήσεις</th>
                <th>Διαθέσιμη</th>
                <th>Ενέργειες</th>
            </tr>
            <c:if test="${provoliList.isEmpty()}">
                <tr>
                    <td colspan="8" style="text-align: center;">Δεν υπάρχουν προβολές στο σύστημα</td>
                </tr>
            </c:if>
            <c:forEach items="${provoliList}" var="provoli">
                <tr>
                    <td>${provoli.id}</td>
                    <td>${provoli.filmId}</td>
                    <td>${provoli.cinemaRoomId}</td>
                    <td>${dateFormat.format(provoli.startDate)}</td>
                    <td>${dateFormat.format(provoli.endDate)}</td>
                    <td>${provoli.numberOfReservations}</td>
                    <td>${provoli.available}</td>
                    <td>
                        <a href="contentAdmin/provoli_update?provoli_id=${provoli.id}" class="btn btn-primary">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
</t:contentAdmin>