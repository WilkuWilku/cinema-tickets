package pl.wilk.cinematickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wilk.cinematickets.dto.MovieDto;
import pl.wilk.cinematickets.mapper.MovieMapper;
import pl.wilk.cinematickets.model.MovieEntity;
import pl.wilk.cinematickets.service.MovieService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieMapper mapper;

    @PostMapping
    public ResponseEntity newMovie(@RequestBody MovieDto movieDto){
        movieService.addMovie(mapper.toMovieEntity(movieDto));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public List<MovieDto> listOfMovies(){
        return movieService.findAllMovies().stream()
                .map(movieEntity -> mapper.toMovieDto(movieEntity))
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity editMovie(@RequestBody MovieDto newMovieDto, @PathVariable Long id){
        movieService.editMovie(mapper.toMovieEntity(newMovieDto), id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
