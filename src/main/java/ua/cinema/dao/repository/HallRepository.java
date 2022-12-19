package ua.cinema.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.cinema.dao.entity.Hall;

@Repository
public interface HallRepository extends CrudRepository<Hall, Long> {
}
