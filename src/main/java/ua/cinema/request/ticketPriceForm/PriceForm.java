package ua.cinema.request.ticketPriceForm;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PriceForm {

    private List<Price> prices;

    public PriceForm(List<Price> prices) {
        this.prices = prices;
    }

    public void addPrice(Price price) {
        this.prices.add(price);
    }
}
