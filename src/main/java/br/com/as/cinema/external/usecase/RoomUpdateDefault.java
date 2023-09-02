package br.com.as.cinema.external.usecase;

import br.com.as.cinema.internal.domain.Room;
import br.com.as.cinema.internal.repository.BaseRepository;
import br.com.as.cinema.internal.usecase.RoomUpdate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnSingleCandidate(RoomUpdate.class)
public class RoomUpdateDefault extends BaseUpdateDefault<Room> implements RoomUpdate {
    public RoomUpdateDefault(BaseRepository<Room> repository) {
        super(repository);
    }

    @Override
    protected Room loadDependencies(Room request, Room saved) {
        return request;
    }
}
