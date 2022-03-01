package io.centilliard.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "holiday")
public class HolidayEntity implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "holidaySeq", sequenceName = "holiday_pk_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "holidaySeq")
    @Column(name = "holiday_pk")
    private Long id;
    private String name;
    private Date date;

    @ManyToOne
    private CountryEntity countryEntity;

    public HolidayEntity() {

    }

    public HolidayEntity(String name, Date date,CountryEntity countryEntity) {
        this.date = date;
        this.name = name;
        this.countryEntity = countryEntity;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryEntity getCountryEntity() {
        return countryEntity;
    }

    public void setCountryEntity(CountryEntity countryEntity) {
        this.countryEntity = countryEntity;
    }    

}
