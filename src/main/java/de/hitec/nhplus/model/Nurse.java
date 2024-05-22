package de.hitec.nhplus.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse Nurse repräsentiert eine Krankenschwester oder einen Krankenpfleger.
 * Sie erbt von der Klasse Person und fügt spezifische Eigenschaften hinzu.
 */
public class Nurse extends Person {
    private long nPersonalNumber;
    private String Username;
    private String Password;
    private int Position;
    private List<Permission> m_UserPermission = new ArrayList<Permission>() {};
    private List<Treatment> m_treatmentsAssigned = new ArrayList<Treatment>() {};
    private Treatment m_CurrentTreatment;
    private String load;

    /**
     * Standardkonstruktor für Nurse
     * @param firstName Vorname der Krankenschwester/des Krankenpflegers
     * @param surname Nachname der Krankenschwester/des Krankenpflegers
     */
    public Nurse(String firstName, String surname) {
        super(firstName, surname);
    }

    /**
     * Überladener Konstruktor für Nurse
     * @param PersNumber Personalnummer der Krankenschwester/des Krankenpflegers
     * @param firstName Vorname der Krankenschwester/des Krankenpflegers
     * @param surname Nachname der Krankenschwester/des Krankenpflegers
     * @param UserName Benutzername der Krankenschwester/des Krankenpflegers
     * @param password Passwort der Krankenschwester/des Krankenpflegers
     * @param position Position der Krankenschwester/des Krankenpflegers im Krankenhaus
     */
    public Nurse(long PersNumber, String firstName, String surname, String UserName, String password, int position) {
        super(firstName, surname);
        this.nPersonalNumber = PersNumber;
        this.Username = UserName;
        this.Password = password;
        this.Position = position;
    }

    /**
     * Gibt die Personalnummer zurück
     * @return Personalnummer
     */
    public long getnPersonalNumber() {
        return nPersonalNumber;
    }

    /**
     * Gibt den Benutzernamen zurück
     * @return Benutzername
     */
    public String getUsername() {
        return this.Username;
    }

    /**
     * Gibt das Passwort zurück
     * @return Passwort
     */
    public String getPassword() {
        return this.Password;
    }

    /**
     * Gibt die Position zurück
     * @return Position
     */
    public int getPosition() {
        return this.Position;
    }

    /**
     * Gibt die aktuelle Behandlung zurück
     * @return aktuelle Behandlung
     */
    public Treatment getCurrentTreatment() {
        return m_CurrentTreatment;
    }

    /**
     * Fügt eine neue Behandlung zur Liste der zugewiesenen Behandlungen hinzu
     * @param newAddition neue Behandlung
     */
    public void addTreatment(Treatment newAddition) {
        m_treatmentsAssigned.add(newAddition);
    }

    /**
     * Gibt die Liste der zugewiesenen Behandlungen zurück
     * @return Liste der zugewiesenen Behandlungen
     */
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

    public String getLoad(){
        return load;
    }
}
