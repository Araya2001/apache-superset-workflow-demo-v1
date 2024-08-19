package org.aaj.example.mongodbcsvdatawarehouse.service;

import lombok.extern.log4j.Log4j2;
import org.aaj.example.mongodbcsvdatawarehouse.model.EcommerceSaleDocument;
import org.aaj.example.mongodbcsvdatawarehouse.repository.EcommerceSaleDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Log4j2
public class EcommerceSaleDocumentCrudOperatorServiceImpl implements EcommerceSaleDocumentCrudOperatorService {

    private final EcommerceSaleDocumentRepository repository;

    @Autowired
    public EcommerceSaleDocumentCrudOperatorServiceImpl(EcommerceSaleDocumentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<EcommerceSaleDocument> get(Mono<Integer> idMono) {
        return idMono.flatMap(id ->
                repository.findById(id)
                        .doOnError(e -> log.error("Failed to get document with id: {}", id, e))
                        .onErrorResume(e -> Mono.error(new RuntimeException("Document not found", e)))
        );
    }

    @Override
    public Mono<EcommerceSaleDocument> create(Mono<EcommerceSaleDocument> documentMono) {
        return documentMono.flatMap(document ->
                repository.save(document)
                        .doOnError(e -> log.error("Failed to create document: {}", document, e))
                        .onErrorResume(e -> Mono.error(new RuntimeException("Could not create document", e)))
        );
    }

    @Override
    public Mono<EcommerceSaleDocument> update(Mono<EcommerceSaleDocument> documentMono) {
        return documentMono.flatMap(document ->
                repository.save(document)
                        .doOnError(e -> log.error("Failed to update document: {}", document, e))
                        .onErrorResume(e -> Mono.error(new RuntimeException("Could not update document", e)))
        );
    }

    @Override
    public Mono<Void> delete(Mono<Integer> idMono) {
        return idMono.flatMap(id ->
                repository.deleteById(id)
                        .doOnError(e -> log.error("Failed to delete document with id: {}", id, e))
                        .onErrorResume(e -> Mono.error(new RuntimeException("Could not delete document", e)))
        );
    }

    @Override
    public Flux<EcommerceSaleDocument> get(Flux<Integer> idsFlux) {
        return idsFlux.collectList().flatMapMany(ids ->
                repository.findAllById(ids)
                        .doOnError(e -> log.error("Failed to get documents with ids: {}", ids, e))
                        .onErrorResume(e -> Flux.error(new RuntimeException("Documents not found", e)))
        );
    }

    @Override
    public Flux<EcommerceSaleDocument> create(Flux<EcommerceSaleDocument> documentsFlux) {
        return documentsFlux.collectList().flatMapMany(documents ->
                repository.saveAll(documents)
                        .doOnError(e -> log.error("Failed to create documents: {}", documents, e))
                        .onErrorResume(e -> Flux.error(new RuntimeException("Could not create documents", e)))
        );
    }

    @Override
    public Flux<EcommerceSaleDocument> update(Flux<EcommerceSaleDocument> documentsFlux) {
        return documentsFlux.collectList().flatMapMany(documents ->
                repository.saveAll(documents)
                        .doOnError(e -> log.error("Failed to update documents: {}", documents, e))
                        .onErrorResume(e -> Flux.error(new RuntimeException("Could not update documents", e)))
        );
    }

    @Override
    public Mono<Void> delete(Flux<Integer> idsFlux) {
        return idsFlux.collectList().flatMap(ids ->
                repository.deleteAllById(ids)
                        .doOnError(e -> log.error("Failed to delete documents with ids: {}", ids, e))
                        .onErrorResume(e -> Mono.error(new RuntimeException("Could not delete documents", e)))
        ).then();
    }

    @Override
    public Flux<EcommerceSaleDocument> getAll() {
        return repository.findAll()
                .doOnError(e -> log.error("Failed to get all documents", e))
                .onErrorResume(e -> Flux.error(new RuntimeException("Failed to retrieve documents", e)));
    }
}