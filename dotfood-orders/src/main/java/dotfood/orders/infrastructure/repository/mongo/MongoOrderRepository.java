package dotfood.orders.infrastructure.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoOrderRepository extends MongoRepository<OrderDoc, Long>  {

}
