package ua.cinema.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.cinema.dao.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserById(Long id);

    User findUserByLogin(String login);
}
