package dev.noelopez.client.service;

import dev.noelopez.client.exception.LoginException;
import dev.noelopez.client.model.SimpleUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class LoginService {

    @Value("${application.cli.username}")
    private String username;
    @Value("${application.cli.password}")
    private String hashedPassword;
    private final AtomicReference<SimpleUser> user = new AtomicReference<>();

    public void login(String username, String password) {
        if (!username.equals(username) || !BCrypt.checkpw(password,hashedPassword)) {
            throw new LoginException("Incorrect username/name!");
        }

        user.set(new SimpleUser(username));
    }

    public void logout() {
        user.set(null);
    }

    public boolean isLoggedIn() {
        return Objects.nonNull(user.get());
    }

    public SimpleUser getUser() {
        return user.get();
    }
}
