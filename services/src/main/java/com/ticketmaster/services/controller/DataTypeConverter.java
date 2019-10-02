package com.ticketmaster.services.controller;

import com.ticketmaster.services.persistence.repository.DataType;
import org.springframework.core.convert.converter.Converter;


public class DataTypeConverter implements Converter<DataType, String> {
    @Override
    public String convert(DataType s) {
        if (s.equals(DataType.H2))
            return "userJdbcModelRepository";
        return "userJpaModelRepository";
    }
}
