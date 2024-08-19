package org.aaj.example.postgresqletlscheduler.controller;

import lombok.extern.log4j.Log4j2;
import org.aaj.example.postgresqletlscheduler.dto.RunJobResponseDTO;
import org.aaj.example.postgresqletlscheduler.service.ETLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/etl/api/v1")
@RestControllerAdvice
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {})
@Log4j2
public class RunJobController {

    private final ETLService etlService;

    @Autowired
    public RunJobController(@Qualifier("ecommerceSaleETLService") ETLService etlService) {
        this.etlService = etlService;
    }

    @GetMapping("/run-job")
    public ResponseEntity<RunJobResponseDTO> runETLJob() {

        try {
            etlService.runJob();
            return ResponseEntity.ok(RunJobResponseDTO.builder().status("OK").message("Check Superset to validate data").build());
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(RunJobResponseDTO.builder().status("ERROR").message("\"Failed to execute ETL job: \" + e.getMessage()").build());
        }
    }
}
