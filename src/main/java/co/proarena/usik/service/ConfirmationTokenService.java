package co.proarena.usik.service;

import co.proarena.usik.entity.ConfirmationToken;
import co.proarena.usik.repository.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfirmationTokenService {

    @Autowired
    private ConfirmationTokenRepository repository;

    public void save(ConfirmationToken confirmationToken) {
        repository.save(confirmationToken);
    }

    public List<ConfirmationToken> listAll() {
        return (List<ConfirmationToken>) repository.findAll();
    }

    public ConfirmationToken get(Long id) {
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public ConfirmationToken findByConfirmationToken(String confirmationToken) {
        return repository.findByConfirmationToken(confirmationToken);
    }

}
