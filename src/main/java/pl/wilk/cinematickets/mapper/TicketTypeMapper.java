package pl.wilk.cinematickets.mapper;

import org.mapstruct.Mapper;
import pl.wilk.cinematickets.dto.TicketTypeDto;
import pl.wilk.cinematickets.model.TicketTypeEntity;

@Mapper(componentModel = "spring")
public abstract class TicketTypeMapper {

    public abstract TicketTypeEntity toTicketTypeEntity(TicketTypeDto dto);

    public abstract TicketTypeDto toTicketTypeDto(TicketTypeEntity entity);
}
