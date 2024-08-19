package org.aaj.example.mongodbcsvdatawarehouse.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface CsvHandler {
    Mono<ServerResponse> uploadCSV(ServerRequest serverRequest);
}
