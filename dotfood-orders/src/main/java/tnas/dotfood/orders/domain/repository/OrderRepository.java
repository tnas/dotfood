package tnas.dotfood.orders.domain.repository;

import java.util.List;
import java.util.Optional;

import tnas.dotfood.orders.domain.Order;
import tnas.dotfood.orders.domain.Status;

public interface OrderRepository  {
	
	Optional<Order> getFatById(Long id);
	
	Optional<Order> findById(Long id);
	
	List<Order> findAll();
	
    void updateStatus(Status status, Order order);

	Order save(Order order);
}
