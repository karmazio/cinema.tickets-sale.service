package ua.cinema.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.cinema.dao.entity.User;
import ua.cinema.dao.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Transactional
    public void createUser(User user) {
        userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }
}
