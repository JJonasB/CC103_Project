package project.cc101_project;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import project.cc101_project.sql.IssueCRUD;
import project.cc101_project.sql.IssuedToolRecord;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewRecordController implements Initializable {
    @FXML private TableView<IssuedToolRecord> recordsTable;
    @FXML private TableColumn<IssuedToolRecord, Integer> colIssueID;
    @FXML private TableColumn<IssuedToolRecord, String> colToolName;
    @FXML private TableColumn<IssuedToolRecord, String> colIssueDate;
    @FXML private TableColumn<IssuedToolRecord, String> colDueDate;
    @FXML private TableColumn<IssuedToolRecord, String> colStatus;
    @FXML private TableColumn<IssuedToolRecord, String> colStudentName;
    @FXML private TextField Issue_txtfld;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTableColumns();
        loadRecords();
        setupSearchFilter();
    }

    private void setupTableColumns() {
        colIssueID.setCellValueFactory(new PropertyValueFactory<>("issueID"));
        colToolName.setCellValueFactory(new PropertyValueFactory<>("toolName"));
        colIssueDate.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
    }

    private void loadRecords() {
        try {
            recordsTable.setItems(IssueCRUD.getAllIssuedRecords());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setupSearchFilter() {
        Issue_txtfld.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.isEmpty()) {
                loadRecords();
            } else {
                try {
                    recordsTable.setItems(IssueCRUD.getAllIssuedRecords().filtered(record ->
                            String.valueOf(record.getIssueID()).contains(newVal)
                    ));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @FXML
    private void Click_BackBTN() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            Stage stage = (Stage) Issue_txtfld.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // This will print the error details to the console
        }
    }
}