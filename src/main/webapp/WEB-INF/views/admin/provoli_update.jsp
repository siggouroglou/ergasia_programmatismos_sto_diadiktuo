<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:admin>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="body">
        <h3 style="padding: 10px;">Επεξεργασία Αίθουσας Σινεμά</h3>

        <form action="admin/provoli_update" method="POST" style="width: 450px; margin: 30px auto;">
            <input type="hidden" name="id" value="${provoli.id}"/>
            <div class="form-group">
                <label for="filmIdId">Κωδικός Ταινίας</label>
                <input id="filmIdId" type="text" name="filmId" value="${provoli.filmId}" class="form-control" placeholder="Τίτλος">
            </div>
            <div class="form-group">
                <label for="cinemaRoomIdId">Κωδικός Αίθουσας</label>
                <input id="cinemaRoomIdId" type="text" name="cinemaRoomId" value="${provoli.cinemaRoomId}" class="form-control" placeholder="Τίτλος">
            </div>
            <div class="form-group">
                <label for="startDateId">Ημ/νία Έναρξης</label>
                <input id="startDateId" type="text" name="startDate" value="${dateFormat.format(provoli.startDate)}" class="form-control" placeholder="Τίτλος">
            </div>
            <div class="form-group">
                <label for="endDateId">Ημ/νία Λήξης</label>
                <input id="endDateId" type="text" name="endDate" value="${dateFormat.format(provoli.endDate)}" class="form-control" placeholder="Τίτλος">
            </div>
            <div class="form-group">
                <label for="numberOfReservationsId">Πλήθος θέσεων</label>
                <input id="numberOfReservationsId" type="text" name="numberOfReservations" value="${provoli.numberOfReservations}" class="form-control" placeholder="Τίτλος">
            </div>
            <div class="form-group">
                <label>Διαθέσιμη</label>
                <label class="radio-inline">
                    <input type="radio" name="available" value="true" ${provoli.available ? "checked" : ""}> Ναι
                </label>
                <label class="radio-inline">
                    <input type="radio" name="available" value="false" ${!provoli.available ? "checked" : ""}> Όχι
                </label>
            </div>
                
            <c:if test="${hasError}">
                <p class="bg-danger">Μη αποδεκτά στοιχεία</p>
            </c:if>
            <button onclick="javascript:window.history.back();" class="btn btn-default">Πίσω</button>
            <button type="submit" class="btn btn-primary">Αποθήκευση</button>
        </form>

    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
</t:admin>