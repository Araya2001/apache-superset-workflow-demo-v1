package org.aaj.example.mongodbcsvdatawarehouse.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Log4j2
public class CSVHandlerImpl implements CSVHandler {
    @Override
    public Mono<ServerResponse> uploadCSV(ServerRequest serverRequest) {

        var multipartDataPublisher = serverRequest.multipartData();

        var multipartExtractedFile = multipartDataPublisher.map(parts -> (MultipartFile) parts.toSingleValueMap().get("file"));

        map

        return null;
    }
}
