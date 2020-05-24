package com.gt247.cfdapischeduler.schedulingtasks;

import com.gt247.cfdapischeduler.webservice.RunScopeRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class UsdScheduledTasks {

    @Value("${usd_api_trigger}")
    private String usdTrigger;

    @Autowired
    RunScopeRestClient runScopeRestClient;

    private static final Logger log = LoggerFactory.getLogger(ZarScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMMM yyyy HH:mm:ss");

    @Scheduled(cron = "${usd_cron}")
    public void ustScheduler() {
        log.info("Sending USD Trigger");
        runScopeRestClient.sendTrigger(usdTrigger);
        log.info("USD Trigger Sent {}", dateFormat.format(new Date()));
    }
}