package de.hitec.nhplus.datastorage;

import de.hitec.nhplus.model.Nurse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Interface <code>DaoImp</code>. Overrides methods to generate specific <code>PreparedStatements</code>,
 * to execute the specific SQL Statements.
 */
public class NurseDao extends DaoImp<Nurse> {

    /**
     * The constructor initiates an object of <code>NurseDao</code> and passes the connection to its super class.
     *
     * @param connection Object of <code>Connection</code> to execute the SQL-statements.
     */
    public NurseDao(Connection connection) {
        super(connection);
    }

    /**
     * Generates a <code>PreparedStatement</code> to persist the given object of <code>Nurse</code>.
     *
     * @param nurse Object of <code>Nurse</code> to persist.
     * @return <code>PreparedStatement</code> to insert the given nurse.
     */
    @Override
    protected PreparedStatement getCreateStatement(Nurse nurse) {
        PreparedStatement preparedStatement = null;
        try {
            final String SQL = "INSERT INTO nurse (Fname, Lname, NPid) " +
                    "VALUES (?, ?, ?)";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, nurse.getFirstName());
            preparedStatement.setString(2, nurse.getSurname());
            preparedStatement.setLong(3, nurse.getnPersonalNumber());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    /**
     * Generates a <code>PreparedStatement</code> to query a nurse by a given nurse id (nid).
     *
     * @param NPid Nurse id to query.
     * @return <code>PreparedStatement</code> to query the nurse.
     */
    @Override
    protected PreparedStatement getReadByIDStatement(long NPid) {
        PreparedStatement preparedStatement = null;
        try {
            final String SQL = "SELECT * FROM nurse WHERE nPid = ?";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setLong(1, nid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    /**
     * Maps a <code>ResultSet</code> of one nurse to an object of <code>Nurse</code>.
     *
     * @param result ResultSet with a single row. Columns will be mapped to an object of class <code>Nurse</code>.
     * @return Object of class <code>Nurse</code> with the data from the resultSet.
     */
    @Override
    protected Nurse getInstanceFromResultSet(ResultSet result) throws SQLException {
        Nurse nurse = new Nurse(
                result.getString("firstName"),
                result.getString("surname")
        );
        nurse.NPid(result.getLong("nPersonalNumber"));
        return nurse;
    }

    /**
     * Generates a <code>PreparedStatement</code> to query all nurses.
     *
     * @return <code>PreparedStatement</code> to query all nurses.
     */
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

    /**
     * Maps a <code>ResultSet</code> of all nurses to an <code>ArrayList</code> of <code>Nurse</code> objects.
     *
     * @param result ResultSet with all rows. The Columns will be mapped to objects of class <code>Nurse</code>.
     * @return <code>ArrayList</code> with objects of class <code>Nurse</code> of all rows in the
     * <code>ResultSet</code>.
     */
    @Override
    protected ArrayList<Nurse> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Nurse> list = new ArrayList<>();
        while (result.next()) {
            Nurse nurse = new Nurse(
                    result.getString("firstName"),
                    result.getString("surname")
            );
            nurse.NPid(result.getLong("nPersonalNumber"));
            list.add(nurse);
        }
        return list;
    }

    /**
     * Generates a <code>PreparedStatement</code> to update the given nurse, identified
     * by the id of the nurse (nid).
     *
     * @param nurse Nurse object to update.
     * @return <code>PreparedStatement</code> to update the given nurse.
     */
    @Override
    protected PreparedStatement getUpdateStatement(Nurse nurse) {
        PreparedStatement preparedStatement = null;
        try {
            final String SQL =
                    "UPDATE nurse SET " +
                            "firstName = ?, " +
                            "surname = ?, " +
                            "nPersonalNumber = ? " +
                            "WHERE nid = ?";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, nurse.getFirstName());
            preparedStatement.setString(2, nurse.getSurname());
            preparedStatement.setLong(3, nurse.getnPersonalNumber());
            preparedStatement.setLong(4, nurse.getNPid());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    /**
     * Generates a <code>PreparedStatement</code> to delete a nurse with the given id.
     *
     * @param nid Id of the nurse to delete.
     * @return <code>PreparedStatement</code> to delete nurse with the given id.
     */
    @Override
    protected PreparedStatement getDeleteStatement(long nid) {
        PreparedStatement preparedStatement = null;
        try {
            final String SQL = "DELETE FROM nurse WHERE NPid = ?";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setLong(1, nid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }
}
