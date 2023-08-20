package dev.noelopez.client.exception;

import org.springframework.http.HttpStatusCode;

public class RestClientCustomException extends RuntimeException {
    private final HttpStatusCode statusCode;
    private final ErrorDetails errorDetails;
    public RestClientCustomException(HttpStatusCode statusCode, ErrorDetails errorDetails) {
        this.statusCode = statusCode;
        this.errorDetails = errorDetails;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }
}
