package org.aaj.example.postgresqletlscheduler.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;

@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record RunJobResponseDTO(
        String message,
        String status
) {
}
