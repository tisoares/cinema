package br.com.as.cinema.external.usecase;

import br.com.as.cinema.external.domain.ExhibitionRequest;
import br.com.as.cinema.internal.domain.*;
import br.com.as.cinema.internal.domain.enums.ExhibitionSeatStatus;
import br.com.as.cinema.internal.domain.enums.SeatStatus;
import br.com.as.cinema.internal.repository.ExhibitionRepository;
import br.com.as.cinema.internal.usecase.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
@ConditionalOnSingleCandidate(ExhibitionCreate.class)
public class ExhibitionCreateImpl implements ExhibitionCreate {

    private final TransactionTemplate transactionManager;

    private final ExhibitionRepository exhibitionRepository;
    private final ExhibitionSeatCreate exhibitionSeatCreate;
    private final ExhibitionPriceCreate exhibitionPriceCreate;

    private final MovieRetrieve movieRetrieve;
    private final RoomRetrieve roomRetrieve;
    private final PriceRetrieve priceRetrieve;


    @Override
    public Exhibition execute(Exhibition exhibition) {
        return exhibitionRepository.save(exhibition);
    }

    @Override
    public Exhibition execute(ExhibitionRequest exhibitionRequest) {
        return transactionManager.execute(trx -> {
            Exhibition exhibition = new Exhibition()
                    .setExhibitionAt(exhibitionRequest.getExhibitionAt())
                    .setRoom(roomRetrieve.execute(exhibitionRequest.getRoom())) // TODO: Fetch Seats
                    .setMovie(movieRetrieve.execute(exhibitionRequest.getMovie()));
            exhibition.setUuid(exhibitionRequest.getUuid());

            this.execute(exhibition);

            this.makeExhibitionSeats(exhibition.getRoom().getSeats(), exhibition);
            this.makeExhibitionPrices(exhibitionRequest.getPrices(), exhibition);

            return exhibition;
        });
    }

    private void makeExhibitionSeats(Set<Seat> seats, final Exhibition exhibition) {
        if (Objects.isNull(seats) || seats.isEmpty()) {
            return;
        }
        seats.forEach(seat ->
                exhibitionSeatCreate.execute(
                        new ExhibitionSeat()
                                .setSeat(seat)
                                .setStatus(convertExhibitionStatus(seat.getStatus()))
                                .setExhibition(exhibition)));
    }

    private static ExhibitionSeatStatus convertExhibitionStatus(SeatStatus status) {
        return status == SeatStatus.ACTIVE ? ExhibitionSeatStatus.AVAILABLE : ExhibitionSeatStatus.UNAVAILABLE;
    }

    private void makeExhibitionPrices(Set<Price> prices, final Exhibition exhibition) {
        if (Objects.isNull(prices) || prices.isEmpty()) {
            return;
        }
        prices.forEach(price ->
                exhibitionPriceCreate.execute(new ExhibitionPrice()
                        .setExhibition(exhibition)
                        .setPrice(priceRetrieve.execute(price))));
    }
}
