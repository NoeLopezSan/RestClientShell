package dev.noelopez.client.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.noelopez.client.command.shell.ShellReader;
import dev.noelopez.client.format.OuputFormatter;
import dev.noelopez.client.resolver.CLIExceptionResolver;
import dev.noelopez.client.service.LoginService;
import org.jline.reader.LineReader;
import org.jline.utils.AttributedString;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.Availability;
import org.springframework.shell.AvailabilityProvider;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.shell.result.CommandNotFoundMessageProvider;

@Configuration
public class AppConfig {

    private LoginService loginService;

    public AppConfig(LoginService loginService) {
        this.loginService = loginService;
    }

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
    public ShellReader shellReader(@Lazy LineReader lineReader) {
        return new ShellReader(lineReader);
    }
    @Bean
    public AvailabilityProvider userLoggedProvider() {
        return () -> loginService.isLoggedIn()
                ? Availability.available()
                : Availability.unavailable("You are already logged in.");
    }
    @Bean
    public AvailabilityProvider userLoggedOutProvider() {
        return  () -> !loginService.isLoggedIn()
                ? Availability.available()
                : Availability.unavailable("You are not logged in.");
    }
    @Bean
    CommandNotFoundMessageProvider provider() {
        var message = """
               The command '%s' you entered was not found. 
               Use help to view the list of available commands 
               """;
        return ctx -> String.format(message, ctx.text());
    }

    @Bean
    PromptProvider promptProvider() {
        return () -> loginService.isLoggedIn() ?
                new AttributedString("shell("+loginService.getUser().username()+"):>") :
                new AttributedString("shell(unknown):>");
    }
}