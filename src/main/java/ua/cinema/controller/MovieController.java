package ua.cinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.cinema.dao.entity.Movie;
import ua.cinema.dao.service.MovieService;
import ua.cinema.image.service.FileStorageServiceImpl;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    FileStorageServiceImpl storageService;

    @GetMapping("/repertoire")
    public String movies(Model model) {
        List<Movie> movies = movieService.getAll();
        model.addAttribute("repertoire", movies);
        return "repertoire";
    }

    @RequestMapping("/repertoire/add")
    public String addMovie(Model model) {
        model.addAttribute("movie", new Movie());
        return "add-movie";
    }

    @PostMapping("/repertoire/create")
    public String createMovie(@ModelAttribute("movie") Movie movie) {
        movieService.saveMovie(movie);
        return "redirect:/repertoire";
    }

    @PostMapping("/repertoire/delete/{id}")
    public String deleteMovie(@PathVariable("id") Long id) {
        movieService.deleteMovie(id);
        return "redirect:/repertoire";
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        Resource file = storageService.load(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/images/upload/{id}")
    public String uploadImage(@PathVariable(name = "id") Long id, Model model, @RequestParam("file") MultipartFile file) {
        String message = "";

        try {
            storageService.save(file);
            Movie movie = movieService.findById(id);
            movie.setImage(file.getOriginalFilename());
            movieService.saveMovie(movie);

            message = "Uploaded the image successfully: " + file.getOriginalFilename();
            model.addAttribute("message", message);
        } catch (Exception e) {
            message = "Could not upload the image: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            model.addAttribute("message", message);
        }

        return "redirect:/repertoire";
    }

    @GetMapping("/images/delete/{filename:.+}")
    public String deleteImage(@PathVariable String filename, RedirectAttributes redirectAttributes) {
        try {
            boolean existed = storageService.delete(filename);

            if (existed) {
                Movie movie = movieService.findByImage(filename);
                movie.setImage(null);
                redirectAttributes.addFlashAttribute("message", "Delete the image successfully: " + filename);
            } else {
                redirectAttributes.addFlashAttribute("message", "The image does not exist!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message",
                    "Could not delete the image: " + filename + ". Error: " + e.getMessage());
        }

        return "redirect:/repertoire";
    }
}
