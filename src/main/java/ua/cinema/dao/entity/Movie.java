package ua.cinema.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "movie")
public class Movie implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private Long released;

    @Column(name = "durationM", columnDefinition = "INT")
    private Integer durationM;
    private String directed;

    @Column(name = "image")
    private String image;

    @Column(name = "genre")
    private String genre;

    @Column(name = "plot")
    private String plot;

}
