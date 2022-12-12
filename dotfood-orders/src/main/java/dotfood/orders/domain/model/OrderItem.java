package dotfood.orders.domain.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderItem {

	private Long id;

	@NotNull @Positive
	private Integer amount;

	@NotEmpty
	private String description;
	
	@NotNull
	private Order order;
}
