package co.proarena.usik.service;

import co.proarena.usik.entity.Role;
import co.proarena.usik.entity.User;
import co.proarena.usik.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public void save(Role role) {
        repository.save(role);
    }

    public List<Role> listAll() {
        return (List<Role>) repository.findAll();
    }

    public Role get(Long id) {
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
