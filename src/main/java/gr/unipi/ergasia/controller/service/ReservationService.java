package gr.unipi.ergasia.controller.service;

import gr.unipi.ergasia.lib.mutable.IntegerMutable;
import gr.unipi.ergasia.lib.sql.SqlManager;
import gr.unipi.ergasia.model.entity.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * Singleton than manages the connection to the database for the Reservation
 * table.
 *
 * @author siggouroglou@gmail.com
 */
public class ReservationService {

    private static ReservationService INSTANCE;

    private ReservationService() {
    }

    public static ReservationService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReservationService();
        }
        return INSTANCE;
    }

    public boolean insert(final Reservation model) {
        // Rows affected.
        final IntegerMutable integetMutable = new IntegerMutable(-1);

        // Excecute the sql executeUpdate command.
        SqlManager<Reservation> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("INSERT INTO Reservation(customer_username, provoli_id) VALUES (?, ?);");
            query.setString(1, model.getCustomerUsername().toLowerCase());
            query.setInt(2, model.getProvoliId());
            int rowsAffected = query.executeUpdate();
            integetMutable.set(rowsAffected);
        });

        // Validate and return the output.
        return integetMutable.intValue() > 0;
    }

    public int countWithProvoliId(final int provoliId) {
        // List of the returned administrators.
        IntegerMutable integer = new IntegerMutable(0);

        // Excecute the sql executeUpdate command.
        SqlManager<Reservation> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("SELECT count(*) AS counter FROM Reservation WHERE provoli_id=?;");
            query.setInt(1, provoliId);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                integer.set(resultSet.getInt("counter"));
            }
        });

        // Return the output.
        return integer.intValue();
    }
}
