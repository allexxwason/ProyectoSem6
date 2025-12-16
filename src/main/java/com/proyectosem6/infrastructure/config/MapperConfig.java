package com.proyectosem6.infrastructure.config;

import com.proyectosem6.infrastructure.config.mapper.EventMapper;
import com.proyectosem6.infrastructure.config.mapper.VenueMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public EventMapper eventMapper() {
        return org.mapstruct.factory.Mappers.getMapper(EventMapper.class);
    }

    @Bean
    public VenueMapper venueMapper() {
        return org.mapstruct.factory.Mappers.getMapper(VenueMapper.class);
    }
}
