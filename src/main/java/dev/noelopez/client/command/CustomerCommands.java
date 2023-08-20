package dev.noelopez.client.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.noelopez.client.dto.CustomerResponse;
import dev.noelopez.client.dto.CustomerStatus;
import dev.noelopez.client.format.OuputFormatter;
import dev.noelopez.client.service.CustomerService;
import org.hibernate.validator.constraints.Range;
import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.command.annotation.*;
import org.springframework.shell.component.flow.ComponentFlow;

import java.util.List;

@Command(group = "Customer Commands")
public class CustomerCommands {
    private OuputFormatter ouputFormatter;
    private CustomerService customerService;

    @Autowired
    private ComponentFlow.Builder componentFlowBuilder;
    @Autowired
    private Terminal terminal;
    public CustomerCommands(CustomerService customerService, OuputFormatter ouputFormatter) {
        this.customerService = customerService;
        this.ouputFormatter = ouputFormatter;
    }
    @Command(command="find-customer",description="Finds a Customer By Id")
    public String findCustomer(
            @Range(min=1, max=9999, message = "Customer id must be between {min} and {max}.")
            @Option(required = true,description = "The customer id",longNames = "id", shortNames = 'i') Long id) {
        return customerService.findCustomer(id).toString();
    }

    @Command(command="find-customers",description = "Find Customers By Status and vip")
    public String findCustomers(
            @Option(required = false,longNames = "status", shortNames = 's') CustomerStatus status,
            @Option(required = false,longNames = "vip", shortNames = 'v') Boolean vip) throws JsonProcessingException {
        List<CustomerResponse> customers = customerService.findCustomers(status, vip);
        return ouputFormatter.coverToTable(customers);
    }

    @Command(command="update-customer",description="Updates Customer attributes by Id. It requires a password token.")
    @CommandAvailability(provider = "userLoggedInAvailability")
    public String updateCustomer(@Option(label = "id", required = true) Long id) {
        CustomerResponse customer = customerService.findCustomer(id);
        customerService.deleteCustomer(id);
        return String.format("Customer %d deleted successfully", id);
    }
    @Command(command="delete-customer",description="Delete Customer By Id. It requires a password token.")
    @CommandAvailability(provider = "userLoggedInAvailability")
    public String deleteCustomer(
            @Range(min=1, max=9999, message = "Customer id must be between {min} and {max}.")
            @Option(label = "id", required = true) Long id) {

//        ComponentFlow flow = componentFlowBuilder.clone().reset()
//                .withConfirmationInput("confirmation")
//                .name("Confirm Deletion")
//                .postHandler(confirmationInputContext -> {
//                    if (confirmationInputContext.getResultValue())
//                        customerService.deleteCustomer(id);
//                })
//                .and().build();
//        flow.run();
        customerService.deleteCustomer(id);
        return String.format("Customer %d deleted successfully", id);
    }

    @Command(description = "Testing for flow component")
    public void flowTest() {
        ComponentFlow flow = componentFlowBuilder.clone().reset()
                .terminal(this.terminal)
                .withStringInput("field1")
                .name("Field1")
                .defaultValue("defaultField1Value")
                .and()
                .withStringInput("field2")
                .name("Field2")
                .and()
                .withConfirmationInput("confirmation1")
                .name("Confirmation1")
                .and()
                .build();

        flow.run();
    }

}
