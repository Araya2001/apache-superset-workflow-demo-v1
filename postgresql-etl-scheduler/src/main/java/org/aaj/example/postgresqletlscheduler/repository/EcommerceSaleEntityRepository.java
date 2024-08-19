package org.aaj.example.postgresqletlscheduler.repository;

import org.aaj.example.postgresqletlscheduler.model.EcommerceSaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EcommerceSaleEntityRepository extends JpaRepository<EcommerceSaleEntity, Integer> {
}