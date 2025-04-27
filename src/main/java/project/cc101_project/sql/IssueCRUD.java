package project.cc101_project.sql;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class IssueCRUD {

    public static boolean issueTool(int toolId, int userID, LocalDate issueDate, LocalDate dueDate) throws SQLException {
        Connection conn = ConnectDB.connect();
        String insertSql = "INSERT INTO issued_tools_tbl (ToolID, UserID, IssueDate, DueDate) VALUES (?, ?, ?, ?)";
        String updateSql = "UPDATE tools_tbl SET Status = 'Issued' WHERE ToolID = ?";

        try {
            conn.setAutoCommit(false);

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

    public static ObservableList<IssuedToolRecord> getAllIssuedRecords() throws SQLException {
        ObservableList<IssuedToolRecord> records = FXCollections.observableArrayList();

        String sql = "SELECT i.IssueID, t.ToolName, i.IssueDate, i.DueDate, i.Status, u.`Full Name` " +
                "FROM issued_tools_tbl i " +
                "JOIN tools_tbl t ON i.ToolID = t.ToolID " +
                "JOIN users_tbl u ON i.UserID = u.ID";

        try (Connection conn = ConnectDB.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                records.add(new IssuedToolRecord(
                        rs.getInt("IssueID"),
                        rs.getString("ToolName"),
                        rs.getDate("IssueDate").toLocalDate(),
                        rs.getDate("DueDate").toLocalDate(),
                        rs.getString("Status"),
                        rs.getString("Full Name")
                ));
            }
        }
        return records;
    }
}