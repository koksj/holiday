package io.centilliard.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.transaction.TransactionalException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.centilliard.dto.CountryDto;
import io.centilliard.dto.HolidayDto;
import io.centilliard.entity.CountryEntity;
import io.centilliard.entity.HolidayEntity;
import io.centilliard.mapstruct.Country;
import io.centilliard.mapstruct.Holiday;

@ApplicationScoped
public class HolidayService {

    private static Logger log = LoggerFactory.getLogger(HolidayService.class);

    @Inject
    EntityManager em;

    @Inject
    Country country;

    @Inject
    Holiday holiday;

    public HolidayService() {

    }

    /**
     * 
     * @param countryDto
     */
    @Transactional
    public void persistCountry(CountryDto countryDto) {

        CountryEntity countryEntity = country.mapToEntity(countryDto);
        List<HolidayEntity> holidays = holiday.mapToEntityList(countryDto.getHolidays());

        holidays.forEach(holiday -> {
            holiday.setCountryEntity(countryEntity);
        });

        countryEntity.setHolidayEntities(holidays);

        try {
            em.persist(countryEntity);
        } catch (EntityExistsException | IllegalArgumentException | TransactionalException e) {
            log.error(e.getMessage());
        }

    }

    /**
     * 
     * @return
     */
    public  List<CountryDto> getCountries() {

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

        List<CountryDto> countries = country.mapToList(countryEntitys);
        
        if(countries == null) {
            countries =  new ArrayList<>();
            log.info("No countries found.");
        } else {

        }

        return countries;
    }

    /**
     * 
     * @param country
     * @return
     */
    public CountryDto getPublicHolidays(CountryDto country) {

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

        country = this.country.mapToDto(countryEntity);
        List<HolidayDto> holidays = this.holiday.mapToDtoList(countryEntity.getHolidayEntities());

        if(holidays == null) {
            holidays = new ArrayList<>();
        }

        country.setHolidays(holidays);
             
        return country;
    }

}
