package co.proarena.usik.repository;

import co.proarena.usik.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByNameContainsOrderByPrice(@Param("keyword") String keyword);

}
