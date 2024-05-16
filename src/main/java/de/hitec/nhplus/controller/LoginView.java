package de.hitec.nhplus.controller;

import de.hitec.nhplus.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import de.hitec.nhplus.LogIn.*;

public class LoginView {

    public TextField PasswordField;
    public TextField NameField;
    
    @FXML
    private BorderPane LogInPane;

    private String Name;
    private String Password;

    private hashing hash = new hashing();

    public LoginView(){

    }

    @FXML
    private void logIn(ActionEvent event){
        loginSequenz();
    }

    private void loginSequenz(){
        boolean returnBoolean = false;

        getInput();
        checkInput();
        getHash();

        //if(checkLogin()){

        //}

        Main.setLoggedIn(true);


    }

    private void getInput(){
        this.Name = NameField.getText();
        this.Password = PasswordField.getText();
    }

    private void getHash(){
        this.Password = hash.getHash(Password); 
    }

    private void checkInput(){

    }

    private boolean checkLogin(){
        return false;
    }
}
