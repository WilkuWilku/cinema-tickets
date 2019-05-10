package pl.wilk.cinematickets.service;

import com.sun.nio.sctp.IllegalReceiveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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
                .ifPresentOrElse(oldMovie -> {
                    if(newMovie.getTitle() != null)
                        oldMovie.setTitle(newMovie.getTitle());
                    movieRepository.save(oldMovie);
                        },
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No movie found - id: "+id));
    }

    public List<MovieEntity> findAllMovies(){
        return movieRepository.findAll();
    }

    public MovieEntity findMovieById(Long id){
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No movie found - id: "+id));
    }

}
