package ua.cinema.request.userScedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.cinema.dao.entity.Movie;
import ua.cinema.dao.entity.Performance;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MovieCard {

    private Movie movie;

    private List<Performance> performances;
}
