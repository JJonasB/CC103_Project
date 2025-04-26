package project.cc101_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import project.cc101_project.sql.ToolsCRUD;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageToolsController {
    @FXML private TextField toolName_Txtfld;
    @FXML private ChoiceBox<String> toolCondition_ChoiceBox;
    @FXML private TextField toolQuantity_Txtfld;
    @FXML private TextField toolLocation_Txtfld;
    @FXML private TableView<Tool> toolsTable;

    // Initialize Table and ChoiceBox
    @FXML
    public void initialize() {
        setupConditionChoices();
        refreshTable();
        setupTableSelectionListener();
    }

    private void setupConditionChoices() {
        ObservableList<String> conditions = FXCollections.observableArrayList(
                "New",
                "Used - Good",
                "Used - Fair",
                "Damaged",
                "Needs Maintenance"
        );
        toolCondition_ChoiceBox.setItems(conditions);
    }

    private void setupTableSelectionListener() {
        toolsTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        populateFieldsWithSelectedTool(newSelection);
                    }
                }
        );
    }

    private void populateFieldsWithSelectedTool(Tool tool) {
        toolName_Txtfld.setText(tool.getName());
        toolCondition_ChoiceBox.setValue(tool.getCondition());
        toolQuantity_Txtfld.setText(String.valueOf(tool.getQuantity()));
        toolLocation_Txtfld.setText(tool.getLocation());
    }

    // Add Tool (updated for ChoiceBox)
    @FXML
    private void handleAddTool() {
        try {
            String name = toolName_Txtfld.getText().trim();
            String condition = toolCondition_ChoiceBox.getValue();
            int quantity = Integer.parseInt(toolQuantity_Txtfld.getText().trim());
            String location = toolLocation_Txtfld.getText().trim();

            if (name.isEmpty() || condition == null || location.isEmpty()) {
                showAlert("Error", "All fields are required!");
                return;
            }

            if (ToolsCRUD.addTool(name, condition, quantity, location)) {
                refreshTable();
                clearFields();
            } else {
                showAlert("Error", "Failed to add tool!");
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Quantity must be a number!");
        }
    }

    // Update Tool (updated for ChoiceBox)
    @FXML
    private void handleUpdateTool() {
        Tool selected = toolsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Error", "No tool selected!");
            return;
        }

        try {
            String name = toolName_Txtfld.getText().trim();
            String condition = toolCondition_ChoiceBox.getValue();
            System.out.println(toolCondition_ChoiceBox.getValue());
            int quantity = Integer.parseInt(toolQuantity_Txtfld.getText().trim());
            String location = toolLocation_Txtfld.getText().trim();

            if (ToolsCRUD.updateTool(selected.getToolID(), name, condition, quantity, location)) {
                refreshTable();
                clearFields();
            } else {
                showAlert("Error", "Update failed!");
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Quantity must be a number!");
        }
    }


    // Delete Tool
    @FXML
    private void handleDeleteTool() {
        Tool selected = toolsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Error", "No tool selected!");
            return;
        }

        if (ToolsCRUD.deleteTool(selected.getToolID())) {
            refreshTable();
            clearFields();
        } else {
            showAlert("Error", "Deletion failed!");
        }
    }

    // Helper Methods
    private void refreshTable() {
        ObservableList<Tool> tools = FXCollections.observableArrayList();
        try (ResultSet rs = ToolsCRUD.getAllTools()) {
            while (rs != null && rs.next()) {
                tools.add(new Tool(
                        rs.getInt("ToolID"),
                        rs.getString("ToolName"),
                        rs.getString("Condition"),
                        rs.getInt("Quantity"),
                        rs.getString("Location"),
                        rs.getString("Status")
                ));
            }
            toolsTable.setItems(tools);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        toolName_Txtfld.clear();
        toolCondition_ChoiceBox.setValue(null);
        toolQuantity_Txtfld.clear();
        toolLocation_Txtfld.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Tool Model Class
    public static class Tool {
        private final int toolID;
        private final String name;
        private final String condition;
        private final int quantity;
        private final String location;
        private final String status;

        public Tool(int toolID, String name, String condition, int quantity, String location, String status) {
            this.toolID = toolID;
            this.name = name;
            this.condition = condition;
            this.quantity = quantity;
            this.location = location;
            this.status = status;
        }

        // Getters (required for TableView)
        public int getToolID() { return toolID; }
        public String getName() { return name; }
        public String getCondition() { return condition; }
        public int getQuantity() { return quantity; }
        public String getLocation() { return location; }
        public String getStatus() { return status; }
    }


    @FXML
    private void Click_BackBTN() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            Stage stage = (Stage) toolName_Txtfld.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // This will print the error details to the console
        }
    }
}