package org.aaj.example.postgresqletlscheduler.service;

import lombok.extern.log4j.Log4j2;
import org.aaj.example.postgresqletlscheduler.model.EcommerceSaleEntity;
import org.aaj.example.postgresqletlscheduler.repository.EcommerceSaleEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class EcommerceSaleEntityCrudOperatorServiceImpl implements EcommerceSaleEntityCrudOperatorService {

    private final EcommerceSaleEntityRepository repository;

    @Autowired
    public EcommerceSaleEntityCrudOperatorServiceImpl(EcommerceSaleEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<EcommerceSaleEntity> get(Integer id) {
        try {
            return repository.findById(id);
        } catch (Exception e) {
            log.error("Failed to get EcommerceSaleEntity with id: {}", id, e);
            return Optional.empty();
        }
    }

    @Override
    public EcommerceSaleEntity create(EcommerceSaleEntity ecommerceSaleEntity) {
        try {
            return repository.save(ecommerceSaleEntity);
        } catch (Exception e) {
            log.error("Failed to create EcommerceSaleEntity: {}", ecommerceSaleEntity, e);
            return null;
        }
    }

    @Override
    public EcommerceSaleEntity update(EcommerceSaleEntity ecommerceSaleEntity) {
        try {
            return repository.save(ecommerceSaleEntity);
        } catch (Exception e) {
            log.error("Failed to update EcommerceSaleEntity: {}", ecommerceSaleEntity, e);
            return null;
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            log.error("Failed to delete EcommerceSaleEntity with id: {}", id, e);
        }
    }

    @Override
    public List<EcommerceSaleEntity> get(List<Integer> ids) {
        try {
            return repository.findAllById(ids);
        } catch (Exception e) {
            log.error("Failed to get EcommerceSaleEntities with ids: {}", ids, e);
            return List.of();
        }
    }

    @Override
    public List<EcommerceSaleEntity> create(List<EcommerceSaleEntity> ecommerceSaleEntities) {
        try {
            return repository.saveAll(ecommerceSaleEntities);
        } catch (Exception e) {
            log.error("Failed to create EcommerceSaleEntities: {}", ecommerceSaleEntities, e);
            return List.of();
        }
    }

    @Override
    public List<EcommerceSaleEntity> update(List<EcommerceSaleEntity> ecommerceSaleEntities) {
        try {
            return repository.saveAll(ecommerceSaleEntities);
        } catch (Exception e) {
            log.error("Failed to update EcommerceSaleEntities: {}", ecommerceSaleEntities, e);
            return List.of();
        }
    }

    @Override
    public void delete(List<Integer> ids) {
        try {
            ids.forEach(repository::deleteById);
        } catch (Exception e) {
            log.error("Failed to delete EcommerceSaleEntities with ids: {}", ids, e);
        }
    }

    @Override
    public List<EcommerceSaleEntity> getAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            log.error("Failed to get all EcommerceSaleEntities", e);
            return List.of();
        }
    }
}