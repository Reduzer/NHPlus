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

    /**
     * Füllt die Liste mit SQL-Befehlen, die in den Benutzereingaben nicht erlaubt sein sollen.
     * Stellt eine Verbindung zur Datenbank her, die durch ConnectionBuilder bereitgestellt wird.
     *
     * @param event
     */

    @FXML
    private void logIn(ActionEvent event){
        loginSequenz();
    }

    private void loginSequenz(){
        boolean returnBoolean = false;

        getInput();
        System.out.println(Name + " " + Password);

        if(checkInput()){
            getHash();

            if(checkLogin()){
                System.out.println("Login Successful");
                Main.setLoggedIn(true);
                Stage stage = (Stage) PasswordField.getScene().getWindow();
                Main.checkInput(true, stage);
            }
            else{
                System.out.println("Input is wrong");
                JOptionPane.showMessageDialog(null, "Input is wrong");
            }
        }
        else{
            System.out.println("Input is not allowed");
        }
    }

    /**
     * Führt die notwendigen Schritte zur Anmeldung durch:
     * Holt die Benutzereingaben (getInput).
     * Überprüft die Eingaben auf unerlaubte Argumente (checkInput).
     * Überprüft die Anmeldedaten in der Datenbank (checkLogin).
     * Meldet den Benutzer an oder zeigt eine Fehlermeldung an.
     *
     */

    private void getInput(){
        this.Name = NameField.getText();
        this.Password = PasswordField.getText();
    }

    /**
     * Liest die Benutzereingaben aus den Textfeldern und speichert sie in den Variablen Name und Password.
     *
     */

    private void getHash(){
        this.Password = hash.getHash(Password);
        System.out.println(this.Password);
    }

    /**
     * Berechnet den Hash des Passworts. Diese Methode ist auskommentiert und wird derzeit nicht verwendet.
     *
     * @return
     */

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

    /**
     * Überprüft, ob die Benutzereingaben unerlaubte SQL-Befehle enthalten. Gibt false zurück, wenn unerlaubte Argumente gefunden werden.
     *
     * @return
     */

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
        return 0;
    }
}

/**
 * Führt eine SQL-Abfrage aus, um zu überprüfen, ob der Benutzername in der Datenbank vorhanden ist. Gibt true zurück, wenn ein Eintrag gefunden wird, ansonsten false.
 *
 */