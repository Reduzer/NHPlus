package de.hitec.nhplus.datastorage;

import de.hitec.nhplus.model.Nurse;
import de.hitec.nhplus.utils.DateConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementiert das Interface <code>DaoImp</code>. Überschreibt Methoden, um spezifische <code>PreparedStatements</code> zu erzeugen,
 * um spezifische SQL-Befehle auszuführen.
 */
public class NurseDao extends DaoImp<Nurse> {

    /**
     * Standardkonstruktor für NurseDao
     * @param connection die Datenbankverbindung
     */
    public NurseDao(Connection connection) {
        super(connection);
    }

    @Override
    protected PreparedStatement getCreateStatement(Nurse nurse) {
        PreparedStatement statement = null;
        try {
            final String sSQLCommand = "INSERT INTO nurse (NPID, Username, Password, Position, Fname, Lname) " +
                    "VALUES (?,?,?,?,?,?)";
            statement = connection.prepareStatement(sSQLCommand);
            statement.setString(1, String.valueOf(nurse.getnPersonalNumber()));
            statement.setString(2, nurse.getUsername());
            statement.setString(3, nurse.getPassword());
            statement.setString(4, String.valueOf(nurse.getPosition()));
            statement.setString(5, nurse.getFirstName());
            statement.setString(6, nurse.getSurname());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected PreparedStatement getReadByIDStatement(long pid) {
        PreparedStatement preparedStatement = null;
        try {
            final String SQL = "SELECT * FROM nurse WHERE NPid = ?";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setLong(1, pid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    protected Nurse getInstanceFromResultSet(ResultSet result) throws SQLException {
        return new Nurse(
                result.getLong(1),
                result.getString(2),
                result.getString(3),
                result.getString(4),
                result.getString(5),
                result.getInt(6));
    }

    @Override
    protected PreparedStatement getReadAllStatement() {
        PreparedStatement statement = null;
        try {
            final String SQL = "SELECT * FROM nurse";
            statement = this.connection.prepareStatement(SQL);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return statement;
    }

    @Override
    protected ArrayList<Nurse> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Nurse> list = new ArrayList<>();
        while (result.next()) {
            Nurse nurse = new Nurse(
                    result.getLong(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getInt(6));
            list.add(nurse);
        }
        return list;
    }

    @Override
    protected PreparedStatement getUpdateStatement(Nurse nurse) {
        PreparedStatement preparedStatement = null;
        try {
            final String SQL =
                    "UPDATE nurse SET " +
                            "Username = ?, " +
                            "Password = ?, " +
                            "Position = ?, " +
                            "Fname = ?, " +
                            "Lname = ? " +
                            "WHERE NPid = ?";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, nurse.getUsername());
            preparedStatement.setString(2, nurse.getPassword());
            preparedStatement.setInt(3, nurse.getPosition());
            preparedStatement.setString(4, nurse.getFirstName());
            preparedStatement.setString(5, nurse.getSurname());
            preparedStatement.setLong(6, nurse.getnPersonalNumber());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getDeleteStatement(long pid) {
        PreparedStatement preparedStatement = null;
        try {
            final String SQL = "DELETE FROM nurse WHERE NPid = ?";
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
