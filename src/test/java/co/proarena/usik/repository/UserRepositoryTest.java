package co.proarena.usik.repository;

import co.proarena.usik.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserService userService;

    @Test
    @Transactional
    void findByEmailAndPassword() {
        assertEquals(userService.findByEmailAndPassword
                        ("usik.example@gmail.com", "$2a$10$JP9eQPKLQOeRqpKGa4lJXOpvpgssu2h/po7ZxFADIPA1tcq2N1VKi").toString(),
                "User = [id = 1, firstName = Nazar, lastName = Usik, " +
                        "email = usik.example@gmail.com, password = $2a$10$JP9eQPKLQOeRqpKGa4lJXOpvpgssu2h/po7ZxFADIPA1tcq2N1VKi, roleId = 1]");

        assertNotEquals(userService.findByEmailAndPassword
                        ("user.example@gmail.com", "$2a$10$O4f8o0FA5WU4SrlAx.8DXe9TpWJt3p1OuGjFzhpZuGMeLdAn2th6.").toString(),
                "User = [id = 1, firstName = Nazar, lastName = Usik, " +
                        "email = usik.example@gmail.com, password = $2a$10$JP9eQPKLQOeRqpKGa4lJXOpvpgssu2h/po7ZxFADIPA1tcq2N1VKi, roleId = 1]");
    }

    @Test
    @Transactional
    void findByEmail() {
        assertEquals(userService.findByEmail("usik.example@gmail.com").toString(),
                "User = [id = 1, firstName = Nazar, lastName = Usik, " +
                        "email = usik.example@gmail.com, password = $2a$10$JP9eQPKLQOeRqpKGa4lJXOpvpgssu2h/po7ZxFADIPA1tcq2N1VKi, roleId = 1]");

        assertNull(userService.findByEmail("1111111111111111111111111"));
    }

}