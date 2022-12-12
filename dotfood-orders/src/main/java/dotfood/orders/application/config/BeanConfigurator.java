package dotfood.orders.application.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigurator {

    @Bean
    public ModelMapper getModelMapper() {
    	
    	var mapper = new ModelMapper();
    	
        mapper.getConfiguration()
        	.setFieldMatchingEnabled(Boolean.TRUE);
        	
        return mapper;
    }
}
