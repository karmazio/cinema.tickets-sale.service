package ua.cinema;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ua.cinema.image.service.FileStorageService;

import javax.annotation.Resource;

@SpringBootApplication
@EnableWebMvc
@EnableJpaRepositories
public class App implements CommandLineRunner {

    @Resource
    FileStorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {
//    storageService.deleteAll();
        storageService.init();
    }
}
