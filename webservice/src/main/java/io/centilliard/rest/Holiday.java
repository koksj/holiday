package io.centilliard.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Holiday {

    private LocalDate date;
    private String name;

    public Holiday() {

    }

    public Holiday(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {

        return name + " " + date.format(DateTimeFormatter.BASIC_ISO_DATE);
    }

}
