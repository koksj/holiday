package io.centilliard.entity;

import java.io.Serializable;
import java.util.Collection;

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
        @Index(name = "", columnList = "", unique = true)
})
public class CountryEntity implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "countrySeq", sequenceName = "country_pk_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "countrySeq")
    private long countryPk;

    @Column(name = "country_name", nullable = false)
    private String name;

    @Column(name = "country_code", nullable = false)
    private String code;

    @OneToMany()
    private Collection<HolidayEntity> holidayEntities;

    public CountryEntity() {

    }



}
