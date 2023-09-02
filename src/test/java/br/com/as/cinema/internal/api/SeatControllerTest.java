package br.com.as.cinema.internal.api;

import br.com.as.cinema.internal.configuration.CinemaConstants;
import br.com.as.cinema.internal.domain.Room;
import br.com.as.cinema.internal.domain.Seat;
import br.com.as.cinema.internal.usecase.SeatCreate;
import br.com.as.cinema.utils.JsonUtils;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import jakarta.transaction.Transactional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class})
class SeatControllerTest {

    private static final String URL = CinemaConstants.URL_PREFIX_V1 + "seats";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private SeatCreate seatCreate;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

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
        Seat seat = createSeat();
        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtils.toJson(seat)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", is(seat.getUuid())));
    }

    @Test
    @Transactional
    void update() throws Exception {
        Seat seat = seatCreate.execute(createSeat());
        Seat update = new Seat()
                .setRow("A")
                .setNumber(5);

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
        Seat seat = createSeat();
        mockMvc.perform(put(URL + "/" + seat.getUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtils.toJson(seat)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(status().reason(Matchers.containsString("UUID not found")));
    }

    private Seat createSeat() {
        return new Seat()
                .setNumber(1)
                .setRow("A")
                .setRoom(new Room());
    }
}