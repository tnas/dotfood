package dotfood.orders.application.dto;

import dotfood.orders.domain.model.Status;

public class StatusDto {
	
    private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
