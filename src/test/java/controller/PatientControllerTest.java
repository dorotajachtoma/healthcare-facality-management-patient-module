package controller;

import com.djachtoma.model.Gender;
import com.djachtoma.model.patient.dto.PatientDTO;
import configuration.RedisContainerSetup;
import configuration.TestSetup;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PatientControllerTest extends TestSetup {

    private static final String NAME = "NAME";
    private static final String SURNAME = "SURNAME";
    private static final LocalDateTime DATE_OF_BIRTH = LocalDateTime.now();
    private static final String PHONE_NUMBER = "552-123-565";
    private static final String ID_CARD_SERIES_NUMBER = "DFG2312";
    private static final String CITY = "CITY";
    private static final String ZIP_CODE = "ZIP_CODE";
    private static final String STREET = "STREET";

    @Autowired
    private WebTestClient client;

    @Autowired
    private RedisContainerSetup redisContainerSetup;

    @Before
    public void setup() {
        redisContainerSetup.start();
    }

    @Test
    public void getAllPatientsShouldReturnAllPatient() {
        //when
        List<PatientDTO> result = this.client.get()
                .uri("/api/patient")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBodyList(PatientDTO.class)
                .returnResult()
                .getResponseBody();
        //then
    }

    @Test
    public void createPatientShouldReturnPatientDTO() {
        //given
        PatientDTO patientDTO = getPatientDTO();

        //when
        PatientDTO result = this.client.post()
                .uri("/api/patient")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(patientDTO)
                .exchange()
                .expectBody(PatientDTO.class)
                .returnResult()
                .getResponseBody();

        //then
        assertThat(patientDTO).isEqualTo(result);
    }

    private static PatientDTO getPatientDTO() {
        return PatientDTO.builder()
                .name(NAME)
                .surname(SURNAME)
                .dateOfBirth(DATE_OF_BIRTH)
                .phoneNumber(PHONE_NUMBER)
                .idCardSeriesNumber(ID_CARD_SERIES_NUMBER)
                .gender(Gender.FEMALE.toString())
                .city(CITY)
                .zipCode(ZIP_CODE)
                .address(STREET)
                .build();
    }
}
