package project.cc101_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import project.cc101_project.sql.UserCRUD;

import java.io.IOException;

public class LogInController {
    @FXML
    Label welcomeText;
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    Text errorText;

    UserCRUD userCRUD = new UserCRUD();

    @FXML
    private void Click_SignInBTN() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            errorText.setText("Username and Password must not be empty!");
        }

        if (userCRUD.validateUser(username, password)) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
                Stage stage = (Stage) welcomeText.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            errorText.setText("Wrong Credentials!");
        }
    }

    @FXML
    private void Click_SignUpBTN() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
            Stage stage = (Stage) welcomeText.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
