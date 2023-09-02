package br.com.as.cinema.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShortUuidUtilsTest {

    @Test
    void generateKey() {
        String result = ShortUuidUtils.generateKey();
        assertEquals(22, result.length());
    }

    @Test
    void uuidFromBase64() {
        String result = ShortUuidUtils.uuidFromBase64(ShortUuidUtils.generateKey());
        assertEquals(36, result.length());
    }
}