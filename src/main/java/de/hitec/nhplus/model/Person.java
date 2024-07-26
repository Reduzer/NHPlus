package de.hitec.nhplus.model;

import javafx.beans.property.SimpleStringProperty;

public abstract class Person {

    private final SimpleStringProperty firstName;
    private final SimpleStringProperty surname;

    public Person(String firstName, String surname) {
        this.firstName = new SimpleStringProperty(firstName);
        this.surname = new SimpleStringProperty(surname);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getSurname() {
        return surname.get();
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }
}
