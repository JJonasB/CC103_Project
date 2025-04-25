package project.cc101_project.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCRUD {

    public boolean validateUser(String username, String password) {
        String sql = "SELECT * FROM users_tbl WHERE Username = ? AND Password = ?";

        try (
                Connection conn = ConnectDB.connect(); // Use existing ConnectDB class
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Returns true if credentials match
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            // Catch potential runtime exceptions from ConnectDB
            e.printStackTrace();
            return false;
        }
    }
}