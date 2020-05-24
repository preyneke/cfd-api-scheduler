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
public class AudScheduledTasks {
    @Value("${aud_api_trigger}")
    private String audTrigger;

    @Autowired
    RunScopeRestClient runScopeRestClient;

    private static final Logger log = LoggerFactory.getLogger(ZarScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMMM yyyy HH:mm:ss");

    @Scheduled(cron = "${aud_cron_Mon_THURS}")
    public void weeklyAudSchedule() {
        log.info("Sending AUD Trigger");
        runScopeRestClient.sendTrigger(audTrigger);
        log.info("AUD Trigger Sent {}", dateFormat.format(new Date()));
    }

    @Scheduled(cron = "${aud_cron_Fri}")
    public void fridayAudSchedule() {
        log.info("Sending AUD Trigger");
        runScopeRestClient.sendTrigger(audTrigger);
        log.info("AUD Trigger Sent {}", dateFormat.format(new Date()));
    }

    @Scheduled(cron = "${aud_cron_Sunday}")
    public void sundayAudSchedule() {
        log.info("Sending AUD Trigger");
        runScopeRestClient.sendTrigger(audTrigger);
        log.info("AUD Trigger Sent {}", dateFormat.format(new Date()));
    }
}