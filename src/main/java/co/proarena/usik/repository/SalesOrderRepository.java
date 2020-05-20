package co.proarena.usik.repository;

import co.proarena.usik.entity.SalesOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesOrderRepository extends CrudRepository<SalesOrder, Long> {

    List<SalesOrder> findByUserIdOrderByTotal(@Param("user_id") Long userId);

    SalesOrder findByUserIdAndProductId(@Param("user_id") Long userId, @Param("product_id") Long productId);

}
