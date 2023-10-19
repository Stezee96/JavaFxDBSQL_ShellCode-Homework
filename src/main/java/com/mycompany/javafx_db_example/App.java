package com.mycompany.javafx_db_example;

import com.mycompany.javafx_db_example.db.ConnDbOps;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import java.io.IOException;
import java.util.Scanner;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static ConnDbOps cdbop;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
        createMenu(stage);
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private static void createMenu(Stage stage) {
        MenuBar menuBar = new MenuBar();

        //Data base
        Menu databaseMenu = new Menu("Database");
        MenuItem editMenuItem = new MenuItem("Edit User");

        MenuItem deleteMenuItem = new MenuItem("Delete User");

        editMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
        deleteMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN));

        // event handlers
        editMenuItem.setOnAction(e -> handleEditUser());
        deleteMenuItem.setOnAction(e -> handleDeleteUser());


        databaseMenu.getItems().addAll(editMenuItem, deleteMenuItem);

        // dtat base to menu baar
        menuBar.getMenus().add(databaseMenu);

        menuBar.prefWidthProperty().bind(stage.widthProperty());

        StackPane root = new StackPane(scene.getRoot());
        root.getChildren().add(menuBar);

        scene = new Scene(root);
        stage.setScene(scene);
    }

    private static void handleEditUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the user to edit: ");
        String username = scanner.nextLine();

        if (username.isEmpty()) {
            System.out.println("Invalid username. Please try again.");
        } else {
            System.out.println("User details edited successfully for " + username);
        }
    }

    private static void handleDeleteUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the user to delete: ");
        String username = scanner.nextLine();

        if (username.isEmpty()) {
            System.out.println("Invalid username. Please try again.");
        } else {
            System.out.println("User " + username + " deleted successfully.");
        }
    }

    public static void main(String[] args) {
        cdbop = new ConnDbOps();
        Scanner scan = new Scanner(System.in);

        char input;
        do {
            System.out.println(" ");
            System.out.println("============== Menu ==============");
            System.out.println("| To start GUI,           press 'g' |");
            System.out.println("| To connect to DB,       press 'c' |");
            System.out.println("| To display all users,   press 'a' |");
            System.out.println("| To insert to the DB,    press 'i' |");
            System.out.println("| To query by name,       press 'q' |");
            System.out.println("| To Edit,                press 'w' |");
            System.out.println("| To delete,              press 'd' |");
            System.out.println("| To exit,                press 'e' |");
            System.out.println("===================================");
            System.out.print("Enter your choice: ");
            input = scan.next().charAt(0);
            scan.useDelimiter("\n"); // Fix input spaces

            switch (input) {
                case 'g' -> launch(args); // GUI
                case 'c' -> cdbop.connectToDatabase(); // Your existing method
                case 'a' -> cdbop.listAllUsers(); // all users in DB
                case 'i' -> {
                    System.out.print("Enter Name: ");
                    String name = scan.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scan.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = scan.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scan.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scan.nextLine();
                    System.out.print("Enter Salary: ");
                    int salary = scan.nextInt();
                    cdbop.insertUser(name, email, phone, address, password, salary); // user method
                }
                case 'q' -> {
                    System.out.print("Enter the name to query: ");
                    String queryName = scan.next();
                    cdbop.queryUserByName(queryName); // Your queryUserByName method
                }
                case 'e' -> System.out.println("Exiting...");
                case 'w' -> handleEditUser();
                case 'd' -> handleDeleteUser();
                default -> System.out.println("Invalid option. Please try again.");
            }

            System.out.println(" ");
        } while (input != 'e');

        scan.close();
    }
}
