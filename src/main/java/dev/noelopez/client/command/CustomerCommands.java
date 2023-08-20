package dev.noelopez.client.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.noelopez.client.dto.CustomerResponse;
import dev.noelopez.client.dto.CustomerStatus;
import dev.noelopez.client.format.OuputFormatter;
import dev.noelopez.client.service.CustomerService;
import org.springframework.shell.command.annotation.*;
import java.util.List;

@Command(group = "Customer Commands")
public class CustomerCommands {
    private OuputFormatter ouputFormatter;
    private CustomerService customerService;
    public CustomerCommands(CustomerService customerService, OuputFormatter ouputFormatter) {
        this.customerService = customerService;
        this.ouputFormatter = ouputFormatter;
    }
    @Command(command="find-customer",description="Finds a Customer By Id")
    public String findCustomer(
            @Option(required = true,description = "The customer id",longNames = "id", shortNames = 'i')
            Long id) {
        return customerService.findCustomer(id).toString();
    }

    @Command(command="find-customers",description = "Find Customers By Status and vip")
    public String findCustomers(
            @Option(required = false,longNames = "status", shortNames = 's') CustomerStatus status,
            @Option(required = false,longNames = "vip", shortNames = 'v') Boolean vip) throws JsonProcessingException {
        List<CustomerResponse> customers = customerService.findCustomers(status, vip);
        return ouputFormatter.coverToTable(customers);
    }
//
//    @Command(command="delete-customer",description="Delete Customer By Id. It requires a password token.")
//    @CommandAvailability(provider = "userLoggedInAvailability")
//    public String deleteCustomer(@Option(label = "id", required = true) Long id) {
//            clientService.deleteCustomer(id);
//            return String.format("Customer %d deleted successfully", id);
//    }
}
