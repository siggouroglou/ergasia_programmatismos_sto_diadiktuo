<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:guest>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="body">
        <div id="page-title" class="container">Εγγραφή Χρήστη</div>
        <div id="page" class="container">

            <form action="web/register" method="POST" style="width: 450px; margin: 0 auto;">
                <div class="form-group">
                    <label for="nameId">Όνομα</label>
                    <input id="nameId" type="text" name="name" class="form-control" id="exampleInputEmail1" placeholder="Όνομα">
                </div>
                <div class="form-group">
                    <label for="usernameId">Username</label>
                    <input id="usernameId" type="text" name="username" class="form-control" id="exampleInputEmail1" placeholder="Username">
                </div>
                <div class="form-group">
                    <label for="passwordId">Password</label>
                    <input id="passwordId"type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                </div>
                <button type="submit" class="btn btn-primary">Εγγραφή</button>
            </form>
        </div>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
</t:guest>