package com.hussian.userregistration.entities;

import jakarta.persistence.*;
import org.hibernate.type.descriptor.converter.internal.ArrayConverter;

import java.util.List;

@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registrationId;

    private String name;

    private String cnic;

    private Long eventId;

    @ElementCollection
    @CollectionTable(name = "FAMILY_MEMBERS_LIST", joinColumns = @JoinColumn(name = "REGISTRATION_ID"))
    private List<String> familyMembersList;

    // Getters and Setters

    public Registration() {
    }

    public Registration(Long registrationId, String name, String cnic, Long eventId, List<String> familyMembersList) {
        this.registrationId = registrationId;
        this.name = name;
        this.cnic = cnic;
        this.eventId = eventId;
        this.familyMembersList = familyMembersList;
    }

    public Long getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Long registrationId) {
        this.registrationId = registrationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public List<String> getFamilyMembersList() {
        return familyMembersList;
    }

    public void setFamilyMembersList(List<String> familyMembersList) {
        this.familyMembersList = familyMembersList;
    }
}