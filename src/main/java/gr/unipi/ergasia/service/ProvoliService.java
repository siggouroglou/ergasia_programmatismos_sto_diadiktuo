package gr.unipi.ergasia.service;

import gr.unipi.ergasia.lib.mutable.IntegerMutable;
import gr.unipi.ergasia.lib.sql.SqlManager;
import gr.unipi.ergasia.model.entity.Provoli;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author siggouroglou@gmail.com
 */
public class ProvoliService {

    private static ProvoliService INSTANCE;

    private ProvoliService() {
    }

    public static ProvoliService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProvoliService();
        }
        return INSTANCE;
    }

    public boolean insert(final Provoli model) {
        // Rows affected.
        final IntegerMutable integetMutable = new IntegerMutable(-1);

        // Excecute the sql executeUpdate command.
        SqlManager<Provoli> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement(
                    "INSERT INTO Provoli(film_id, cinemaRoom_id, startDate, endDate, numberOfReservations, available) VALUES (?, ?, ?, ?, ?, ?);");
            query.setInt(1, model.getFilmId());
            query.setInt(2, model.getCinemaRoomId());
            query.setTimestamp(3, new Timestamp(model.getStartDate().getTime()));
            query.setTimestamp(4, new Timestamp(model.getEndDate().getTime()));
            query.setInt(5, model.getNumberOfReservations());
            query.setBoolean(6, model.isAvailable());
            int rowsAffected = query.executeUpdate();
            integetMutable.set(rowsAffected);
        });

        // Validate and return the output.
        return integetMutable.intValue() > 0;
    }
    
    public Provoli read(final Integer id) {
        // List of the returned administrators.
        final Provoli model = new Provoli();
        
        // Excecute the sql executeUpdate command.
        SqlManager<Provoli> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement(
                    "SELECT id, film_id, cinemaRoom_id, startDate, endDate, numberOfReservations, available FROM Provoli WHERE id=?;");
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            while(resultSet.next()) {
                model.setId(resultSet.getInt("id"));
                model.setFilmId(resultSet.getInt("film_id"));
                model.setCinemaRoomId(resultSet.getInt("cinemaRoom_id"));
                model.setStartDate(resultSet.getTimestamp("startDate"));
                model.setEndDate(resultSet.getTimestamp("endDate"));
                model.setNumberOfReservations(resultSet.getInt("numberOfReservations"));
                model.setAvailable(resultSet.getBoolean("available"));
            }
        });
        
        // Return the output.
        return model;
    }

    public boolean update(final Provoli model) {
        return update(model.getId(), model);
    }

    public boolean update(final Integer id, final Provoli model) {
        // Rows affected.
        final IntegerMutable integetMutable = new IntegerMutable(-1);

        // Excecute the sql executeUpdate command.
        SqlManager<Provoli> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement(
                    "UPDATE Provoli SET id = ?, film_id = ?, cinemaRoom_id = ?, startDate = ?, endDate = ?, numberOfReservations = ?, available = ? WHERE id = ?;");
            query.setInt(1, model.getId());
            query.setInt(2, model.getFilmId());
            query.setInt(3, model.getCinemaRoomId());
            query.setTimestamp(4, new Timestamp(model.getStartDate().getTime()));
            query.setTimestamp(5, new Timestamp(model.getEndDate().getTime()));
            query.setInt(6, model.getNumberOfReservations());
            query.setBoolean(7, model.isAvailable());
            query.setInt(8, model.getId());
            int rowsAffected = query.executeUpdate();
            integetMutable.set(rowsAffected);
        });

        // Validate and return the output.
        return integetMutable.intValue() > 0;
    }

    public boolean delete(final Provoli model) {
        return delete(model.getId());
    }

    public boolean delete(final Integer id) {
        // Rows affected.
        final IntegerMutable integetMutable = new IntegerMutable(-1);

        // Excecute the sql executeUpdate command.
        SqlManager<Provoli> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("DELETE FROM Provoli WHERE id = ?;");
            query.setInt(1, id);
            int rowsAffected = query.executeUpdate();
            integetMutable.set(rowsAffected);
        });

        // Validate and return the output.
        return integetMutable.intValue() > 0;
    }

    public List<Provoli> readAll() {
        // List of the returned administrators.
        final List<Provoli> modelList = new LinkedList<>();

        // Excecute the sql executeUpdate command.
        SqlManager<Provoli> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("SELECT id, film_id, cinemaRoom_id, startDate, endDate, numberOfReservations, available FROM Provoli;");
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                Provoli model = new Provoli();
                model.setId(resultSet.getInt("id"));
                model.setFilmId(resultSet.getInt("film_id"));
                model.setCinemaRoomId(resultSet.getInt("cinemaRoom_id"));
                model.setStartDate(resultSet.getTimestamp("startDate"));
                model.setEndDate(resultSet.getTimestamp("endDate"));
                model.setNumberOfReservations(resultSet.getInt("numberOfReservations"));
                model.setAvailable(resultSet.getBoolean("available"));
                modelList.add(model);
            }
        });

        // Validate and return the output.
        return modelList;
    }

    public List<Provoli> readByDuration(Date fromDate, Date toDate) {
        // List of the returned administrators.
        final List<Provoli> modelList = new LinkedList<>();

        // Excecute the sql executeUpdate command.
        SqlManager<Provoli> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement(
                    "SELECT id, film_id, cinemaRoom_id, startDate, endDate, numberOfReservations, available "
                            + "FROM Provoli "
                            + "WHERE startDate > ? AND endDate < ? AND available = true;");
            query.setTimestamp(1, new Timestamp(fromDate.getTime()));
            query.setTimestamp(2, new Timestamp(toDate.getTime()));
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                Provoli model = new Provoli();
                model.setId(resultSet.getInt("id"));
                model.setFilmId(resultSet.getInt("film_id"));
                model.setCinemaRoomId(resultSet.getInt("cinemaRoom_id"));
                model.setStartDate(resultSet.getTimestamp("startDate"));
                model.setEndDate(resultSet.getTimestamp("endDate"));
                model.setNumberOfReservations(resultSet.getInt("numberOfReservations"));
                model.setAvailable(resultSet.getBoolean("available"));
                modelList.add(model);
            }
        });

        // Validate and return the output.
        return modelList;
    }
}
