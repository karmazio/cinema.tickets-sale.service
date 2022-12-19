package ua.cinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.cinema.dao.entity.User;
import ua.cinema.dao.service.UserService;
import ua.cinema.request.ticketDisplay.Line;
import ua.cinema.request.ticketDisplay.TicketForm;
import ua.cinema.request.ticketPriceForm.Price;
import ua.cinema.request.ticketPriceForm.PriceForm;
import ua.cinema.dao.entity.Performance;
import ua.cinema.dao.entity.Ticket;
import ua.cinema.dao.service.PerformanceService;
import ua.cinema.dao.service.TicketService;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private UserService userService;

    @GetMapping("/schedule/createTickets/{id}")
    public String showCreateForm(@PathVariable("id") Long id, HttpSession session, Model model) {
        PriceForm priceForm = new PriceForm(new ArrayList<>());
        Performance performance = performanceService.findById(id);
        for (int i = 1; i <= performance.getHall().getRows(); i++) {
            Price price = new Price();
            price.setLineNumber(i);
            priceForm.addPrice(price);
        }
        model.addAttribute("title", performance.getMovie().getTitle());
        model.addAttribute("priceForm", priceForm);
        session.setAttribute("performanceId", id);
        return "set-price";
    }

    @PostMapping("/schedule/createTickets/prices/save")
    public String setPrices(@ModelAttribute("priceForm") PriceForm priceForm,
                            HttpSession session) {
        Performance performance = performanceService.findById((Long) session.getAttribute("performanceId"));
        int ticketsTotal = 0;
        int priceTotal = 0;
        for (int i = 1; i <= performance.getHall().getRows(); i++) {
            for (int j = 1; j <= performance.getHall().getColumns(); j++) {
                Ticket ticket = new Ticket();
                ticket.setPerformance(performance);
                ticket.setReserved(false);
                ticket.setLine(i);
                ticket.setSeat(j);
                ticket.setPrice(priceForm.getPrices().get(i - 1).getPrice());
                ticketsTotal++;
                priceTotal += ticket.getPrice();
                ticketService.saveTicket(ticket);
            }
        }
        performance.setDisplayed(true);
        performanceService.savePerformance(performance);
        session.setAttribute("ticketsTotal", ticketsTotal);
        session.setAttribute("priceTotal", priceTotal);
        return "tickets-total";
    }

    @GetMapping("/schedule/createTickets/index")
    public String backToMain() {
        return "redirect:/index";
    }

    @GetMapping("/myTickets")
    public String showMyTickets(Model model, HttpSession session) {
        User user = userService.findUserByLogin((String) session.getAttribute("username"));
        List<Ticket> tickets = ticketService.findTicketsByUserOrderByPurchaseTime(user);
        Integer sum = tickets.stream()
                .mapToInt(Ticket::getPrice)
                .sum();
        model.addAttribute("sumToPay", sum);
        model.addAttribute("cart", tickets);
        return "cart";
    }

    @GetMapping("/buy/{id}")
    public String showPurchasePage(@PathVariable("id") Long id, HttpSession session, Model model) {
        Performance performance = performanceService.findById(id);

        TicketForm ticketForm = new TicketForm(new ArrayList<>());
        for (int i = 1; i < performance.getHall().getRows(); i++) {
            Line line = new Line(i, ticketService.findByLineAndPerformance(i, performance));
            ticketForm.addLine(line);
        }
        model.addAttribute("ticketForm", ticketForm);
        session.setAttribute("performance", performance);
        return "buy";
    }

    @GetMapping("/buy/purchase/{ticketId}")
    public String purchaseSuccessful(@PathVariable("ticketId") Long id, HttpSession session, Model model) {
        Ticket ticket = ticketService.findById(id);
        User user = userService.findUserByLogin((String) session.getAttribute("username"));
        ticket.setUser(user);
        ticket.setReserved(true);
        ticket.setPurchaseTime(new Timestamp(System.currentTimeMillis()));
        ticketService.saveTicket(ticket);
        model.addAttribute("ticketPurchased", ticket);
        return "purchased";
    }



    @GetMapping("/back")
    public String returnMain(){
        return "redirect:/user-schedule";
    }

    @GetMapping("/tickets-sold")
    public String viewSoldTickets(Model model) {
        List<Ticket> sold = ticketService.findTicketsByStatus(true);
        Integer sum = sold.stream()
                .mapToInt(Ticket::getPrice)
                .sum();
        model.addAttribute("sold", sold);
        model.addAttribute("sumOfSales", sum);
        return "tickets-sold";
    }
}
