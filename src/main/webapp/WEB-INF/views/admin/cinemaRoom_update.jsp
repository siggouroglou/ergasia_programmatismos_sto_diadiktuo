<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:admin>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="body">
        <h3 style="padding: 10px;">Επεξεργασία Αίθουσας Σινεμά</h3>

        <form action="admin/cinemaRoom_update" method="POST" style="width: 450px; margin: 30px auto;">
            <input type="hidden" name="id" value="${cinemaRoom.id}"/>
            <div class="form-group">
                <label for="titleId">Τίτλος</label>
                <input id="titleId" type="text" name="title" value="${cinemaRoom.title}" class="form-control" placeholder="Τίτλος">
            </div>
            <div class="form-group">
                <label>Υποστηρίζει 3D</label>
                <label class="radio-inline">
                    <input type="radio" name="support3D" value="true" ${cinemaRoom.support3D ? "checked" : ""}> Ναι
                </label>
                <label class="radio-inline">
                    <input type="radio" name="support3D" value="false" ${!cinemaRoom.support3D ? "checked" : ""}> Όχι
                </label>
            </div>
            <div class="form-group">
                <label for="totalSeatsId">Συνολικές Θέσεις</label>
                <input id="totalSeatsId" type="text" value="${cinemaRoom.totalSeats}" name="totalSeats" class="form-control" placeholder="Συνολικές Θέσεις">
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