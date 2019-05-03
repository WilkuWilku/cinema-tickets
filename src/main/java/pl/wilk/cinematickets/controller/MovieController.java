package pl.wilk.cinematickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wilk.cinematickets.model.MovieEntity;
import pl.wilk.cinematickets.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity newMovie(@RequestBody MovieEntity movie){
        movieService.addMovie(movie);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public List<MovieEntity> listOfMovies(){
        return movieService.findAllMovies();
    }

    @PutMapping("/{id}")
    public ResponseEntity editMovie(@RequestBody MovieEntity newMovie, @PathVariable Long id){
        movieService.editMovie(newMovie, id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
