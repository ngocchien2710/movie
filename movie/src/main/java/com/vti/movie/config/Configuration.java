package com.vti.movie.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class Configuration {
    @Bean
    public ModelMapper initModelMapper() {
        return new ModelMapper();
    }
}
