package de.hitec.nhplus.controller;

import de.hitec.nhplus.datastorage.DaoFactory;
import de.hitec.nhplus.datastorage.TreatmentDao;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import de.hitec.nhplus.model.Patient;
import de.hitec.nhplus.model.Treatment;
import de.hitec.nhplus.utils.DateConverter;
import javafx.util.StringConverter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class NewTreatmentController {

    @FXML
    private Label labelFirstName;

    @FXML
    private Label labelSurname;

    @FXML
    private TextField textFieldBegin;

    @FXML
    private TextField textFieldEnd;

    @FXML
    private TextField textFieldDescription;

    @FXML
    private TextArea textAreaRemarks;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button buttonAdd;

    private AllTreatmentController controller;
    private Patient patient;
    private Stage stage;

    public void initialize(AllTreatmentController controller, Stage stage, Patient patient) {
        this.controller= controller;
        this.patient = patient;
        this.stage = stage;

        this.buttonAdd.setDisable(true);
        ChangeListener<String> inputNewPatientListener = (observableValue, oldText, newText) ->
                NewTreatmentController.this.buttonAdd.setDisable(NewTreatmentController.this.areInputDataInvalid());
        this.textFieldBegin.textProperty().addListener(inputNewPatientListener);
        this.textFieldEnd.textProperty().addListener(inputNewPatientListener);
        this.textFieldDescription.textProperty().addListener(inputNewPatientListener);
        this.textAreaRemarks.textProperty().addListener(inputNewPatientListener);
        this.datePicker.valueProperty().addListener((observableValue, localDate, t1) -> NewTreatmentController.this.buttonAdd.setDisable(NewTreatmentController.this.areInputDataInvalid()));
        this.datePicker.setConverter(new StringConverter<>() {
            @Override
            public String toString(LocalDate localDate) {
                return (localDate == null) ? "" : DateConverter.convertLocalDateToString(localDate);
            }

            @Override
            public LocalDate fromString(String localDate) {
                return DateConverter.convertStringToLocalDate(localDate);
            }
        });
        this.showPatientData();
    }

    /**
     *Controller, patient, stage sind Verweise auf den übergeordneten Controller, den aktuellen Patienten und das aktuelle Fenster.
     *Diese Methode initialisiert den Controller, setzt den buttonAdd-Button auf deaktiviert und fügt Listener für die Eingabefelder hinzu, um die Validierung der Eingaben zu ermöglichen.
     *Listener: Überwachen die Änderungen in den Textfeldern und dem DatePicker, um den buttonAdd-Button entsprechend zu aktivieren oder zu deaktivieren.
     *StringConverter: Konvertiert LocalDate zu String und umgekehrt für den DatePicker.
     * showPatientData: Zeigt die Patientendaten in den Labels an.
     */

    private void showPatientData(){
        this.labelFirstName.setText(patient.getFirstName());
        this.labelSurname.setText(patient.getSurname());
    }

    /**
     * showPatientData: Setzt die Labels labelFirstName und labelSurname auf die entsprechenden Werte des Patienten.
     */

    @FXML
    public void handleAdd(){
        LocalDate date = this.datePicker.getValue();
        LocalTime begin = DateConverter.convertStringToLocalTime(textFieldBegin.getText());
        LocalTime end = DateConverter.convertStringToLocalTime(textFieldEnd.getText());
        String description = textFieldDescription.getText();
        String remarks = textAreaRemarks.getText();
        Treatment treatment = new Treatment(patient.getPid(), date, begin, end, description, remarks);
        createTreatment(treatment);
        controller.readAllAndShowInTableView();
        stage.close();
    }

    /**
     * handleAdd: Diese Methode wird aufgerufen, wenn der buttonAdd-Button gedrückt wird.
     * Local Variables: Liest die Eingaben des Benutzers aus den Textfeldern und dem DatePicker.
     * Treatment-Objekt: Erstellt ein neues Treatment-Objekt mit den eingelesenen Daten
     * createTreatment: Fügt die Behandlung zur Datenbank hinzu.
     * Aktualisierung: Aktualisiert die Ansicht der Behandlungen im übergeordneten Controller und schließt das Fenster.
     * @param treatment
     */

    private void createTreatment(Treatment treatment) {
        TreatmentDao dao = DaoFactory.getDaoFactory().createTreatmentDao();
        try {
            dao.create(treatment);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        AllTreatmentController.getAllTreatments().add(treatment);
    }

    /**
     * createTreatment: Diese Methode fügt die neue Behandlung zur Datenbank hinzu und aktualisiert die statische Liste der Behandlungen.
     * DAO: Verwendet TreatmentDao für den Datenbankzugriff.
     * Falls ein SQL-Fehler auftritt, wird der Stack-Trace ausgegeben.
     * Fügt die neue Behandlung zur Liste allTreatments hinzu.
     */

    @FXML
    public void handleCancel(){
        stage.close();
    }

    /**
     * handleCancel: Schließt das aktuelle Fenster, wenn der Abbrechen-Button gedrückt wird.
     * @return
     */

    private boolean areInputDataInvalid() {
        if (this.textFieldBegin.getText() == null || this.textFieldEnd.getText() == null) {
            return true;
        }
        try {
            LocalTime begin = DateConverter.convertStringToLocalTime(this.textFieldBegin.getText());
            LocalTime end = DateConverter.convertStringToLocalTime(this.textFieldEnd.getText());
            if (!end.isAfter(begin)) {
                return true;
            }
        } catch (Exception exception) {
            return true;
        }
        return this.textFieldDescription.getText().isBlank() || this.datePicker.getValue() == null;
    }

    /**
     * areInputDataInvalid: Diese Methode überprüft, ob die Eingabedaten gültig sind.
     * Prüfung der Textfelder: Überprüft, ob textFieldBegin und textFieldEnd nicht null sind und ob textFieldDescription nicht leer ist.
     * Konvertiert die Eingaben in LocalTime und überprüft, ob die Endzeit nach der Beginnzeit liegt.
     * Überprüft, ob ein Datum ausgewählt wurde.
     * Gibt true zurück, wenn eine der Prüfungen fehlschlägt, ansonsten false.
     */
}