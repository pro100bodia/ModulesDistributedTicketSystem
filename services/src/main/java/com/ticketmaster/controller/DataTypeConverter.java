package com.ticketmaster.controller;

import com.ticketmaster.persistence.repository.DataType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DataTypeConverter implements Converter<DataType, String> {
    @Override
    public String convert(DataType s) {
        if (s.equals(DataType.H2))
            return "userJdbcModelRepository";
        return "userJpaModelRepository";
    }
}
