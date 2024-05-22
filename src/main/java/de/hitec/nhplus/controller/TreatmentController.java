package de.hitec.nhplus.controller;

import de.hitec.nhplus.datastorage.ConnectionBuilder;
import de.hitec.nhplus.datastorage.DaoFactory;
import de.hitec.nhplus.datastorage.PatientDao;
import de.hitec.nhplus.datastorage.TreatmentDao;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import de.hitec.nhplus.model.Patient;
import de.hitec.nhplus.model.Treatment;
import de.hitec.nhplus.utils.DateConverter;

import java.sql.SQLException;
import java.time.LocalDate;

public class TreatmentController {

    @FXML
    private Label labelPatientName;

    @FXML
    private Label labelCareLevel;

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

    private AllTreatmentController controller;
    private Stage stage;
    private Patient patient;
    private Treatment treatment;

    public void initializeController(AllTreatmentController controller, Stage stage, Treatment treatment) {
        this.stage = stage;
        this.controller= controller;
        PatientDao pDao = DaoFactory.getDaoFactory().createPatientDAO();
        try {
            this.patient = pDao.read((int) treatment.getPid());
            this.treatment = treatment;
            showData();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * initializeController: Diese Methode initialisiert den Controller mit dem übergeordneten Controller, dem aktuellen Fenster und der zu bearbeitenden Behandlung.
     * Ruft die Daten des Patienten aus der Datenbank ab und zeigt sie an.
     */

    private void showData(){
        this.labelPatientName.setText(patient.getSurname()+", "+patient.getFirstName());
        this.labelCareLevel.setText(patient.getCareLevel());
        LocalDate date = DateConverter.convertStringToLocalDate(treatment.getDate());
        this.datePicker.setValue(date);
        this.textFieldBegin.setText(this.treatment.getBegin());
        this.textFieldEnd.setText(this.treatment.getEnd());
        this.textFieldDescription.setText(this.treatment.getDescription());
        this.textAreaRemarks.setText(this.treatment.getRemarks());
    }

    /**
     * showData: Diese Methode zeigt die Daten der zu bearbeitenden Behandlung und des zugehörigen Patienten in den entsprechenden UI-Elementen an.
     */


    @FXML
    public void handleChange(){
        this.treatment.setDate(this.datePicker.getValue().toString());
        this.treatment.setBegin(textFieldBegin.getText());
        this.treatment.setEnd(textFieldEnd.getText());
        this.treatment.setDescription(textFieldDescription.getText());
        this.treatment.setRemarks(textAreaRemarks.getText());
        doUpdate();
        controller.readAllAndShowInTableView();
        stage.close();
    }

    /**
     * handleChange: Diese Methode wird aufgerufen, wenn Änderungen vorgenommen und gespeichert werden sollen.
     * Aktualisiert die Eigenschaften der Behandlung mit den neuen Benutzereingaben.
     * Aktualisiert die Behandlung in der Datenbank.
     * Ruft die Methode readAllAndShowInTableView() des übergeordneten Controllers auf, um die Ansicht der Behandlungen zu aktualisieren.
     * Schließt das Fenster nach dem Speichern der Änderungen.
     */

    private void doUpdate(){
        TreatmentDao dao = DaoFactory.getDaoFactory().createTreatmentDao();
        try {
            dao.update(treatment);
            int index = AllTreatmentController.getAllTreatments().indexOf(this.treatment);
            if (index >= 0) {
                AllTreatmentController.getAllTreatments().set(index, this.treatment);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * doUpdate: Diese Methode führt das eigentliche Update der Behandlung in der Datenbank durch und aktualisiert auch die statische Liste aller Behandlungen.
     */

    @FXML
    public void handleCancel(){
        stage.close();
    }

    /**
     * handleCancel: Diese Methode wird aufgerufen, wenn die Bearbeitung abgebrochen und das Fenster geschlossen werden soll.
     *
     */
}