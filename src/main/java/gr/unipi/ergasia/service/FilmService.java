package gr.unipi.ergasia.service;

import gr.unipi.ergasia.lib.mutable.IntegerMutable;
import gr.unipi.ergasia.lib.sql.SqlManager;
import gr.unipi.ergasia.model.entity.Film;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author siggouroglou@gmail.com
 */
public class FilmService {

    private static FilmService INSTANCE;

    private FilmService() {
    }

    public static FilmService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FilmService();
        }
        return INSTANCE;
    }

    public boolean insert(final Film model) {
        // Rows affected.
        final IntegerMutable integetMutable = new IntegerMutable(-1);

        // Excecute the sql executeUpdate command.
        SqlManager<Film> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("INSERT INTO Film(title, category, description) VALUES (?, ?, ?);");
            query.setString(1, model.getTitle());
            query.setString(2, model.getCategory());
            query.setString(3, model.getDescription());
            int rowsAffected = query.executeUpdate();
            integetMutable.set(rowsAffected);
        });

        // Validate and return the output.
        return integetMutable.intValue() > 0;
    }
    
    public Film read(final Integer id) {
        // List of the returned administrators.
        final Film model = new Film();
        
        // Excecute the sql executeUpdate command.
        SqlManager<Film> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("SELECT id, title, category, description FROM Film WHERE id=?;");
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            while(resultSet.next()) {
                model.setId(resultSet.getInt("id"));
                model.setTitle(resultSet.getString("title"));
                model.setCategory(resultSet.getString("category"));
                model.setDescription(resultSet.getString("description"));
            }
        });
        
        // Return the output.
        return model;
    }

    public boolean update(final Film model) {
        return update(model.getId(), model);
    }

    public boolean update(final Integer id, final Film model) {
        // Rows affected.
        final IntegerMutable integetMutable = new IntegerMutable(-1);

        // Excecute the sql executeUpdate command.
        SqlManager<Film> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("UPDATE Film SET id = ?, title = ?, category = ?, description = ? WHERE id = ?;");
            query.setInt(1, model.getId());
            query.setString(2, model.getTitle());
            query.setString(3, model.getCategory());
            query.setString(4, model.getDescription());
            query.setInt(5, model.getId());
            int rowsAffected = query.executeUpdate();
            integetMutable.set(rowsAffected);
        });

        // Validate and return the output.
        return integetMutable.intValue() > 0;
    }

    public boolean delete(final Film model) {
        return delete(model.getId());
    }

    public boolean delete(final Integer id) {
        // Rows affected.
        final IntegerMutable integetMutable = new IntegerMutable(-1);

        // Excecute the sql executeUpdate command.
        SqlManager<Film> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("DELETE FROM Film WHERE id = ?;");
            query.setInt(1, id);
            int rowsAffected = query.executeUpdate();
            integetMutable.set(rowsAffected);
        });

        // Validate and return the output.
        return integetMutable.intValue() > 0;
    }

    public List<Film> readAll() {
        // List of the returned administrators.
        final List<Film> modelList = new LinkedList<>();

        // Excecute the sql executeUpdate command.
        SqlManager<Film> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("SELECT id, title, category, description FROM Film;");
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                Film model = new Film();
                model.setId(resultSet.getInt("id"));
                model.setTitle(resultSet.getString("title"));
                model.setCategory(resultSet.getString("category"));
                model.setDescription(resultSet.getString("description"));
                modelList.add(model);
            }
        });

        // Validate and return the output.
        return modelList;
    }

}
