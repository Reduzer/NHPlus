package de.hitec.nhplus.LogIn;

import de.hitec.nhplus.datastorage.ConnectionBuilder;

import java.sql.Connection;
import java.util.ArrayList;

public class LoginHandler {

    private String sName, sPassword;

    private hashing hashing = new hashing();
    private Connection m_Connection;

    private ArrayList<String> IllegalArguments = new ArrayList<String>();


    public LoginHandler(){
        IllegalArguments.add("DROP");
        IllegalArguments.add("INSERT");
        IllegalArguments.add("DElETE");
        IllegalArguments.add("UPDATE");
        IllegalArguments.add("CREATE");
        IllegalArguments.add(String.valueOf('"'));

        this.m_Connection = ConnectionBuilder.getConnection();
    }

    public boolean LoginUser(String sInputName, String sInputPassword){

        this.sName = sInputName;
        this.sPassword = sInputPassword;

        checkForIllegalArguments();

        return false;
    }

    private boolean checkForIllegalArguments(){
        for(String s : IllegalArguments){
            if(sName.contains(s)){
                return false;
            }
            if(sPassword.contains(s)){
                return false;
            }
            else{
                continue;
            }
        }
        return true;
    }
}
