package com.djachtoma.service;

import com.djachtoma.exception.ItemNotFoundException;
import com.djachtoma.model.Gender;
import com.djachtoma.model.id.IDCard;
import com.djachtoma.model.patient.Patient;
import com.djachtoma.model.patient.dto.PatientDTO;
import com.djachtoma.model.patient.dto.PatientFilter;
import com.djachtoma.model.patient.dto.PatientMapper;
import com.djachtoma.model.phone.PhoneNumber;
import com.djachtoma.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.TreeSet;

import static com.djachtoma.model.patient.dto.PatientMapper.toDTO;
import static com.djachtoma.utils.ObjectUtils.nullSafeUpdate;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public TreeSet<PatientDTO> getPatients() {
        TreeSet<PatientDTO> patients = new TreeSet<>();
        patientRepository.findAll().forEach(patient -> patients.add(toDTO(patient)));
        return patients;
    }

    public PatientDTO getPatient(PatientFilter patientFilter) {
        return patientRepository.getPatientFilters(patientFilter)
                .map(PatientMapper::toDTO)
                .orElseThrow(() -> new ItemNotFoundException("Patient with provided data does not exits"));
    }

    @Transactional
    public PatientDTO createPatient(PatientDTO patientDTO) {
        Patient patient = patientMapper.toEntity(patientDTO);
        patientRepository.save(patient);
        return toDTO(patient);
    }

    @Transactional
    public void deletePatient(String id) {
        Patient patient = getPatient(id);
        patientRepository.delete(patient);
    }

    @Transactional
    public PatientDTO updatePatient(String id, PatientDTO patientDTO) {
        Patient patient = getPatient(id);
        updatePatientEntity(patient, patientDTO);
        return toDTO(patient);
    }


    private Patient getPatient(String id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(String.format("Patient with provided ID: %s does not exit.", id)));
    }

    private Patient updatePatientEntity(Patient patient, PatientDTO patientDTO) {
        nullSafeUpdate(patientDTO.getName(), patientDTO::getName, patient::setName);
        nullSafeUpdate(patientDTO.getSurname(), patientDTO::getSurname, patient::setName);
        nullSafeUpdate(patientDTO.getGender(), patientDTO::getGender, x -> patient.setGender(Gender.valueOf(x)));
        nullSafeUpdate(patient.getDateOfBirth(), patientDTO::getDateOfBirth, patient::setDateOfBirth);
        nullSafeUpdate(patientDTO.getIdCardSeriesNumber(), patientDTO::getIdCardSeriesNumber, x -> patient.setIdCard(IDCard.builder()
                .seriesNumber(x)
                .build()));
        nullSafeUpdate(patientDTO.getPhoneNumber(), patientDTO::getPhoneNumber, x -> patient.setPhoneNumber(PhoneNumber.builder()
                .number(x)
                .build()));
        patientRepository.save(patient);
        return patient;
    }
}
