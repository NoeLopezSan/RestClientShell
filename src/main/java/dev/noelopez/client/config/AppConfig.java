package dev.noelopez.client.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.noelopez.client.command.shell.ShellReader;
import dev.noelopez.client.format.OuputFormatter;
import dev.noelopez.client.resolver.CLIExceptionResolver;
import dev.noelopez.client.service.LoginService;
import org.jline.reader.LineReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.Availability;
import org.springframework.shell.AvailabilityProvider;

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
//    @Bean
//    CommandNotFoundMessageProvider provider() {
//        return ctx -> "The command was not found "+ctx.text();
//    }

//    @Bean
//    PromptProvider promptProvider() {
//        return () -> loginService.isLoggedIn() ?
//                new AttributedString(loginService.getLoggedUser().get().username()+">",
//                AttributedStyle.BOLD.foreground(AttributedStyle.YELLOW)) :
//                new AttributedString("customer-shell>",
//                        AttributedStyle.BOLD.foreground(AttributedStyle.YELLOW));
//    }
}