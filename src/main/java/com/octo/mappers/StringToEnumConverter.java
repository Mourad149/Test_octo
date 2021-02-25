package com.octo.mappers;

import com.octo.domain.enums.Level;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, Level> {
    @Override
    public Level convert(String source) {
        try {
            return Level.valueOf(source.toUpperCase());
        }catch(IllegalArgumentException e) {
            return null;
        }
    }
}