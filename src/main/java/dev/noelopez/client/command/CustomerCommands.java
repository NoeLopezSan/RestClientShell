package dev.noelopez.client.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.noelopez.client.command.shell.ShellReader;
import dev.noelopez.client.dto.*;
import dev.noelopez.client.format.DateUtils;
import dev.noelopez.client.format.OuputFormatter;
import dev.noelopez.client.service.CustomerService;
import org.hibernate.validator.constraints.Range;
import org.springframework.shell.command.annotation.*;
import org.springframework.util.StringUtils;

import java.util.List;

@Command(group = "Customer Commands")
public class CustomerCommands {
    private CustomerService customerService;
    private OuputFormatter ouputFormatter;
    private ShellReader shellReader;

    public CustomerCommands(CustomerService customerService, OuputFormatter ouputFormatter, ShellReader shellReader) {
        this.customerService = customerService;
        this.ouputFormatter = ouputFormatter;
        this.shellReader = shellReader;
    }

    @Command(command = "find-customer", description = "Finds a Customer By Id")
    public String findCustomer(
        @Range(min = 1, max = 9999, message = "Customer id must be between {min} and {max}.")
        @Option(required = true, description = "The customer id", longNames = "id", shortNames = 'i') Long id) {
        CustomerResponse customer = customerService.findCustomer(id);
        return ouputFormatter.coverToTable(List.of(customer));
    }

    @Command(command = "find-customers", description = "Find Customers By Status and vip")
    public String findCustomers(
            @Option(required = false, longNames = "name", shortNames = 'n') String name,
            @Option(required = false, longNames = "status", shortNames = 's') CustomerStatus status,
            @Option(required = false, longNames = "email", shortNames = 'e') String email,
            @Option(required = false, longNames = "vip", shortNames = 'v') String vip) throws JsonProcessingException {
        var customerRequest = new CustomerRequest(
                status,
                new CustomerPersonInfo(name,email,null),
                new CustomerDetailsInfo(null, StringUtils.hasLength(vip) ? Boolean.valueOf(vip): null));
        var customers = customerService.findCustomers(customerRequest);
        return customers.isEmpty() ? "No customers found." : ouputFormatter.coverToTable(customers);
    }

    @Command(command = "update-customer", description = "Updates Customer fields. It requires user login.")
    @CommandAvailability(provider = "userLoggedProvider")
    public String updateCustomer(@Option(label = "id", required = true, longNames = "id", shortNames = 'i') Long id) {
        CustomerResponse customer = customerService.findCustomer(id);

        var name  = shellReader.readLineRequired(String.format("Enter new Name (%s)",customer.personInfo().name()));
        var email = shellReader.readLineRequired(String.format("Enter new Email (%s)",customer.personInfo().email()));
        var dob   = shellReader.readLineRequired(String.format("Enter new Date Of Birth (%s)",customer.personInfo().dateOfBirth()));
        var info  = shellReader.readLineRequired(String.format("Enter new Info (%s)",customer.detailsInfo().info()));
        var vip   = shellReader.readLineOptions(
                String.format("Is customer vip ?",customer.detailsInfo().vip()),
                List.of("Y","N"));

        var customerRequest = new CustomerUpdateRequest(
                name, email, DateUtils.parseDate(dob),info,vip.equals("Y"));

        customer = customerService.updateCustomer(id, customerRequest);
        return String.format("Customer %d updated successfully. \n %s", id, ouputFormatter.coverToTable(customer));
    }

    @Command(command = "delete-customer", description = "Delete Customer By Id. It requires user login.")
    @CommandAvailability(provider = "userLoggedProvider")
    public String deleteCustomer(
        @Range(min = 1, max = 9999, message = "Customer id must be between {min} and {max}.")
        @Option(label = "id", required = true, longNames = "id", shortNames = 'i') Long id) {
        customerService.deleteCustomer(id);
        return String.format("Customer %d deleted successfully", id);
    }
}