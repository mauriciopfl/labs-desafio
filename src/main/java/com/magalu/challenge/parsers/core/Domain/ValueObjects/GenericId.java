package com.magalu.challenge.parsers.core.Domain.ValueObjects;

import com.magalu.challenge.parsers.core.Domain.Exceptions.GenericIdException;

import java.util.Objects;

public class GenericId {
    private final Integer id;

    public GenericId(Integer id) throws GenericIdException {
        if (id == null || id <= 0) {
            throw new GenericIdException("Ivalid ID");
        }
        this.id = Integer.parseInt(id.toString());
    }

    public GenericId(String id) throws GenericIdException {
        if (id == null ||
                id.trim().isEmpty() ||
                id.trim().isBlank() ||
                id.trim().equals("null") ||
                id.trim().equals("0")) {
            throw new GenericIdException("ID from string cannot be empty, null, zero or characters.");
        }
        try {
            this.id = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new GenericIdException("Error parsing ID from string: " + id, e);
        }
    }

    public String formatted() {
        return id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericId that = (GenericId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return formatted();
    }
}
