<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:admin>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="body">
        <h3 style="padding: 10px;">Λίστα Ταινιών</h3>
        <div style="margin: 30px 0; width:98%; text-align: right;">
            <a href="admin/film_create" class="btn btn-success">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Προσθήκη
            </a>
        </div>
        <table style="width:780px; margin:20px auto 10px auto;" class="table table-striped">
            <tr>
                <th>Κωδικός</th>
                <th>Τίτλος</th>
                <th>Κατηγορία</th>
                <th>Περιγραφή</th>
                <th>Ενέργειες</th>
            </tr>
            <c:if test="${filmList.isEmpty()}">
                <tr>
                    <td colspan="5" style="text-align: center;">Δεν υπάρχουν ταινίες στο σύστημα</td>
                </tr>
            </c:if>
            <c:forEach items="${filmList}" var="film">
                <tr>
                    <td>${film.id}</td>
                    <td>${film.title}</td>
                    <td>${film.category}</td>
                    <td>${film.description}</td>
                    <td>
                        <a href="admin/film_create" class="btn btn-primary">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 
                        </a>
                        <a href="admin/film_create" class="btn btn-danger">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
</t:admin>