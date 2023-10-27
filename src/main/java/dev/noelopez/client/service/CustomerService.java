package dev.noelopez.client.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.noelopez.client.dto.CustomerRequest;
import dev.noelopez.client.dto.CustomerResponse;
import dev.noelopez.client.dto.CustomerStatus;
import dev.noelopez.client.dto.CustomerUpdateRequest;
import dev.noelopez.client.service.api.HttpAPIHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private HttpAPIHandler httpAPIHandler;

    public CustomerService(HttpAPIHandler httpAPIHandler) {
        this.httpAPIHandler = httpAPIHandler;
    }
    public CustomerResponse findCustomer(Long id) {
        return httpAPIHandler.findCustomer(id);
    }

    public List<CustomerResponse> findCustomers(CustomerRequest customerRequest) throws JsonProcessingException {
        return httpAPIHandler.findCustomers(customerRequest);
    }

    public CustomerResponse updateCustomer(Long id, CustomerUpdateRequest customerRequest) {
        return httpAPIHandler.updateCustomer(id, customerRequest);
    }

    public void deleteCustomer(Long id) {
        httpAPIHandler.deleteCustomer(id);
    }
}
