package org.aaj.example.postgresqletlscheduler.service;

import lombok.extern.log4j.Log4j2;
import org.aaj.example.postgresqletlscheduler.model.EcommerceSaleDocument;
import org.aaj.example.postgresqletlscheduler.repository.EcommerceSaleDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@Transactional
public class EcommerceSaleDocumentCrudOperatorServiceImpl implements EcommerceSaleDocumentCrudOperatorService {

    private final EcommerceSaleDocumentRepository repository;

    @Autowired
    public EcommerceSaleDocumentCrudOperatorServiceImpl(EcommerceSaleDocumentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<EcommerceSaleDocument> get(Integer id) {
        try {
            return repository.findById(id);
        } catch (Exception e) {
            log.error("Failed to get EcommerceSaleDocument with id: {}", id, e);
            return Optional.empty();
        }
    }

    @Override
    public EcommerceSaleDocument create(EcommerceSaleDocument ecommerceSaleDocument) {
        try {
            return repository.save(ecommerceSaleDocument);
        } catch (Exception e) {
            log.error("Failed to create EcommerceSaleDocument: {}", ecommerceSaleDocument, e);
            return null;
        }
    }

    @Override
    public EcommerceSaleDocument update(EcommerceSaleDocument ecommerceSaleDocument) {
        try {
            return repository.save(ecommerceSaleDocument);
        } catch (Exception e) {
            log.error("Failed to update EcommerceSaleDocument: {}", ecommerceSaleDocument, e);
            return null;
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            log.error("Failed to delete EcommerceSaleDocument with id: {}", id, e);
        }
    }

    @Override
    public List<EcommerceSaleDocument> get(List<Integer> ids) {
        try {
            return repository.findAllById(ids);
        } catch (Exception e) {
            log.error("Failed to get EcommerceSaleDocuments with ids: {}", ids, e);
            return List.of();
        }
    }

    @Override
    public List<EcommerceSaleDocument> create(List<EcommerceSaleDocument> ecommerceSaleDocuments) {
        try {
            return repository.saveAll(ecommerceSaleDocuments);
        } catch (Exception e) {
            log.error("Failed to create EcommerceSaleDocuments: {}", ecommerceSaleDocuments, e);
            return List.of();
        }
    }

    @Override
    public List<EcommerceSaleDocument> update(List<EcommerceSaleDocument> ecommerceSaleDocuments) {
        try {
            return repository.saveAll(ecommerceSaleDocuments);
        } catch (Exception e) {
            log.error("Failed to update EcommerceSaleDocuments: {}", ecommerceSaleDocuments, e);
            return List.of();
        }
    }

    @Override
    public void delete(List<Integer> ids) {
        try {
            ids.forEach(repository::deleteById);
        } catch (Exception e) {
            log.error("Failed to delete EcommerceSaleDocuments with ids: {}", ids, e);
        }
    }

    @Override
    public List<EcommerceSaleDocument> getAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            log.error("Failed to get all EcommerceSaleDocuments", e);
            return List.of();
        }
    }
}