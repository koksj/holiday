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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
    EntityManager em;

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
    public Response getPublicHolidays(Country country) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<CountryEntity> criteriaQuery = criteriaBuilder.createQuery(CountryEntity.class);
        Root<CountryEntity> from = criteriaQuery.from(CountryEntity.class);

        if (country.getCode() == null || country.getCode().isEmpty()) {
            log.info("Code is null or empty");
            criteriaQuery.where(criteriaBuilder.equal(from.get("name"), country.getName()));
        } else if (country.getName() == null || country.getName().isEmpty()) {
            log.info("Name is null or empty");
            criteriaQuery.where(criteriaBuilder.equal(from.get("code"), country.getCode()));
        }

        TypedQuery<CountryEntity> typedQuery = em.createQuery(criteriaQuery);

        CountryEntity countryEntity = new CountryEntity();

        try {
            countryEntity = typedQuery.getSingleResult();

        } catch (PersistenceException e) {
            log.error("PersistenceException " + e.getMessage());
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
     * Create new public holidays
     * 
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
            holidayEntities.add(new HolidayEntity(holiday.getName(), new Date(mili),countryEntity));
        });

        countryEntity.setHolidayEntities(holidayEntities);

        try {
            em.persist(countryEntity);            
        } catch (EntityExistsException | IllegalArgumentException | TransactionException e) {
            //log.error(e.getMessage());
        }

        return Response.ok(country).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/countries")
    public Response getCountries() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CountryEntity> cq = cb.createQuery(CountryEntity.class);
        Root<CountryEntity> rootEntry = cq.from(CountryEntity.class);
        CriteriaQuery<CountryEntity> all = cq.select(rootEntry);

        TypedQuery<CountryEntity> allQuery = em.createQuery(all);
        List<CountryEntity> countryEntitys = new ArrayList<>();

        try {
            countryEntitys = allQuery.getResultList();
        } catch (PersistenceException e) {
            log.error("PersistenceException " + e.getMessage());            
        }

        List<Country> countries = new ArrayList<>();

        countryEntitys.forEach(country -> {
            countries.add(new Country(country.getName(), country.getCode()));
        });

        return Response.ok(countries).build();
    }

}
