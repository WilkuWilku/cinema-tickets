package pl.wilk.cinematickets.mapper;

import org.mapstruct.Mapper;
import pl.wilk.cinematickets.dto.MovieDto;
import pl.wilk.cinematickets.model.MovieEntity;

@Mapper(componentModel = "spring")
public abstract class MovieMapper {

    public abstract MovieEntity toMovieEntity(MovieDto dto);

    public abstract MovieDto toMovieDto(MovieEntity entity);
}
