<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:contentAdmin>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="body">
        <h3 style="padding: 10px;">Εισαγωγή Ταινίας</h3>

        <form action="contentAdmin/film_create" method="POST" style="width: 450px; margin: 30px auto;">
            <div class="form-group">
                <label for="titleId">Τίτλος</label>
                <input id="titleId" type="text" name="title" class="form-control" placeholder="Τίτλος">
            </div>
            <div class="form-group">
                <label for="categoryId">Κατηγορία</label>
                <input id="categoryId" type="text" name="category" class="form-control" placeholder="Κατηγορία">
            </div>
            <div class="form-group">
                <label for="descriptionId">Περιγραφή</label>
                <input id="descriptionId" type="text" name="description" class="form-control" id="exampleInputPassword1" placeholder="Περιγραφή">
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
</t:contentAdmin>