//package dev.noelopez.client.command;
//
//import dev.noelopez.client.config.ClientProperties;
//import dev.noelopez.client.dto.CustomerRequest;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestClient;
//
//import java.time.LocalDate;
//import java.util.Base64;
//
//@Order(3)
//@Configuration
//public class PostInitializer implements CommandLineRunner {
//    private Logger logger = LoggerFactory.getLogger(PostInitializer.class);
//    private ClientProperties properties;
//    public PostInitializer(ClientProperties properties) {
//        this.properties = properties;
//    }
//    @Override
//    public void run(String... args) throws Exception {
//        RestClient restClient = RestClient.builder()
//                .baseUrl(properties.getUrl())
//                .defaultHeader(HttpHeaders.AUTHORIZATION,
//                        encodeBasic(properties.getUsername(),properties.getPassword()))
//                .defaultStatusHandler(
//                        HttpStatusCode::is4xxClientError,
//                        (request, response) -> {
//                            logger.error("Client Error Status "+response.getStatusCode());
//                            logger.error("Client Error Body "+new String(response.getBody().readAllBytes()));
//                        })
//                .build();
//
//        CustomerRequest customer = new CustomerRequest(
//                "John Smith",
//                "john.smith@mycompany.com",
//                LocalDate.of(1998, 10, 25),
//                "Customer detailed info here",
//                true
//        );
//        ResponseEntity<Void> response = restClient.post()
//                .accept(MediaType.APPLICATION_JSON)
//                .body(customer)
//                .retrieve()
//                .toBodilessEntity();
//
//        if (response.getStatusCode().is2xxSuccessful()) {
//            logger.info("Created " + response.getStatusCode());
//            logger.info("New URL " + response.getHeaders().getLocation());
//        }
//    }
//    private String encodeBasic(String username, String password) {
//        return "Basic "+ Base64
//                .getEncoder()
//                .encodeToString((username+":"+password).getBytes());
//    }
//}
