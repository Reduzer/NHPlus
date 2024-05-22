package de.hitec.nhplus.datastorage;

public class DaoFactory {

    // Singleton-Instanz der DaoFactory
    private static DaoFactory instance;

    // Privater Konstruktor, um die Instanzierung von außen zu verhindern
    private DaoFactory() {
    }

    // Methode, um die Singleton-Instanz der DaoFactory zu erhalten
    public static DaoFactory getDaoFactory() {
        // Erstellt eine neue Instanz, wenn noch keine existiert
        if (DaoFactory.instance == null) {
            DaoFactory.instance = new DaoFactory();
        }
        // Gibt die vorhandene oder neu erstellte Instanz zurück
        return DaoFactory.instance;
    }

    // Methode, um ein TreatmentDao-Objekt zu erstellen
    public TreatmentDao createTreatmentDao() {
        // Gibt ein neues TreatmentDao-Objekt zurück, das mit der Datenbankverbindung initialisiert wird
        return new TreatmentDao(ConnectionBuilder.getConnection());
    }

    // Methode, um ein PatientDao-Objekt zu erstellen
    public PatientDao createPatientDAO() {
        // Gibt ein neues PatientDao-Objekt zurück, das mit der Datenbankverbindung initialisiert wird
        return new PatientDao(ConnectionBuilder.getConnection());
    }
}
