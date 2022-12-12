module pl.comp.viewproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires ModelProject;

    opens pl.comp.viewproject to javafx.fxml;
    exports pl.comp.viewproject;
}