package com.nm.patient.controller;

import com.nm.patient.configurations.ApplicationPropertiesConfiguration;
import com.nm.patient.exception.NotFoundException;
import com.nm.patient.model.Patient;
import com.nm.patient.service.PatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/patientFile")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private ApplicationPropertiesConfiguration appProperties;

    private static final Logger logger = LogManager.getLogger(PatientController.class);

    @GetMapping("/{firstName}/{lastName}")
   // @GetMapping(value = "/{firstName}/{lastName}", produces = MediaType.TEXT_PLAIN_VALUE)
    public Patient get(@PathVariable String firstName, @PathVariable String lastName)
            throws NotFoundException {
        Optional<Patient> patient = patientService.get(firstName, lastName);
        if (!patient.isPresent()) {
            logger.error("Unable to find patient corresponding to firstName {} and lastName {}" + appProperties.getTestConstante(), firstName, lastName);
            throw new NotFoundException("Patient with firstName: " + firstName +  "  and lastName: " + lastName +
                    "  doesn't exist in Data Base." );
            }
        return patient.get();
        /*
        if (patient.isPresent()) {
            return patient.get();
        } else {
            logger.error("Unable to find patient corresponding to firstName {} and lastName {}", firstName, lastName);
            throw new NotFoundException("Patient with firstName: " + firstName + "  and lastName: " + lastName +
                    "  doesn't exist in Data Base." );
        }
         */
    }

    // fonctionne donne 201 et uri mais pas JSON updated
    @PutMapping
    public ResponseEntity<Patient> update(@Valid @RequestBody Patient patient) throws NotFoundException {
        Patient updatedPatient = patientService.update(patient);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updatedPatient.getIdPatient())
                .toUri();
        return ResponseEntity.created(location).build();
    }
/*
// fonctionne JSON mdfié mais pas 201 ni URI
    @PutMapping
    public Patient update(@Valid @RequestBody Patient patient) throws NotFoundException {
        return patientService.update(patient);
    }

 */
/*
Pas utile car API REst mais utilité message l 31 ???
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleNotFoundException(NotFoundException notFoundException) {
        return notFoundException.getMessage();
    }

 */

}

