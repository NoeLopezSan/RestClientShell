package dev.noelopez.client.command;

import dev.noelopez.client.service.LoginService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandAvailability;
import org.springframework.shell.command.annotation.Option;

@Command(group = "Login Commands")
public class AuthenticationCommands {
    private LoginService loginService;
    public AuthenticationCommands(LoginService loginService) {
        this.loginService = loginService;
    }
    @Command(command="login",description="Login to get Admin privileges.")
    @CommandAvailability(provider = "userLoggedOutProvider")
    public String login(
        @Size(min = 8, max = 20, message = "Username must be between {min} and {max} ") @Option(required = true) String username,
        @Min(value = 10, message = "Password must be at least {value} chars long") @Option(required = true) String password) {
        loginService.login(username,password);
        return "You are logged in now!";
    }

    @Command(command="logout",description="logout as an Admin.")
    @CommandAvailability(provider = "userLoggedProvider")
    public String logout() {
        loginService.logout();
        return "You have been logged out.";
    }
}
