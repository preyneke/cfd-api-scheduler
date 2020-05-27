package com.gt247.cfdapischeduler.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gt247.cfdapischeduler.schedulingtasks.AudScheduledTasks;
import com.gt247.cfdapischeduler.schedulingtasks.DcxScheduledTask;
import com.gt247.cfdapischeduler.schedulingtasks.UsdScheduledTasks;
import com.gt247.cfdapischeduler.schedulingtasks.ZarScheduledTasks;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;


@RestController
@Api
@RequestMapping("/ExchangeSchedulers")
public class ScheduleRestController {

    private static final String SCHEDULED_TASKS = "scheduledTasks";

    @Autowired
    private ScheduledAnnotationBeanPostProcessor postProcessor;

    @Autowired
    private ZarScheduledTasks zarScheduledTasks;

    @Autowired
    private UsdScheduledTasks usdScheduledTasks;

    @Autowired
    private AudScheduledTasks audScheduledTasks;

    @Autowired
    private DcxScheduledTask dcxScheduledTask;

    @Autowired
    private ObjectMapper objectMapper;

    @ApiOperation(value="This is to stop ZAR Scheduler")
    @GetMapping(value = "/stopZarScheduler")
    public String stopZarSchedule() {
        postProcessor.postProcessBeforeDestruction(zarScheduledTasks, SCHEDULED_TASKS);
        return "OK";
    }

    @ApiOperation(value="This is to start ZAR scheduler")
    @GetMapping(value = "/startZarScheduler")
    public String startZarSchedule() {
        postProcessor.postProcessAfterInitialization(zarScheduledTasks, SCHEDULED_TASKS);
        return "OK";
    }

    @ApiOperation(value="This is to stop USD Scheduler")
    @GetMapping(value = "/stopUsdScheduler")
    public String stopUsdSchedule() {
        postProcessor.postProcessBeforeDestruction(usdScheduledTasks, SCHEDULED_TASKS);
        return "OK";
    }

    @ApiOperation(value="This is to Start USD scheduler")
    @GetMapping(value = "/startUsdScheduler")
    public String startUsdSchedule() {
        postProcessor.postProcessAfterInitialization(usdScheduledTasks, SCHEDULED_TASKS);
        return "OK";
    }

    @ApiOperation(value="This is to stop AUD Scheduler")
    @GetMapping(value = "/stopAudScheduler")
    public String stopAudSchedule() {
        postProcessor.postProcessBeforeDestruction(audScheduledTasks, SCHEDULED_TASKS);
        return "OK";
    }

    @ApiOperation(value="This is to Start AUD scheduler")
    @GetMapping(value = "/startAudScheduler")
    public String startAudSchedule() {
        postProcessor.postProcessAfterInitialization(audScheduledTasks, SCHEDULED_TASKS);
        return "OK";
    }

    @ApiOperation(value="This is to stop DCX Scheduler")
    @GetMapping(value = "/stopDcxScheduler")
    public String stopDcxSchedule() {
        postProcessor.postProcessBeforeDestruction(dcxScheduledTask, SCHEDULED_TASKS);
        return "OK";
    }

    @ApiOperation(value="This is to Start DCX scheduler")
    @GetMapping(value = "/startDcxScheduler")
    public String startDcxSchedule() {
        postProcessor.postProcessAfterInitialization(dcxScheduledTask, SCHEDULED_TASKS);
        return "OK";
    }
    @GetMapping(value = "/listScheduler")
    public String listSchedules() throws JsonProcessingException {
        Set<ScheduledTask> setTasks = postProcessor.getScheduledTasks();
        if (!setTasks.isEmpty()) {

            return objectMapper.writeValueAsString(setTasks);
        } else {
            return "No running tasks !";
        }
    }
}
