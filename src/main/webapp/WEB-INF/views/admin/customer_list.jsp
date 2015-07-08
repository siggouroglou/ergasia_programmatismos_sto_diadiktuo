<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:admin>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="body">
        <h3>Λίστα Πελατών</h3>
        <p>Διαχειριστείτε τους πελάτες του συστήματος. Δημιουργήστε ένα πελάτη, επεξεργαστείτε ή διαγράψτε τον.</p>
        <div style="margin: 30px 0; width:98%; text-align: right;">
            <a href="admin/customer_create" class="btn btn-success">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Προσθήκη
            </a>
        </div>
        <table style="width:780px; margin:20px auto 10px auto;" class="table table-striped">
            <tr>
                <th>Username</th>
                <th>Όνομα</th>
                <th>Ενέργειες</th>
            </tr>
            <c:if test="${customerList.isEmpty()}">
                <tr>
                    <td colspan="3" style="text-align: center;">Δεν υπάρχουν πελάτες στο σύστημα</td>
                </tr>
            </c:if>
            <c:forEach items="${customerList}" var="customer">
                <tr>
                    <td>${customer.username}</td>
                    <td>${customer.name}</td>
                    <td>
                        <a href="admin/customer_updateInfo?customer_username=${customer.username}" class="btn btn-primary">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 
                        </a>
                        <a href="admin/customer_updatePass?customer_username=${customer.username}" class="btn btn-primary">
                            <span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span> 
                        </a>
                        <button class="btn btn-danger delete-btn" data-id="${customer.username}">
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
                    var username = $(this).attr("data-id");
                    var deleteIt = confirm("Είστε σίγουρος για τη διαγραφή του πελάτη με username  " + username);
                    if (deleteIt) {
                        window.location = "admin/customer_delete?customer_username=" + username;
                    }
                });
            });
        </script>
    </jsp:attribute>
</t:admin>