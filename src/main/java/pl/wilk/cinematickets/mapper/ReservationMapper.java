package pl.wilk.cinematickets.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import pl.wilk.cinematickets.dto.ReservationDto;
import pl.wilk.cinematickets.model.ReservationEntity;
import pl.wilk.cinematickets.service.ScreeningService;

@Mapper(componentModel = "spring")
public abstract class ReservationMapper {

    @Autowired
    protected ScreeningService screeningService;

    @Mapping(target = "screening", expression = "java(screeningService.findScreeningById(dto.getScreeningId()))" )
    public abstract ReservationEntity toReservationEntity(ReservationDto dto);

    @Mapping(target = "screeningId", source = "screening.id")
    public abstract ReservationDto toReservationDto(ReservationEntity entity);

}
