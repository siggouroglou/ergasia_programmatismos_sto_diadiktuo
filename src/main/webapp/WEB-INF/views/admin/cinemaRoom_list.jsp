<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:admin>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="body">
        <h3>Λίστα Αιθουσών Σινεμά</h3>
        <p>Διαχειριστείτε τις αίθουσες σινεμά του συστήματος. Δημιουργήστε μια αίθουσα, επεξεργαστείτε μια υπάρχουσα ή διαγράψτε την.</p>
        <div style="margin: 30px 0; width:98%; text-align: right;">
            <a href="admin/cinemaRoom_create" class="btn btn-success">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Προσθήκη
            </a>
        </div>
        <table style="width:780px; margin:20px auto 10px auto;" class="table table-striped">
            <tr>
                <th>Κωδικός</th>
                <th>Τίτλος</th>
                <th>3D</th>
                <th>Θέσεις</th>
                <th>Ενέργειες</th>
            </tr>
            <c:if test="${cinemaRoomList.isEmpty()}">
                <tr>
                    <td colspan="5" style="text-align: center;">Δεν υπάρχουν αίθουσες στο σύστημα</td>
                </tr>
            </c:if>
            <c:forEach items="${cinemaRoomList}" var="cinemaRoom">
                <tr>
                    <td>${cinemaRoom.id}</td>
                    <td>${cinemaRoom.title}</td>
                    <td>${cinemaRoom.support3D}</td>
                    <td>${cinemaRoom.totalSeats}</td>
                    <td>
                        <a href="admin/cinemaRoom_update?cinemaRoom_id=${cinemaRoom.id}" class="btn btn-primary">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 
                        </a>
                        <button class="btn btn-danger delete-btn" data-id="${cinemaRoom.id}">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </jsp:attribute>
    <jsp:attribute name="footer">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
        <script type="text/javascript">
            $(document).on("ready", function () {
                $(".delete-btn").on("click", function () {
                    var id = $(this).attr("data-id");
                    var deleteIt = confirm("Είστε σίγουρος για τη διαγραφή της αίθουσας με κωδικό  " + id);
                    if (deleteIt) {
                        window.location = "admin/cinemaRoom_delete?cinemaRoom_id=" + id;
                    }
                });
            });
        </script>
    </jsp:attribute>
</t:admin>