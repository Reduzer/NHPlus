package de.hitec.nhplus.model;

import java.util.ArrayList;
import java.util.List;

public class Nurse extends Person {

    private long nPersonalNumber;
    private int Position;

    private String Username;
    private String Password;
    private String load;

    private List<Treatment> m_treatmentsAssigned = new ArrayList<Treatment>() {};

    private Treatment m_CurrentTreatment;

    public Nurse(String firstName, String surname) {
        super(firstName, surname);
    }

    public Nurse(long PersNumber, String firstName, String surname, String UserName, String Password, int position) {
        super(firstName, surname);
        this.nPersonalNumber = PersNumber;
        this.Username = UserName;
        this.Password = Password;
        this.Position = position;
    }

    public long getnPersonalNumber() {
        return nPersonalNumber;
    }

    public String getUsername() {
        return this.Username;
    }

    public int getPosition() {
        return this.Position;
    }

    public String getLoad(){
        calcLoad();

        return load;
    }

    public Treatment getCurrentTreatment() {
        return m_CurrentTreatment;
    }

    public void addTreatment(Treatment newAddition) {
        m_treatmentsAssigned.add(newAddition);
    }

    public List<Treatment> getTreatmentsAssigned() {
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

    public String getPassword() {
        return Password;
    }
}
