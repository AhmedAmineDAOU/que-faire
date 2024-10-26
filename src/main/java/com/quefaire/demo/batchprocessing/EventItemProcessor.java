package com.quefaire.demo.batchprocessing;

import com.quefaire.demo.entity.Event;
import org.springframework.batch.item.ItemProcessor;

public class EventItemProcessor implements ItemProcessor<Event, Event>{

    @Override
    public Event process(Event event) throws Exception {
        //Transformation des données
        //Filtrage
        //Nettoyage des données
        return event;
    }
}
