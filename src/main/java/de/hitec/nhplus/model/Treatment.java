package de.hitec.nhplus.model;

import de.hitec.nhplus.utils.DateConverter;

import java.time.LocalDate;
import java.time.LocalTime;

public class Treatment {
    // ID der Behandlung
    private long tid;
    // ID des behandelten Patienten
    private final long pid;
    // Datum der Behandlung
    private LocalDate date;
    // Beginn der Behandlung
    private LocalTime begin;
    // Ende der Behandlung
    private LocalTime end;
    // Beschreibung der Behandlung
    private String description;
    // Anmerkungen zur Behandlung
    private String remarks;

    /**
     * Konstruktor zur Initialisierung eines Treatment-Objekts mit den angegebenen Parametern.
     * Verwenden Sie diesen Konstruktor, um Objekte zu initialisieren, die noch nicht gespeichert sind,
     * da sie noch keine Behandlungs-ID (tid) haben.
     *
     * @param pid ID des behandelten Patienten.
     * @param date Datum der Behandlung.
     * @param begin Beginn der Behandlung im Format "hh:MM".
     * @param end Ende der Behandlung im Format "hh:MM".
     * @param description Beschreibung der Behandlung.
     * @param remarks Anmerkungen zur Behandlung.
     */
    public Treatment(long pid, LocalDate date, LocalTime begin,
                     LocalTime end, String description, String remarks) {
        this.pid = pid;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.description = description;
        this.remarks = remarks;
    }

    /**
     * Konstruktor zur Initialisierung eines Treatment-Objekts mit den angegebenen Parametern.
     * Verwenden Sie diesen Konstruktor, um Objekte zu initialisieren, die bereits gespeichert sind und eine
     * Behandlungs-ID (tid) haben.
     *
     * @param tid ID der Behandlung.
     * @param pid ID des behandelten Patienten.
     * @param date Datum der Behandlung.
     * @param begin Beginn der Behandlung im Format "hh:MM".
     * @param end Ende der Behandlung im Format "hh:MM".
     * @param description Beschreibung der Behandlung.
     * @param remarks Anmerkungen zur Behandlung.
     */
    public Treatment(long tid, long pid, LocalDate date, LocalTime begin,
                     LocalTime end, String description, String remarks) {
        this.tid = tid;
        this.pid = pid;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.description = description;
        this.remarks = remarks;
    }

    // Gibt die ID der Behandlung zurück
    public long getTid() {
        return tid;
    }

    // Gibt die ID des Patienten zurück
    public long getPid() {
        return this.pid;
    }

    // Gibt das Datum der Behandlung als String zurück
    public String getDate() {
        return date.toString();
    }

    // Gibt den Beginn der Behandlung als String zurück
    public String getBegin() {
        return begin.toString();
    }

    // Gibt das Ende der Behandlung als String zurück
    public String getEnd() {
        return end.toString();
    }

    // Setzt das Datum der Behandlung
    public void setDate(String date) {
        this.date = DateConverter.convertStringToLocalDate(date);
    }

    // Setzt den Beginn der Behandlung
    public void setBegin(String begin) {
        this.begin = DateConverter.convertStringToLocalTime(begin);
    }

    // Setzt das Ende der Behandlung
    public void setEnd(String end) {
        this.end = DateConverter.convertStringToLocalTime(end);
    }

    // Gibt die Beschreibung der Behandlung zurück
    public String getDescription() {
        return description;
    }

    // Setzt die Beschreibung der Behandlung
    public void setDescription(String description) {
        this.description = description;
    }

    // Gibt die Anmerkungen zur Behandlung zurück
    public String getRemarks() {
        return remarks;
    }

    // Setzt die Anmerkungen zur Behandlung
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    // Gibt eine String-Repräsentation des Treatment-Objekts zurück
    public String toString() {
        return "\nBehandlung" + "\nTID: " + this.tid +
                "\nPID: " + this.pid +
                "\nDate: " + this.date +
                "\nBegin: " + this.begin +
                "\nEnd: " + this.end +
                "\nDescription: " + this.description +
                "\nRemarks: " + this.remarks + "\n";
    }
}
