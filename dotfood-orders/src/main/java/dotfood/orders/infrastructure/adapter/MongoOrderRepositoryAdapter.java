package dotfood.orders.infrastructure.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import dotfood.orders.domain.model.Order;
import dotfood.orders.domain.model.Status;
import dotfood.orders.domain.repository.OrderRepository;

@Primary
@Repository
public class MongoOrderRepositoryAdapter implements OrderRepository {

	@Override
	public Optional<Order> getFatById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Order> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(Status status, Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Order save(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

}
