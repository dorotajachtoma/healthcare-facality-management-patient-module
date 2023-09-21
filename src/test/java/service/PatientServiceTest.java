package service;

import com.djachtoma.exception.PatientNotFoundException;
import com.djachtoma.model.patient.Patient;
import com.djachtoma.model.patient.dto.PatientDTO;
import com.djachtoma.model.patient.dto.PatientMapper;
import com.djachtoma.repository.PatientRepository;
import com.djachtoma.service.PatientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static util.TestObjectUtil.getPatient;
import static util.TestObjectUtil.getPatientDTO;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    @Test
    public void getFacilities_shouldReturnAllFacilities() {
        //given
        Set<Patient> patients = Set.of(getPatient());
        when(patientRepository.findAll()).thenReturn(patients);

        //when
        patientService.getPatients();

        //then
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    public void getPatient_shouldReturnPatientById() {
        //given
        Patient patient = getPatient();
        PatientDTO patientDTO = getPatientDTO();
        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));
        when(patientMapper.toDTO(patient)).thenReturn(patientDTO);

        //when
        patientService.getPatient(patient.getId());

        //then
        verify(patientRepository, times(1)).findById(patient.getId());
    }

    @Test
    public void getPatient_shouldThrowExceptionPatientNotFound() {
        //given
        Patient patient = getPatient();

        //when
        Throwable throwable = catchThrowable(() -> patientService.getPatient(patient.getId()));

        //then
        assertThat(throwable).isInstanceOf(PatientNotFoundException.class);
        assertThat(throwable).hasMessageContaining("Patient with provided ID: ID does not exist.");
    }

    @Test
    public void createPatient_shouldReturnPatient() {
        //given
        PatientDTO patientDTO = getPatientDTO();
        Patient patient = getPatient();
        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));
        when(patientRepository.save(patient)).thenReturn(patient);
        when(patientMapper.toDTO(patient)).thenReturn(patientDTO);

        //when
        patientService.updatePatient(patient.getId(), patientDTO);

        //then
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    public void updatePatient_shouldReturnUpdatedPatient() {
        //given
        PatientDTO patientDTO = getPatientDTO();
        Patient patient = getPatient();
        when(patientMapper.toEntity(patientDTO)).thenReturn(patient);
        when(patientRepository.save(patient)).thenReturn(patient);
        when(patientMapper.toDTO(patient)).thenReturn(patientDTO);

        //when
        patientService.createPatient(patientDTO);

        //then
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    public void updatePatient_shouldThrowException() {
        //given
        PatientDTO patientDTO = getPatientDTO();
        Patient patient = getPatient();

        //when
        Throwable throwable = catchThrowable(() -> patientService.getPatient(patient.getId()));

        //then
        assertThat(throwable).isInstanceOf(PatientNotFoundException.class);
        assertThat(throwable).hasMessageContaining("Patient with provided ID: ID does not exist.");
    }

    @Test
    public void deletePatient_shouldDelete() {
        //given
        Patient patient = getPatient();
        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));

        //when
        patientService.deletePatient(patient.getId());

        //then
        verify(patientRepository, times(1)).delete(patient);
    }

    @Test
    public void deletePatient_shouldThrowException() {
        //given
        Patient patient = getPatient();

        //when
        Throwable throwable = catchThrowable(() -> patientService.getPatient(patient.getId()));

        //then
        assertThat(throwable).isInstanceOf(PatientNotFoundException.class);
        assertThat(throwable).hasMessageContaining("Patient with provided ID: ID does not exist.");
    }
}
