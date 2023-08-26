package com.djachtoma.model.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {

    private String name;
    private String surname;
    private LocalDateTime dateOfBirth;
    private String gender;
    private String phoneNumber;
    private String idCardSeriesNumber;

}
