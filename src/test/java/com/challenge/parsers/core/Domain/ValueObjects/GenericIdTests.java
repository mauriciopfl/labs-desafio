package com.challenge.parsers.core.Domain.ValueObjects;

import com.challenge.parsers.core.Domain.Exceptions.GenericIdException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class GenericIdTests {
    @Test
    public void testGenericIdInvalidByStringNumberZero() {
        String valor = "0";
        assertThatThrownBy(() -> {
            new GenericId(valor);
        }).isInstanceOf(GenericIdException.class);
    }

    @Test
    void testValidGenericIdByNumberString() throws GenericIdException {
        String valor = "10";
        GenericId genericId = new GenericId(valor);
        assertThat(genericId.formatted()).isEqualTo("10");
    }

    @Test
    void testValidGenericIdByNumber() throws GenericIdException {
        Integer valor = 10;
        GenericId genericId = new GenericId(valor);
        assertThat(genericId.formatted()).isEqualTo("10");
    }

    @Test
    void testInvalidGenericIdByNumber() {
        Integer valor = 0;
        assertThatThrownBy(() -> {
            new GenericId(valor);
        }).isInstanceOf(GenericIdException.class);
    }

    @Test
    void testInvalidGenericIdByStringNumber() {
        String valor = "0";
        assertThatThrownBy(() -> {
            new GenericId(valor);
        }).isInstanceOf(GenericIdException.class);
    }


}
