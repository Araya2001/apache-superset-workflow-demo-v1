package org.aaj.example.postgresqletlscheduler.worker;

import lombok.extern.log4j.Log4j2;
import org.aaj.example.postgresqletlscheduler.service.ETLService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@EnableScheduling
@EnableAsync
public class EcommerceSaleETLWorker {

    private final ETLService ecommeceETLService;

    public EcommerceSaleETLWorker(@Qualifier("ecommerceSaleETLService") ETLService ecommeceETLService) {
        this.ecommeceETLService = ecommeceETLService;
    }

    @Async
    @Scheduled(cron = "0 */15 * * * MON-FRI")
    protected void runScheduledJob() {
        ecommeceETLService.runJob();
    }
}
