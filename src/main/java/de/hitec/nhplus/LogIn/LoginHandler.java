package de.hitec.nhplus.LogIn;

import de.hitec.nhplus.datastorage.ConnectionBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginHandler {

    private String sName, sPassword;

    private hashing m_Hashing = new hashing();
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

        if(!checkForIllegalArguments()){
            return false;
        }

        //Not used for debug purposes
        //sPassword = m_Hashing.getHash(sPassword);

        if(checkLogin()){
            return true;
        }
        else
        {
            return false;
        }
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

    private boolean checkLogin(){
        PreparedStatement statement = null;
        try{
            final String sSQLCommand = "SELECT Password FROM nurse WHERE Fname = '" + sName + "'";
            statement = m_Connection.prepareStatement(sSQLCommand);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }

        return false;
    }
}
