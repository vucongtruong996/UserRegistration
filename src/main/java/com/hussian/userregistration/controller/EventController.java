package com.hussian.userregistration.controller;

import com.hussian.userregistration.entities.Event;
import com.hussian.userregistration.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        Long eventId = eventService.createEvent(event).getEventId();
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("eventId", eventId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> viewRegistration(@PathVariable("id")  Long id) {
        Event event = eventService.findById(id);

        if (event == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(event);

    }
}
