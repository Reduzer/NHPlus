package de.hitec.nhplus.datastorage;

import java.sql.SQLException;
import java.util.List;

// Generisches DAO-Interface für CRUD-Operationen (Create, Read, Update, Delete)
public interface Dao<T> {

    // Methode zum Erstellen eines neuen Eintrags
    void create(T t) throws SQLException;

    // Methode zum Lesen eines Eintrags anhand eines Schlüssels
    T read(long key) throws SQLException;

    // Methode zum Lesen aller Einträge
    List<T> readAll() throws SQLException;

    // Methode zum Aktualisieren eines Eintrags
    void update(T t) throws SQLException;

    // Methode zum Löschen eines Eintrags anhand eines Schlüssels
    void deleteById(long key) throws SQLException;

    // Methode zum Löschen einer Behandlung anhand eines Schlüssels
    void deleteTreatment(long key) throws SQLException;
}
