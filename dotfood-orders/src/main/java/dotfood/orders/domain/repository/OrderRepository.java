package dotfood.orders.domain.repository;

import java.util.List;
import java.util.Optional;

import dotfood.orders.domain.model.Order;
import dotfood.orders.domain.model.Status;

public interface OrderRepository  {
	
	Optional<Order> getFatById(Long id);
	
	Optional<Order> findById(Long id);
	
	List<Order> findAll();
	
    void updateStatus(Status status, Order order);

	Order save(Order order);
}
