package dev.noelopez.client.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.noelopez.client.format.OuputFormatter;
import dev.noelopez.client.resolver.CLIExceptionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.Availability;
import org.springframework.shell.AvailabilityProvider;
import org.springframework.shell.result.CommandNotFoundMessageProvider;

@Configuration
public class AppConfig {
    @Bean
    CLIExceptionResolver customExceptionResolver() {
        return new CLIExceptionResolver();
    }
    @Bean
    ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    OuputFormatter ouputFormatter(){
        return new OuputFormatter();
    }

    @Bean
    UserLoggedIn userLoggedIn() {
        return new UserLoggedIn();
    }
    @Bean
    AvailabilityProvider userLoggedInAvailability() {
        return () -> userLoggedIn().getLoggedIn()
                ? Availability.available()
                : Availability.unavailable("You are already logged in.");
    }
    @Bean
    public AvailabilityProvider userNotLoggedInAvailability() {
        return  () -> !userLoggedIn().getLoggedIn()
                ? Availability.available()
                : Availability.unavailable("You are not logged in.");
    }

    @Bean
    CommandNotFoundMessageProvider provider() {
        return ctx -> "The command was not found "+ctx.text();
    }
}