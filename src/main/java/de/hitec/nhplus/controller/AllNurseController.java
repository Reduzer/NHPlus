package de.hitec.nhplus.controller;

import de.hitec.nhplus.datastorage.DaoFactory;
import de.hitec.nhplus.datastorage.NurseDao;
import de.hitec.nhplus.datastorage.PatientDao;
import de.hitec.nhplus.model.Nurse;
import de.hitec.nhplus.model.Patient;
import de.hitec.nhplus.utils.DateConverter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.sql.SQLException;
import java.time.LocalDate;

public class AllNurseController {

    @FXML
    private TableView<Nurse> tableView;

    @FXML
    private TableColumn<Nurse, Integer> colId;

    @FXML
    private TableColumn<Nurse, String> colFirstName;

    @FXML
    private TableColumn<Nurse, String> colSurname;

    @FXML
    private TableColumn<Nurse, String> colTelephone;

    @FXML
    private TableColumn<Nurse, String> colCurrentTreatment;

    @FXML
    private TableColumn<Nurse, String> colCurrentLoads;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonAdd;

    @FXML
    private TextField txfSurname;

    @FXML
    private TextField txfFirstName;

    private final ObservableList<Nurse> nurses = FXCollections.observableArrayList();
    private NurseDao dao;

    /**
     * Initializes / fills the View of the All Nurse View
     * @param None
     */

    public void initialize() {
        this.readAllAndShowInTableView();

        this.colId.setCellValueFactory(new PropertyValueFactory<>("nPersonalNumber"));

        // CellValueFactory to show property values in TableView
        this.colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        // CellFactory to write property values from with in the TableView
        this.colFirstName.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        this.colSurname.setCellFactory(TextFieldTableCell.forTableColumn());

        //Anzeigen der Daten
        this.tableView.setItems(this.nurses);

        this.buttonDelete.setDisable(true);
        this.tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Nurse>() {
            @Override
            public void changed(ObservableValue<? extends Nurse> observableValue, Nurse oldNurse, Nurse newNurse) {;
                AllNurseController.this.buttonDelete.setDisable(newNurse == null);
            }
        });

        this.buttonAdd.setDisable(true);
        ChangeListener<String> inputNewPatientListener = (observableValue, oldText, newText) ->
                AllNurseController.this.buttonAdd.setDisable(!AllNurseController.this.areInputDataValid());
        this.txfSurname.textProperty().addListener(inputNewPatientListener);
        this.txfFirstName.textProperty().addListener(inputNewPatientListener);
    }

    /**
     * When a cell of the column with first names was changed, this method will be called, to persist the change.
     *
     * @param event Event including the changed object and the change.
     */
    @FXML
    public void handleOnEditFirstname(TableColumn.CellEditEvent<Nurse, String> event) {
        event.getRowValue().setFirstName(event.getNewValue());
        this.doUpdate(event);
    }

    /**
     * When a cell of the column with surnames was changed, this method will be called, to persist the change.
     *
     * @param event Event including the changed object and the change.
     */
    @FXML
    public void handleOnEditSurname(TableColumn.CellEditEvent<Nurse, String> event) {
        event.getRowValue().setSurname(event.getNewValue());
        this.doUpdate(event);
    }

    /**
     * Updates a patient by calling the method <code>update()</code> of {@link PatientDao}.
     *
     * @param event Event including the changed object and the change.
     */
    private void doUpdate(TableColumn.CellEditEvent<Nurse, String> event) {
        try {
            this.dao.update(event.getRowValue());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Reloads all patients to the table by clearing the list of all patients and filling it again by all persisted
     * patients, delivered by {@link PatientDao}.
     */
    private void readAllAndShowInTableView() {
        this.nurses.clear();
        this.dao = DaoFactory.getDaoFactory().createNurseDao();
        try {
            this.nurses.addAll(this.dao.readAll());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * This method handles events fired by the button to delete patients. It calls {@link PatientDao} to delete the
     * patient from the database and removes the object from the list, which is the data source of the
     * <code>TableView</code>.
     */
    @FXML
    public void handleDelete() {
        Nurse selectedItem = this.tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            try {
                DaoFactory.getDaoFactory().createPatientDAO().deleteById(selectedItem.getnPersonalNumber());
                this.tableView.getItems().remove(selectedItem);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * This method handles the events fired by the button to add a patient. It collects the data from the
     * <code>TextField</code>s, creates an object of class <code>Patient</code> of it and passes the object to
     * {@link PatientDao} to persist the data.
     */
    @FXML
    public void handleAdd() {
        String surname = this.txfSurname.getText();
        String firstName = this.txfFirstName.getText();
        try {
            this.dao.create(new Nurse(firstName, surname));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        readAllAndShowInTableView();
        clearTextfields();
    }

    /**
     * Clears all contents from all <code>TextField</code>s.
     */
    private void clearTextfields() {
        this.txfFirstName.clear();
        this.txfSurname.clear();
    }

    private boolean areInputDataValid() {
        return !this.txfFirstName.getText().isBlank() && !this.txfSurname.getText().isBlank();
    }
}
