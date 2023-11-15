package br.com.as.cinema.internal.api;

import br.com.as.cinema.BaseTest;
import br.com.as.cinema.external.domain.SeatRequest;
import br.com.as.cinema.internal.configuration.CinemaConstants;
import br.com.as.cinema.internal.domain.Room;
import br.com.as.cinema.internal.domain.Seat;
import br.com.as.cinema.internal.usecase.RoomCreate;
import br.com.as.cinema.internal.usecase.SeatCreate;
import br.com.as.cinema.utils.JsonUtils;
import jakarta.transaction.Transactional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SeatControllerTest extends BaseTest {

    private static final String URL = CinemaConstants.URL_PREFIX_V1 + "seats";

    @Autowired
    private SeatCreate seatCreate;

    @Autowired
    private RoomCreate roomCreate;

    @Test
    @Transactional
    void getByUuid() throws Exception {
        Seat seat = seatCreate.execute(createSeat());
        mockMvc.perform(get(URL + "/" + seat.getUuid()))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", is(seat.getUuid())));
    }

    @Test
    @Transactional
    void getAll() throws Exception {
        Seat seat = seatCreate.execute(createSeat());
        mockMvc.perform(get(URL))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].uuid", is(seat.getUuid())));
    }

    @Test
    @Transactional
    void create() throws Exception {
        SeatRequest seat = createSeat();
        String json = JsonUtils.toJson(seat);
        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", is(seat.getUuid())));
    }

    @Test
    @Transactional
    void update() throws Exception {
        Seat seat = seatCreate.execute(createSeat());
        SeatRequest update = new SeatRequest()
                .setRow("A")
                .setNumber(5)
                .setRoom(seat.getRoom());

        mockMvc.perform(put(URL + "/" + seat.getUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtils.toJson(update)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", is(seat.getUuid())))
                .andExpect(jsonPath("$.row", is(update.getRow())))
                .andExpect(jsonPath("$.number", is(update.getNumber())));
    }

    @Test
    @Transactional
    void updateNotFound() throws Exception {
        SeatRequest seat = createSeat();
        mockMvc.perform(put(URL + "/" + seat.getUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtils.toJson(seat)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(status().reason(Matchers.containsString("UUID not found")));
    }

    @Test
    @Transactional
    void updateNotFoundRoomNull() throws Exception {
        Seat seat = seatCreate.execute(createSeat());
        SeatRequest update = new SeatRequest()
                .setRow("A")
                .setNumber(5);
        mockMvc.perform(put(URL + "/" + seat.getUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtils.toJson(update)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(status().reason(Matchers.containsString("Entity Room not found")));
    }

    @Test
    @Transactional
    void updateNotFoundRoom() throws Exception {
        Seat seat = seatCreate.execute(createSeat());
        Room room = new Room();
        SeatRequest update = new SeatRequest()
                .setRow("A")
                .setNumber(5)
                .setRoom(room);
        mockMvc.perform(put(URL + "/" + seat.getUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtils.toJson(update)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(status().reason(Matchers.containsString(String.format("Entity Room %s not found", room.getUuid()))));
    }

    private SeatRequest createSeat() {
        Room room = roomCreate.execute(new Room()
                .setDescription("VIP")
                .setNumber("15"));

        return new SeatRequest()
                .setNumber(1)
                .setRow("A")
                .setRoom(room);
    }
}