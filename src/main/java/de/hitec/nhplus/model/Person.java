package de.hitec.nhplus.model;

import javafx.beans.property.SimpleStringProperty;

public abstract class Person {
    // Vorname der Person, als SimpleStringProperty für Datenbindung
    private final SimpleStringProperty firstName;
    // Nachname der Person, als SimpleStringProperty für Datenbindung
    private final SimpleStringProperty surname;

    // Konstruktor zur Initialisierung des Vornamens und Nachnamens
    public Person(String firstName, String surname) {
        this.firstName = new SimpleStringProperty(firstName);
        this.surname = new SimpleStringProperty(surname);
    }

    // Gibt den Vornamen der Person zurück
    public String getFirstName() {
        return firstName.get();
    }

    // Property für die Datenbindung des Vornamens
    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    // Setzt den Vornamen der Person
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    // Gibt den Nachnamen der Person zurück
    public String getSurname() {
        return surname.get();
    }

    // Property für die Datenbindung des Nachnamens
    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    // Setzt den Nachnamen der Person
    public void setSurname(String surname) {
        this.surname.set(surname);
    }
}
