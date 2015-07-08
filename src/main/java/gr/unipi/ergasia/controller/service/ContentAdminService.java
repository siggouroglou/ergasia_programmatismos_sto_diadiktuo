package gr.unipi.ergasia.controller.service;

import gr.unipi.ergasia.lib.mutable.BooleanMutable;
import gr.unipi.ergasia.lib.mutable.IntegerMutable;
import gr.unipi.ergasia.lib.security.Encryption;
import gr.unipi.ergasia.lib.sql.SqlManager;
import gr.unipi.ergasia.model.entity.ContentAdmin;
import gr.unipi.ergasia.model.entity.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author siggouroglou@gmail.com
 */
public class ContentAdminService {
    private static ContentAdminService INSTANCE;

    private ContentAdminService() {
    }
    
    public static ContentAdminService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ContentAdminService();
        }
        return INSTANCE;
    }
    
    public boolean insert(final ContentAdmin model) {
        // Rows affected.
        final IntegerMutable integetMutable = new IntegerMutable(-1);
        
        // Excecute the sql executeUpdate command.
        SqlManager<ContentAdmin> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("INSERT INTO ContentAdmin(username, password, name) VALUES (?, ?, ?);");
            query.setString(1, model.getUsername().toLowerCase());
            query.setString(2, model.getPassword());
            query.setString(3, model.getName());
            int rowsAffected = query.executeUpdate();
            integetMutable.set(rowsAffected);
        });
        
        // Validate and return the output.
        return integetMutable.intValue() > 0;
    }
    
    public ContentAdmin read(final String username) {
        // List of the returned administrators.
        final ContentAdmin model = new ContentAdmin();
        
        // Excecute the sql executeUpdate command.
        SqlManager<ContentAdmin> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("SELECT username, password, name FROM ContentAdmin WHERE username=?;");
            query.setString(1, username.toLowerCase());
            ResultSet resultSet = query.executeQuery();
            while(resultSet.next()) {
                model.setUsername(resultSet.getString("username"));
                model.setPassword(resultSet.getString("password"));
                model.setName(resultSet.getString("name"));
            }
        });
        
        // Return the output.
        return model;
    }
    
    public boolean update(final ContentAdmin model) {
        return update(model.getUsername(), model);
    }
    
    public boolean update(final String username, final ContentAdmin model) {
        // Rows affected.
        final IntegerMutable integetMutable = new IntegerMutable(-1);
        
        // Excecute the sql executeUpdate command.
        SqlManager<ContentAdmin> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("UPDATE ContentAdmin SET username=?, password=?, name=? WHERE username=?;");
            query.setString(1, model.getUsername().toLowerCase());
            query.setString(2, model.getPassword());
            query.setString(3, model.getName());
            query.setString(4, username);
            int rowsAffected = query.executeUpdate();
            integetMutable.set(rowsAffected);
        });
        
        // Validate and return the output.
        return integetMutable.intValue() > 0;
    }
    
    public boolean delete(final ContentAdmin model) {
        return delete(model.getUsername());
    }
    
    public boolean delete(final String username) {
        // Rows affected.
        final IntegerMutable integetMutable = new IntegerMutable(-1);
        
        // Excecute the sql executeUpdate command.
        SqlManager<ContentAdmin> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("DELETE FROM ContentAdmin WHERE username=?;");
            query.setString(1, username.toLowerCase());
            int rowsAffected = query.executeUpdate();
            integetMutable.set(rowsAffected);
        });
        
        // Validate and return the output.
        return integetMutable.intValue() > 0;
    }
    
    public List<ContentAdmin> readAll() {
        // List of the returned administrators.
        final List<ContentAdmin> modelList = new LinkedList<>();
        
        // Excecute the sql executeUpdate command.
        SqlManager<ContentAdmin> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("SELECT username, password, name FROM ContentAdmin;");
            ResultSet resultSet = query.executeQuery();
            while(resultSet.next()) {
                ContentAdmin model = new ContentAdmin();
                model.setUsername(resultSet.getString("username"));
                model.setPassword(resultSet.getString("password"));
                model.setName(resultSet.getString("name"));
                modelList.add(model);
            }
        });
        
        // Validate and return the output.
        return modelList;
    }

    public boolean isAuthedicated(final String username, final String password) {
        // List of the returned administrators.
        final BooleanMutable isExisting = new BooleanMutable(false);

        // Excecute the sql executeUpdate command.
        SqlManager<Customer> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("SELECT username, password, name FROM ContentAdmin WHERE username=? AND password=?;");
            query.setString(1, username.toLowerCase());
            query.setString(2, password);
            ResultSet resultSet = query.executeQuery();
            isExisting.set(resultSet.next());
        });

        // Validate and return the output.
        return isExisting.booleanValue();
    }
}
