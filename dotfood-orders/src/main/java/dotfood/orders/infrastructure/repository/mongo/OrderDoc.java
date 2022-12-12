package dotfood.orders.infrastructure.repository.mongo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import dotfood.orders.domain.model.Status;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Document("orders")
public class OrderDoc {
	
	@Id
	private Long id;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull @Enumerated(EnumType.STRING)
    private Status status;
    
    private List<OrderItemDoc> items;
}
