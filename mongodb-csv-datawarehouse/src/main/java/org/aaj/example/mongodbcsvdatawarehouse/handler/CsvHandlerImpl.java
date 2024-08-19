package org.aaj.example.mongodbcsvdatawarehouse.handler;

import lombok.extern.log4j.Log4j2;
import org.aaj.example.mongodbcsvdatawarehouse.dto.ModelResponse;
import org.aaj.example.mongodbcsvdatawarehouse.model.EcommerceSaleDocument;
import org.aaj.example.mongodbcsvdatawarehouse.service.EcommerceSaleCsvToDocumentMapperService;
import org.aaj.example.mongodbcsvdatawarehouse.service.EcommerceSaleDocumentCrudOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
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
        var multipartExtractedFile = multipartDataPublisher.map(parts -> (MultipartFile) parts.toSingleValueMap().get("file"));

        // Map Multipart File to MongoDB Documents
        var mappedEcommerceSaleDocumentsFromFile =
                multipartExtractedFile.map(ecommerceSaleCsvToDocumentMapperService::mapInitialObjectToResultObject);

        // Create flux out of the list from Document Mapper service
        var fluxFromEcommerceSalesDocuments =
                mappedEcommerceSaleDocumentsFromFile.flatMapIterable(ecommerceSaleDocuments -> ecommerceSaleDocuments);

        // Insert the documents in MongoDb from the created flux
        var createdEcommerceSaleDocuments = ecommerceSaleDocumentCrudOperatorService.create(fluxFromEcommerceSalesDocuments);


        // Build a Server response
        var builtServerResponse = createdEcommerceSaleDocuments.
                collectList()
                .map(ecommerceSaleDocuments ->
                        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(
                                ModelResponse.<List<EcommerceSaleDocument>>builder()
                                        .message("The Ecommerce Sale Documents have been uploaded")
                                        .status("OK")
                                        .model(ecommerceSaleDocuments)
                                        .build(),
                                ModelResponse.class)
                )
                .onErrorReturn(
                        ServerResponse.badRequest().contentType(MediaType.APPLICATION_JSON).body(
                                ModelResponse.<List<EcommerceSaleDocument>>builder()
                                        .status("ERROR")
                                        .message("The Ecommerce Sale Documents upload failed")
                                        .model(List.of())
                                        .build(),
                                ModelResponse.class)
                );


        return builtServerResponse.flatMap(serverResponseMono -> serverResponseMono);
    }
}
