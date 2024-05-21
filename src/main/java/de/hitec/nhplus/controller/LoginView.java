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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginView {
    @FXML
    public TextField PasswordField;
    @FXML
    public TextField NameField;
    
    @FXML
    private BorderPane LogInPane;

    private String Name;
    private String Password;

    private Connection connection;
    private hashing hash = new hashing();
    private ArrayList<String> IllegalArguments = new ArrayList<String>();

    public LoginView(){
        IllegalArguments.add("DROP");
        IllegalArguments.add("INSERT");
        IllegalArguments.add("DElETE");
        IllegalArguments.add("UPDATE");
        IllegalArguments.add("CREATE");
        IllegalArguments.add(String.valueOf('"'));

        this.connection = ConnectionBuilder.getConnection();
    }

    @FXML
    private void logIn(ActionEvent event){
        loginSequenz();
    }

    private void loginSequenz(){
        boolean returnBoolean = false;

        getInput();
        System.out.println(Name + " " + Password);

        if(checkInput()){
            //getHash();

            if(checkLogin()){
                System.out.println("Login Successful");
                Main.setPermissions(getPermissions());
                Main.setLoggedIn(true);

                Stage stage = (Stage) PasswordField.getScene().getWindow();
                stage.close();
            }
            else{
                System.out.println("Input is wrong");
            }
        }
        else{
            System.out.println("Input is not allowed");
        }
    }

    private void getInput(){
        this.Name = NameField.getText();
        this.Password = PasswordField.getText();
    }

    private void getHash(){
        this.Password = hash.getHash(Password);
        System.out.println(this.Password);
    }

    private boolean checkInput() {
        for(int i = 0; i < IllegalArguments.size(); i++){
            if(Name.contains(IllegalArguments.get(i))){
                return false;
            }
            else{
                continue;
            }
        }
        return true;
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

    private int getPermissions(){
        PreparedStatement statement = null;

        try{
            final String sSqlStatement = "";
            statement = connection.prepareStatement(sSqlStatement);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                //WIP get number missing
                return 1;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return 0;
        }
    }
}
