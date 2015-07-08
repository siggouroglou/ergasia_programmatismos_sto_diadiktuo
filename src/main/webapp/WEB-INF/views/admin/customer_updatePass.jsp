<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:admin>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="body">
        <h3 style="padding: 10px;">Επεξεργασία Κωδικού Πελάτη</h3>

        <form action="admin/customer_updatePass" method="POST" style="width: 450px; margin: 30px auto;">
            <input type="hidden" name="username" value="${customer.username}"/>
            <div class="form-group">
                <label for="passwordId">Κωδικός</label>
                <input id="passwordId" type="password" name="password" class="form-control" placeholder="Password">
            </div>
            <div class="form-group">
                <label for="passwordRepeatId">Επανάληψη Κωδικού</label>
                <input id="passwordRepeatId" type="password" name="passwordRepeat" class="form-control" placeholder="Password">
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