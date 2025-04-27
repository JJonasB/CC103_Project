package project.cc101_project.sql;

import java.sql.*;
import java.time.LocalDate;

public class IssueCRUD {
    public static boolean issueTool(int toolId, int userID, LocalDate issueDate, LocalDate dueDate) throws SQLException {
        Connection conn = ConnectDB.connect();
        String insertSql = "INSERT INTO issued_tools_tbl (ToolID, UserID, IssueDate, DueDate) VALUES (?, ?, ?, ?)";
        String updateSql = "UPDATE tools_tbl SET Status = 'Issued' WHERE ToolID = ?";

        try {
            conn.setAutoCommit(false);

            // Insert issuance record
            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                pstmt.setInt(1, toolId);
                pstmt.setInt(2, userID);
                pstmt.setDate(3, Date.valueOf(issueDate));
                pstmt.setDate(4, Date.valueOf(dueDate));
                pstmt.executeUpdate();
            }

            // Update tool status
            try (PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
                pstmt.setInt(1, toolId);
                pstmt.executeUpdate();
            }
            conn.commit();
            return true;
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}