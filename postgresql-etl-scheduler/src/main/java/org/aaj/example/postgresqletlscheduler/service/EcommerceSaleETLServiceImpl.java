package org.aaj.example.postgresqletlscheduler.service;

import lombok.extern.log4j.Log4j2;
import org.aaj.example.postgresqletlscheduler.model.EcommerceSaleDocument;
import org.aaj.example.postgresqletlscheduler.model.EcommerceSaleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("ecommerceSaleETLService")
@Log4j2
public class EcommerceSaleETLServiceImpl implements ETLService {

    private final EcommerceSaleEntityCrudOperatorService entityCrudOperatorService;
    private final EcommerceSaleDocumentCrudOperatorService documentCrudOperatorService;

    @Autowired
    public EcommerceSaleETLServiceImpl(EcommerceSaleEntityCrudOperatorService entityCrudOperatorService, EcommerceSaleDocumentCrudOperatorService documentCrudOperatorService) {
        this.entityCrudOperatorService = entityCrudOperatorService;
        this.documentCrudOperatorService = documentCrudOperatorService;
    }

    @Override
    public void runJob() {

        // Perform pre ETL task
        initTask();

        // Extract Data From MongoDB Data Warehouse
        List<EcommerceSaleDocument> extractedDocuments = extract();

        // Transform Data From MongoDB Data Warehouse for PostgreSQL storage
        List<EcommerceSaleEntity> transformedEntities = transform(extractedDocuments);

        // Load Transformed Entities to PostgreSQL storage
        List<EcommerceSaleEntity> createdEntities = load(transformedEntities);

        log.info("Ecommerce sale ETL Pipeline has been created with Objects {}", createdEntities);

    }

    private List<EcommerceSaleDocument> extract() {
        return documentCrudOperatorService.getAll();
    }

    private List<EcommerceSaleEntity> transform(List<EcommerceSaleDocument> documentList) {
        return documentList.parallelStream()
                .map(document -> EcommerceSaleEntity.builder()
                        .id(document.getProductID())
                        .productName(document.getProductName())
                        .category(document.getCategory())
                        .price(document.getPrice())
                        .rating(document.getRating())
                        .numReviews(document.getNumReviews())
                        .stockQuantity(document.getStockQuantity())
                        .discount(document.getDiscount())
                        .sales(document.getSales())
                        .city(document.getCity())
                        .isStockVast(document.getIsStockVast())
                        .dateAdded(convertToDate(document.getDateAdded()))
                        .dateCreate(Instant.now())
                        .dateUpdate(Instant.now())
                        .build()
                )
                .collect(Collectors.toList());
    }

    private List<EcommerceSaleEntity> load(List<EcommerceSaleEntity> ecommerceSaleEntities) {
        return entityCrudOperatorService.create(ecommerceSaleEntities);
    }

    private void initTask() {
        List<Integer> existingIds = entityCrudOperatorService.getAll().stream().map(EcommerceSaleEntity::getId).toList();
        entityCrudOperatorService.delete(existingIds);
    }


    private LocalDate convertToDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            log.error("Invalid date format: {}", dateString, e);
            return null;
        }
    }


}
