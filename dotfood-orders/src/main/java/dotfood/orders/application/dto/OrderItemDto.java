package dotfood.orders.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderItemDto {

    private Long id;
    
    private Integer amount;
    
    private String description;
}
