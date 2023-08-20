package dev.noelopez.client.resolver;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.noelopez.client.exception.RestClientCustomException;
import org.springframework.shell.command.CommandExceptionResolver;
import org.springframework.shell.command.CommandHandlingResult;
import org.springframework.web.client.ResourceAccessException;

public class CLIExceptionResolver implements CommandExceptionResolver {
    @Override
    public CommandHandlingResult resolve(Exception ex) {
        if (ex instanceof RestClientCustomException e)
            return CommandHandlingResult.of(e.getErrorDetails().message()+'\n');
        else if (ex instanceof ResourceAccessException e)
            return CommandHandlingResult.of("Customer API is not available at the moment"+'\n');
        
        return CommandHandlingResult.of(ex.getMessage()+'\n', 1);
    }
}
