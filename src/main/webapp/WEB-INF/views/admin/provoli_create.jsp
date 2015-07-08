<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:admin>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="body">
        <h3 style="padding: 10px;">Εισαγωγή Προβολής</h3>

        <form action="admin/provoli_create" method="POST" style="width: 450px; margin: 30px auto;">
            <div class="form-group">
                <label for="filmIdId">Κωδικός Ταινίας</label>
                <input id="filmIdId" type="text" name="filmId" class="form-control" placeholder="Κωδικός Ταινίας">
            </div>
            <div class="form-group">
                <label for="cinemaRoomIdId">Κωδικός Αίθουσας</label>
                <input id="cinemaRoomIdId" type="text" name="cinemaRoomId" class="form-control" placeholder="Κωδικός Αίθουσας">
            </div>
            <div class="form-group">
                <label for="startDateId">Ημ/νία Έναρξης</label>
                <input id="startDateId" type="text" name="startDate" class="form-control" placeholder="ΥΥΥΥ-ΜΜ-DD HH:MM">
            </div>
            <div class="form-group">
                <label for="endDateId">Ημ/νία Λήξης</label>
                <input id="endDateId" type="text" name="endDate" class="form-control" placeholder="ΥΥΥΥ-ΜΜ-DD HH:MM">
            </div>
            <div class="form-group">
                <label for="numberOfReservationsId">Πλήθος θέσεων</label>
                <input id="numberOfReservationsId" type="text" name="numberOfReservations" class="form-control" placeholder="Πλήθος θέσεων">
            </div>
            <div class="form-group">
                <label>Διαθέσιμη</label>
                <label class="radio-inline">
                    <input type="radio" name="available" value="true"> Ναι
                </label>
                <label class="radio-inline">
                    <input type="radio" name="available" value="false"> Όχι
                </label>
            </div>
            <c:if test="${hasError}">
                <p class="bg-danger">Μη αποδεκτά στοιχεία</p>
            </c:if>
            <button onclick="javascript:window.history.back();" class="btn btn-default">Πίσω</button>
            <button type="submit" class="btn btn-success">Εισαγωγή</button>
        </form>

    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
</t:admin>