package gr.unipi.ergasia.service;

import gr.unipi.ergasia.lib.mutable.BooleanMutable;
import gr.unipi.ergasia.lib.mutable.IntegerMutable;
import gr.unipi.ergasia.lib.security.Encryption;
import gr.unipi.ergasia.lib.sql.SqlManager;
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
public class CustomerService {

    private static CustomerService INSTANCE;

    private CustomerService() {
    }

    public static CustomerService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CustomerService();
        }
        return INSTANCE;
    }

    public boolean insert(final Customer model) {
        // Rows affected.
        final IntegerMutable integetMutable = new IntegerMutable(-1);

        // Excecute the sql executeUpdate command.
        SqlManager<Customer> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("INSERT INTO Customer(username, password, name) VALUES (?, ?, ?);");
            query.setString(1, model.getUsername().toLowerCase());
            query.setString(2, model.getPassword());
            query.setString(3, model.getName());
            int rowsAffected = query.executeUpdate();
            integetMutable.set(rowsAffected);
        });

        // Validate and return the output.
        return integetMutable.intValue() > 0;
    }
    
    public Customer read(final String username) {
        // List of the returned administrators.
        final Customer model = new Customer();
        
        // Excecute the sql executeUpdate command.
        SqlManager<Customer> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("SELECT username, password, name FROM Customer WHERE username=?;");
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

    public boolean update(final Customer model) {
        return update(model.getUsername(), model);
    }

    public boolean update(final String username, final Customer model) {
        // Rows affected.
        final IntegerMutable integetMutable = new IntegerMutable(-1);

        // Excecute the sql executeUpdate command.
        SqlManager<Customer> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("UPDATE Customer SET username=?, password=?, name=? WHERE username=?;");
            query.setString(1, model.getUsername().toLowerCase());
            query.setString(2, model.getPassword());
            query.setString(3, model.getName());
            query.setString(4, username.toLowerCase());
            int rowsAffected = query.executeUpdate();
            integetMutable.set(rowsAffected);
        });

        // Validate and return the output.
        return integetMutable.intValue() > 0;
    }

    public boolean delete(final Customer model) {
        return delete(model.getUsername());
    }

    public boolean delete(final String username) {
        // Rows affected.
        final IntegerMutable integetMutable = new IntegerMutable(-1);

        // Excecute the sql executeUpdate command.
        SqlManager<Customer> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("DELETE FROM Customer WHERE username=?;");
            query.setString(1, username.toLowerCase());
            int rowsAffected = query.executeUpdate();
            integetMutable.set(rowsAffected);
        });

        // Validate and return the output.
        return integetMutable.intValue() > 0;
    }

    public List<Customer> readAll() {
        // List of the returned administrators.
        final List<Customer> modelList = new LinkedList<>();

        // Excecute the sql executeUpdate command.
        SqlManager<Customer> sqlManager = new SqlManager<>();
        sqlManager.executeSql((Connection connection) -> {
            PreparedStatement query = connection.prepareStatement("SELECT username, password, name FROM Customer;");
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                Customer model = new Customer();
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
            PreparedStatement query = connection.prepareStatement("SELECT username, password, name FROM Customer WHERE username=? AND password=?;");
            query.setString(1, username.toLowerCase());
            query.setString(2, password);
            ResultSet resultSet = query.executeQuery();
            isExisting.set(resultSet.next());
        });

        // Validate and return the output.
        return isExisting.booleanValue();
    }
}
