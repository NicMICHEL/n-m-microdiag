package com.nm.patient.service;

import com.nm.patient.controller.PatientController;
import com.nm.patient.exception.NotFoundException;
import com.nm.patient.model.Patient;
import com.nm.patient.repository.PatientRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    private static final Logger logger = LogManager.getLogger(PatientService.class);

    public Optional<Patient> get(String firstName, String lastName) {
        return patientRepository.findByFirstNameAndLastName(firstName, lastName);
    }


    @Transactional
    public Patient update(Patient patient) throws NotFoundException {
        Optional<Patient> testPatient = patientRepository.findById(patient.getIdPatient());
        if (testPatient.isPresent()) {
            patientRepository.save(patient);
            return patientRepository.findById(patient.getIdPatient()).get();
        } else {
            logger.error("Unable to find and update patient corresponding to id {}", patient.getIdPatient());
            throw new NotFoundException("Invalid patient id");
        }
    }

}

    /*
    private static final Logger logger = LogManager.getLogger(PatientService.class);

    public Patient get(String firstName, String lastName) {
        Optional<Patient> patient = patientRepository.findByFirstNameAndLastName(firstName, lastName);
        if (patient.isPresent()) {
            return patient.get();
        } else {
            logger.error("Unable to find patient corresponding to firstName {} and lastName {}", firstName, lastName);
            throw new IllegalArgumentException("Invalid patient firstName and lastName ");
        }

    }

     */

