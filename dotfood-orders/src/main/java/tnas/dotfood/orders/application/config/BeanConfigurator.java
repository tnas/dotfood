package tnas.dotfood.orders.application.config;

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
