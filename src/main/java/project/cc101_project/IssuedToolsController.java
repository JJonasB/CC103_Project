package project.cc101_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class IssuedToolsController {
    @FXML
    TextField toolName_Txtfld;
    @FXML
    TextField toolCondition_Txtfld;
    @FXML
    TextField toolQuantity_Txtfld;
    @FXML
    TextField toolLocation_Txtfld;

    @FXML
    private void Click_BackBTN() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            Stage stage = (Stage) toolCondition_Txtfld.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // This will print the error details to the console
        }


    }
}
