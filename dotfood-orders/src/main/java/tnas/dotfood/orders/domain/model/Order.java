package tnas.dotfood.orders.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Order {

		@Setter
	    private Long id;

	    @NotNull
	    private LocalDateTime dateTime;

	    @NotNull
	    private Status status;

	    @NotEmpty 
	    private List<OrderItem> items;
	    
	    public Order() {
	    	this(new ArrayList<>());
	    }
	    
	    public Order(List<OrderItem> items) {
	    	this.setItems(items);
	    	this.dateTime = LocalDateTime.now();
	    	this.status = Status.SENT;
	    }
	    
	    public void markPaymentApproved() {
	    	this.status = Status.PAID;
	    }
	    
	    public void changeStatus(Status status) {
	    	this.status = status;
	    }

		public void setItems(List<OrderItem> items) {
			items.forEach(it -> it.setOrder(this));
	    	this.items = items;
		}
}
