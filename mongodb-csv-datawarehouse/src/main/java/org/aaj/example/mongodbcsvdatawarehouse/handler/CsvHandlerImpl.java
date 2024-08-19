package org.aaj.example.mongodbcsvdatawarehouse.handler;

import lombok.extern.log4j.Log4j2;
import org.aaj.example.mongodbcsvdatawarehouse.dto.ModelResponse;
import org.aaj.example.mongodbcsvdatawarehouse.model.EcommerceSaleDocument;
import org.aaj.example.mongodbcsvdatawarehouse.service.EcommerceSaleCsvToDocumentMapperService;
import org.aaj.example.mongodbcsvdatawarehouse.service.EcommerceSaleDocumentCrudOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Log4j2
public class CsvHandlerImpl implements CsvHandler {

    private final EcommerceSaleDocumentCrudOperatorService ecommerceSaleDocumentCrudOperatorService;
    private final EcommerceSaleCsvToDocumentMapperService ecommerceSaleCsvToDocumentMapperService;


    @Autowired
    public CsvHandlerImpl(EcommerceSaleDocumentCrudOperatorService ecommerceSaleDocumentCrudOperatorService,
                          EcommerceSaleCsvToDocumentMapperService ecommerceSaleCsvToDocumentMapperService) {
        this.ecommerceSaleDocumentCrudOperatorService = ecommerceSaleDocumentCrudOperatorService;
        this.ecommerceSaleCsvToDocumentMapperService = ecommerceSaleCsvToDocumentMapperService;
    }

    @Override
    public Mono<ServerResponse> uploadCSV(ServerRequest serverRequest) {

        // Extract Multipart data from ServerRequest
        var multipartDataPublisher = serverRequest.multipartData();

        // Map multiple file parts into a single value map
        var multiValueMapExtractedPart = multipartDataPublisher.map(parts -> parts.toSingleValueMap().get("file"));

        // Filter Part to be instanceof FilePart since we want files.
        var partFileExtractedFromPartInterface = multiValueMapExtractedPart
                .filter(part -> part instanceof FilePart)
                .ofType(FilePart.class)
                .map(filePart -> filePart);

        // Map Multipart File to MongoDB Documents
        var mappedEcommerceSaleDocumentsFromFile =
                partFileExtractedFromPartInterface.flatMapMany(ecommerceSaleCsvToDocumentMapperService::mapInitialObjectToResultObject);

        // Insert the documents in MongoDb from the created flux
        var createdEcommerceSaleDocuments = ecommerceSaleDocumentCrudOperatorService.create(mappedEcommerceSaleDocumentsFromFile);


        // Build a Server response with inline return value
        return createdEcommerceSaleDocuments.
                collectList()
                .flatMap(ecommerceSaleDocuments ->
                        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(
                                ModelResponse.<List<EcommerceSaleDocument>>builder()
                                        .status("OK")
                                        .message("The Ecommerce Sale Documents have been uploaded")
                                        .model(ecommerceSaleDocuments)
                                        .build())
                )
                .doOnError(throwable -> log.error(throwable.getMessage(), throwable));
    }
}
