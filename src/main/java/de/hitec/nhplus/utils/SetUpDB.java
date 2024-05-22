package de.hitec.nhplus.utils;

import de.hitec.nhplus.datastorage.ConnectionBuilder;
import de.hitec.nhplus.datastorage.DaoFactory;
import de.hitec.nhplus.datastorage.PatientDao;
import de.hitec.nhplus.datastorage.TreatmentDao;
import de.hitec.nhplus.model.Patient;
import de.hitec.nhplus.model.Treatment;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static de.hitec.nhplus.utils.DateConverter.convertStringToLocalDate;
import static de.hitec.nhplus.utils.DateConverter.convertStringToLocalTime;

/**
 * Die statische Klasse SetUpDB bietet zwei statische Methoden zum Einrichten und Löschen der Datenbank.
 * Sie verwendet die Klasse ConnectionBuilder und ihren Pfad, um eine Verbindung zur Datenbank aufzubauen.
 * Die Klasse ist ausführbar. Durch Ausführen der Klasse wird eine Verbindung zur Datenbank hergestellt
 * und setUpDb() aufgerufen, um die Datenbank zu löschen, eine saubere Datenbank einzurichten und die
 * Datenbank mit einigen Testdaten zu füllen.
 */
public class SetUpDB {

    /**
     * Diese Methode löscht die Datenbank, indem sie die Tabellen löscht. Dann werden die Methoden
     * zum Einrichten der benötigten Tabellen aufgerufen.
     */
    public static void setUpDb() {
        Connection connection = ConnectionBuilder.getConnection();
        SetUpDB.wipeDb(connection);
        SetUpDB.setUpTablePatient(connection);
        SetUpDB.setUpTableNurse(connection);
        SetUpDB.setUpTableTreatment(connection);
    }

    /**
     * Diese Methode löscht die Datenbank, indem sie die Tabellen löscht.
     */
    public static void wipeDb(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE patient");
            statement.execute("DROP TABLE treatment");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Diese Methode richtet die Tabelle für Patienten ein.
     */
    private static void setUpTablePatient(Connection connection) {
        final String SQL = "CREATE TABLE IF NOT EXISTS patient (" +
                "   pid INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "   firstname TEXT NOT NULL, " +
                "   surname TEXT NOT NULL, " +
                "   dateOfBirth TEXT NOT NULL, " +
                "   carelevel TEXT NOT NULL, " +
                "   roomnumber TEXT NOT NULL" +
                ");";
        try (Statement statement = connection.createStatement()) {
            statement.execute(SQL);
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Diese Methode richtet die Tabelle für Behandlungen ein.
     */
    private static void setUpTableTreatment(Connection connection) {
        final String SQL = "CREATE TABLE IF NOT EXISTS treatment (" +
                "   tid INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "   pid INTEGER NOT NULL, " +
                "   treatment_date TEXT NOT NULL, " +
                "   begin TEXT NOT NULL, " +
                "   end TEXT NOT NULL, " +
                "   description TEXT NOT NULL, " +
                "   remark TEXT NOT NULL," +
                "   FOREIGN KEY (pid) REFERENCES patient (pid) ON DELETE CASCADE " +
                ");";

        try (Statement statement = connection.createStatement()) {
            statement.execute(SQL);
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Diese Methode richtet die Tabelle für Krankenschwestern ein.
     */
    private static void setUpTableNurse(Connection connection){
        final String SQL = "CREATE TABLE IF NOT EXISTS nurse (" +
                "   pid INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "   firstname TEXT NOT NULL, " +
                "   surname TEXT NOT NULL, " +
                "   dateOfBirth TEXT NOT NULL, " +
                "   Permissions TEXT NOT NULL, " +
                "   CurrentTreatment TEXT NOT NULL " +
                ");";

        try(Statement statement = connection.createStatement()){
            statement.execute(SQL);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void main(String[] args) {
        SetUpDB.setUpDb();
    }
}
