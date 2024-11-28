package com.nm.patient.repository;

import com.nm.patient.model.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Indexed;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Integer> {

    Optional<Patient> findByFirstNameAndLastName(String firstName, String lastName);

}
