package br.com.as.cinema.internal.api;

import br.com.as.cinema.BaseTest;
import br.com.as.cinema.internal.configuration.CinemaConstants;
import br.com.as.cinema.internal.domain.Price;
import br.com.as.cinema.internal.domain.enums.PriceStatus;
import br.com.as.cinema.internal.usecase.PriceCreate;
import br.com.as.cinema.utils.JsonUtils;
import jakarta.transaction.Transactional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PriceControllerTest extends BaseTest {

    private static final String URL = CinemaConstants.URL_PREFIX_V1 + "prices";

    @Autowired
    private PriceCreate priceCreate;

    @Test
    @Transactional
    void getByUuid() throws Exception {
        Price price = priceCreate.execute(createPrice());
        mockMvc.perform(get(URL + "/" + price.getUuid()))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", is(price.getUuid())));
    }

    @Test
    @Transactional
    void getAll() throws Exception {
        Price price = priceCreate.execute(createPrice());
        mockMvc.perform(get(URL))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].uuid", is(price.getUuid())));
    }

    @Test
    @Transactional
    void create() throws Exception {
        Price price = createPrice();
        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtils.toJson(price)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", is(price.getUuid())));
    }

    @Test
    @Transactional
    void update() throws Exception {
        Price price = priceCreate.execute(createPrice());
        Price update = new Price()
                .setAmount(BigDecimal.ONE)
                .setDescription("New Description")
                .setDetails("New Details")
                .setStatus(PriceStatus.INACTIVE);

        mockMvc.perform(put(URL + "/" + price.getUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtils.toJson(update)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", is(price.getUuid())))
                .andExpect(jsonPath("$.description", is(update.getDescription())))
                .andExpect(jsonPath("$.details", is(update.getDetails())));
    }

    @Test
    @Transactional
    void updateNotFound() throws Exception {
        Price seat = createPrice();
        mockMvc.perform(put(URL + "/" + seat.getUuid())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtils.toJson(seat)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(status().reason(Matchers.containsString("UUID not found")));
    }

    private Price createPrice() {
        return new Price()
                .setAmount(BigDecimal.TEN)
                .setDescription("Normal")
                .setDetails("Adults")
                .setStatus(PriceStatus.ACTIVE);
    }
}