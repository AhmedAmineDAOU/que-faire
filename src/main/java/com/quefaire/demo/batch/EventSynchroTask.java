package com.quefaire.demo.batch;


import com.quefaire.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventSynchroTask {

    @Autowired
    private final EventService eventService;

    @Autowired
    public EventSynchroTask(EventService eventService) {
        this.eventService = eventService;
    }
}

