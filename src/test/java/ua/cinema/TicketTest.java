package ua.cinema;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.cinema.dao.entity.Ticket;
import ua.cinema.dao.service.TicketService;

@SpringBootTest
public class TicketTest {

    @Autowired
    private TicketService ticketService;

    @Test
    void addTicketTest() {
        Ticket ticket = new Ticket();
//        ticket.setReserved(true);
        ticket.setPrice(100);
        ticket.setLine(10);
        ticket.setSeat(10);
        ticketService.saveTicket(ticket);
    }
}
