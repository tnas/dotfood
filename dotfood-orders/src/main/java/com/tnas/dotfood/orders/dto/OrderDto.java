package com.tnas.dotfood.orders.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.tnas.dotfood.orders.model.Status;

public class OrderDto {

    private Long id;
    
    private LocalDateTime dateTime;
    
    private Status status;
    
    private List<OrderItemDto> itens = new ArrayList<>();

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

	public List<OrderItemDto> getItens() {
		return itens;
	}

	public void setItens(List<OrderItemDto> itens) {
		this.itens = itens;
	}

}
