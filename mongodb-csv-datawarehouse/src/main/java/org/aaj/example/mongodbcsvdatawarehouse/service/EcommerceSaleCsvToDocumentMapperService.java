package org.aaj.example.mongodbcsvdatawarehouse.service;

import org.aaj.example.mongodbcsvdatawarehouse.model.EcommerceSaleDocument;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;

import java.util.List;

public interface EcommerceSaleCsvToDocumentMapperService extends ObjectMapperService<FilePart, Flux<EcommerceSaleDocument>> {
}
