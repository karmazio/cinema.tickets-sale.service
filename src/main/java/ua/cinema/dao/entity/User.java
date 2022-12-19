package ua.cinema.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "user")
@ToString
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    private String login;
    private String email;
    private String password;

    @Column(columnDefinition = "enum('ADMIN', 'CUSTOMER')")
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = Role.CUSTOMER;
    }
}
