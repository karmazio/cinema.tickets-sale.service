package ua.cinema.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.cinema.dao.entity.Movie;


@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

    Movie findMovieById(Long id);

    Movie findMovieByImage(String image);
}
