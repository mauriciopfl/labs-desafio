package com.challenge.parsers.core.Domain.ValueObjects;

import com.challenge.parsers.core.Domain.Exceptions.GenericValueException;
import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class GenericValue {
    private final BigDecimal value;

    public GenericValue(BigDecimal value) {

        this.value = value;
    }

    public GenericValue(String stringValue) throws GenericValueException {
        if(stringValue == null || stringValue.isEmpty()) {
            throw new GenericValueException("Value cannot be null or empty");
        }
        try {
            this.value = new BigDecimal(stringValue).setScale(2, RoundingMode.HALF_UP);
        }catch (NumberFormatException e) {
            throw new GenericValueException("Value cannot be converted to BigDecimal", e);
        }
    }


    public String formatted() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericValue that = (GenericValue) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return formatted();
    }

    @JsonValue
    public BigDecimal getValue() {
        return value;
    }

}