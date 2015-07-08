<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:admin>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="body">
        <h3 style="padding: 10px;">Εισαγωγή Διαχειριστή Περιεχομένου</h3>

        <form action="admin/contentAdmin_create" method="POST" style="width: 450px; margin: 30px auto;">
            <div class="form-group">
                <label for="usernameId">Username</label>
                <input id="usernameId" type="username" name="username" class="form-control" placeholder="Username">
            </div>
            <div class="form-group">
                <label for="passwordId">Κωδικός</label>
                <input id="passwordId" type="password" name="password" class="form-control" placeholder="Password">
            </div>
            <div class="form-group">
                <label for="passwordRepeatId">Επανάληψη Κωδικού</label>
                <input id="passwordRepeatId" type="password" name="passwordRepeat" class="form-control" placeholder="Password">
            </div>
            <div class="form-group">
                <label for="nameId">Όνομα</label>
                <input id="nameId" type="text" name="name" class="form-control" placeholder="Όνομα">
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