package de.hitec.nhplus;

import de.hitec.nhplus.datastorage.ConnectionBuilder;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    private Stage primaryStage;
    private FXMLLoader loader;
    private static boolean loggedIn = false;
    public static void setLoggedIn(boolean loggedInBool){
        loggedIn = loggedInBool;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        loginLoad();
        if(loggedIn){
            mainWindow();
        }
    }

    public void mainWindow() {
      //  loginLoad();

            try {
                loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/MainWindowView.fxml"));
                BorderPane pane = loader.load();

                Scene scene = new Scene(pane);
                this.primaryStage.setTitle("NHPlus");
                this.primaryStage.setScene(scene);
                this.primaryStage.setResizable(false);
                this.primaryStage.show();

                this.primaryStage.setOnCloseRequest(event -> {
                    ConnectionBuilder.closeConnection();
                    Platform.exit();
                    System.exit(0);
                });
            } catch (IOException exception) {
                exception.printStackTrace();
            }
    }

    public void loginLoad(){
        try{
            Stage loginStage = new Stage();

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


    public static void main(String[] args) {
        launch(args);
    }
}