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

public class
Main extends Application {

    private static Stage primaryStage;
    private Stage loginStage;
    private static FXMLLoader loader;

    /**
     * This calls the method for login
     * @param Stage
     */
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
    public static void checkInput(boolean value, Stage stage){
        if(value == true){
            mainWindow();
            stage.close();
        }
        else{
            JOptionPane.showMessageDialog(null, "Something went wrong");
        }
    }

    /**
     * This Method loades the Main Page, with which the User interacts
     * @param None
     */
    public static void mainWindow() {
        try {
            loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/MainWindowView.fxml"));
            BorderPane pane = loader.load();

            Scene scene = new Scene(pane);
            primaryStage.setTitle("NHPlus");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

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
    public static void main(String[] args) {
        launch(args);
    }
}