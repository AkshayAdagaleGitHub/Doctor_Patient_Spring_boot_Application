package akshay.springframework.services;

import akshay.springframework.domain.Appointment;
import akshay.springframework.domain.Doctor;
import akshay.springframework.domain.Patient;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class DoctorServiceImpl implements DoctorService {

    private Map<Integer, Doctor> listOfDoctors;

    public DoctorServiceImpl() {
        this.loadDoctors();
    }

    @Override
    public List<Doctor> listOfDoctors() {
        return new ArrayList<>(listOfDoctors.values());
    }

    @Override
    public Doctor getDoctorById(Integer id) {
        return listOfDoctors.get(id);
    }

    @Override
    public Doctor saveOrUpdateDoctor(Doctor p) {
        if(p != null){
            if(p.getDocId() == null){
                p.setDocId(Collections.max(listOfDoctors.keySet()) + 1);
                p.setAppointment(new ArrayList<>());
                p.setPatient(new ArrayList<>());
                listOfDoctors.put(p.getDocId(), p);
            }else {
                Doctor prev = listOfDoctors.get(p.getDocId());
                p.setPatient(prev.getPatient());
                p.setAppointment(prev.getAppointment());
                listOfDoctors.put(p.getDocId(), p);
            }
            return p;
        }
        throw new RuntimeException("Doctor cannot be null");
    }

    @Override
    public void delete(Integer docId) {
        listOfDoctors.remove(docId);
    }

    @Override
    public void deleteAppointment(Integer docId, Integer appointmentId) {
        listOfDoctors.get(docId).getAppointment().remove(appointmentId);
    }

    @Override
    public void addAppointment(Integer docId, String firstName, String lastName) {
        Doctor d =  listOfDoctors.get(docId);
        Patient p = new Patient();
        p.setFirstName(firstName);
        p.setLastName(lastName);
        p.setPatientType("New Patient");
        Appointment a = new Appointment();
        a.setPatient(p);
        a.setDoctor(d);
        List<Appointment> lp = listOfDoctors.get(docId).getAppointment();
        Appointment t = null;
        if(lp != null){
            t = lp.get(lp.size() - 1);
        }
        List<Appointment> numbOfAppointments = d.getNumberOfAppointments().get(t.getTime());
        long minutesBetween = ChronoUnit.MINUTES.between(LocalTime.now().truncatedTo(ChronoUnit.SECONDS),t.getTime());
        if(t != null && LocalTime.now().truncatedTo(ChronoUnit.SECONDS).isAfter(t.getTime()) && minutesBetween >= 15){
            a.setDate(LocalDate.now());
            a.setTime(LocalTime.now().plusMinutes(15));
            List<Appointment> la = new ArrayList<>();
            la.add(a);
            numbOfAppointments.add(a);
            d.getAppointment().add(a);
        } else {
            if(t!= null && numbOfAppointments != null && numbOfAppointments.size() < 3 && (minutesBetween < 15)){
                a.setDate(t.getDate());
                a.setTime(t.getTime());
                System.out.println("num of appointments " + numbOfAppointments.size() + " " + t.getTime());
                numbOfAppointments.add(a);
                d.getAppointment().add(a);
            } else {
                a.setDate(LocalDate.now());
                a.setTime(t.getTime().plusMinutes(15));
                List<Appointment> la = new ArrayList<>();
                la.add(a);
                Map<LocalTime, List<Appointment>> numberOfAppointments = new HashMap();
                numberOfAppointments.put(a.getTime(), la);
                d.getAppointment().add(a);
            }
        }
        listOfDoctors.get(docId).getPatient().add(p);
    }


    private void loadDoctors(){
        listOfDoctors = new HashMap<>();

        Doctor d1 = new Doctor();
        d1.setDocId(101);
        d1.setFirstName("Doctor ");
        d1.setLastName("Strange");
        Patient p1 = new Patient();
        p1.setFirstName("Patient");
        p1.setLastName("Amazon");
        p1.setPatientType("Follow up Patient");
        Appointment a1 = new Appointment();
        a1.setAppoinetmentId(1);
        a1.setDoctor(d1);
        a1.setPatient(p1);
        a1.setDate(LocalDate.now());
        a1.setTime(LocalTime.now().truncatedTo(ChronoUnit.SECONDS));
        List<Appointment> la1 = new ArrayList<>();
        la1.add(a1);
        d1.setAppointment(la1);
        List<Appointment> lp1 = new ArrayList<>();
        lp1.add(a1);
        d1.setAppointment(lp1);
        List<Patient> pa1 = new ArrayList<>();
        pa1.add(p1);
        d1.setPatient(pa1);
        Map<LocalTime, List<Appointment>> localTimeListMap = new HashMap<>();
        localTimeListMap.put(a1.getTime(),la1);
        d1.setNumberOfAppointments(localTimeListMap);
        listOfDoctors.put(101,d1);

        Doctor d2 = new Doctor();
        d2.setDocId(102);
        d2.setFirstName("Doc ");
        d2.setLastName("Who");
        Patient p2 = new Patient();
        p2.setFirstName("Patient");
        p2.setLastName(" Wals");
        p2.setPatientType("New Patient");
        Appointment a2 = new Appointment();
        a2.setAppoinetmentId(1);
        a2.setDoctor(d2);
        a2.setPatient(p2);
        a2.setDate(LocalDate.now());
        a2.setTime(LocalTime.now().plusMinutes(15).truncatedTo(ChronoUnit.SECONDS));
        List<Appointment> la2 = new ArrayList<>();
        la2.add(a2);
        d2.setAppointment(la2);
        List<Appointment> lp2 = new ArrayList<>();
        lp2.add(a2);
        d2.setAppointment(lp2);
        List<Patient> pa2 = new ArrayList<>();
        pa2.add(p2);
        d2.setPatient(pa2);
        Map<LocalTime, List<Appointment>> localTimeListMap1 = new HashMap<>();
        localTimeListMap1.put(a2.getTime(),la2);
        d2.setNumberOfAppointments(localTimeListMap1);

        listOfDoctors.put(102,d2);

        Doctor d3 = new Doctor();
        d3.setDocId(103);
        d3.setFirstName("Doctor ");
        d3.setLastName(" WhatsApp");
        Patient p3 = new Patient();
        p3.setFirstName("Patient");
        p3.setLastName("Zoro");
        p3.setPatientType("Follow up Patient");
        Appointment a3 = new Appointment();
        a3.setAppoinetmentId(1);
        a3.setDoctor(d3);
        a3.setPatient(p3);
        a3.setDate(LocalDate.now());
        a3.setTime(a2.getTime().plusMinutes(15).truncatedTo(ChronoUnit.SECONDS));
        List<Appointment> la3 = new ArrayList<>();
        la3.add(a3);
        d3.setAppointment(la3);
        List<Patient> pa3 = new ArrayList<>();
        pa3.add(p3);
        d3.setPatient(pa3);
        Map<LocalTime, List<Appointment>> localTimeListMap2 = new HashMap<>();
        localTimeListMap2.getOrDefault(a3.getTime(),la3);
        d3.setNumberOfAppointments(localTimeListMap2);

        listOfDoctors.put(103,d3);
    }
}
