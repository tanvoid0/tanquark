package com.tanvoid0.tanquark.config;

import com.tanvoid0.tanquark.common.base.interfaces.MappingConfigurer;
import jakarta.annotation.PostConstruct;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.modelmapper.ModelMapper;

public abstract class AbstractMappingConfigurer implements MappingConfigurer {
    @Lazy
    ModelMapper mapper;

    @PostConstruct
    void configureMapper() {
        configure(mapper);
    }
}
