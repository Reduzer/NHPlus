package de.hitec.nhplus.datastorage;

import de.hitec.nhplus.model.Treatment;
import de.hitec.nhplus.utils.DateConverter;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementiert das Interface <code>DaoImp</code>. Überschreibt Methoden, um spezifische <code>PreparedStatements</code> zu erzeugen,
 * um spezifische SQL-Befehle auszuführen.
 */
public class TreatmentDao extends DaoImp<Treatment> {

    /**
     * Der Konstruktor initiiert ein Objekt von <code>TreatmentDao</code> und übergibt die Verbindung an seine Superklasse.
     *
     * @param connection Objekt der <code>Connection</code>, um die SQL-Anweisungen auszuführen.
     */
    public TreatmentDao(Connection connection) {
        super(connection);
    }

    /**
     * Generiert ein <code>PreparedStatement</code>, um das gegebene <code>Treatment</code>-Objekt zu speichern.
     *
     * @param treatment Objekt des <code>Treatment</code>, das gespeichert werden soll.
     * @return <code>PreparedStatement</code>, um das gegebene Treatment einzufügen.
     */
    @Override
    protected PreparedStatement getCreateStatement(Treatment treatment) {
        PreparedStatement preparedStatement = null;
        try {
            final String SQL = "INSERT INTO treatment (pid, treatment_date, begin, end, description, remark) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setLong(1, treatment.getPid());
            preparedStatement.setString(2, treatment.getDate());
            preparedStatement.setString(3, treatment.getBegin());
            preparedStatement.setString(4, treatment.getEnd());
            preparedStatement.setString(5, treatment.getDescription());
            preparedStatement.setString(6, treatment.getRemarks());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    /**
     * Generiert ein <code>PreparedStatement</code>, um ein Treatment anhand einer gegebenen Behandlungs-ID (tid) abzufragen.
     *
     * @param tid Behandlungs-ID zur Abfrage.
     * @return <code>PreparedStatement</code>, um die Behandlung abzufragen.
     */
    @Override
    protected PreparedStatement getReadByIDStatement(long tid) {
        PreparedStatement preparedStatement = null;
        try {
            final String SQL = "SELECT * FROM treatment WHERE tid = ?";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setLong(1, tid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    /**
     * Mapped ein <code>ResultSet</code> einer Behandlung zu einem Objekt des <code>Treatment</code>.
     *
     * @param result ResultSet mit einer einzigen Zeile. Die Spalten werden zu einem Objekt der Klasse <code>Treatment</code> gemappt.
     * @return Objekt der Klasse <code>Treatment</code> mit den Daten aus dem ResultSet.
     */
    @Override
    protected Treatment getInstanceFromResultSet(ResultSet result) throws SQLException {
        LocalDate date = DateConverter.convertStringToLocalDate(result.getString(3));
        LocalTime begin = DateConverter.convertStringToLocalTime(result.getString(4));
        LocalTime end = DateConverter.convertStringToLocalTime(result.getString(5));
        return new Treatment(result.getLong(1), result.getLong(2),
                date, begin, end, result.getString(6), result.getString(7));
    }

    /**
     * Generiert ein <code>PreparedStatement</code>, um alle Behandlungen abzufragen.
     *
     * @return <code>PreparedStatement</code>, um alle Behandlungen abzufragen.
     */
    @Override
    protected PreparedStatement getReadAllStatement() {
        PreparedStatement statement = null;
        try {
            final String SQL = "SELECT * FROM treatment";
            statement = this.connection.prepareStatement(SQL);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return statement;
    }

    @Override
    protected PreparedStatement getUpdateStatement(Treatment treatment) {
        return null;
    }

    @Override
    protected PreparedStatement getDeleteStatement(long key) {
        return null;
    }

    @Override
    protected PreparedStatement deleteOldTreatment(long key) {
        return null;
    }

    /**
     * Mapped ein <code>ResultSet</code> aller Behandlungen zu einer <code>ArrayList</code> mit Objekten der Klasse
     * <code>Treatment</code>.
     *
     * @param result ResultSet mit allen Zeilen. Die Spalten werden zu Objekten der Klasse <code>Treatment</code> gemappt.
     * @return <code>ArrayList</code> mit Objekten der Klasse <code>Treatment</code> aller Zeilen im
     * <code>ResultSet</code>.
     */
    @Override
    protected ArrayList<Treatment> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Treatment> list = new ArrayList<Treatment>();
        while (result.next()) {
            LocalDate date = DateConverter.convertStringToLocalDate(result.getString(3));
            LocalTime begin = DateConverter.convertStringToLocalTime(result.getString(4));
            LocalTime end = DateConverter.convertStringToLocalTime(result.getString(5));
            Treatment treatment = new Treatment(result.getLong(1), result.getLong(2),
                    date, begin, end, result.getString(6), result.getString(7));
            list.add(treatment);
        }
        return list;
    }

    /**
     * Generiert ein <code>PreparedStatement</code>, um alle Behandlungen eines Patienten mit einer gegebenen
     * Patienten-ID (pid) abzufragen.
     *
     * @param pid Patienten-ID zur Abfrage aller Behandlungen, die auf diese ID verweisen.
     * @return <code>PreparedStatement</code>, um alle Behandlungen der gegebenen Patienten-ID (pid) abzufragen.
     */
    private PreparedStatement getReadAllTreatmentsOfOnePatientByPid(long pid) {
        PreparedStatement preparedStatement = null;
        try {
            final String SQL = "SELECT * FROM treatment WHERE pid = ?";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setLong(1, pid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    /**
     * Ruft alle Behandlungen einer gegebenen Patienten-ID (pid) ab und mappt die Ergebnisse zu einer <code>ArrayList</code> mit
     * Objekten der Klasse <code>Treatment</code>.
     *
     * @param pid Patienten-ID zur Abfrage aller Behandlungen, die auf diese ID verweisen.
     * @return <code>ArrayList</code> mit Objekten der Klasse <code>Treatment</code> aller Zeilen im
     * <code>ResultSet</code>.
     */
    public List<Treatment> readTreatmentsByPid(long pid) throws SQLException {
        ResultSet result = getReadAllTreatmentsOfOnePatientByPid(pid).executeQuery();
        return getListFromResultSet(result);
    }
}


/**
 * Generiert ein <code>PreparedStatement</code>, um die gegebene Behandlung zu aktualisieren, identifiziert
*/

    /**
     * Generates a <code>PreparedStatement</code> to update the given treatment, identified
     * by the id of the treatment (tid).
     *
     * @param treatment Treatment object to update.
     * @return <code>PreparedStatement</code> to update the given treatment.
     */
    @Override
    protected PreparedStatement getUpdateStatement(Treatment treatment) {
        PreparedStatement preparedStatement = null;
        try {
            final String SQL =
                    "UPDATE treatment SET " +
                            "pid = ?, " +
                            "treatment_date = ?, " +
                            "begin = ?, " +
                            "end = ?, " +
                            "description = ?, " +
                            "remark = ? " +
                            "WHERE tid = ?";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setLong(1, treatment.getPid());
            preparedStatement.setString(2, treatment.getDate());
            preparedStatement.setString(3, treatment.getBegin());
            preparedStatement.setString(4, treatment.getEnd());
            preparedStatement.setString(5, treatment.getDescription());
            preparedStatement.setString(6, treatment.getRemarks());
            preparedStatement.setLong(7, treatment.getTid());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    /**
     * Generates a <code>PreparedStatement</code> to delete a treatment with the given id.
     *
     * @param tid Id of the Treatment to delete.
     * @return <code>PreparedStatement</code> to delete treatment with the given id.
     */
    @Override
    protected PreparedStatement getDeleteStatement(long tid) {
        PreparedStatement preparedStatement = null;
        try {
            final String SQL =
                    "DELETE FROM treatment WHERE tid = ?";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setLong(1, tid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    protected PreparedStatement deleteOldTreatment(long key) {
        return null;
    }


    public void deleteOldTreatment() {
        try {
            LocalDate tenYearsAgo = LocalDate.now().minusYears(10);
            ArrayList<Treatment> treatmentsToDelete = new ArrayList<>();
            for (Treatment treatment : readAll()) {
            if (treatment.getDate() == String.valueOf(tenYearsAgo)) {
                treatmentsToDelete.add(treatment);
            }

            }
            for (Treatment treatment : treatmentsToDelete) {
                deleteById(treatment.getTid());
            }
        }catch (SQLException exception) {
            exception.printStackTrace();
        }

    }


}
