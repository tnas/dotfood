package dotfood.orders.infrastructure.repository.mongo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Document("order_items")
public class OrderItemDoc {
	
//	@Id
//	private BigInteger id;
	
    @NotNull
    @Positive
    private Integer amount;

    private String description;
    
//    public OrderItemDoc() {
//    	this.id = new BigInteger(ObjectId.get().toString());
//    }
}
