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
    public Mono<EcommerceSaleDocument> get(Integer id) {
        try {
            return repository.findById(id);
        } catch (Exception e) {
            log.error("Failed to get document with id: {}", id, e);
            return Mono.error(e);
        }
    }

    @Override
    public Mono<EcommerceSaleDocument> create(EcommerceSaleDocument document) {
        try {
            return repository.save(document);
        } catch (Exception e) {
            log.error("Failed to create document: {}", document, e);
            return Mono.error(e);
        }
    }

    @Override
    public Mono<EcommerceSaleDocument> update(EcommerceSaleDocument document) {
        try {
            return repository.save(document);
        } catch (Exception e) {
            log.error("Failed to update document: {}", document, e);
            return Mono.error(e);
        }
    }

    @Override
    public Mono<Void> delete(Integer id) {
        try {
            return repository.deleteById(id);
        } catch (Exception e) {
            log.error("Failed to delete document with id: {}", id, e);
            return Mono.error(e).then();
        }
    }

    @Override
    public Flux<EcommerceSaleDocument> get(Iterable<Integer> ids) {
        try {
            return repository.findAllById(ids);
        } catch (Exception e) {
            log.error("Failed to get documents with ids: {}", ids, e);
            return Flux.error(e);
        }
    }

    @Override
    public Flux<EcommerceSaleDocument> create(Iterable<EcommerceSaleDocument> documents) {
        try {
            return repository.saveAll(documents);
        } catch (Exception e) {
            log.error("Failed to create documents: {}", documents, e);
            return Flux.error(e);
        }
    }

    @Override
    public Flux<EcommerceSaleDocument> update(Iterable<EcommerceSaleDocument> documents) {
        try {
            return repository.saveAll(documents);
        } catch (Exception e) {
            log.error("Failed to update documents: {}", documents, e);
            return Flux.error(e);
        }
    }

    @Override
    public Mono<Void> delete(Iterable<Integer> ids) {
        try {
            return repository.deleteAllById(ids);
        } catch (Exception e) {
            log.error("Failed to delete documents with ids: {}", ids, e);
            return Mono.error(e).then();
        }
    }

    @Override
    public Flux<EcommerceSaleDocument> getAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            log.error("Failed to get all documents", e);
            return Flux.error(e);
        }
    }
}