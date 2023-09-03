package dev.noelopez.client.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Order(1)
@Configuration
public class PasswordGeneratorInitializer implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        //System.out.println(BCrypt.hashpw("password2",BCrypt.gensalt(12)));
    }
}
