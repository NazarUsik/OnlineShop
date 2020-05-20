package co.proarena.usik.service;

import co.proarena.usik.entity.User;
import co.proarena.usik.exeption.ResourceNotFoundException;
import co.proarena.usik.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(User user) {
        repository.save(user);
    }

    public List<User> listAll() {
        return (List<User>) repository.findAll();
    }

    public User get(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public User findByEmailAndPassword(String email, String password) {

        try {
            return repository.findByEmailAndPassword(email, password)
                    .orElseThrow(
                            () -> new ResourceNotFoundException("User", "EmailOrPassword", email + " " + password)
                    );
        } catch (ResourceNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    public boolean matchesPassword(String password, String passwordDB) {
        return passwordEncoder.matches(password, passwordDB);
    }
}
