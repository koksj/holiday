package io.centilliard.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;

import io.centilliard.dto.HolidayDto;
import io.centilliard.entity.HolidayEntity;

@Mapper(config = QuarkusMappingConfig.class)
public interface Holiday {

    HolidayDto mapToDto(HolidayEntity holidayEntity);

    HolidayEntity mapToEntity(HolidayDto holidayDto);

    List<HolidayEntity> mapToEntityList(final List<HolidayDto> holidays);

    List<HolidayDto> mapToDtoList(final List<HolidayEntity> holidayEntities);
    
}