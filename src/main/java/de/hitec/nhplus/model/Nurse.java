package de.hitec.nhplus.model;

import java.util.ArrayList;
import java.util.List;

public class Nurse extends Person{
    private long nPersonalNumber;
    private String Username;
    private String Password;
    private int Position;
    private List<Permission> m_UserPermission = new ArrayList<Permission>(){};
    private List<Treatment> m_treatmentsAssigned = new ArrayList<Treatment>() {};
    private Treatment m_CurrentTreatment;
    private String load;

    /**
     * Default Constructor for Nurse
     * @param firstName
     * @param surname
     */
    public Nurse(String firstName, String surname) {
        super(firstName, surname);
    }

    public Nurse(long PersNumber,String firstName, String surname, String UserName, String password, int position){
        super(firstName, surname);
        nPersonalNumber = PersNumber;
        Username = UserName;
        Password = password;
        Position = position;
    }

    public long getnPersonalNumber(){
        return nPersonalNumber;
    }

    public String getUsername(){
        return this.Username;
    }

    public String getPassword(){
        return this.Password;
    }

    public int getPosition(){
        return this.Position;
    }

    public Treatment getCurrentTreatment(){
        return m_CurrentTreatment;
    }
    public void addTreatment(Treatment newAddition){
        m_treatmentsAssigned.add(newAddition);
    }
    public List<Treatment> getTreatmentsAssigned(){
        return m_treatmentsAssigned;
    }

    private void calcLoad(){
        int size = m_treatmentsAssigned.size();
        
        if(size < 3){
            load = "light";
        }
        else{
            if(size < 5){
                load = "medium";
            }
            else{
                if(size < 10){
                    load = "heavy";
                }
                else{
                    load = "extreme";
                }
            }
        }
    }

    public String getLoad(){
        return load;
    }


}
