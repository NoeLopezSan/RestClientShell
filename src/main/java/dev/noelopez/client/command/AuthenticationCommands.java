package dev.noelopez.client.command;

import dev.noelopez.client.config.SimpleUser;
import dev.noelopez.client.config.UserLoggedIn;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandAvailability;
import org.springframework.shell.command.annotation.Option;

//@Command(command = "Customer Admin Commands")
@Command(group = "Authenticate Commands")
public class AuthenticationCommands {

    private UserLoggedIn userLoggedIn;

    public AuthenticationCommands(UserLoggedIn userLoggedIn) {
        this.userLoggedIn = userLoggedIn;
    }

    SimpleUser getAdminUser() {
        return new SimpleUser("admin","qTyb2lr^6");
    }
    @Command(command="login",description="Login to get Admin privileges.")
    @CommandAvailability(provider = "userNotLoggedInAvailability")
    //@ShellMethodAvailability("loginAvailability")
    public String login(@Option(required = true) String user, @Option(required = true) String password) {
        if (!getAdminUser().equals(new SimpleUser(user,password)))
            return "Incorrect username/password.";

        userLoggedIn.setLoggedIn(true);
        return "You are logged in now!";
    }

    //@ShellMethod(key="logout",value="logout as an Admin.")
    @Command(command="logout",description="logout as an Admin.", group = "Authenticate Commands")
    //@ShellMethodAvailability("logoutAvailability")
    @CommandAvailability(provider = "userLoggedInAvailability")
    public String logout() {
        userLoggedIn.setLoggedIn(false);
        return "You have been logged out.";
    }

//    public Availability loginAvailability() {
//        return !loggedIn
//                ? Availability.available()
//                : Availability.unavailable("You are already logged in.");
//    }
//
//    public Availability logoutAvailability() {
//        return loggedIn
//                ? Availability.available()
//                : Availability.unavailable("You are not logged in.");
//    }
}
