package com.kafka.utils;

import org.modelmapper.ModelMapper;

public class ConvertObjectUtils {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static <T> T mapToEntity(Object objectDTO, Class<T> objectEntity) {
        return modelMapper.map(objectDTO, objectEntity);
    }

    public static <T> T mapToDTO(Object objectEntity, Class<T> objectDTO) {
        return modelMapper.map(objectEntity, objectDTO);
    }
}
