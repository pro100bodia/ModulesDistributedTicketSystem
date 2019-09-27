package com.ticketmaster.services.persistence;

public enum DataType {
    H2, MYSQL;

    public static DataType of(String value) {
        return DataType.valueOf(value.toUpperCase());
    }
}
