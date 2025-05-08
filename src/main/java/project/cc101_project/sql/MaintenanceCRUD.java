package project.cc101_project.sql;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaintenanceCRUD {
    public static ObservableList<MaintenanceReport> getAllReports() throws SQLException {
        ObservableList<MaintenanceReport> reports = FXCollections.observableArrayList();
        String sql = "SELECT * FROM maintenance_reports_tbl";

        try {
            Connection conn = ConnectDB.connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                reports.add(new MaintenanceReport(
                        resultSet.getInt("ReportID"),
                        resultSet.getInt("ToolID"),
                        resultSet.getString("IssueDescription"),
                        resultSet.getDate("ReportDate").toLocalDate(),
                        resultSet.getString("Status")
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return reports;
    }

    public static boolean insertReport(int toolID, String description) throws SQLException {
        String sql = "INSERT INTO maintenance_reports_tbl (ToolID, IssueDescription, ReportDate, Status) VALUES (?, ?, CURDATE(), 'pending')";
        try (Connection conn = ConnectDB.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, toolID);
            pstmt.setString(2, description);
            return pstmt.executeUpdate() > 0;
        }
    }


    public static ObservableList<MaintenanceReport> getReportsByToolID(int toolID) throws SQLException {
        ObservableList<MaintenanceReport> reports = FXCollections.observableArrayList();
        String sql = "SELECT * FROM maintenance_reports_tbl WHERE ToolID = ?";

        try (Connection conn = ConnectDB.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, toolID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                reports.add(new MaintenanceReport(
                        rs.getInt("ReportID"),
                        rs.getInt("ToolID"),
                        rs.getString("IssueDescription"),
                        rs.getDate("ReportDate").toLocalDate(),
                        rs.getString("Status")
                ));
            }
        }
        return reports;
    }
}
