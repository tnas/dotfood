package tnas.dotfood.orders.domain.service;

import java.util.List;

import tnas.dotfood.orders.domain.Order;
import tnas.dotfood.orders.domain.Status;

public interface OrderService {

	List<Order> getAll();

	Order getById(Long id);

	Order createOrder(Order order);

	Order updateStatus(Long id, Status status);

	void approveOrderPayment(Long id);
}