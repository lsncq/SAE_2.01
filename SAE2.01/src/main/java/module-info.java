module org.example.model {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens org.example.model to javafx.fxml;
    exports org.example.model;
    exports org.example.controller;
    opens org.example.controller to javafx.fxml;
    exports org.example.view;
    opens org.example.view to javafx.fxml;
}