package dotfood.orders.infrastructure.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import dotfood.orders.domain.model.Status;

public interface MySqlOrderRepository extends JpaRepository<OrderEntity, Long> {
	
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update OrderEntity o set o.status = :status where o = :order")
    void updateStatus(Status status, OrderEntity order);

    @Query(value = "SELECT o from OrderEntity o LEFT JOIN FETCH o.items where o.id = :id")
    OrderEntity getFatById(Long id);
}
