package tnas.dotfood.orders.application;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import tnas.dotfood.orders.domain.Status;

public class OrderDto {

    private Long id;
    
    private LocalDateTime dateTime;
    
    private Status status;
    
    private List<OrderItemDto> items = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<OrderItemDto> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDto> items) {
		this.items = items;
	}
}
