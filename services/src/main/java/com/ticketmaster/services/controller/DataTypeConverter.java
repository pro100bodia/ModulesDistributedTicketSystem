package com.ticketmaster.services.controller;

import com.ticketmaster.services.persistence.repository.DataType;
import org.springframework.core.convert.converter.Converter;


public class DataTypeConverter implements Converter<String, DataType> {
    @Override
    public DataType convert(String s) {
        return DataType.of(s);
    }
}
