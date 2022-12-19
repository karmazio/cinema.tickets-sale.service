package ua.cinema.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.cinema.dao.entity.Hall;
import ua.cinema.dao.repository.HallRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class HallService {

    @Autowired
    private HallRepository hallRepository;

    public List<Hall> getAllHalls() {
        List<Hall> halls = new ArrayList<>();
        hallRepository.findAll().forEach(halls::add);
        return halls;
    }
}
