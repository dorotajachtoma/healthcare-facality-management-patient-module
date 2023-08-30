package controller;

import com.djachtoma.model.patient.dto.PatientDTO;
import configuration.TestSetup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

public class PatientControllerTest extends TestSetup {

    @Autowired
    private WebTestClient client;

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
}
