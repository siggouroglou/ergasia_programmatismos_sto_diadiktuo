<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:admin>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="body">
        <h3 style="padding: 10px;">Επεξεργασία Στοιχείων Πελάτη</h3>

        <form action="admin/customer_updateInfo" method="POST" style="width: 450px; margin: 30px auto;">
            <input type="hidden" name="usernameOld" value="${customer.username}"/>
            <div class="form-group">
                <label for="usernameId">Username</label>
                <input id="usernameId" type="username" name="username" value="${customer.username}" class="form-control" placeholder="Username">
            </div>
            <div class="form-group">
                <label for="nameId">Όνομα</label>
                <input id="nameId" type="text" name="name" value="${customer.name}" class="form-control" placeholder="Όνομα">
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