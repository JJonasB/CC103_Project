package project.cc101_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.cc101_project.sql.CurrentUser;
import project.cc101_project.sql.IssueCRUD;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReturnToolsController {
    @FXML private TextField tools_txtfld;
    @FXML private TextField student_txtfld;
    @FXML private Label issueIDAnslabel, toolsNameAnslabel, studentNameAnslabel, issueDateAnsLabel, dueDateAnsLabel;
    @FXML private Button findDetailsBTN, returnToolsBTN;

    private int currentIssueID;

    @FXML
    private void initialize() {
        // Disable return button until details are loaded
        CurrentUser currentUser = CurrentUser.getInstance();
        student_txtfld.setDisable(true);
        student_txtfld.setText(String.valueOf(currentUser.getId()));
        returnToolsBTN.setDisable(true);
    }

    @FXML
    private void handleFindDetails() {
        try {
            int toolID = Integer.parseInt(tools_txtfld.getText());
            int studentID = Integer.parseInt(student_txtfld.getText());

            ResultSet rs = IssueCRUD.getIssueDetails(toolID, studentID);
            if (rs.next()) {
                currentIssueID = rs.getInt("IssueID");
                issueIDAnslabel.setText(String.valueOf(currentIssueID));
                toolsNameAnslabel.setText(rs.getString("ToolName"));
                studentNameAnslabel.setText(rs.getString("Full Name"));
                issueDateAnsLabel.setText(rs.getDate("IssueDate").toString());
                dueDateAnsLabel.setText(rs.getDate("DueDate").toString());
                returnToolsBTN.setDisable(false);
            } else {
                showAlert("No active issuance found for these IDs!");
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid ID format! Use numbers only.");
        } catch (SQLException e) {
            showAlert("Database error: " + e.getMessage());
        }
    }

    @FXML
    private void handleReturnTools() {
        try {
            boolean success = IssueCRUD.returnTool(currentIssueID);
            if (success) {
                showAlert("Tool returned successfully!");
                clearFields();
            } else {
                showAlert("Return failed!");
            }
        } catch (SQLException e) {
            showAlert("Database error: " + e.getMessage());
        }
    }

    private void clearFields() {
        tools_txtfld.clear();
        student_txtfld.clear();
        issueIDAnslabel.setText("");
        toolsNameAnslabel.setText("");
        studentNameAnslabel.setText("");
        issueDateAnsLabel.setText("");
        dueDateAnsLabel.setText("");
        returnToolsBTN.setDisable(true);
    }

    private void showAlert(String message) {
        new Alert(Alert.AlertType.WARNING, message).showAndWait();
    }
    @FXML
    private void Click_BackBTN() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            Stage stage = (Stage) student_txtfld.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // This will print the error details to the console
        }
    }
}
