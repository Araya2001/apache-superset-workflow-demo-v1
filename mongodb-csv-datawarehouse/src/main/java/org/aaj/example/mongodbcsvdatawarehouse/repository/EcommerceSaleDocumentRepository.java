package org.aaj.example.mongodbcsvdatawarehouse.repository;

import org.aaj.example.mongodbcsvdatawarehouse.model.EcommerceSaleDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EcommerceSaleDocumentRepository extends ReactiveMongoRepository<EcommerceSaleDocument, Integer> {
}
