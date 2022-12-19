package ua.cinema.request.ticketDisplay;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.cinema.dao.entity.Ticket;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Line {
    private Integer lineNumber;
    private List<Ticket> tickets;

    public Line(Integer lineNumber, List<Ticket> tickets) {
        this.lineNumber = lineNumber;
        this.tickets = tickets;
    }
}
