package gr.unipi.ergasia.lib.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;

/**
 *
 * @author siggouroglou@gmail.com
 */
public class SqlManager<T> {

    private final Logger logger = Logger.getLogger(SqlManager.class);

    private final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final static String DB_URL = "jdbc:mysql://localhost/programonwebdb?characterEncoding=greek";
    private final static String USER = "root";
    private final static String PASS = "root";

    public void executeSql(SqlStrategy<T> strategy) {
        Connection connection = null;
        Statement statement = null;
        try {
            // Register JDBC driver.
            Class.forName(JDBC_DRIVER);

            // Open a connection.
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute Insert query.
            strategy.fetchResult(connection);

        } catch (SQLException ex) {
            // Handle errors for JDBC.
            logger.error(ex);
        } catch (Exception ex) {
            // Handle errors for Class.forName.
            logger.error(ex);
        } finally {
            // Finally block used to close resources.
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                logger.error(ex);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                logger.error(ex);
            }
        }
    }

//    public boolean executeUpdate(T model) {
//        Connection conn = null;
//        Statement statement = null;
//        try {
//            // Register JDBC driver
//            Class.forName(JDBC_DRIVER);
//
//            // Open a connection
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//
//            // Execute Insert query.
//            statement = conn.createStatement();
//            String sql;
//            sql = "SELECT id, first, last, age FROM Employees";
//            ResultSet rs = stmt.executeQuery(sql);
//
//            // Extract data from result set
//            while (rs.next()) {
//                //Retrieve by column name
//                int id = rs.getInt("id");
//                int age = rs.getInt("age");
//                String first = rs.getString("first");
//                String last = rs.getString("last");
//
//                //Display values
//                out.println("ID: " + id + "<br>");
//                out.println(", Age: " + age + "<br>");
//                out.println(", First: " + first + "<br>");
//                out.println(", Last: " + last + "<br>");
//            }
//            out.println("</body></html>");
//
//            // Clean-up environment
//            rs.close();
//            stmt.close();
//            conn.close();
//        } catch (SQLException se) {
//            //Handle errors for JDBC
//            se.printStackTrace();
//        } catch (Exception e) {
//            //Handle errors for Class.forName
//            e.printStackTrace();
//        } finally {
//            //finally block used to close resources
//            try {
//                if (stmt != null) {
//                    stmt.close();
//                }
//            } catch (SQLException se2) {
//            }// nothing we can do
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }//end finally try
//        } //end try
//    }
}
