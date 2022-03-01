package io.centilliard.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;

import io.centilliard.dto.CountryDto;
import io.centilliard.entity.CountryEntity;

@Mapper(config = QuarkusMappingConfig.class)
public interface Country {

    CountryDto mapToDto(CountryEntity countryEntity);
    CountryEntity mapToEntity(CountryDto countryDto);
    List<CountryDto> mapToList(List<CountryEntity> countries);    
}
