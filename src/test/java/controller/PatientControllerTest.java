package controller;

import com.djachtoma.model.patient.dto.PatientDTO;
import com.djachtoma.reference.entity.model.Gender;
import configuration.RedisContainerSetup;
import configuration.TestSetup;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PatientControllerTest extends TestSetup {

    private static final String NAME = "NAME";
    private static final String SURNAME = "SURNAME";
    private static final LocalDateTime DATE_OF_BIRTH = LocalDateTime.of(2023, 5, 10, 0, 0);
    private static final String PHONE_NUMBER = "552-123-565";
    private static final String ID_CARD_SERIES_NUMBER = "DFG2312";
    private static final String CITY = "CITY";
    private static final String ZIP_CODE = "ZIP_CODE";
    private static final String STREET = "STREET";

    @Autowired
    private WebTestClient client;

    private RedisContainerSetup redisContainerSetup;

    @BeforeEach
    public void setup() {
        redisContainerSetup.start();
    }

    @Test
    public void getAllPatientsShouldReturnAllPatient() {
        //given
        PatientDTO patientDTO = getPatientDTO();
        this.client.post()
                .uri("/api/patient")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(patientDTO)
                .exchange()
                .expectBody(PatientDTO.class)
                .returnResult()
                .getResponseBody();

        //when
        List<PatientDTO> result = this.client.get()
                .uri("/api/patient")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBodyList(PatientDTO.class)
                .returnResult()
                .getResponseBody();

        //then
        PatientDTO created = result.get(0);
        assertThat(created.getName()).isEqualTo(patientDTO.getName());
        assertThat(created.getSurname()).isEqualTo(patientDTO.getSurname());
        assertThat(created.getGender()).isEqualTo(patientDTO.getGender());
        assertThat(created.getPhoneNumber()).isEqualTo(patientDTO.getPhoneNumber());
        assertThat(created.getDateOfBirth()).isEqualTo(patientDTO.getDateOfBirth().toString());
        assertThat(created.getAddress()).isEqualTo(patientDTO.getAddress());
        assertThat(created.getCity()).isEqualTo(patientDTO.getCity());
        assertThat(created.getZipCode()).isEqualTo(patientDTO.getZipCode());
        assertThat(created.getIdCardSeriesNumber()).isEqualTo(patientDTO.getIdCardSeriesNumber());
    }

    @Test
    public void getPatientShouldReturnPatientDTO() {
        //given
        PatientDTO patientDTO = getPatientDTO();
        PatientDTO created = this.client.post()
                .uri("/api/patient")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(patientDTO)
                .exchange()
                .expectBody(PatientDTO.class)
                .returnResult()
                .getResponseBody();

        //when
        PatientDTO result = this.client.get()
                .uri("/api/patient/" + created.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBody(PatientDTO.class)
                .returnResult()
                .getResponseBody();

        //then
        assertThat(created.getName()).isEqualTo(patientDTO.getName());
        assertThat(created.getSurname()).isEqualTo(patientDTO.getSurname());
        assertThat(created.getGender()).isEqualTo(patientDTO.getGender());
        assertThat(created.getPhoneNumber()).isEqualTo(patientDTO.getPhoneNumber());
        assertThat(created.getDateOfBirth()).isEqualTo(patientDTO.getDateOfBirth().toString());
        assertThat(created.getAddress()).isEqualTo(patientDTO.getAddress());
        assertThat(created.getCity()).isEqualTo(patientDTO.getCity());
        assertThat(created.getZipCode()).isEqualTo(patientDTO.getZipCode());
        assertThat(created.getIdCardSeriesNumber()).isEqualTo(patientDTO.getIdCardSeriesNumber());
    }

    @Test
    public void createPatientShouldReturnPatientDTO() {
        //given
        PatientDTO patientDTO = getPatientDTO();

        //when
        PatientDTO created = this.client.post()
                .uri("/api/patient")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(patientDTO)
                .exchange()
                .expectBody(PatientDTO.class)
                .returnResult()
                .getResponseBody();

        //then
        assertThat(created.getName()).isEqualTo(patientDTO.getName());
        assertThat(created.getSurname()).isEqualTo(patientDTO.getSurname());
        assertThat(created.getGender()).isEqualTo(patientDTO.getGender());
        assertThat(created.getPhoneNumber()).isEqualTo(patientDTO.getPhoneNumber());
        assertThat(created.getDateOfBirth()).isEqualTo(patientDTO.getDateOfBirth().toString());
        assertThat(created.getAddress()).isEqualTo(patientDTO.getAddress());
        assertThat(created.getCity()).isEqualTo(patientDTO.getCity());
        assertThat(created.getZipCode()).isEqualTo(patientDTO.getZipCode());
        assertThat(created.getIdCardSeriesNumber()).isEqualTo(patientDTO.getIdCardSeriesNumber());
    }

    @Test
    public void updatePatientShouldReturnPatientDTO() {
        //given
        PatientDTO patientDTO = getPatientDTO();
        PatientDTO created = this.client.post()
                .uri("/api/patient")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(patientDTO)
                .exchange()
                .expectBody(PatientDTO.class)
                .returnResult()
                .getResponseBody();

        //when
        created.setName(SURNAME);

        PatientDTO result = this.client.patch()
                .uri("/api/patient/" + created.getId())
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(patientDTO)
                .exchange()
                .expectBody(PatientDTO.class)
                .returnResult()
                .getResponseBody();
        //then
        assertThat(result).isEqualTo(created);
    }

    @Test
    public void deletePatientShouldDeletePatient() {
        //given
        PatientDTO patientDTO = getPatientDTO();
        PatientDTO created = this.client.post()
                .uri("/api/patient")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(patientDTO)
                .exchange()
                .expectBody(PatientDTO.class)
                .returnResult()
                .getResponseBody();

        //when
        this.client.delete()
                .uri("/api/patient/" + created.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk();
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
