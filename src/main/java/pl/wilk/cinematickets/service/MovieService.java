package pl.wilk.cinematickets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wilk.cinematickets.model.MovieEntity;
import pl.wilk.cinematickets.repository.MovieRepository;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public void addMovie(MovieEntity movie){
        movieRepository.save(movie);
    }

    public void editMovie(MovieEntity newMovie, Long id){
        movieRepository.findById(id)
                .ifPresentOrElse(oldMovie -> oldMovie.setTitle(newMovie.getTitle()),
                        () -> new IllegalArgumentException("No movie found - id: "+id));
    }

    public List<MovieEntity> findAllMovies(){
        return movieRepository.findAll();
    }

}
