package com.tanvoid0.tanquark.common.base;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Converter(autoApply = true)
public class EnumSetConverter<E extends Enum<E>> implements AttributeConverter<Set<E>, String> {

    private final Class<E> clazz;

    public EnumSetConverter(Class<E> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String convertToDatabaseColumn(Set<E> attribute) {
        if (attribute == null) return null;
        return attribute.stream()
                .map(Enum::name)
                .collect(Collectors.joining(","));
    }

    @Override
    public Set<E> convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return Arrays.stream(dbData.split(","))
                .map(String::trim)
                .map(name -> Enum.valueOf(clazz, name))
                .collect(Collectors.toSet());
    }
}
