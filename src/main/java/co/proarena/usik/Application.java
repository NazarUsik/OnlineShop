package co.proarena.usik;

import co.proarena.usik.config.JpaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(new Class<?>[]{Application.class, JpaConfig.class}, args);
    }

}
