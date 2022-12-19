package ua.cinema.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "hall")
public class Hall implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;
    private String name;

    @Column(name = "rows", columnDefinition = "INT")
    private Integer rows;
    @Column(name = "columns", columnDefinition = "INT")
    private Integer columns;
}
