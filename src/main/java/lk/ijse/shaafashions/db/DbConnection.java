package lk.ijse.shaafashions.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    private final static String URL = "jdbc:mysql://localhost:3306/shaaFashions";
    private final static Properties property =  new Properties();

    static {
        property.setProperty("user", "root");
        property.setProperty("password", "1234");
    }
    private static DbConnection dbConnection;
    private Connection connection;

    private DbConnection() throws SQLException {
        connection = DriverManager.getConnection(URL, property);
    }
    private static DbConnection getInstance() throws SQLException {
        if (dbConnection == null){
            return dbConnection = new DbConnection();
        } else {
            return dbConnection ;
        }
    }
    public Connection getConnection(){
        return connection;
    }
}
