package com.tnas.dotfood.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tnas.dotfood.orders.model.Order;
import com.tnas.dotfood.orders.model.Status;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Order o set o.status = :status where o = :order")
    void updateStatus(Status status, Order pedido);

    @Query(value = "SELECT o from Order p LEFT JOIN FETCH o.items where o.id = :id")
    Order getById(Long id);

}
