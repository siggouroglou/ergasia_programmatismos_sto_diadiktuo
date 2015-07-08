<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:admin>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="body">
        <h3 style="padding: 10px;">Επεξεργασία Ταινίας</h3>

        <form action="admin/film_update" method="POST" style="width: 450px; margin: 30px auto;">
            <input type="hidden" name="id" value="${film.id}"/>
            <div class="form-group">
                <label for="exampleInputEmail1">Τίτλος</label>
                <input type="text" name="title" value="${film.title}" class="form-control" id="exampleInputEmail1" placeholder="Τίτλος">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Κατηγορία</label>
                <input type="text" name="category" value="${film.category}" class="form-control" id="exampleInputPassword1" placeholder="Κατηγορία">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Περιγραφή</label>
                <input type="text" name="description" value="${film.description}" class="form-control" id="exampleInputPassword1" placeholder="Περιγραφή">
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