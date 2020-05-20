package co.proarena.usik.service;

import co.proarena.usik.entity.SalesOrder;
import co.proarena.usik.repository.SalesOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SalesOrderService {

    @Autowired
    private SalesOrderRepository repository;

    public void save(SalesOrder salesOrder) {
        repository.save(salesOrder);
    }

    public List<SalesOrder> listAll() {
        return (List<SalesOrder>) repository.findAll();
    }

    public SalesOrder get(Long id) {
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<SalesOrder> findByUserId(Long userId) {
        return repository.findByUserIdOrderByTotal(userId);
    }

    public SalesOrder findByUserIdAndProductId(Long userId, Long productId) {
        return repository.findByUserIdAndProductId(userId, productId);
    }

}
