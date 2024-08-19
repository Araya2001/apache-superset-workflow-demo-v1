package org.aaj.example.mongodbcsvdatawarehouse.service;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

public interface MongoDbCrudOperatorService<Document, Id> {
    Mono<Document> get(Id id);

    Mono<Document> create(Document document);

    Mono<Document> update(Document document);

    Mono<Void> delete(Id id);

    Flux<Document> get(Iterable<Id> ids);

    Flux<Document> create(Iterable<Document> documents);

    Flux<Document> update(Iterable<Document> documents);

    Mono<Void> delete(Iterable<Id> ids);

    Flux<Document> getAll();
}