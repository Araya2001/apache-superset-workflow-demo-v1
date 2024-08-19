package org.aaj.example.postgresqletlscheduler.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@SuperBuilder
public class BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "date_create", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant dateCreate;

    @Column(name = "date_update", nullable = false)
    @UpdateTimestamp
    private Instant dateUpdate;

}