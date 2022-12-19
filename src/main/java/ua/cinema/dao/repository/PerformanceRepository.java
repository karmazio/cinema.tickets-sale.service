package ua.cinema.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.cinema.dao.entity.Movie;
import ua.cinema.dao.entity.Performance;

import java.util.List;

@Repository
public interface PerformanceRepository extends CrudRepository<Performance, Long> {

    Performance findPerformanceById(Long id);

    List<Performance> findPerformancesByMovieAndDisplayedOrderByDateAscStartAsc(Movie movie, boolean displayed);
}
