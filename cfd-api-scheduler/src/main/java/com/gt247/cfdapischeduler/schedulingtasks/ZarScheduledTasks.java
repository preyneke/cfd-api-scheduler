package com.gt247.cfdapischeduler.schedulingtasks;

import com.gt247.cfdapischeduler.webservice.RunScopeRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ZarScheduledTasks {

    @Autowired
    RunScopeRestClient runScopeRestClient;

    private static final Logger log = LoggerFactory.getLogger(ZarScheduledTasks.class);

    @Value("${zar_api_trigger}")
    private String zarTrigger;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMMM yyyy HH:mm:ss");

    @Scheduled(cron="${test_cron}")
    public void zarScheduler() {
        log.info("Sending Zar Trigger");
        runScopeRestClient.sendTrigger(zarTrigger);
        log.info("ZAR Trigger Sent {}", dateFormat.format(new Date()));
    }

}