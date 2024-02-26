package br.com.as.cinema.external.api;

import br.com.as.cinema.external.domain.RoomSeats;
import br.com.as.cinema.external.domain.SearchCriteria;
import br.com.as.cinema.internal.api.RoomController;
import br.com.as.cinema.internal.domain.Room;
import br.com.as.cinema.internal.usecase.RoomCreate;
import br.com.as.cinema.internal.usecase.RoomRetrieve;
import br.com.as.cinema.internal.usecase.RoomUpdate;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(RoomController.class)
public class RoomControllerImpl implements RoomController {

    private final RoomRetrieve roomRetrieve;
    private final RoomCreate roomCreate;
    private final RoomUpdate roomUpdate;

    @Override
    public Optional<Room> getByUuid(String uuid) {
        return roomRetrieve.execute(uuid);
    }

    @Override
    public Page<Room> getAll(Pageable pageable, SearchCriteria searchCriteria) {
        return roomRetrieve.execute(pageable, searchCriteria);
    }

    @Override
    public Room create(Room room) {
        return roomCreate.execute(room);
    }

    @Override
    public Room create(RoomSeats roomSeats) {
        return roomCreate.execute(roomSeats);
    }

    @Override
    public Room update(String uuid, Room room) {
        return roomUpdate.execute(uuid, room);
    }
}
