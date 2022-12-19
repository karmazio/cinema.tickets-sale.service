package ua.cinema.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.cinema.dao.entity.Performance;
import ua.cinema.dao.entity.Ticket;
import ua.cinema.dao.entity.User;

import java.util.List;


@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {

    List<Ticket> findTicketsByPerformance(Performance performance);

    List<Ticket> findTicketsByUser(User user);

    List<Ticket> findTicketsByUserOrderByPurchaseTime(User user);

    List<Ticket> findTicketsByReserved(boolean reserved);

    @Query("select t from Ticket t where t.line = ?1 and t.performance = ?2")
    List<Ticket> findAllByLineAndAndPerformance(Integer Line, Performance performance);

    Ticket findTicketById(Long id);

    void deleteTicketsByPerformance(Performance performance);
}
