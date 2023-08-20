package dev.noelopez.client.service.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.noelopez.client.config.APIProperties;
import dev.noelopez.client.dto.CustomerResponse;
import dev.noelopez.client.dto.CustomerStatus;
import dev.noelopez.client.exception.ErrorDetails;
import dev.noelopez.client.exception.RestClientCustomException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Component
public final class HttpAPIHandler {
    private final APIProperties apiProperties;
    private final RestClient restClient;
    private final ObjectMapper objectMapper;
    public HttpAPIHandler(APIProperties apiProperties, ObjectMapper objectMapper) {
        this.apiProperties = apiProperties;
        this.objectMapper = objectMapper;
        restClient = RestClient.builder()
                .baseUrl(apiProperties.getUrl())
                .defaultHeader(HttpHeaders.AUTHORIZATION,
                        encodeBasic(apiProperties.getUsername(),apiProperties.getPassword()))
                .defaultStatusHandler(
                        HttpStatusCode::is4xxClientError,
                        (request, response) -> {
                            var errorDetails = objectMapper.readValue(response.getBody().readAllBytes(), ErrorDetails.class);
                            throw new RestClientCustomException(response.getStatusCode(), errorDetails);
                        })
                .build();
    }

    public CustomerResponse findCustomer(Long id) {
        return restClient.get()
                .uri("/{id}",id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(CustomerResponse.class);
    }

    public List<CustomerResponse> findCustomers(CustomerStatus status, Boolean vip) throws JsonProcessingException {
        String response = restClient.get()
                .uri(getQueryString(status, vip))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(String.class);
        return Arrays.stream(objectMapper.readValue(response, CustomerResponse[].class)).toList();
    }

    private static String getQueryString(CustomerStatus status, Boolean vip) {
        String queryString = "?";

        if (status != null)
            queryString += "status="+ status.toString();
        if (vip != null)
            queryString += (queryString.isEmpty() ? "&" : "") +"vip="+ vip.toString();
        return queryString;
    }

    public void deleteCustomer(Long id) {
        restClient.delete()
                .uri("/{id}",id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toBodilessEntity();
    }
    private String encodeBasic(String username, String password) {
        return "Basic "+ Base64
                .getEncoder()
                .encodeToString((username+":"+password).getBytes());
    }

    public void updateCustomer(CustomerResponse customer) {
        restClient.put()
                .uri("/{id}",customer.id())
                .accept(MediaType.APPLICATION_JSON)
                .body(customer)
                .retrieve()
                .toBodilessEntity();
    }
}
