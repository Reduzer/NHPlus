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

    private static Stage m_PrimaryStage;
    private static FXMLLoader m_Loader;

    @Override
    public void start(Stage givenStage) {
        m_PrimaryStage = givenStage;
        m_Loader = new FXMLLoader();
        loginLoad();
    }

    public static void mainWindow() {
        try {
            m_Loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/MainWindowView.fxml"));
            BorderPane pane = m_Loader.load();

            Scene scene = new Scene(pane);
            m_PrimaryStage.setTitle("NHPlus");
            m_PrimaryStage.setScene(scene);
            m_PrimaryStage.setResizable(false);
            m_PrimaryStage.show();

            m_PrimaryStage.setOnCloseRequest(event -> {
                ConnectionBuilder.closeConnection();
                Platform.exit();
                System.exit(0);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loginLoad(){
        try{
            Stage m_LoginStage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/de/hitec/nhplus/LoginView.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();

            stage.setTitle("Login NHPlus");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            m_LoginStage.setOnCloseRequest(event -> {
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
