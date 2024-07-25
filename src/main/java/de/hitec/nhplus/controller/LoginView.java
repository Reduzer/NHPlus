package de.hitec.nhplus.controller;

import de.hitec.nhplus.Main;
import de.hitec.nhplus.datastorage.ConnectionBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import de.hitec.nhplus.LogIn.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginView
{
    @FXML
    public TextField PasswordField;
    @FXML
    public TextField NameField;
    @FXML
    private BorderPane LogInPane;

    //User Input
    private String sName;
    private String sPassword;

    @FXML
    private void logIn(ActionEvent event){
        loginSequenz();
    }

    private void loginSequenz(){
        boolean returnBoolean = false;

        getInput();

            if(checkLogin()){
                Stage stage = (Stage) PasswordField.getScene().getWindow();
            }
            else{
                JOptionPane.showMessageDialog(null, "Input is wrong");
            }
        }

    private void getInput(){
        this.sName = NameField.getText();
        this.sPassword = PasswordField.getText();
    }

    private boolean checkLogin(){
        PreparedStatement statement = null;
        try{
            final String sSQLCommand = "SELECT Password FROM nurse WHERE Fname = '" + Name + "'";
            statement = connection.prepareStatement(sSQLCommand);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return false;
    }
}
