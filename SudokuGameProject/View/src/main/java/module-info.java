module pl.comp.viewproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.comp.viewproject to javafx.fxml;
    exports pl.comp.viewproject;
}