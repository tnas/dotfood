package com.tnas.dotfood.orders.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigurator {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
