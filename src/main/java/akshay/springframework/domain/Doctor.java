package akshay.springframework.domain;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class Doctor {

    private Integer docId;
    private String firstName;
    private String lastName;
    private List<Appointment> appointment;
    private List<Patient> patient;
    private Map<LocalTime,List<Appointment>> numberOfAppointments;

    public Map<LocalTime, List<Appointment>> getNumberOfAppointments() {
        return numberOfAppointments;
    }

    public void setNumberOfAppointments(Map<LocalTime, List<Appointment>> numberOfAppointments) {
        this.numberOfAppointments = numberOfAppointments;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Appointment> getAppointment() {
        return appointment;
    }

    public void setAppointment(List<Appointment> appointment) {
        this.appointment = appointment;
    }

    public List<Patient> getPatient() {
        return patient;
    }

    public void setPatient(List<Patient> patient) {
        this.patient = patient;
    }
}
