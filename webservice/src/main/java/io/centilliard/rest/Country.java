package io.centilliard.rest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Country {

    private String name;
    private String code;
    private List<Holiday> holidays = new ArrayList<>();

    public Country() {
    }

    public Country(String code) {
        this.code = code;
    }

    public Country(String name, String code) {
        this.code = code;
        this.name = name;
    }

    public Country(String name, String code, List<Holiday> holidays) {
        this.code = code;
        this.name = name;
        this.holidays.addAll(holidays);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Holiday> getHolidays() {

        return holidays;
    }

    public void setHolidays(List<Holiday> holidays) {
        this.holidays = new ArrayList<>(
                holidays.stream().sorted(Comparator.comparing(Holiday::getDate)).collect(Collectors.toList()));
    }

}
