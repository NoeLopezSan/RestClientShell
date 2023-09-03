package dev.noelopez.client.format;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static LocalDate parseDate(String dob) {
        return LocalDate.parse(dob, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
