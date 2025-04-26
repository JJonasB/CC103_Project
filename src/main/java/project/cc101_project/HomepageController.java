package project.cc101_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HomepageController {
    @FXML
    private Label welcomeText;

    @FXML
    private void returnToolsBTN_clicked() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("returnTools.fxml"));
            Stage stage = (Stage) welcomeText.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // This will print the error details to the console
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