package com.hussian.userregistration.service;

import com.hussian.userregistration.entities.Registration;
import com.hussian.userregistration.repositories.EventRepository;
import com.hussian.userregistration.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private EventRepository eventRepository;

    public Registration registerUser(Registration registration) {
        return registrationRepository.save(registration);
    }

    public List<String> validateInput(Registration registration) {
        List<String> errors = new ArrayList<>();

        if (registration.getName() == null) {
            errors.add("Mame is missing");
        } else if (!registration.getName().matches("^[a-zA-Z]+( [a-zA-Z]+)*$")) {
            errors.add("Name contains invalid characters");
        }

        if (registration.getCnic() == null || !registration.getCnic().matches("^\\d{13}$")) {
            errors.add("CNIC is mandatory and should contain 13 digits");
        }

        if (registration.getFamilyMembersList() != null) {
            Set<String> familyCnics = new HashSet<>();
            for (String cnic : registration.getFamilyMembersList()) {
                if (!cnic.matches("^\\d{13}$")) {
                    errors.add("Family list contains cnic(s) with invalid characters");
                } else if (!familyCnics.add(cnic)) {
                    errors.add("Family list contains duplicate cnics");
                }
            }
        }

        return errors;
    }

    public boolean isAlreadyRegistered(Registration registration) {
        return !registrationRepository.findByEventId(registration.getEventId()).isEmpty();
    }

    public Registration getRegistrationById(Long registrationId) {
        return registrationRepository.findById(registrationId).orElse(null);
    }
}
