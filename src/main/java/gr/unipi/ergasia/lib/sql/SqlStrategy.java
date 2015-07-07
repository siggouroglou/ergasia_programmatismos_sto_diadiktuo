package gr.unipi.ergasia.lib.sql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author siggouroglou
 */
public interface SqlStrategy<T> {

    public void fetchResult(Connection connection) throws SQLException;
}
