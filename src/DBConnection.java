import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final String ConnectionString = "jdbc:sqlite:resources/db/EmerAgencyDB.db";


    private static DBConnection ourInstance = new DBConnection();

    public static DBConnection getInstance() {
        return ourInstance;
    }

    private DBConnection() {
    }



    public Connection getSQLLiteDBConnection() throws SQLException {
        Connection conn = null;

        if (conn == null || conn.isClosed()) {
            try {
                // db parameters
                // create a connection to the database
                conn = DriverManager.getConnection(ConnectionString);

            } catch (SQLException e) {
                e.getMessage();
            }
        }
        return conn;
    }
}
