package project.cc101_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import project.cc101_project.sql.CurrentUser;

import java.io.IOException;

public class HomepageController {
    @FXML private Label welcomeText;
    @FXML private Label IDLabel;
    @FXML private Label fullNameLabel;
    @FXML private Label IsStudentLabel;


    @FXML
    public void initialize() {
        // Load current user data
        CurrentUser currentUser = CurrentUser.getInstance();
        IDLabel.setText(String.valueOf(currentUser.getId()));
        fullNameLabel.setText(currentUser.getFullName());
        IsStudentLabel.setText(currentUser.isStudent() ? "Yes" : "No");
    }

    @FXML
    private void returnToolsBTN_clicked() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("returnTools.fxml"));
            Stage stage = (Stage) welcomeText.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void issueToolsBTN_clicked() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("issueTools.fxml"));
            Stage stage = (Stage) welcomeText.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void viewRecordsBTN_clicked() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("viewRecord.fxml"));
            Stage stage = (Stage) welcomeText.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void maintenanceAndReportBTN_clicked() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("maintenanceAndReport.fxml"));
            Stage stage = (Stage) welcomeText.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void manageToolsBTN_clicked() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("manageTools.fxml"));
            Stage stage = (Stage) welcomeText.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void logOutBTN_clicked() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("logIn.fxml"));
            Stage stage = (Stage) welcomeText.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}