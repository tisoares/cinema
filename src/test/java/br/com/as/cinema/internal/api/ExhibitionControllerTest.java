package br.com.as.cinema.internal.api;

import br.com.as.cinema.BaseTest;
import br.com.as.cinema.external.domain.ExhibitionRequest;
import br.com.as.cinema.external.domain.RoomSeats;
import br.com.as.cinema.internal.configuration.CinemaConstants;
import br.com.as.cinema.internal.domain.Exhibition;
import br.com.as.cinema.internal.domain.Movie;
import br.com.as.cinema.internal.domain.Price;
import br.com.as.cinema.internal.domain.Room;
import br.com.as.cinema.internal.domain.enums.ExhibitionStatus;
import br.com.as.cinema.internal.domain.enums.MovieStatus;
import br.com.as.cinema.internal.domain.enums.PriceStatus;
import br.com.as.cinema.internal.usecase.ExhibitionCreate;
import br.com.as.cinema.internal.usecase.MovieCreate;
import br.com.as.cinema.internal.usecase.PriceCreate;
import br.com.as.cinema.internal.usecase.RoomCreate;
import br.com.as.cinema.utils.JsonUtils;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ExhibitionControllerTest extends BaseTest {

    private static final String URL = CinemaConstants.URL_PREFIX_V1 + "exhibitions";

    @Autowired
    private ExhibitionCreate exhibitionCreate;
    @Autowired
    private RoomCreate roomCreate;
    @Autowired
    private PriceCreate priceCreate;
    @Autowired
    private MovieCreate movieCreate;

    @Test
    @Transactional
    void getByUuid() throws Exception {
        Exhibition exhibition = exhibitionCreate.execute(createExhibition(1));
        mockMvc.perform(get(URL + "/" + exhibition.getUuid()))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", is(exhibition.getUuid())));
    }

    @Test
    @Transactional
    void getAll() throws Exception {
        Exhibition exhibition = exhibitionCreate.execute(createExhibition(2));
        mockMvc.perform(get(URL)
                        .param("$page", "0")
                        .param("$size", "11")
                        .param("$sort", "uuid desc")
                        .param("$sort", "status asc")
                )
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].uuid", is(exhibition.getUuid())));
    }

    @Test
    @Transactional
    void create() throws Exception {
        ExhibitionRequest exhibition = createExhibition(3);
        String json = JsonUtils.toJson(exhibition);
        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.uuid", is(exhibition.getUuid())));
    }

    @Test
    @Transactional
    void update() throws Exception {
        Exhibition exhibition = exhibitionCreate.execute(createExhibition(4));
        ExhibitionRequest update = new ExhibitionRequest().setStatus(ExhibitionStatus.CANCELED);
        mockMvc.perform(put(URL + "/" + exhibition.getUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtils.toJson(update)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", is(exhibition.getUuid())))
                .andExpect(jsonPath("$.status", is(update.getStatus().name())));
    }

    @Test
    @Transactional
    void updateNotFound() throws Exception {
        ExhibitionRequest exhibition = createExhibition(5);
        String json = JsonUtils.toJson(exhibition);
        mockMvc.perform(put(URL + "/" + exhibition.getUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(status().reason(containsString("UUID not found")));
    }

    private ExhibitionRequest createExhibition(int roomNumber) {
        Room room = roomCreate.execute(new RoomSeats()
                .setSeatsPerRow(2)
                .setRows(2)
                .setNumber(roomNumber + "A")
                .setDescription("Room number " + roomNumber));

        Price price = priceCreate.execute(new Price()
                .setAmount(new BigDecimal("10.50"))
                .setStatus(PriceStatus.ACTIVE)
                .setDescription("Regular")
                .setDetails("Regular Price"));

        Movie movie = movieCreate.execute(new Movie().setDescription("New Exhibition")
                .setStatus(MovieStatus.ACTIVE)
                .setDuration("125m"));

        return new ExhibitionRequest()
                .setExhibitionAt(LocalDateTime.now())
                .setStatus(ExhibitionStatus.ACTIVE)
                .setRoom(room)
                .setMovie(movie)
                .setPrices(Collections.singleton(price));
    }
}
