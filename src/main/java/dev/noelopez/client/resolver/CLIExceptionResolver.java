package dev.noelopez.client.resolver;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.noelopez.client.exception.RestClientCustomException;
import jakarta.validation.ConstraintViolation;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.shell.ParameterValidationException;
import org.springframework.shell.command.CommandExceptionResolver;
import org.springframework.shell.command.CommandHandlingResult;
import org.springframework.web.client.ResourceAccessException;

import java.util.stream.Collectors;

public class CLIExceptionResolver implements CommandExceptionResolver {
    @Override
    public CommandHandlingResult resolve(Exception ex) {
        if (ex instanceof RestClientCustomException e)
            return CommandHandlingResult.of(e.getErrorDetails().message()+'\n');
        else if (ex instanceof ResourceAccessException e)
            return CommandHandlingResult.of("Customer API is not available at the moment"+'\n');
        else if (ex instanceof ConversionFailedException e)
            return CommandHandlingResult.of("Customer id must be a positive integer"+'\n');
        else if (ex instanceof ParameterValidationException e)
            return CommandHandlingResult.of(e.getConstraintViolations()
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(". "))
                    +'\n');

        return CommandHandlingResult.of(ex.getMessage()+'\n', 1);
    }
}
