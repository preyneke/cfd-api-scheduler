package com.gt247.cfdapischeduler.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gt247.cfdapischeduler.schedulingtasks.ScheduledTasks;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


@RestController
@Api
@RequestMapping("/test")
public class ScheduleRestController {

    private static final String SCHEDULED_TASKS = "scheduledTasks";
    @Autowired
    private ScheduledAnnotationBeanPostProcessor postProcessor;

    @Autowired
    private ScheduledTasks scheduledTasks;

    @Autowired
    private ObjectMapper objectMapper;

    @ApiOperation(value="This is to stop Schedular")
    @GetMapping(value = "/stopScheduler")
    public String stopSchedule() {
        postProcessor.postProcessBeforeDestruction(scheduledTasks, SCHEDULED_TASKS);
        return "OK";
    }

    @ApiOperation(value="This is to Stop scheduler")
    @GetMapping(value = "/startScheduler")
    public String startSchedule() {
        postProcessor.postProcessAfterInitialization(scheduledTasks, SCHEDULED_TASKS);
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
