package com.pukhalskyi.config;

import com.pukhalskyi.entity.DbType;
import org.springframework.core.convert.converter.Converter;

public class StringToDbTypeConverter implements Converter<String, DbType> {
    @Override
    public DbType convert(String s) {
        try {
            return DbType.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            return DbType.H2;
        }
    }
}
