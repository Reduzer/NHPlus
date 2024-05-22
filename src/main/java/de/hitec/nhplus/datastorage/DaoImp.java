package de.hitec.nhplus.datastorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Abstrakte Klasse DaoImp, die das Dao-Interface implementiert
public abstract class DaoImp<T> implements Dao<T> {
    // Verbindung zur Datenbank
    protected Connection connection;

    // Konstruktor, der die Datenbankverbindung initialisiert
    public DaoImp(Connection connection) {
        this.connection = connection;
    }

    // Methode zum Erstellen eines Datensatzes in der Datenbank
    @Override
    public void create(T t) throws SQLException {
        getCreateStatement(t).executeUpdate();
    }

    // Methode zum Lesen eines Datensatzes aus der Datenbank anhand eines Schlüssels
    @Override
    public T read(long key) throws SQLException {
        T object = null;
        ResultSet result = getReadByIDStatement(key).executeQuery();
        if (result.next()) {
            object = getInstanceFromResultSet(result);
        }
        return object;
    }

    // Methode zum Lesen aller Datensätze aus der Datenbank
    @Override
    public List<T> readAll() throws SQLException {
        return getListFromResultSet(getReadAllStatement().executeQuery());
    }

    // Methode zum Aktualisieren eines Datensatzes in der Datenbank
    @Override
    public void update(T t) throws SQLException {
        getUpdateStatement(t).executeUpdate();
    }

    // Methode zum Löschen eines Datensatzes aus der Datenbank anhand eines Schlüssels
    @Override
    public void deleteById(long key) throws SQLException {
        getDeleteStatement(key).executeUpdate();
    }

    // Methode zum Löschen eines spezifischen Behandlungsdatensatzes
    public void deleteTreatment(long key) throws SQLException {
        deleteOldTreatment(key).executeUpdate();
    }

    // Abstrakte Methode zum Erstellen einer Instanz von T aus einem ResultSet
    protected abstract T getInstanceFromResultSet(ResultSet set) throws SQLException;

    // Abstrakte Methode zum Erstellen einer Liste von T aus einem ResultSet
    protected abstract ArrayList<T> getListFromResultSet(ResultSet set) throws SQLException;

    // Abstrakte Methode zum Erstellen eines PreparedStatements für das Erstellen eines Datensatzes
    protected abstract PreparedStatement getCreateStatement(T t);

    // Abstrakte Methode zum Erstellen eines PreparedStatements für das Lesen eines Datensatzes anhand eines Schlüssels
    protected abstract PreparedStatement getReadByIDStatement(long key);

    // Abstrakte Methode zum Erstellen eines PreparedStatements für das Lesen aller Datensätze
    protected abstract PreparedStatement getReadAllStatement();

    // Abstrakte Methode zum Erstellen eines PreparedStatements für das Aktualisieren eines Datensatzes
    protected abstract PreparedStatement getUpdateStatement(T t);

    // Abstrakte Methode zum Erstellen eines PreparedStatements für das Löschen eines Datensatzes anhand eines Schlüssels
    protected abstract PreparedStatement getDeleteStatement(long key);

    // Abstrakte Methode zum Erstellen eines PreparedStatements für das Löschen eines spezifischen Behandlungsdatensatzes
    protected abstract PreparedStatement deleteOldTreatment(long key);
}
