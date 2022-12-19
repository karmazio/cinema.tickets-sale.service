package ua.cinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.cinema.dao.entity.Movie;
import ua.cinema.dao.entity.Performance;
import ua.cinema.dao.service.MovieService;
import ua.cinema.dao.service.PerformanceService;
import ua.cinema.dao.service.TicketService;
import ua.cinema.request.userScedule.MovieCard;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private MovieService movieService;


    @GetMapping("/index")
    public String schedule(Model model) {
        List<Performance> shows = performanceService.getAll();
        model.addAttribute("schedule", shows);
        return "index";
    }

    @PostMapping("/schedule/delete/{id}")
    public String deleteSession(@PathVariable("id") Long id) {
        ticketService.deleteTicketsByPerformance(performanceService.findById(id));
        performanceService.deletePerformance(id);
        return "redirect:/index";
    }

    @GetMapping("/user-schedule")
    public String getUserSchedule(Model model) {
        List<Movie> movies = movieService.getAll();
        List<MovieCard> cards = new ArrayList<>();
        for (Movie movie : movies) {
            MovieCard movieCard = new MovieCard();
            movieCard.setMovie(movie);
            movieCard.setPerformances(performanceService.findByMovie(movie, true));
            if(!movieCard.getPerformances().isEmpty()) {
                cards.add(movieCard);
            }
        }
        model.addAttribute("cards", cards);
        return "user-schedule";
    }
}
