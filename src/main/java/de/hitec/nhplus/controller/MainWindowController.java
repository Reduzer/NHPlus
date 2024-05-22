package de.hitec.nhplus.controller;

import de.hitec.nhplus.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainWindowController {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private void handleShowAllPatient(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/AllPatientView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Diese Methode wird aufgerufen, wenn das Ereignis event (z.B. ein Button-Klick) ausgelöst wird.
     *
     * @param event
     */

    @FXML
    private void handleShowAllTreatments(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/AllTreatmentView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Funktioniert ähnlich wie handleShowAllPatient, lädt jedoch die AllTreatmentView.fxml, die die Behandlungsansicht definiert.
     *
     * @param actionEvent
     */

    @FXML
    public void handleShowAllNurses(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/AllCaregiverView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        }
        catch(IOException exception){
            exception.printStackTrace();
        }
    }

    /**
     * Diese Methode wird aufgerufen, um die Ansicht der Pflegekräfte anzuzeigen. Sie lädt die AllCaregiverView.fxml in den zentralen Bereich des mainBorderPane.
     */
}



