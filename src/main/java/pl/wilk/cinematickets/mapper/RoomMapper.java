package pl.wilk.cinematickets.mapper;

import org.mapstruct.Mapper;
import pl.wilk.cinematickets.dto.RoomDto;
import pl.wilk.cinematickets.model.RoomEntity;

@Mapper(componentModel = "spring")
public abstract class RoomMapper {

    public abstract RoomEntity toRoomEntity(RoomDto dto);

    public abstract RoomDto toRoomEntity(RoomEntity entity);
}
