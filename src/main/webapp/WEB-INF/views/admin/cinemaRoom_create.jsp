<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:admin>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="body">
        <h3 style="padding: 10px;">Εισαγωγή Αίθουσας Σινεμά</h3>

        <form action="admin/cinemaRoom_create" method="POST" style="width: 450px; margin: 30px auto;">
            <div class="form-group">
                <label for="titleId">Τίτλος</label>
                <input id="titleId" type="text" name="title" class="form-control" placeholder="Τίτλος">
            </div>
            <div class="form-group">
                <label>Υποστηρίζει 3D</label>
                <label class="radio-inline">
                    <input type="radio" name="support3D" value="true"> Ναι
                </label>
                <label class="radio-inline">
                    <input type="radio" name="support3D" value="false"> Όχι
                </label>
            </div>
            <div class="form-group">
                <label for="totalSeatsId">Συνολικές Θέσεις</label>
                <input id="totalSeatsId" type="text" name="totalSeats" class="form-control" placeholder="Συνολικές Θέσεις">
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