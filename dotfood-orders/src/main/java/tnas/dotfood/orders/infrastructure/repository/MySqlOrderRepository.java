package tnas.dotfood.orders.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import tnas.dotfood.orders.domain.model.Status;
import tnas.dotfood.orders.infrastructure.entity.OrderEntity;

public interface MySqlOrderRepository extends JpaRepository<OrderEntity, Long> {
	
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update OrderEntity o set o.status = :status where o = :order")
    void updateStatus(Status status, OrderEntity order);

    @Query(value = "SELECT o from OrderEntity o LEFT JOIN FETCH o.items where o.id = :id")
    OrderEntity getFatById(Long id);
}
