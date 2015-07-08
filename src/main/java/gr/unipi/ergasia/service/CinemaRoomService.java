package gr.unipi.ergasia.service;

import gr.unipi.ergasia.lib.mutable.IntegerMutable;
import gr.unipi.ergasia.lib.security.Encryption;
import gr.unipi.ergasia.lib.sql.SqlManager;
import gr.unipi.ergasia.model.entity.CinemaRoom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author siggouroglou@gmail.com
 */
public class CinemaRoomService {
    private static CinemaRoomService INSTANCE;

    private CinemaRoomService() {
    }
    
    public static CinemaRoomService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CinemaRoomService();
        }
        return INSTANCE;
    }
    
    public boolean insert(final CinemaRoom model) {
        // Rows affected.
        final IntegerMutable integetMutable = new IntegerMutable(-1);
        
        // Excecute the sql executeUpdate command.
        SqlManager<CinemaRoom> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("INSERT INTO CinemaRoom(id, support3D, totalSeats) VALUES (?, ?, ?);");
            query.setInt(1, model.getId());
            query.setBoolean(2, model.isSupport3D());
            query.setInt(3, model.getTotalSeats());
            int rowsAffected = query.executeUpdate();
            integetMutable.set(rowsAffected);
        });
        
        // Validate and return the output.
        return integetMutable.intValue() > 0;
    }
    
    public CinemaRoom read(final Integer id) {
        // List of the returned administrators.
        final CinemaRoom model = new CinemaRoom();
        
        // Excecute the sql executeUpdate command.
        SqlManager<CinemaRoom> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("SELECT id, support3D, totalSeats FROM CinemaRoom WHERE id=?;");
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            while(resultSet.next()) {
                model.setId(resultSet.getInt("id"));
                model.setSupport3D(resultSet.getBoolean("support3D"));
                model.setTotalSeats(resultSet.getInt("totalSeats"));
            }
        });
        
        // Return the output.
        return model;
    }
    
    public boolean update(final CinemaRoom model) {
        return update(model.getId(), model);
    }
    
    public boolean update(final Integer id, final CinemaRoom model) {
        // Rows affected.
        final IntegerMutable integetMutable = new IntegerMutable(-1);
        
        // Excecute the sql executeUpdate command.
        SqlManager<CinemaRoom> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("UPDATE CinemaRoom SET id = ?, support3D = ?, totalSeats = ? WHERE id = ?;");
            query.setInt(1, model.getId());
            query.setBoolean(2, model.isSupport3D());
            query.setInt(3, model.getTotalSeats());
            query.setInt(4, model.getId());
            int rowsAffected = query.executeUpdate();
            integetMutable.set(rowsAffected);
        });
        
        // Validate and return the output.
        return integetMutable.intValue() > 0;
    }
    
    public boolean delete(final CinemaRoom model) {
        return delete(model.getId());
    }
    
    public boolean delete(final Integer id) {
        // Rows affected.
        final IntegerMutable integetMutable = new IntegerMutable(-1);
        
        // Excecute the sql executeUpdate command.
        SqlManager<CinemaRoom> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("DELETE FROM CinemaRoom WHERE id = ?;");
            query.setInt(1, id);
            int rowsAffected = query.executeUpdate();
            integetMutable.set(rowsAffected);
        });
        
        // Validate and return the output.
        return integetMutable.intValue() > 0;
    }
    
    public List<CinemaRoom> readAll() {
        // List of the returned administrators.
        final List<CinemaRoom> modelList = new LinkedList<>();
        
        // Excecute the sql executeUpdate command.
        SqlManager<CinemaRoom> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("SELECT id, support3D, totalSeats FROM CinemaRoom;");
            ResultSet resultSet = query.executeQuery();
            while(resultSet.next()) {
                CinemaRoom model = new CinemaRoom();
                model.setId(resultSet.getInt("id"));
                model.setSupport3D(resultSet.getBoolean("support3D"));
                model.setTotalSeats(resultSet.getInt("totalSeats"));
                modelList.add(model);
            }
        });
        
        // Validate and return the output.
        return modelList;
    }

}
