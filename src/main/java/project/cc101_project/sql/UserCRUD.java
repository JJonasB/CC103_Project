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

    public boolean createUser(String username, String password, String fullName, String email, String gender, int phonenumber) {
        String sql = "INSERT INTO users_tbl (Username, Password, `Full Name`, Email, Gender, `Phone number`) VALUES (?, ?, ?, ?, ?, ?)";

        try (
                Connection conn = ConnectDB.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, fullName);
            pstmt.setString(4, email);
            pstmt.setString(5, gender);
            pstmt.setInt(6, phonenumber);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}