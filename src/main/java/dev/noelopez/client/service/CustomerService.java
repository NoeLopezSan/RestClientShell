package dev.noelopez.client.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.noelopez.client.dto.CustomerResponse;
import dev.noelopez.client.dto.CustomerStatus;
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

    public List<CustomerResponse> findCustomers(CustomerStatus status, Boolean vip) throws JsonProcessingException {
        return httpAPIHandler.findCustomers(status, vip);
    }

    public void deleteCustomer(Long id) {
        httpAPIHandler.deleteCustomer(id);
    }

}
