package com.mycompany.javafx_db_example;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class PrimaryController {

    @FXML
    private MenuBar menuBar;

    public void initialize() {
        createMenu();
    }

    private void createMenu() {
        Menu databaseMenu = new Menu("Database");

        MenuItem editMenuItem = new MenuItem("Edit User");
        // Add event handler for editMenuItem

        MenuItem deleteMenuItem = new MenuItem("Delete User");
        // Add event handler for deleteMenuItem

        databaseMenu.getItems().addAll(editMenuItem, deleteMenuItem);
        menuBar.getMenus().add(databaseMenu);
    }

    // Event handlers for menu items

    @FXML
    private void switchToSecondary() throws IOException, IOException {
        App.setRoot("secondary");
    }

    // Other methods for your primary view
}
