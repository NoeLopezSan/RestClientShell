//package dev.noelopez.client.command;
//
//import dev.noelopez.client.config.ClientProperties;
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
//import java.util.Base64;
//@Order(2)
//@Configuration
//public class DeleteInitializer implements CommandLineRunner {
//    private Logger logger = LoggerFactory.getLogger(DeleteInitializer.class);
//    private ClientProperties properties;
//    public DeleteInitializer(ClientProperties properties) {
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
//        ResponseEntity response = restClient.delete()
//                .uri("/{id}",2)
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .onStatus(HttpStatusCode::is4xxClientError,
//                        (req, res) -> logger.error("Couldn't delete "+res.getStatusText())
//                )
//                .toBodilessEntity();
//
//        if (response.getStatusCode().is2xxSuccessful())
//            logger.info("Deleted with status " + response.getStatusCode());
//    }
//
//    private String encodeBasic(String username, String password) {
//        return "Basic "+ Base64
//                .getEncoder()
//                .encodeToString((username+":"+password).getBytes());
//    }
//}
