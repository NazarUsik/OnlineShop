package co.proarena.usik.service;

import co.proarena.usik.entity.Product;
import co.proarena.usik.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public void save(Product product) {
        repository.save(product);
    }

    public List<Product> listAll() {
        return (List<Product>) repository.findAll();
    }

    public Product get(Long id) {
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Product> search(String keyword){
        return repository.findByNameContainsOrderByPrice(keyword);
    }
}
