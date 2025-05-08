package project.cc101_project;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import project.cc101_project.sql.MaintenanceCRUD;
import project.cc101_project.sql.MaintenanceReport;
import project.cc101_project.sql.ToolsCRUD;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

    public class MaintenanceAndReportController implements Initializable {
        @FXML private TextField toolinfo_txtfld;
        @FXML private TextField problemDescription_txtfld;
        @FXML private TableView<MaintenanceReport> reportTableView;
        @FXML private TableColumn<MaintenanceReport, Integer> reportIDColumn, toolIDColumn;
        @FXML private TableColumn<MaintenanceReport, String> issueDescColumn, statusColumn;
        @FXML private TableColumn<MaintenanceReport, LocalDate> reportDateColumn;

        @Override
        public void initialize(URL url, ResourceBundle rb) {
            // Initialize Table Columns
            reportIDColumn.setCellValueFactory(new PropertyValueFactory<>("reportID"));
            toolIDColumn.setCellValueFactory(new PropertyValueFactory<>("toolID"));
            issueDescColumn.setCellValueFactory(new PropertyValueFactory<>("issueDescription"));
            reportDateColumn.setCellValueFactory(new PropertyValueFactory<>("reportDate"));
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

            refreshTableView();
        }

        @FXML
        private void handleSearch() {
            try {
                int toolID = Integer.parseInt(toolinfo_txtfld.getText());
                reportTableView.setItems(MaintenanceCRUD.getReportsByToolID(toolID));
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Tool ID!");
                alert.showAndWait();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @FXML
        private void handleSubmit() {
            try {
                int toolID = Integer.parseInt(toolinfo_txtfld.getText());
                String description = problemDescription_txtfld.getText();

                // Validate Tool ID exists
                if (!ToolsCRUD.doesToolExist(toolID)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Tool ID does not exist!");
                    alert.showAndWait();
                    return;
                }

                if (MaintenanceCRUD.insertReport(toolID, description)) {
                    Alert success = new Alert(Alert.AlertType.INFORMATION, "Report submitted!");
                    success.showAndWait();
                    refreshTableView();
                    clearFields();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Tool ID must be a number!");
                alert.showAndWait();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage());
                alert.showAndWait();
            }
        }

        private void refreshTableView() {
            try {
                reportTableView.setItems(MaintenanceCRUD.getAllReports());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private void clearFields() {
            toolinfo_txtfld.clear();
            problemDescription_txtfld.clear();
        }

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
