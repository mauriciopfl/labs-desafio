package com.magalu.challenge.parsers.core.Domain.ValueObjects;

import com.magalu.challenge.parsers.core.Domain.Exceptions.GenericDateException;
import com.magalu.challenge.parsers.core.Domain.ValueObjects.GenericDate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class GerenicDateTests {
    @Test
    public void testValidGenericDate() throws GenericDateException {
        String data = "20211201";
        GenericDate dataGenerica = new GenericDate(data);
        assertThat(dataGenerica.formatted()).isEqualTo("2021-12-01");
    }

    @Test
    public void testInvalidGenericDate() {
        String data = "20211301";
        assertThatThrownBy(() -> {
            new GenericDate(data);
        }).isInstanceOf(GenericDateException.class);
    }

    @Test
    public void testEmptyGenericDate() {
        String data = "";
        assertThatThrownBy(() -> {
            new GenericDate(data);
        }).isInstanceOf(GenericDateException.class);
    }
}
