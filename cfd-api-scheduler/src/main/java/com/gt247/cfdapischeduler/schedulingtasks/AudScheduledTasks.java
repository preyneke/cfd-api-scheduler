package com.gt247.cfdapischeduler.schedulingtasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class AudScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ZarScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 2500)
    public void reportCurrentTime() {
        log.info("The AUD time is now {}", dateFormat.format(new Date()));
    }
}