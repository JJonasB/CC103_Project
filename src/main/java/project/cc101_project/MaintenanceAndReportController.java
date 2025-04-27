package project.cc101_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MaintenanceAndReportController {

    @FXML
    TextField toolinfo_txtfld;

    @FXML
    private void Click_BackBTN() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            Stage stage = (Stage) toolinfo_txtfld.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
