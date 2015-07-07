package gr.unipi.ergasia.service;

import gr.unipi.ergasia.lib.IntegerMutable;
import gr.unipi.ergasia.lib.security.Encryption;
import gr.unipi.ergasia.lib.sql.SqlManager;
import gr.unipi.ergasia.model.entity.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Singleton than manages the connection to the database for the Admin table.
 * @author siggouroglou@gmail.com
 */
public class AdminService {
    private static AdminService INSTANCE;

    private AdminService() {
    }
    
    public static AdminService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new AdminService();
        }
        return INSTANCE;
    }
    
    public boolean insert(final Admin model) {
        // Rows affected.
        final IntegerMutable integetMutable = new IntegerMutable(-1);
        
        // Excecute the sql executeUpdate command.
        SqlManager<Admin> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("INSERT INTO Admin(username, password, name) VALUES (?, ?, ?);");
            query.setString(1, model.getUsername());
            query.setString(2, Encryption.getHashMD5(model.getPassword()));
            query.setString(3, model.getName());
            int rowsAffected = query.executeUpdate();
            integetMutable.set(rowsAffected);
        });
        
        // Validate and return the output.
        return integetMutable.intValue() > 0;
    }
    
    public boolean update(final Admin model) {
        return update(model.getUsername(), model);
    }
    
    public boolean update(final String username, final Admin model) {
        // Rows affected.
        final IntegerMutable integetMutable = new IntegerMutable(-1);
        
        // Excecute the sql executeUpdate command.
        SqlManager<Admin> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("UPDATE Admin SET username=?, password=?, name=? WHERE username=?;");
            query.setString(1, model.getUsername());
            query.setString(2, Encryption.getHashMD5(model.getPassword()));
            query.setString(3, model.getName());
            query.setString(4, username);
            int rowsAffected = query.executeUpdate();
            integetMutable.set(rowsAffected);
        });
        
        // Validate and return the output.
        return integetMutable.intValue() > 0;
    }
    
    public boolean delete(final Admin model) {
        return delete(model.getUsername());
    }
    
    public boolean delete(final String username) {
        // Rows affected.
        final IntegerMutable integetMutable = new IntegerMutable(-1);
        
        // Excecute the sql executeUpdate command.
        SqlManager<Admin> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("DELETE FROM Admin WHERE username=?;");
            query.setString(1, username);
            int rowsAffected = query.executeUpdate();
            integetMutable.set(rowsAffected);
        });
        
        // Validate and return the output.
        return integetMutable.intValue() > 0;
    }
    
    public List<Admin> getAll() {
        // List of the returned administrators.
        final List<Admin> modelList = new LinkedList<>();
        
        // Excecute the sql executeUpdate command.
        SqlManager<Admin> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("SELECT username, password, name FROM Admin;");
            ResultSet resultSet = query.executeQuery();
            while(resultSet.next()) {
                Admin model = new Admin();
                model.setUsername(resultSet.getString("username"));
                model.setPassword(resultSet.getString("password"));
                model.setName(resultSet.getString("name"));
                modelList.add(model);
            }
        });
        
        // Validate and return the output.
        return modelList;
    }
}
