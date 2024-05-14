package de.hitec.nhplus.LogIn;

import java.security.MessageDigest;

public class hashing {
    
    

    //Hashes the input and return the hashed string
    public String getHash(String input){
        String returnString = null;
        try{
            MessageDigest msgDigest = MessageDigest.getInstance("SHA3-256");
            msgDigest.update(input.getBytes());  
            returnString = new String(msgDigest.digest()); 
        }
        catch(Exception e){
            e.setStackTrace(null);
        }
             
        return returnString;
    }

}
