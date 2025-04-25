package project.cc101_project.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    public static Connection connect() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/cc103_db", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
