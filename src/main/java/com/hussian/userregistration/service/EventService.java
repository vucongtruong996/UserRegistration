package com.hussian.userregistration.service;

import com.hussian.userregistration.entities.Event;
import com.hussian.userregistration.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event findById(Long eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }

}
