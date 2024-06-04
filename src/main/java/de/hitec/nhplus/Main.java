package de.hitec.nhplus;

import de.hitec.nhplus.datastorage.ConnectionBuilder;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    // Statische Variable für die primäre Bühne
    private static Stage primaryStage;
    // Variable für die Anmeldebühne
    private Stage loginStage;
    private FXMLLoader loader;
    private static boolean loggedIn = false;
    public static void setLoggedIn(boolean loggedInBool) {
        loggedIn = loggedInBool;
    }

    /**
     * This calls the method for login
     * @param Stage
     */
  
    // Überschriebene Startmethode der Anwendung
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        loader = new FXMLLoader();
        loginLoad();
    }

    /**
     * This method closes the Login Page and calls the mainWindow method
     * @param Boolean and Stage
     */

    // Methode zum Überprüfen der Benutzereingabe
    public void checkInput(boolean value, Stage stage){
        if(value == true){
            mainWindow();
            stage.close();
        }
        else{
            JOptionPane.showMessageDialog(null, "Etwas ist schiefgelaufen");
        }
    }


    /**
     * This Method loades the Main Page, with which the User interacts
     * @param None
     */
  
    // Methode zum Anzeigen des Hauptfensters
    public void mainWindow() {
        try {
            loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/MainWindowView.fxml"));
            BorderPane pane = loader.load();

            Scene scene = new Scene(pane);
            primaryStage.setTitle("NHPlus");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

            // Ereignisbehandlung für das Schließen des Hauptfensters
            primaryStage.setOnCloseRequest(event -> {
                ConnectionBuilder.closeConnection();
                Platform.exit();
                System.exit(0);
            });
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


    /**
     * This Method Loades the Login Page, from which the user has to sign into the Project
     * @param None
     */

    // Methode zum Laden der Anmeldeseite
    public void loginLoad(){
        try{
            loginStage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/de/hitec/nhplus/LoginView.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();

            stage.setTitle("Login NHPlus");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            // Ereignisbehandlung für das Schließen der Anmeldeseite
            loginStage.setOnCloseRequest(event -> {
                ConnectionBuilder.closeConnection();
                Platform.exit();
                System.exit(0);
            });
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    /**
     *  The Main method of the Project
     * @param args
     */

    // Hauptmethode zum Starten der Anwendung
    public static void main(String[] args) {
        launch(args);
    }
}
