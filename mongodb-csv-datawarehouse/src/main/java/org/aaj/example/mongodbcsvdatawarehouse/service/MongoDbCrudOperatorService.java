package org.aaj.example.mongodbcsvdatawarehouse.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MongoDbCrudOperatorService<Document, Id> {
    Mono<Document> get(Mono<Id> id);

    Mono<Document> create(Mono<Document> document);

    Mono<Document> update(Mono<Document> document);

    Mono<Void> delete(Mono<Id> id);

    Flux<Document> get(Flux<Id> ids);

    Flux<Document> create(Flux<Document> documents);

    Flux<Document> update(Flux<Document> documents);

    Mono<Void> delete(Flux<Id> ids);

    Flux<Document> getAll();
}