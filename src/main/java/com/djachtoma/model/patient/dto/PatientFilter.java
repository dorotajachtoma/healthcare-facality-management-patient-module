package com.djachtoma.model.patient.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatientFilter {

    private String name;
    private String surname;
    private String idCardSeriesNumber;
}
