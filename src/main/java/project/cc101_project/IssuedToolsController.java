package project.cc101_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import project.cc101_project.sql.IssueCRUD;
import project.cc101_project.sql.ToolsCRUD;
import project.cc101_project.sql.UserCRUD;
import project.cc101_project.sql.CurrentUser;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class IssuedToolsController {
    @FXML private TextField toolIdField;
    @FXML private TextField studentIdField;
    @FXML private DatePicker issueDatePicker;
    @FXML private DatePicker returnDatePicker;

    // Tool Info Answers
    @FXML private Text toolID_ans;
    @FXML private Text toolName_ans;
    @FXML private Text toolQuantity_ans;

    // Student Info Answers
    @FXML private Text studentID_ans;
    @FXML private Text studentName_ans;
    @FXML private Text studentCourse_ans;

    @FXML
    private void initialize() {
        // Auto-fetch tool info when Tool ID is entered
        toolIdField.textProperty().addListener((_, _, newVal) -> {
            if (!newVal.isEmpty()) {
                fetchToolInfo(Integer.parseInt(newVal));
            }
        });

        // Auto-fetch student info when Student ID is entered
        studentIdField.textProperty().addListener((_, _, newVal) -> {
            if (!newVal.isEmpty()) {
                try {
                    fetchStudentInfo(Integer.parseInt(newVal));
                } catch (NumberFormatException e) {
                    System.out.println("BRUV");
                }
            }
        });
        studentIdField.setDisable(true);
        CurrentUser currentUser = CurrentUser.getInstance();
        studentIdField.setText(String.valueOf(currentUser.getId()));
    }

    private void fetchToolInfo(int toolID) {
        try {
            ResultSet rs = ToolsCRUD.getToolByID(toolID);
            if (rs.next()) {
                toolID_ans.setText(rs.getString("ToolID"));
                toolName_ans.setText(rs.getString("ToolName"));
                toolQuantity_ans.setText(rs.getString("Quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fetchStudentInfo(int userID) {
        try {
            ResultSet rs = UserCRUD.getUserByID(userID);
            if (rs.next()) {
                studentID_ans.setText(rs.getString("ID"));
                studentName_ans.setText(rs.getString("Full Name"));
                studentCourse_ans.setText(rs.getString("YearAndCourse"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleIssueTool() {
        try {
            // Validate Tool ID and Student ID
            if (toolIdField.getText().isEmpty() || studentIdField.getText().isEmpty()) {
                showAlert("Tool ID and Student ID cannot be empty!");
                return;
            }
            int toolID = Integer.parseInt(toolIdField.getText());
            int studentID = Integer.parseInt(studentIdField.getText());

            // Validate Dates
            if (issueDatePicker.getValue() == null || returnDatePicker.getValue() == null) {
                showAlert("Please select both Issue Date and Return Date!");
                return;
            }
            LocalDate issueDate = issueDatePicker.getValue();
            LocalDate dueDate = returnDatePicker.getValue();

            // Check tool existence and availability
            if (!ToolsCRUD.doesToolExist(toolID)) {
                showAlert("Tool ID does not exist!");
                return;
            }
            if (!ToolsCRUD.isToolAvailable(toolID)) {
                showAlert("Tool is not available!");
                return;
            }

            // Check user existence
            if (!UserCRUD.doesUserExist(studentID)) {
                showAlert("User ID does not exist!");
                return;
            }

            // Issue the tool
            boolean success = IssueCRUD.issueTool(toolID, studentID, issueDate, dueDate);
            if (success) {
                showAlert("Tool issued successfully!");
                clearFields();
            } else {
                showAlert("Failed to issue tool.");
            }
        } catch (NumberFormatException e) {
            showAlert("Tool ID and Student ID must be numbers!");
        } catch (SQLException e) {
            showAlert("Database error: " + e.getMessage());
        } catch (Exception e) {
            showAlert("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        toolIdField.clear();
        studentIdField.clear();
        issueDatePicker.setValue(null);
        returnDatePicker.setValue(null);
        toolID_ans.setText("");
        toolName_ans.setText("");
        toolQuantity_ans.setText("");
        studentID_ans.setText("");
        studentName_ans.setText("");
        studentCourse_ans.setText("");
    }
    @FXML
    private void Click_BackBTN() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            Stage stage = (Stage) toolIdField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}