package ua.cinema.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "ticket")
@NoArgsConstructor
public class Ticket implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "reserved", columnDefinition = "TINYINT")
    private boolean reserved;

    @Column(name = "price", columnDefinition = "INT")
    private Integer price;

    @Column(name = "line", columnDefinition = "INT")
    private Integer line;

    @Column(name = "seat", columnDefinition = "INT")
    private Integer seat;

    @OneToOne
    @JoinColumn(name = "user")
    private User user;

    @OneToOne
    @JoinColumn(name = "performance")
    private Performance performance;

    @Column(name = "purchaseTime", columnDefinition = "DATETIME")
    private Timestamp purchaseTime;
}
