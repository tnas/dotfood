package tnas.dotfood.orders.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
public class Order {

	    private Long id;

	    @NotNull
	    private LocalDateTime dateTime;

	    @NotNull @Setter
	    private Status status;

	    @NotEmpty
	    private List<OrderItem> items;
	    
	    public Order(List<OrderItem> items) {
	    	items.forEach(it -> it.setOrder(this));
	    	this.items = items;
	    	this.dateTime = LocalDateTime.now();
	    	this.status = Status.SENT;
	    }
	    
	    public void markPaymentApproved() {
	    	this.status = Status.CONFIRMED;
	    }
}
