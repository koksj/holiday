package io.centilliard.rest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/public")
public class HolidayResource {

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Inject
    EntityManager em;

    public HolidayResource() {

    }

    @GET
    @Path("/holidays")
    public Response getPublicHolidays() {

        Country country = new Country("Israel","IL");        
        List<Holiday> list = new ArrayList<>();                
        list.add(new Holiday("Simchat Torah", LocalDate.of(2022, 10, 16)));
        list.add(new Holiday("Simchat Torah", LocalDate.of(2022, 10, 17)));
        list.add(new Holiday("Shemini Atzeret", LocalDate.of(2022, 10, 16)));
        list.add(new Holiday("Shemini Atzeret", LocalDate.of(2022, 10, 17)));
        list.add(new Holiday("First day of Passover", LocalDate.of(2022, 4, 15)));
        list.add(new Holiday("First day of Passover", LocalDate.of(2022, 4, 16)));
        list.add(new Holiday("Seventh day of Passover", LocalDate.of(2022, 4, 21)));
        list.add(new Holiday("Seventh day of Passover", LocalDate.of(2022, 4, 22)));
        list.add(new Holiday("Yom Ha'atzmaut", LocalDate.of(2022, 5, 4)));
        list.add(new Holiday("Yom Ha'atzmaut", LocalDate.of(2022, 5, 5)));
        list.add(new Holiday("Shavuot", LocalDate.of(2022, 6, 4)));
        list.add(new Holiday("Shavuot", LocalDate.of(2022, 6, 5)));
        list.add(new Holiday("Rosh Hashanah", LocalDate.of(2022, 9, 25)));
        list.add(new Holiday("Rosh Hashanah", LocalDate.of(2022, 9, 26)));
        list.add(new Holiday("Rosh Hashanah", LocalDate.of(2022, 9, 27)));
        list.add(new Holiday("Yom Kippur", LocalDate.of(2022, 10, 4)));
        list.add(new Holiday("Yom Kippur", LocalDate.of(2022, 10, 5)));
        list.add(new Holiday("First day of Sukkot", LocalDate.of(2022, 10, 9)));
        list.add(new Holiday("First day of Sukkot", LocalDate.of(2022, 10, 10)));
        
        country.setHolidays(list);

        return Response.ok(country).build();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setHoliday(Country country) {

        return Response.ok(country).build();
    }

}
