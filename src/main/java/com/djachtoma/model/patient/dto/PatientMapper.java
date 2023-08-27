package com.djachtoma.model.patient.dto;

import com.djachtoma.model.Gender;
import com.djachtoma.model.id.IDCard;
import com.djachtoma.model.patient.Patient;
import com.djachtoma.model.phone.PhoneNumber;

public class PatientMapper {

    public static PatientDTO toDTO(Patient patient) {
        return PatientDTO.builder()
                .name(patient.getName())
                .surname(patient.getSurname())
                .gender(patient.getGender().name())
                .dateOfBirth(patient.getDateOfBirth())
                .phoneNumber(patient.getPhoneNumber().getNumber())
                .idCardSeriesNumber(patient.getIdCard().getSeriesNumber())
                .build();
    }

    public Patient toEntity(PatientDTO patientDTO) {
        return Patient.builder()
                .name(patientDTO.getName())
                .surname(patientDTO.getSurname())
                .dateOfBirth(patientDTO.getDateOfBirth())
                .gender(Gender.valueOf(patientDTO.getGender()))
                .idCard(IDCard.builder()
                        .seriesNumber(patientDTO.getIdCardSeriesNumber())
                        .build())
                .phoneNumber(PhoneNumber.builder()
                        .number(patientDTO.getPhoneNumber())
                        .build())
                .build();
    }


}
