package br.com.as.cinema.internal.api;

import br.com.as.cinema.BaseTest;
import br.com.as.cinema.internal.configuration.CinemaConstants;
import br.com.as.cinema.internal.domain.Movie;
import br.com.as.cinema.internal.domain.enums.MovieStatus;
import br.com.as.cinema.internal.usecase.MovieCreate;
import br.com.as.cinema.utils.JsonUtils;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MovieControllerTest extends BaseTest {

    private static final String URL = CinemaConstants.URL_PREFIX_V1 + "movies";

    @Autowired
    private MovieCreate movieCreate;

    @Test
    @Transactional
    void getByUuid() throws Exception {

        Movie movie = movieCreate.execute(createMovie());
        mockMvc.perform(get(URL + "/" + movie.getUuid()))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", is(movie.getUuid())));
    }

    @Test
    @Transactional
    void getAll() throws Exception {
        Movie movie = movieCreate.execute(createMovie());
        mockMvc.perform(get(URL))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].uuid", is(movie.getUuid())));
    }

    @Test
    @Transactional
    void create() throws Exception {
        Movie movie = createMovie();
        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtils.toJson(movie)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.uuid", is(movie.getUuid())));
    }

    @Test
    @Transactional
    void update() throws Exception {
        Movie movie = movieCreate.execute(createMovie());
        Movie update = new Movie()
                .setName("New Name")
                .setDescription("New Description")
                .setDuration("New Duration")
                .setStatus(MovieStatus.INACTIVE);

        mockMvc.perform(put(URL + "/" + movie.getUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtils.toJson(update)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", is(movie.getUuid())))
                .andExpect(jsonPath("$.name", is(update.getName())))
                .andExpect(jsonPath("$.duration", is(update.getDuration())))
                .andExpect(jsonPath("$.description", is(update.getDescription())));
    }

    @Test
    @Transactional
    void updateNotFound() throws Exception {
        Movie movie = createMovie();
        mockMvc.perform(put(URL + "/" + movie.getUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtils.toJson(movie)))
                .andDo(print())
//                .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(status().isNotFound())
                .andExpect(status().reason(containsString("UUID not found")));
    }

    private Movie createMovie() {
        return new Movie()
                .setName("V for Vendetta")
                .setDescription("V for Vendetta is a 2006 dystopian political action film directed by James McTeigue " +
                        "(in his feature directorial debut) from a screenplay by the Wachowskis.")
                .setDuration("2h 12m")
                .setStatus(MovieStatus.ACTIVE);
    }

}