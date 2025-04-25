module project.cc101_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens project.cc101_project to javafx.fxml;
    exports project.cc101_project;
}