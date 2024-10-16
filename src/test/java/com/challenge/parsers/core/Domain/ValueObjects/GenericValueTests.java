package com.challenge.parsers.core.Domain.ValueObjects;

import com.challenge.parsers.core.Domain.Exceptions.GenericValueException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class GenericValueTests {

    @Test
    public void testValidGenericValue() throws GenericValueException {
        String vo = "512.245";
        GenericValue voGeneric = new GenericValue(vo);
        assertThat(voGeneric.formatted()).isEqualTo("512.25");
    }

    @Test
    public void testNegativeGenericValue() throws GenericValueException  {
        String vo = "-10.00";
        GenericValue voGenerico = new GenericValue(vo);
        assertThat(voGenerico.formatted()).isEqualTo("-10.00");
    }

    @Test
    public void testInvalidGenericValue() throws GenericValueException  {
        String vo = "XSA";
        assertThatThrownBy(() -> {
            new GenericValue(vo);
        }).isInstanceOf(GenericValueException.class);
    }

    @Test
    public void testEmptyGenericValue() throws GenericValueException  {
        String vo = "";
        assertThatThrownBy(() -> {
            new GenericValue(vo);
        }).isInstanceOf(GenericValueException.class);
    }

    @Test
    public void testNullGenericValue() throws GenericValueException  {
        String vo = "null";
        assertThatThrownBy(() -> {
            new GenericValue(vo);
        }).isInstanceOf(GenericValueException.class);
    }

    @Test
    public void testGetValue() throws GenericValueException {
        String vo = "123.45";
        GenericValue voGeneric = new GenericValue(vo);
        assertThat(voGeneric.getValue()).isEqualByComparingTo(new BigDecimal("123.45"));
    }
}
