package ua.cinema.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cinema.dao.entity.Movie;
import ua.cinema.dao.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {


    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAll() {
        List<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movies::add);
        return movies;
    }

    public Movie findById(Long id) {
        return movieRepository.findMovieById(id);
    }

    @Transactional
    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Transactional
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public Movie findByImage(String image) {
        return movieRepository.findMovieByImage(image);
    }

}
