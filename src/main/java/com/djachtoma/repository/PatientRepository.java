package com.djachtoma.repository;

import com.djachtoma.model.patient.Patient;
import com.djachtoma.model.patient.dto.PatientFilter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends CrudRepository<Patient, String> {

    Optional<Patient> getPatientFilters(PatientFilter patientFilter);
}
