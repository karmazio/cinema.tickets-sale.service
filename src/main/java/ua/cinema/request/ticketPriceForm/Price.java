package ua.cinema.request.ticketPriceForm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Price {

    private Integer lineNumber;

    private Integer price;
}
