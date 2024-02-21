package br.com.as.cinema.external.usecase;

import br.com.as.cinema.internal.configuration.EntitySpecification;
import br.com.as.cinema.internal.domain.Room;
import br.com.as.cinema.internal.repository.BaseRepository;
import br.com.as.cinema.internal.usecase.RoomRetrieve;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnSingleCandidate(RoomRetrieve.class)
public class RoomRetrieveImpl extends BaseRetrieveImpl<Room> implements RoomRetrieve {

    public RoomRetrieveImpl(BaseRepository<Room> repository, EntitySpecification entitySpecification) {
        super(repository, entitySpecification);
    }
}
