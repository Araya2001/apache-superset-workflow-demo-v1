package org.aaj.example.mongodbcsvdatawarehouse.router;

import org.aaj.example.mongodbcsvdatawarehouse.handler.CsvHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration(proxyBeanMethods = false)
public class CsvRouter {
    @Bean
    public RouterFunction<ServerResponse> route(CsvHandler handler) {
        return RouterFunctions
                .route(POST("/upload-csv"), handler::uploadCSV);
    }
}
