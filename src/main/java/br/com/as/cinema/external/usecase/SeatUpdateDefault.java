package br.com.as.cinema.external.usecase;

import br.com.as.cinema.internal.domain.Seat;
import br.com.as.cinema.internal.repository.BaseRepository;
import br.com.as.cinema.internal.usecase.RoomRetrieve;
import br.com.as.cinema.internal.usecase.SeatUpdate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnSingleCandidate(SeatUpdate.class)
public class SeatUpdateDefault extends BaseUpdateDefault<Seat> implements SeatUpdate {

    private final RoomRetrieve roomRetrieve;

    public SeatUpdateDefault(BaseRepository<Seat> repository, RoomRetrieve roomRetrieve) {
        super(repository);
        this.roomRetrieve = roomRetrieve;
    }

    @Override
    protected Seat loadDependencies(Seat request, Seat saved) {
        request.setRoom(roomRetrieve.execute(request.getRoom()));
        return request;
    }
}
