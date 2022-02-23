package io.centilliard.rest;

import java.sql.Date;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.centilliard.entity.CountryEntity;
import io.centilliard.entity.HolidayEntity;

@Path("/public")
public class HolidayResource {

    private static final Logger log = LoggerFactory.getLogger(HolidayResource.class);

    @Inject
    private EntityManager em;

    /**
     * Return the public holidays for a country name
     * @param country
     * @return
     */
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/holidays/country/{name}")
    public Response getPublicHolidaysByName(@PathParam("name") String name) {

        Country country = new Country(name);
       
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<CountryEntity> criteriaQuery = criteriaBuilder.createQuery(CountryEntity.class);
        Root<CountryEntity> from = criteriaQuery.from(CountryEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(from.get("name"), country.getCode()));

        TypedQuery<CountryEntity> typedQuery = em.createQuery(criteriaQuery);

        CountryEntity countryEntity = new CountryEntity();

        try {
            countryEntity = typedQuery.getSingleResult();

        } catch ( PersistenceException e) {
            log.error(e.getMessage());
            countryEntity = new CountryEntity();
        }

        List<Holiday> holidays = new ArrayList<>();

        /* Check for null */
        if (countryEntity.getHolidayEntities() != null && countryEntity.getHolidayEntities().size() > 0) {
            countryEntity.getHolidayEntities().forEach(holidayEntity -> {
                holidays.add(new Holiday(holidayEntity.getName(), holidayEntity.getDate().toLocalDate()));
            });
        }

        country = new Country(countryEntity.getName(), countryEntity.getCode(), holidays);

        return Response.ok(country).build();
    }
  
    /**
     * Return the public holidays for a ISO country code
     * @param country
     * @return
     */
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/holidays/country/{code}")
    public Response getPublicHolidaysByCode(@PathParam("code") String code) {

        Country country = new Country(code);
       
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<CountryEntity> criteriaQuery = criteriaBuilder.createQuery(CountryEntity.class);
        Root<CountryEntity> from = criteriaQuery.from(CountryEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(from.get("code"), country.getCode()));

        TypedQuery<CountryEntity> typedQuery = em.createQuery(criteriaQuery);

        CountryEntity countryEntity = new CountryEntity();

        try {
            countryEntity = typedQuery.getSingleResult();

        } catch ( PersistenceException e) {
            log.error(e.getMessage());
            countryEntity = new CountryEntity();
        }

        List<Holiday> holidays = new ArrayList<>();

        /* Check for null */
        if (countryEntity.getHolidayEntities() != null && countryEntity.getHolidayEntities().size() > 0) {
            countryEntity.getHolidayEntities().forEach(holidayEntity -> {
                holidays.add(new Holiday(holidayEntity.getName(), holidayEntity.getDate().toLocalDate()));
            });
        }

        country = new Country(countryEntity.getName(), countryEntity.getCode(), holidays);

        return Response.ok(country).build();
    }

    /**
     * Create a new public holiday
     * @param country
     * @return
     */
    @POST
    @Path("/holiday/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response setHoliday(Country country) {

        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setCode(country.getCode());
        countryEntity.setName(country.getName());

        List<HolidayEntity> holidayEntities = new ArrayList<>();

        country.getHolidays().forEach(holiday -> {
            // Convert to mili seconds since epoch
            Long mili = holiday.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
            holidayEntities.add(new HolidayEntity(holiday.getName(), new Date(mili)));
        });

        countryEntity.setHolidayEntities(holidayEntities);

        try {
            em.persist(countryEntity);
        } catch (EntityExistsException | IllegalArgumentException | TransactionException e) {
            log.error(e.getMessage());
        }

        return Response.ok(country).build();
    }

}
