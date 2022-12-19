package ua.cinema.request.ticketDisplay;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TicketForm {

    private List<Line> lines;

    public TicketForm(List<Line> lines) {
        this.lines = lines;
    }

    public void addLine(Line line) {
        this.lines.add(line);
    }
}
