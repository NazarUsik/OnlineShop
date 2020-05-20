package co.proarena.usik.repository;

import co.proarena.usik.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void findByEmailAndPassword() {
        assertEquals(userRepository.findByEmailAndPassword
                        ("usik.example@gmail.com", "admin50510").toString(),
                "User = [id = 1, firstName = Nazar, lastName = Usik, " +
                        "email = usik.example@gmail.com, password = admin50510, roleId = 1]");

        assertNotEquals(userRepository.findByEmailAndPassword
                        ("user.example@gmail.com", "user12345").toString(),
                "User = [id = 1, firstName = Nazar, lastName = Usik, " +
                        "email = usik.example@gmail.com, password = admin50510, roleId = 1]");
    }

    @Test
    @Transactional
    void findByEmail() {
        assertEquals(userRepository.findByEmail("usik.example@gmail.com").toString(),
                "User = [id = 1, firstName = Nazar, lastName = Usik, " +
                        "email = usik.example@gmail.com, password = admin50510, roleId = 1]");

        assertNull(userRepository.findByEmail("1111111111111111111111111"));
    }

    @Test
    @Transactional
    void save() {
        userRepository.save(new User(0L, "ffff", "ffff", "ffff", "ffff", 2L));
    }
}