package by.arabienko.onlineSchool.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectToDB {
    public static Connection getConnection() throws SQLException {
        /*ResourceBundle resource = ResourceBundle.getBundle("resources/property.db");
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");*/
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/db_schoolOnline", "admin", "root");
    }
}
