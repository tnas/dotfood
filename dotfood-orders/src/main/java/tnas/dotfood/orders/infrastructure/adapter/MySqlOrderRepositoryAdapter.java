package tnas.dotfood.orders.infrastructure.adapter;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tnas.dotfood.orders.domain.Order;
import tnas.dotfood.orders.domain.Status;
import tnas.dotfood.orders.domain.repository.OrderRepository;
import tnas.dotfood.orders.infrastructure.OrderEntity;
import tnas.dotfood.orders.infrastructure.repository.MySqlOrderRepository;

@Repository
public class MySqlOrderRepositoryAdapter implements OrderRepository {

	@Autowired
	private MySqlOrderRepository orderRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Override
	public void updateStatus(Status status, Order order) {
		this.orderRepository.updateStatus(status, this.modelMapper.map(order, OrderEntity.class));
	}

	@Override
	public Optional<Order> findById(Long id) {
		var optEntity = this.orderRepository.findById(id);
		return optEntity.isPresent() ? 
				Optional.of(this.modelMapper.map(optEntity.get(), Order.class)) :
				Optional.empty();
	}

	@Override
	public List<Order> findAll() {
		return this.orderRepository.findAll().stream()
			.map(o -> this.modelMapper.map(o, Order.class))
			.toList();
	}

	@Override
	public Optional<Order> getFatById(Long id) {
		var fatOrder = this.orderRepository.getFatById(id);
		return Objects.isNull(fatOrder) ? Optional.empty() : 
				Optional.of(this.modelMapper.map(fatOrder, Order.class));
	}

	@Override
	public Order save(Order order) {
		var savedEntity = this.orderRepository.save(this.modelMapper.map(order, OrderEntity.class)); 
		return this.modelMapper.map(savedEntity, Order.class);
	}
}
