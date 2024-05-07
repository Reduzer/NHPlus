package de.hitec.nhplus.model;

import java.util.ArrayList;
import java.util.List;

public class Nurse extends Person{
    private long nPersonalNumber;
    private List<Permission> m_UserPermission = new ArrayList<Permission>(){};
    private List<Treatment> m_treatmentsAssigned = new ArrayList<Treatment>() {};
    private Treatment m_CurrentTreatment;
    public Nurse(String firstName, String surname) {
        super(firstName, surname);
    }

    public long getnPersonalNumber(){
        return nPersonalNumber;
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
}
