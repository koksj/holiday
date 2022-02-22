package io.centilliard.application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import io.centilliard.rest.Country;
import io.centilliard.rest.Holiday;

public class Misc {

    public static void main(String[] args) {   
        
    }

    public static void test() {

        Country country = new Country("Israel","IL");                        
        
        country.getHolidays().add(new Holiday("First day of Passover", LocalDate.of(2022, 4, 15)));
        country.getHolidays().add(new Holiday("First day of Passover", LocalDate.of(2022, 4, 16)));
        country.getHolidays().add(new Holiday("Seventh day of Passover", LocalDate.of(2022, 4, 21)));
        country.getHolidays().add(new Holiday("Seventh day of Passover", LocalDate.of(2022, 4, 22)));
        country.getHolidays().add(new Holiday("Yom Ha'atzmaut", LocalDate.of(2022, 5, 4)));
        country.getHolidays().add(new Holiday("Yom Ha'atzmaut", LocalDate.of(2022, 5, 5)));
        country.getHolidays().add(new Holiday("Shavuot", LocalDate.of(2022, 6, 4)));
        country.getHolidays().add(new Holiday("Shavuot", LocalDate.of(2022, 6, 5)));
        country.getHolidays().add(new Holiday("Rosh Hashanah", LocalDate.of(2022, 9, 25)));
        country.getHolidays().add(new Holiday("Rosh Hashanah", LocalDate.of(2022, 9, 26)));
        country.getHolidays().add(new Holiday("Rosh Hashanah", LocalDate.of(2022, 9, 27)));
        country.getHolidays().add(new Holiday("Yom Kippur", LocalDate.of(2022, 10, 4)));
        country.getHolidays().add(new Holiday("Yom Kippur", LocalDate.of(2022, 10, 5)));
        country.getHolidays().add(new Holiday("First day of Sukkot", LocalDate.of(2022, 10, 9)));
        country.getHolidays().add(new Holiday("First day of Sukkot", LocalDate.of(2022, 10, 10)));
        country.getHolidays().add(new Holiday("Shemini Atzeret", LocalDate.of(2022, 10, 16)));
        country.getHolidays().add(new Holiday("Shemini Atzeret", LocalDate.of(2022, 10, 17)));
        country.getHolidays().add(new Holiday("Simchat Torah", LocalDate.of(2022, 10, 16)));
        country.getHolidays().add(new Holiday("Simchat Torah", LocalDate.of(2022, 10, 17)));
        
        List<Holiday> list =
        country.getHolidays().stream().sorted(Comparator.comparing(Holiday::getDate).reversed()).collect(Collectors.toList());
        list.forEach(action -> System.out.println(action.getDate().format(DateTimeFormatter.BASIC_ISO_DATE)));

    }
    
}
