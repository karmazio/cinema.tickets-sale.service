package ua.cinema.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cinema.dao.entity.Movie;
import ua.cinema.dao.entity.Performance;
import ua.cinema.dao.repository.PerformanceRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PerformanceService {
    @Autowired
    private PerformanceRepository performanceRepository;

    public List<Performance> getAll() {
        List<Performance> performances = new ArrayList<>();
        performanceRepository.findAll().forEach(performances::add);
        return performances;
    }
    public List<Performance> getAllOrdered() {
        return new ArrayList<>(performanceRepository.findAllByOrderByDisplayedAscDateAscStartAsc());
    }



    @Transactional
    public void savePerformance(Performance performance) {
        performanceRepository.save(performance);
    }

    @Transactional
    public void deletePerformance(Long id) {
        performanceRepository.deleteById(id);
    }

    public Performance findById(Long id) {
        return performanceRepository.findPerformanceById(id);
    }

    public List<Performance> findByMovie(Movie movie, boolean displayed) {
        return performanceRepository.findPerformancesByMovieAndDisplayedOrderByDateAscStartAsc(movie, displayed);
    }
}
