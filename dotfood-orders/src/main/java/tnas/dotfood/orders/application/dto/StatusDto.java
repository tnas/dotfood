package tnas.dotfood.orders.application.dto;

import tnas.dotfood.orders.domain.model.Status;

public class StatusDto {
	
    private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
