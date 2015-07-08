<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:guest>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="body">
        <div id="page-title" class="container">Σύνδεση Χρηστών</div>
        <div id="page" class="container">

            <form action="web/signIn" method="POST" style="width: 450px; margin: 0 auto;">
                <div class="form-group">
                    <label for="usernameId">Username</label>
                    <input id="usernameId" type="text" name="username" class="form-control" placeholder="Username">
                </div>
                <div class="form-group">
                    <label for="passwordId">Password</label>
                    <input id="passwordId" type="password" name="password" class="form-control" placeholder="Password">
                </div>
                <div class="form-group">
                    <label for="roleId">Ρόλος</label>
                    <select id="roleId" name="role" class="form-control">
                        <option value="customer" selected="selected">Πελάτης</option>
                        <option value="contentAdmin">Διαχειριστής Περιεχομένου</option>
                        <option value="admin">Διαχειριστής</option>
                    </select>
                </div>
                <c:if test="${hasError}">
                    <p class="bg-danger">Μη αποδεκτά στοιχεία</p>
                </c:if>
                <button type="submit" class="btn btn-primary">Σύνδεση</button>
            </form>
        </div>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
</t:guest>