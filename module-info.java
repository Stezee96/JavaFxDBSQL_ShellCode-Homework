module com.example.module03_basicgui_db_interface {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.module03_basicgui_db_interface to javafx.fxml;
    exports com.example.module03_basicgui_db_interface;
}