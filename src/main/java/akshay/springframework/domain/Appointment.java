package akshay.springframework.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {

    private Integer appoinetmentId;
    private Doctor doctor;
    private Patient patient;
    private LocalDate date;
    private LocalTime time;

    public Doctor getDoctor() {
        return doctor;
    }

    public Integer getAppoinetmentId() {
        return appoinetmentId;
    }

    public void setAppoinetmentId(Integer appoinetmentId) {
        this.appoinetmentId = appoinetmentId;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }
}
