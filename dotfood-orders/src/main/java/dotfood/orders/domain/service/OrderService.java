package dotfood.orders.domain.service;

import java.util.List;

import dotfood.orders.domain.model.Order;
import dotfood.orders.domain.model.Status;

public interface OrderService {

	List<Order> getAll();

	Order getById(Long id);

	Order createOrder(Order order);

	Order updateStatus(Long id, Status status);

	void approveOrderPayment(Long id);
}