package de.hitec.nhplus.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateConverter {

    // Das Datum-Format (Jahr-Monat-Tag)
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    // Das Zeit-Format (Stunden:Minuten)
    private static final String TIME_FORMAT = "HH:mm";

    // Konvertiert einen String in ein LocalDate-Objekt
    public static LocalDate convertStringToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    // Konvertiert einen String in ein LocalTime-Objekt
    public static LocalTime convertStringToLocalTime(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    // Konvertiert ein LocalDate-Objekt in einen String
    public static String convertLocalDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    // Konvertiert ein LocalTime-Objekt in einen String
    public static String convertLocalTimeToString(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    }
}
