package pl.wilk.cinematickets.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import pl.wilk.cinematickets.dto.MovieDto;
import pl.wilk.cinematickets.model.MovieEntity;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class MovieMapper {

    public abstract MovieEntity toMovieEntity(MovieDto dto);

    public abstract MovieDto toMovieDto(MovieEntity entity);
}
