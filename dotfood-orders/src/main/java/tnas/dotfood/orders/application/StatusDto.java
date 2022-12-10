package tnas.dotfood.orders.application;

import tnas.dotfood.orders.domain.Status;

public class StatusDto {
	
    private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
