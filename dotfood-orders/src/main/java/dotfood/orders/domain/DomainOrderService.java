package dotfood.orders.domain;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dotfood.orders.domain.model.Order;
import dotfood.orders.domain.model.Status;
import dotfood.orders.domain.repository.OrderRepository;
import dotfood.orders.domain.service.OrderService;

@Service
public class DomainOrderService implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Override
	public List<Order> getAll() {
        return repository.findAll();
    }

    @Override
	public Order getById(Long id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
	public Order createOrder(Order order) {
        return this.repository.save(order);
    }

    @Override
	public Order updateStatus(Long id, Status status) {

        var optOrder = repository.getFatById(id);

        if (optOrder.isEmpty()) {
            throw new EntityNotFoundException();
        }

        var order = optOrder.get();
        order.changeStatus(status);
        this.repository.save(order);
        
        return order;
    }

    @Override
	public void approveOrderPayment(Long id) {

        var optOrder = repository.findById(id);

        if (optOrder.isEmpty()) {
            throw new EntityNotFoundException();
        }
        
        var order = optOrder.get();
        order.markPaymentApproved();
        
        this.repository.save(order);
    }
}
