package de.hitec.nhplus.controller;

import de.hitec.nhplus.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import de.hitec.nhplus.LogIn.*;

public class LoginView {

    @FXML
    private BorderPane LogInPane;
    private String Name;
    private String Password;

    private hashing hash = new hashing();

    public LoginView(){

    }

    public void logIn(String name, String Password){
        this.Name = name;
        this.Password = Password;

        loginSequenz();
    }

    private void loginSequenz(){
        Main.setLoggedIn(false);
    }

    private void getHash(){
        this.Password = hash.getHash(Password); 
    }

}
