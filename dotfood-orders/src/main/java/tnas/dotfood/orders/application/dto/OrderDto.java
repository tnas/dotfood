package tnas.dotfood.orders.application.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import tnas.dotfood.orders.domain.model.Status;

@Getter @Setter
public class OrderDto {

    private Long id;
    
    private LocalDateTime dateTime;
    
    private Status status;
    
    private List<OrderItemDto> items;
}
