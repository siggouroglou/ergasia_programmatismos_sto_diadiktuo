<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:guest>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="body">
        <div id="page-title" class="container">Λίστα Προβολών</div>
        <div id="page" class="container">
            <p>Παρακάτω εμφανίζεται η λίστα με τις διαθέσιμες ταινίες προς προβολή για χρονικό διάστημα από ${dateFormat.format(fromDate)} έως ${dateFormat.format(toDate)}</p>

            <table style="width:950px; margin:20px auto 10px auto;" class="table table-striped">
                <tr>
                    <th>Κωδικός</th>
                    <th>Ταινία</th>
                    <th>Αίθουσα</th>
                    <th>Έναρξη</th>
                    <th>Λήξη</th>
                    <th>Κρατήσεις</th>
                    <th>Διαθέσιμη</th>
                    <th>Κράτηση</th>
                </tr>
                <c:if test="${provoliList.isEmpty()}">
                    <tr>
                        <td colspan="8" style="text-align: center;">Δεν βρέθηκαν προβολές</td>
                    </tr>
                </c:if>
                <c:forEach items="${provoliList}" var="provoli">
                    <tr>
                        <td>${provoli.id}</td>
                        <td>${provoli.filmId}</td>
                        <td>${provoli.cinemaRoomId}</td>
                        <td>${dateFormat.format(provoli.startDate)}</td>
                        <td>${dateFormat.format(provoli.endDate)}</td>
                        <td>${provoli.numberOfReservations}</td>
                        <td>${provoli.available}</td>
                        <td>
                            <button class="btn btn-primary kratisi-btn" data-id="${provoli.id}" data-filmId="${provoli.filmId}" data-cinemaRoomId="${provoli.cinemaRoomId}">
                                <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
        <script type="text/javascript">
            $(document).on("ready", function () {
                $(".kratisi-btn").on("click", function () {
                    var filmId = $(this).attr("data-filmId");
                    var cinemaRoomId = $(this).attr("data-cinemaRoomId");
                    var id = $(this).attr("data-id");
                    var deleteIt = confirm("Είστε σίγουρος για την κράτηση στην ταινία με κωδικό " + filmId 
                            + ", στην αίθουσα με κωδικό " + cinemaRoomId + ", για την προβολή με κωδικό " + id + "?");
                    if (deleteIt) {
                        window.location = "guest/makeReservation?provoli_id=" + id;
                    }
                });
            });
        </script>
    </jsp:attribute>
</t:guest>