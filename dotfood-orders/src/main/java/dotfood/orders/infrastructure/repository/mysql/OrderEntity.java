package dotfood.orders.infrastructure.repository.mysql;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import dotfood.orders.domain.model.Status;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="order")
    private List<OrderItemEntity> items;

	public void setItems(List<OrderItemEntity> items) {
		items.forEach(i -> i.setOrder(this));
		this.items = items;
	}
}
