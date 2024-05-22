package de.hitec.nhplus.datastorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

public class ConnectionBuilder {

    // Name der Datenbankdatei
    private static final String DB_NAME = "nursingHome.db";
    // URL für die SQLite-Datenbankverbindung
    private static final String URL = "jdbc:sqlite:db/" + DB_NAME;

    // Einzige Instanz der Verbindung
    private static Connection connection;

    // Synchronisierte Methode, um die Verbindung zur Datenbank zu erhalten
    synchronized public static Connection getConnection() {
        try {
            // Wenn die Verbindung noch nicht existiert, wird sie erstellt
            if (ConnectionBuilder.connection == null) {
                SQLiteConfig configuration = new SQLiteConfig();
                // Erzwingt die Verwendung von Fremdschlüsseln
                configuration.enforceForeignKeys(true);
                // Baut die Verbindung mit den angegebenen Einstellungen auf
                ConnectionBuilder.connection = DriverManager.getConnection(URL, configuration.toProperties());
            }
        } catch (SQLException exception) {
            // Fehlermeldung, falls die Verbindung nicht aufgebaut werden kann
            System.out.println("Verbindung zur Datenbank konnte nicht aufgebaut werden!");
            exception.printStackTrace();
        }
        // Gibt die Verbindung zurück
        return ConnectionBuilder.connection;
    }

    // Synchronisierte Methode, um die Verbindung zur Datenbank zu schließen
    synchronized public static void closeConnection() {
        try {
            // Wenn die Verbindung existiert, wird sie geschlossen
            if (ConnectionBuilder.connection != null) {
                ConnectionBuilder.connection.close();
                ConnectionBuilder.connection = null;
            }
        } catch (SQLException exception) {
            // Fehlermeldung, falls die Verbindung nicht geschlossen werden kann
            exception.printStackTrace();
        }
    }
}