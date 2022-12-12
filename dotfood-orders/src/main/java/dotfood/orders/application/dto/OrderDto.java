package dotfood.orders.application.dto;

import java.time.LocalDateTime;
import java.util.List;

import dotfood.orders.domain.model.Status;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderDto {

    private Long id;
    
    private LocalDateTime dateTime;
    
    private Status status;
    
    private List<OrderItemDto> items;
}
