package br.com.as.cinema.internal.api;

import br.com.as.cinema.external.domain.RoomSeats;
import br.com.as.cinema.internal.configuration.CinemaConstants;
import br.com.as.cinema.internal.domain.Room;
import br.com.as.cinema.internal.usecase.RoomCreate;
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
class RoomControllerTest {

    private static final String URL = CinemaConstants.URL_PREFIX_V1 + "rooms";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private RoomCreate roomCreate;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Transactional
    void getByUuid() throws Exception {
        Room room = roomCreate.execute(createRoomSeats());
        mockMvc.perform(get(URL + "/" + room.getUuid()))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", is(room.getUuid())));
    }

    @Test
    @Transactional
    void getAll() throws Exception {
        Room room = roomCreate.execute(createRoomSeats());
        mockMvc.perform(get(URL))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].uuid", is(room.getUuid())));
    }

    @Test
    @Transactional
    void createWithSeats() throws Exception {
        RoomSeats room = createRoomSeats();
        mockMvc.perform(post(URL + "/seats")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtils.toJson(room)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is(room.getDescription())));
    }

    @Test
    @Transactional
    void create() throws Exception {
        Room room = createRoom();
        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtils.toJson(room)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is(room.getDescription())))
                .andExpect(jsonPath("$.uuid", is(room.getUuid())));
    }

    @Test
    @Transactional
    void update() throws Exception {
        Room room = roomCreate.execute(createRoom());
        Room update = new Room()
                .setDescription("New Description")
                .setNumber("5");

        mockMvc.perform(put(URL + "/" + room.getUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtils.toJson(update)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", is(room.getUuid())))
                .andExpect(jsonPath("$.description", is(update.getDescription())))
                .andExpect(jsonPath("$.number", is(update.getNumber())));
    }

    @Test
    @Transactional
    void updateNotFound() throws Exception {
        Room room = createRoom();
        mockMvc.perform(put(URL + "/" + room.getUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtils.toJson(room)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(status().reason(Matchers.containsString("UUID not found")));
    }

    private Room createRoom() {
        return new Room()
                .setNumber("1")
                .setDescription("VIP");
    }
    private RoomSeats createRoomSeats() {
        return new RoomSeats()
                .setNumber("1")
                .setDescription("VIP")
                .setRows(3)
                .setSeatsPerRow(3);
    }
}