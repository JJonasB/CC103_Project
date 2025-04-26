package project.cc101_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import project.cc101_project.sql.UserCRUD;

import java.io.IOException;

public class SignUpController {
    @FXML Label welcomeText;
    @FXML TextField firstNameField;
    @FXML TextField lastNameField;
    @FXML TextField emailOrUsernameField;
    @FXML TextField phoneField;
    @FXML PasswordField passwordField;
    @FXML PasswordField confirmPasswordField;
    @FXML ToggleGroup Gender1;
    @FXML Text errorText;

    UserCRUD userCRUD = new UserCRUD();

    @FXML
    private void Click_BackBTN() {
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

    @FXML
    private void Click_SignUpBTN() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String emailUsername = emailOrUsernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String gender = getRadioAnswers(Gender1);
        String phoneNumber = phoneField.getText();

        System.out.println(gender);

        if (anyEmptyField(firstName, lastName, emailUsername, password, confirmPassword, gender, phoneNumber)) {
            errorText.setText("Please fill all fields");
            return;
        }
        if (!password.equals(confirmPassword)) {
            errorText.setText("Passwords do not match!");
            return;
        }

        if (userCRUD.createUser(emailUsername, password, firstName + " " + lastName, emailUsername, gender, Integer.parseInt(phoneNumber))) {
            errorText.setText("Registration Successful!");
        } else {
            errorText.setText("Registration Failed, Username may be taken");
        }
    }

    boolean anyEmptyField(String... fields) {
        for (String x : fields) {
            if (x.isEmpty()) return true;
        }
        return false;
    }
    String getRadioAnswers(ToggleGroup x) {
        RadioButton answer = (RadioButton) x.getSelectedToggle();
        if (answer != null) {
            return answer.getText();
        } else {
            return "";
        }
    }


}
