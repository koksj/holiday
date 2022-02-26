package io.centilliard.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "country", indexes = {
        @Index(name = "country_name_index", columnList = "country_name", unique = true),
        @Index(name = "country_code_index", columnList = "country_code", unique = true)
})
public class CountryEntity implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "countrySeq", sequenceName = "country_pk_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "countrySeq")
    private Long countryPk;

    @Column(name = "country_name", nullable = false)
    private String name;

    @Column(name = "country_code", nullable = false)
    private String code;

    @OneToMany( mappedBy = "countryEntity", cascade = CascadeType.ALL)
    private List<HolidayEntity> holidayEntities;

    public CountryEntity() {

    }

    public long getCountryPk() {
        return countryPk;
    }

    public void setCountryPk(long countryPk) {
        this.countryPk = countryPk;
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

    public List<HolidayEntity> getHolidayEntities() {
        return holidayEntities;
    }

    public void setHolidayEntities(List<HolidayEntity> holidayEntities) {
        this.holidayEntities = new ArrayList<>(holidayEntities);
    }



}
