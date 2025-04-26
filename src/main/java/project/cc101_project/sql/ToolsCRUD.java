package project.cc101_project.sql;

import java.sql.*;

public class ToolsCRUD {
    static final Connection conn = ConnectDB.connect();

    // Create (Add Tool)
    public static boolean addTool(String toolName, String condition, int quantity, String location) {
        String sql = "INSERT INTO tools_tbl(ToolName, `Condition`, Quantity, Location) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, toolName);
            stmt.setString(2, condition);
            stmt.setInt(3, quantity);
            stmt.setString(4, location);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
                throw new RuntimeException(e);
        }
    }

    // Read (Getting all tools)
    public static ResultSet getAllTools() {
        String sql = "SELECT * FROM tools_tbl";
        try {
            Statement stmt = conn.prepareStatement(sql);
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update (Modify Tool)
    public static boolean updateTool(int ToolID, String toolName, String condition, int quantity, String location) {
        String sql = "UPDATE tools_tbl SET ToolName = ?, `Condition` = ?, Quantity = ?, Location = ? WHERE ToolID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, toolName);
            pstmt.setString(2, condition);
            pstmt.setInt(3, quantity);
            pstmt.setString(4, location);
            pstmt.setInt(5, ToolID);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Delete (Remove Tool)
    public static boolean deleteTool(int toolID) {
        String sql = "DELETE FROM tools_tbl WHERE toolID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, toolID);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
