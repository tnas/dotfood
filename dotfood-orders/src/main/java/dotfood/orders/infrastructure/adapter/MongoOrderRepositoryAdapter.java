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
		return this.docToModel(this.repository.findById(id));
	}

	@Override
	public Optional<Order> findById(Long id) {
		return this.getFatById(id);
	}

	@Override
	public List<Order> findAll() {
		return this.repository.findAll().stream()
				.map(d -> this.modelMapper.map(d, Order.class))
				.toList();
	}

	@Override
	public void updateStatus(Status status, Order order) {
		var doc = this.modelMapper.map(order, OrderDoc.class);
		doc.setStatus(status);
		this.repository.save(this.modelMapper.map(order, OrderDoc.class));
	}

	@Override
	public Order save(Order order) {
		var savedEntity = this.repository.save(this.modelMapper.map(order, OrderDoc.class)); 
		return this.modelMapper.map(savedEntity, Order.class);
	}
	
	private Optional<Order> docToModel(Optional<OrderDoc> doc) {
		return doc.isPresent() ? Optional.of(this.modelMapper.map(doc.get(), Order.class))
				: Optional.empty();	
	}

}
