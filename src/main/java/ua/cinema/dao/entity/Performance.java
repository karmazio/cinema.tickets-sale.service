package ua.cinema.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@Entity
@Table(name = "performance")
public class Performance implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date", columnDefinition = "DATE")
    private Date date;

    @Column(name = "start", columnDefinition = "TIME")
    private Time start;

    @OneToOne
    @JoinColumn(name = "movie")
    private Movie movie;

    @OneToOne
    @JoinColumn(name = "hall")
    private Hall hall;

    @Column(name = "displayed", columnDefinition = "TINYINT")
    private boolean displayed;
}
