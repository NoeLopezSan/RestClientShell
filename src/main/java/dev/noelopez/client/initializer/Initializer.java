//package dev.noelopez.client.command;
//
//import dev.noelopez.client.config.ClientProperties;
//import dev.noelopez.client.dto.CustomerResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.*;
//import org.springframework.http.client.ClientHttpRequestInterceptor;
//import org.springframework.web.client.RestClient;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Base64;
//import java.util.List;
//
//@Order(1)
//@Configuration
//public class Initializer implements CommandLineRunner {
//    private Logger logger = LoggerFactory.getLogger(Initializer.class);
//    private ClientProperties properties;
//    public Initializer(ClientProperties properties) {
//        this.properties = properties;
//    }
//
//    public void run(String... args) throws Exception {
//        RestClient restClient = RestClient.builder()
//                .baseUrl(properties.getUrl())
//                .defaultHeader(HttpHeaders.AUTHORIZATION,
//                    encodeBasic(properties.getUsername(),properties.getPassword()))
//                .defaultStatusHandler(
//                HttpStatusCode::is4xxClientError,
//                (request, response) -> {
//                    logger.error("Client Error Status "+response.getStatusCode());
//                    logger.error("Client Error Body "+new String(response.getBody().readAllBytes()));
//                })
//                .build();
//
////        SimpleResponse simpleResponse = restClient.get()
////                .uri("/{id}",4)
////                .accept(MediaType.APPLICATION_JSON)
////                .exchange((req,res) -> switch (res.getStatusCode().value()) {
////                        case 200 -> SimpleResponse.FOUND;
////                        case 404 -> SimpleResponse.NOT_FOUND;
////                        default -> SimpleResponse.ERROR;
////                    }
////                );
////
////        logger.info("simpleResponse " + simpleResponse);
//        logger.info(Thread.currentThread().getName());
//        logger.info(LocalDateTime.now().toString());
////        List<CustomerResponse> customers = restClient.get()
//        SimpleResponse simpleResponse = restClient.get()
//                .accept(MediaType.APPLICATION_JSON)
////                .retrieve()
////                .body(List.class);
//                .exchange((req,res) -> {
//                    logger.info(Thread.currentThread().getName());
//                        return switch (res.getStatusCode().value()) {
//                        case 200 -> SimpleResponse.FOUND;
//                        case 404 -> SimpleResponse.NOT_FOUND;
//                        default -> SimpleResponse.ERROR;
//                    };
//                });
//        logger.info(LocalDateTime.now().toString());
//        logger.info("simpleResponse " + simpleResponse);
////        logger.info("Customers size " + customers.size());
//
//
//        String data = restClient.get()
//                .uri("?status={STATUS}&vip={vip}","activated", true)
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .body(String.class);
//
//        logger.info(data);
//
//        List<CustomerResponse> customers = restClient.get()
//                .uri("?status={STATUS}&vip={vip}","activated", true)
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .body(List.class);
//
//        logger.info("Customers size " + customers.size());
//    }
//
//    private String encodeBasic(String username, String password) {
//        return "Basic "+Base64
//            .getEncoder()
//            .encodeToString((username+":"+password).getBytes());
//    }
//
//    enum SimpleResponse { FOUND, NOT_FOUND, ERROR }
//}
