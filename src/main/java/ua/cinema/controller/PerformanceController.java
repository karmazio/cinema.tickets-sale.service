package ua.cinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.cinema.dao.entity.Hall;
import ua.cinema.dao.entity.Performance;
import ua.cinema.dao.service.HallService;
import ua.cinema.dao.service.MovieService;
import ua.cinema.dao.service.PerformanceService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private HallService hallService;

    @Autowired
    private MovieService movieService;


    @RequestMapping("/performance/add/{id}")
    public String addPerformance(@PathVariable("id") Long id, HttpSession session, Model model) {
        session.setAttribute("movieId", id);
        List<Hall> halls = hallService.getAllHalls();
        model.addAttribute("halls", halls);
        model.addAttribute("performance", new Performance());

        return "add-session";
    }


    @PostMapping("/performance/create")
    public String createPerformance(@ModelAttribute("performance") Performance performance, HttpSession session) {
        performance.setMovie(movieService.findById((Long) session.getAttribute("movieId")));
        performance.setDisplayed(false);
        performanceService.savePerformance(performance);
        return "redirect:/index";
    }

}
