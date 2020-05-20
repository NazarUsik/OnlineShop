package co.proarena.usik.repository;

import co.proarena.usik.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    Optional<User> findByEmail(@Param("email") String email);
}
