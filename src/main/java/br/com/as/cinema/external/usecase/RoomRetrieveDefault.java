package br.com.as.cinema.external.usecase;

import br.com.as.cinema.internal.domain.Room;
import br.com.as.cinema.internal.repository.BaseRepository;
import br.com.as.cinema.internal.usecase.RoomRetrieve;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnSingleCandidate(RoomRetrieve.class)
public class RoomRetrieveDefault extends BaseRetrieveDefault<Room> implements RoomRetrieve {

    public RoomRetrieveDefault(BaseRepository<Room> repository) {
        super(repository);
    }
}
