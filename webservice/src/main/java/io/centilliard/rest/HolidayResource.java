package io.centilliard.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.centilliard.dto.CountryDto;
import io.centilliard.service.HolidayService;

@Path("/public")
public class HolidayResource {

    private static final Logger log = LoggerFactory.getLogger(HolidayResource.class);

    @Inject
    HolidayService holidayService;

    /**
     * Return the public holidays for a ISO country code
     * 
     * @param country
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/holidays/find")
    public Response getPublicHolidays(CountryDto country) {

        return Response.ok(holidayService.getPublicHolidays(country)).build();
    }

    /**
     * Create new public holidays
     * 
     * @param country
     * @return
     */
    @POST
    @Path("/holiday/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setHoliday(CountryDto country) {

        holidayService.persistCountry(country);

        return Response.ok().build();
    }

    @GET
    @Operation(summary = "List of countries only", description = "Will return a list of countries only. No holidays will be returned.")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/countries")
    public Response getCountries() {

        return Response.ok(holidayService.getCountries()).build();
    }

}
