package br.com.as.cinema.external.usecase;

import br.com.as.cinema.external.domain.ExhibitionRequest;
import br.com.as.cinema.internal.domain.*;
import br.com.as.cinema.internal.domain.enums.ExhibitionSeatStatus;
import br.com.as.cinema.internal.repository.ExhibitionRepository;
import br.com.as.cinema.internal.usecase.ExhibitionCreate;
import br.com.as.cinema.internal.usecase.PriceRetrieve;
import br.com.as.cinema.internal.usecase.RoomRetrieve;
import br.com.as.cinema.internal.usecase.SeatRetrieve;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@ConditionalOnSingleCandidate(ExhibitionCreate.class)
public class ExhibitionCreateDefault implements ExhibitionCreate {

    private final ExhibitionRepository exhibitionRepository;
    private final SeatRetrieve seatRetrieve;
    private final RoomRetrieve roomRetrieve;
    private final PriceRetrieve priceRetrieve;

    @Override
    public Exhibition execute(Exhibition exhibition) {
        return exhibitionRepository.save(exhibition);
    }

    @Override
    @Transactional
    public Exhibition execute(ExhibitionRequest exhibitionRequest) {
        Exhibition exhibition = new Exhibition()
                .setExhibitionAt(exhibitionRequest.getExhibitionAt())
                .setRoom(roomRetrieve.execute(exhibitionRequest.getRoom()))
                .setMovie(exhibitionRequest.getMovie());

        exhibition.setSeats(this.makeExhibitionSeats(exhibition.getRoom().getSeats(), exhibition));
        exhibition.setPrices(this.makeExhibitionPrices(exhibitionRequest.getPrices(), exhibition));
        exhibition.setUuid(exhibitionRequest.getUuid());
        return this.execute(exhibition);
    }

    private Set<ExhibitionSeat> makeExhibitionSeats(Set<Seat> seats, final Exhibition exhibition) {
        if (Objects.isNull(seats) || seats.isEmpty()) {
            return Collections.emptySet();
        }
        return seats.stream()
                .map(seat -> new ExhibitionSeat()
                        .setSeat(seat)
                        .setStatus(ExhibitionSeatStatus.AVAILABLE)
                        .setExhibition(exhibition))
                .collect(Collectors.toSet());
    }

    private Set<ExhibitionPrice> makeExhibitionPrices(Set<Price> prices, final Exhibition exhibition) {
        if (Objects.isNull(prices) || prices.isEmpty()) {
            return Collections.emptySet();
        }
        return prices.stream()
                .map(price -> new ExhibitionPrice()
                        .setExhibition(exhibition)
                        .setPrice(priceRetrieve.execute(price)))
                .collect(Collectors.toSet());
    }
}
