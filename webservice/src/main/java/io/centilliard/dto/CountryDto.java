package io.centilliard.dto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CountryDto {

    private String name;
    private String code;
    private List<HolidayDto> holidays = new ArrayList<>();

    public CountryDto() {
    }

    public CountryDto(String code) {
        this.code = code;
    }

    public CountryDto(String name, String code) {
        this.code = code;
        this.name = name;
    }

    public CountryDto(String name, String code, List<HolidayDto> holidays) {
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

    public List<HolidayDto> getHolidays() {

        return holidays;
    }

    public void setHolidays(List<HolidayDto> holidays) {
        this.holidays = new ArrayList<>(
                holidays.stream().sorted(Comparator.comparing(HolidayDto::getDate)).collect(Collectors.toList()));
    }

}
