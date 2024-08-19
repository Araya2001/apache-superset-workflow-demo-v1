package org.aaj.example.postgresqletlscheduler.repository;


import org.aaj.example.postgresqletlscheduler.model.EcommerceSaleDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EcommerceSaleDocumentRepository extends MongoRepository<EcommerceSaleDocument, Integer> {
}
