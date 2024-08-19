package org.aaj.example.mongodbcsvdatawarehouse.router;

import org.aaj.example.mongodbcsvdatawarehouse.handler.CSVHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration(proxyBeanMethods = false)
public class CSVRouter {
    @Bean
    public RouterFunction<ServerResponse> route(CSVHandler handler) {
        return RouterFunctions
                .route(POST("/reactive-streams-basics-demo/api/v1/user"), handler::uploadCSV);
    }
}
