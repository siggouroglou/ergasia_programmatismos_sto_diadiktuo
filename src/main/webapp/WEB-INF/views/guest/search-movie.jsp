<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:guest>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="body">
        <div id="page-title" class="container">Αναζήτηση Ταινίας</div>
        <div id="page" class="container">

            <form action="web/movieList" method="GET" style="width: 450px; margin: 0 auto;">
                <div class="form-group">
                    <label for="fromDateId">Χρονικό Διάστημα Από</label>
                    <input id="fromDateId" type="text" name="fromDate" class="form-control" placeholder="DD-ΜΜ-ΥΥΥΥ HH:MM">
                </div>
                <div class="form-group">
                    <label for="toDateId">Χρονικό Διάστημα Έως</label>
                    <input id="toDateId" type="text" name="toDate" class="form-control" placeholder="DD-ΜΜ-ΥΥΥΥ HH:MM">
                </div>
                <c:if test="${hasError}">
                    <p class="bg-danger">Μη αποδεκτά στοιχεία</p>
                </c:if>
                <button type="submit" class="btn btn-primary">Αναζήτηση</button>
            </form>
        </div>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
</t:guest>