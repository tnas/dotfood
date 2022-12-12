package dotfood.orders.application.api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dotfood.orders.application.dto.OrderDto;
import dotfood.orders.application.dto.StatusDto;
import dotfood.orders.domain.model.Order;
import dotfood.orders.domain.model.Status;
import dotfood.orders.domain.service.OrderService;

@Service
class OrderApiService {

	@Autowired
	private OrderService service;

	@Autowired
	private ModelMapper modelMapper;

	List<OrderDto> getAll() {
		return this.service.getAll().stream()
				.map(o -> this.modelMapper.map(o, OrderDto.class)).toList();
	}

	OrderDto getById(Long id) {
		return this.modelMapper.map(this.service.getById(id), OrderDto.class);
	}

	OrderDto createOrder(OrderDto dto) {
		return this.modelMapper.map(
				this.service.createOrder(
						this.modelMapper.map(dto, Order.class)), OrderDto.class);
	}

	OrderDto updateStatus(Long id, StatusDto status) {
		return this.modelMapper.map(this.service.updateStatus(id, 
				this.modelMapper.map(status.getStatus(), Status.class)), OrderDto.class);
	}

	void approveOrderPayment(Long id) {
		this.service.approveOrderPayment(id);
	}
}
