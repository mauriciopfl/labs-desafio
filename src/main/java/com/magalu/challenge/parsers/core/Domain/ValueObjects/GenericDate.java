package com.magalu.challenge.parsers.core.Domain.ValueObjects;

import com.magalu.challenge.parsers.core.Domain.Exceptions.GenericDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class GenericDate {
    private final LocalDate date;

    public GenericDate(String orderDateString) throws GenericDateException {
        if (orderDateString == null || orderDateString.isEmpty()) {
            throw new GenericDateException("The order date cannot be null or empty");
        }
        try {


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            this.date = LocalDate.parse(orderDateString, formatter);
        } catch (DateTimeParseException e) {
            throw new GenericDateException(e.getMessage());
        }
    }

    public GenericDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getOriginalDateFormatted(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return date.format(formatter);
    }

    public String formatted()  {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericDate that = (GenericDate) o;
        return Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    @Override
    public String toString() {
        return formatted();
    }
}
