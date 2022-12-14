package dotfood.orders.infrastructure.adapter;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import dotfood.orders.domain.model.Order;
import dotfood.orders.domain.model.Status;
import dotfood.orders.domain.repository.OrderRepository;
import dotfood.orders.infrastructure.repository.mongo.MongoOrderRepository;
import dotfood.orders.infrastructure.repository.mongo.OrderDoc;

@Primary
@Repository
public class MongoOrderRepositoryAdapter implements OrderRepository {
	
	@Autowired
	private MongoOrderRepository repository;

	@Autowired
    private ModelMapper modelMapper;
	
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
		return this.repository.findAll().stream()
				.map(d -> this.modelMapper.map(d, Order.class))
				.toList();
	}

	@Override
	public void updateStatus(Status status, Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Order save(Order order) {
		var savedEntity = this.repository.save(this.modelMapper.map(order, OrderDoc.class)); 
		return this.modelMapper.map(savedEntity, Order.class);
	}

}
