package de.hitec.nhplus.datastorage;

import de.hitec.nhplus.model.Patient;
import de.hitec.nhplus.utils.DateConverter;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Implementiert das Interface <code>DaoImp</code>. Überschreibt Methoden, um spezifische <code>PreparedStatements</code> zu erzeugen,
 * um spezifische SQL-Befehle auszuführen.
 */
public class PatientDao extends DaoImp<Patient> {

    /**
     * Der Konstruktor initiiert ein Objekt von <code>PatientDao</code> und übergibt die Verbindung an seine Superklasse.
     *
     * @param connection Objekt der <code>Connection</code>, um die SQL-Anweisungen auszuführen.
     */
    public PatientDao(Connection connection) {
        super(connection);
    }

    /**
     * Erzeugt ein <code>PreparedStatement</code>, um das gegebene <code>Patient</code>-Objekt zu speichern.
     *
     * @param patient Objekt des <code>Patient</code>, das gespeichert werden soll.
     * @return <code>PreparedStatement</code>, um den gegebenen Patienten einzufügen.
     */
    @Override
    protected PreparedStatement getCreateStatement(Patient patient) {
        PreparedStatement preparedStatement = null;
        try {
            final String SQL = "INSERT INTO patient (pid, firstname, surname, dateOfBirth, carelevel, roomnumber) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, patient.getFirstName());
            preparedStatement.setString(2, patient.getSurname());
            preparedStatement.setString(3, patient.getDateOfBirth().toString());
            preparedStatement.setString(4, patient.getCareLevel());
            preparedStatement.setString(5, patient.getRoomNumber());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    /**
     * Erzeugt ein <code>PreparedStatement</code>, um einen Patienten anhand einer gegebenen Patienten-ID (pid) abzufragen.
     *
     * @param pid Patienten-ID zur Abfrage.
     * @return <code>PreparedStatement</code>, um den Patienten abzufragen.
     */
    @Override
    protected PreparedStatement getReadByIDStatement(long pid) {
        PreparedStatement preparedStatement = null;
        try {
            final String SQL = "SELECT * FROM patient WHERE pid = ?";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setLong(1, pid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    /**
     * Mapped ein <code>ResultSet</code> eines Patienten zu einem <code>Patient</code>-Objekt.
     *
     * @param result ResultSet mit einer einzigen Zeile. Die Spalten werden zu einem Objekt der Klasse <code>Patient</code> gemappt.
     * @return Objekt der Klasse <code>Patient</code> mit den Daten aus dem ResultSet.
     */
    @Override
    protected Patient getInstanceFromResultSet(ResultSet result) throws SQLException {
        return new Patient(
                result.getInt(1),
                result.getString(2),
                result.getString(3),
                DateConverter.convertStringToLocalDate(result.getString(4)),
                result.getString(5),
                result.getString(6));
    }

    /**
     * Erzeugt ein <code>PreparedStatement</code>, um alle Patienten abzufragen.
     *
     * @return <code>PreparedStatement</code>, um alle Patienten abzufragen.
     */
    @Override
    protected PreparedStatement getReadAllStatement() {
        PreparedStatement statement = null;
        try {
            final String SQL = "SELECT * FROM patient";
            statement = this.connection.prepareStatement(SQL);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return statement;
    }

    /**
     * Mapped ein <code>ResultSet</code> aller Patienten zu einer <code>ArrayList</code> von <code>Patient</code>-Objekten.
     *
     * @param result ResultSet mit allen Zeilen. Die Spalten werden zu Objekten der Klasse <code>Patient</code> gemappt.
     * @return <code>ArrayList</code> mit Objekten der Klasse <code>Patient</code> aller Zeilen im <code>ResultSet</code>.
     */
    @Override
    protected ArrayList<Patient> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Patient> list = new ArrayList<>();
        while (result.next()) {
            LocalDate date = DateConverter.convertStringToLocalDate(result.getString(4));
            Patient patient = new Patient(result.getInt(1), result.getString(2),
                    result.getString(3), date,
                    result.getString(5), result.getString(6));
            list.add(patient);
        }
        return list;
    }

    /**
     * Erzeugt ein <code>PreparedStatement</code>, um den gegebenen Patienten zu aktualisieren, identifiziert
     * durch die ID des Patienten (pid).
     *
     * @param patient Patientenobjekt zur Aktualisierung.
     * @return <code>PreparedStatement</code>, um den gegebenen Patienten zu aktualisieren.
     */
    @Override
    protected PreparedStatement getUpdateStatement(Patient patient) {
        PreparedStatement preparedStatement = null;
        try {
            final String SQL =
                    "UPDATE patient SET " +
                            "firstname = ?, " +
                            "surname = ?, " +
                            "dateOfBirth = ?, " +
                            "carelevel = ?, " +
                            "roomnumber = ? " +
                            "WHERE pid = ?";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, patient.getFirstName());
            preparedStatement.setString(2, patient.getSurname());
            preparedStatement.setString(3, patient.getDateOfBirth().toString());
            preparedStatement.setString(4, patient.getCareLevel());
            preparedStatement.setString(5, patient.getRoomNumber());
            preparedStatement.setLong(6, patient.getPid());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    /**
     * Erzeugt ein <code>PreparedStatement</code>, um einen Patienten mit der gegebenen ID zu löschen.
     *
     * @param pid ID des Patienten, der gelöscht werden soll.
     * @return <code>PreparedStatement</code>, um den Patienten mit der gegebenen ID zu löschen.
     */
    @Override
    protected PreparedStatement getDeleteStatement(long pid) {
        PreparedStatement preparedStatement = null;
        try {
            final String SQL = "DELETE FROM patient WHERE pid = ?";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setLong(1, pid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    protected PreparedStatement deleteOldTreatment(long key) {
        return null; // Diese Methode wird in dieser Klasse nicht verwendet
    }
}
