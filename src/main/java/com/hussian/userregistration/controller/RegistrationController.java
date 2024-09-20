package com.hussian.userregistration.controller;

import com.hussian.userregistration.entities.Event;
import com.hussian.userregistration.entities.Registration;
import com.hussian.userregistration.model.ErrorResponse;
import com.hussian.userregistration.service.EventService;
import com.hussian.userregistration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<?> createRegistration(@RequestBody Registration registration) {
        // Perform input validations
        List<String> errors = registrationService.validateInput(registration);
        if (!errors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors.toString()));
        }

        // Check if the event exists
        Event event = eventService.findById(registration.getEventId());
        if (event == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Provided event does not exist"));
        }

        // Check if the user is already registered for the event
        if (registrationService.isAlreadyRegistered(registration)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse("Already registered"));
        }

        // Process registration
        Registration savedRegistration = registrationService.registerUser(registration);
        return ResponseEntity.ok().body(Map.of("registrationId", savedRegistration.getRegistrationId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> viewRegistration(@PathVariable("id")  Long id) {
        Registration registration = registrationService.getRegistrationById(id);

        if (registration == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(registration);

    }
}
