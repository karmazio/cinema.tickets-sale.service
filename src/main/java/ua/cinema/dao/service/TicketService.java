package ua.cinema.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cinema.dao.entity.Performance;
import ua.cinema.dao.entity.Ticket;
import ua.cinema.dao.entity.User;
import ua.cinema.dao.repository.TicketRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAll() {
        List<Ticket> tickets = new ArrayList<>();
        ticketRepository.findAll().forEach(tickets::add);
        return tickets;
    }

    @Transactional
    public void saveTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public Ticket findById(Long id) {
        return ticketRepository.findTicketById(id);
    }

    public List<Ticket> findByPerformance(Performance performance) {
        return ticketRepository.findTicketsByPerformance(performance);
    }

    public List<Ticket> findByLineAndPerformance(Integer line, Performance performance) {
        return ticketRepository.findAllByLineAndAndPerformance(line, performance);
    }

    public List<Ticket> findTicketsByUser(User user) {
        return ticketRepository.findTicketsByUser(user);
    }

    public List<Ticket> findTicketsByUserOrderByPurchaseTime(User user) {
        return ticketRepository.findTicketsByUserOrderByPurchaseTime(user);
    }

    public List<Ticket> findTicketsByStatus(boolean reserved) {
        return ticketRepository.findTicketsByReserved(reserved);
    }

    @Transactional
    public void deleteTicketsByPerformance(Performance performance) {
        ticketRepository.deleteTicketsByPerformance(performance);
    }
}
