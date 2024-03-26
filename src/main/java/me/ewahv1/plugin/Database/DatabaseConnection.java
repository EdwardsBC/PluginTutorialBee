package me.ewahv1.plugin.Database;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static java.sql.Connection connection;

    public static java.sql.Connection getConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bee_db", "root", "70284412B*");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
