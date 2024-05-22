package de.hitec.nhplus.model;

import de.hitec.nhplus.utils.DateConverter;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Patienten leben in einem Pflegeheim und werden von Krankenschwestern betreut.
 */
public class Patient extends Person {
    private SimpleLongProperty pid;  // Einzigartige Patienten-ID
    private final SimpleStringProperty dateOfBirth;  // Geburtsdatum des Patienten
    private final SimpleStringProperty careLevel;  // Pflegestufe des Patienten
    private final SimpleStringProperty roomNumber;  // Zimmernummer des Patienten
    private final List<Treatment> allTreatments = new ArrayList<>();  // Liste aller dem Patienten zugewiesenen Behandlungen

    /**
     * Konstruktor zur Initialisierung eines Patientenobjekts mit den angegebenen Parametern.
     * Verwende diesen Konstruktor für Objekte, die noch nicht persistiert sind, da sie keine Patienten-ID (pid) haben.
     *
     * @param firstName Vorname des Patienten.
     * @param surname Nachname des Patienten.
     * @param dateOfBirth Geburtsdatum des Patienten.
     * @param careLevel Pflegestufe des Patienten.
     * @param roomNumber Zimmernummer des Patienten.
     */
    public Patient(String firstName, String surname, LocalDate dateOfBirth, String careLevel, String roomNumber) {
        super(firstName, surname);
        this.dateOfBirth = new SimpleStringProperty(DateConverter.convertLocalDateToString(dateOfBirth));
        this.careLevel = new SimpleStringProperty(careLevel);
        this.roomNumber = new SimpleStringProperty(roomNumber);
    }

    /**
     * Konstruktor zur Initialisierung eines Patientenobjekts mit den angegebenen Parametern.
     * Verwende diesen Konstruktor für Objekte, die bereits persistiert sind und eine Patienten-ID (pid) haben.
     *
     * @param pid Patienten-ID.
     * @param firstName Vorname des Patienten.
     * @param surname Nachname des Patienten.
     * @param dateOfBirth Geburtsdatum des Patienten.
     * @param careLevel Pflegestufe des Patienten.
     * @param roomNumber Zimmernummer des Patienten.
     */
    public Patient(int pid, String firstName, String surname, LocalDate dateOfBirth, String careLevel, String roomNumber) {
        super(firstName, surname);
        this.pid = new SimpleLongProperty(pid);
        this.dateOfBirth = new SimpleStringProperty(DateConverter.convertLocalDateToString(dateOfBirth));
        this.careLevel = new SimpleStringProperty(careLevel);
        this.roomNumber = new SimpleStringProperty(roomNumber);
    }

    /**
     * Gibt die Patienten-ID zurück.
     *
     * @return Patienten-ID.
     */
    public long getPid() {
        return pid.get();
    }

    /**
     * Property zur Bindung der Patienten-ID.
     *
     * @return Property der Patienten-ID.
     */
    public SimpleLongProperty pidProperty() {
        return pid;
    }

    /**
     * Gibt das Geburtsdatum des Patienten zurück.
     *
     * @return Geburtsdatum.
     */
    public String getDateOfBirth() {
        return dateOfBirth.get();
    }

    /**
     * Property zur Bindung des Geburtsdatums.
     *
     * @return Property des Geburtsdatums.
     */
    public SimpleStringProperty dateOfBirthProperty() {
        return dateOfBirth;
    }

    /**
     * Setzt das Geburtsdatum des Patienten.
     *
     * @param dateOfBirth Geburtsdatum im Format YYYY-MM-DD.
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

    /**
     * Gibt die Pflegestufe des Patienten zurück.
     *
     * @return Pflegestufe.
     */
    public String getCareLevel() {
        return careLevel.get();
    }

    /**
     * Property zur Bindung der Pflegestufe.
     *
     * @return Property der Pflegestufe.
     */
    public SimpleStringProperty careLevelProperty() {
        return careLevel;
    }

    /**
     * Setzt die Pflegestufe des Patienten.
     *
     * @param careLevel Neue Pflegestufe.
     */
    public void setCareLevel(String careLevel) {
        this.careLevel.set(careLevel);
    }

    /**
     * Gibt die Zimmernummer des Patienten zurück.
     *
     * @return Zimmernummer.
     */
    public String getRoomNumber() {
        return roomNumber.get();
    }

    /**
     * Property zur Bindung der Zimmernummer.
     *
     * @return Property der Zimmernummer.
     */
    public SimpleStringProperty roomNumberProperty() {
        return roomNumber;
    }

    /**
     * Setzt die Zimmernummer des Patienten.
     *
     * @param roomNumber Neue Zimmernummer.
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber.set(roomNumber);
    }

    /**
     * Fügt eine Behandlung zur Liste der Behandlungen hinzu, falls diese noch nicht vorhanden ist.
     *
     * @param treatment Behandlung, die hinzugefügt werden soll.
     * @return False, wenn die Behandlung bereits in der Liste enthalten ist, sonst true.
     */
    public boolean add(Treatment treatment) {
        if (this.allTreatments.contains(treatment)) {
            return false;
        }
        this.allTreatments.add(treatment);
        return true;
    }

    /**
     * Gibt eine String-Repräsentation des Patienten zurück.
     *
     * @return String-Repräsentation des Patienten.
     */
    @Override
    public String toString() {
        return "Patient" +
                "\nMNID: " + this.pid +
                "\nFirstname: " + this.getFirstName() +
                "\nSurname: " + this.getSurname() +
                "\nBirthday: " + this.dateOfBirth +
                "\nCarelevel: " + this.careLevel +
                "\nRoomnumber: " + this.roomNumber +
                "\n";
    }
}
