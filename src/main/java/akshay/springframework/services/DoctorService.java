package akshay.springframework.services;

import akshay.springframework.domain.Doctor;

import java.util.List;

public interface DoctorService {

    List<Doctor> listOfDoctors();
    Doctor getDoctorById(Integer id);
    Doctor saveOrUpdateDoctor(Doctor p);
    void delete(Integer id);
    void addAppointment(Integer docId, String firstName, String lastName);
    void deleteAppointment(Integer docId, Integer appointmentId);
}
