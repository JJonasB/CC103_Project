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
    @FXML private ToggleGroup isStudent;
    @FXML private TextField yearAndCourse;
    @FXML private RadioButton yesRadio;
    @FXML private RadioButton noRadio;

    UserCRUD userCRUD = new UserCRUD();

    @FXML
    public void initialize() {
        // Hide year/course initially
        yearAndCourse.setVisible(false);

        // Add listener to student toggle group
        isStudent.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            boolean isStudent = yesRadio.isSelected();
            yearAndCourse.setVisible(isStudent);
            if (!isStudent) yearAndCourse.clear();
        });
    }

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
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String emailUsername = emailOrUsernameField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();
        String gender = getRadioAnswers(Gender1);
        String phoneNumber = phoneField.getText().trim();
        boolean isStudent = yesRadio.isSelected();
        String yearCourse = yearAndCourse.getText().trim();

        // Validation
        if (anyEmptyField(firstName, lastName, emailUsername, password, confirmPassword, gender, phoneNumber)) {
            errorText.setText("Please fill all required fields!");
            return;
        }

        if (isStudent && yearCourse.isEmpty()) {
            errorText.setText("Year & Course is required for students!");
            return;
        }

        if (!password.equals(confirmPassword)) {
            errorText.setText("Passwords do not match!");
            return;
        }

        // Create user with student info
        if (userCRUD.createUser(emailUsername, password,
                firstName + " " + lastName, emailUsername, gender,
                Integer.parseInt(phoneNumber), isStudent, yearCourse)) {
            errorText.setText("Registration Successful!");
            clearFields();
        } else {
            errorText.setText("Registration Failed, Username may be taken");
        }
    }

    private void clearFields() {
        // Add these lines
        yesRadio.setSelected(false);
        noRadio.setSelected(false);
        yearAndCourse.clear();
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
