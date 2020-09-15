package akshay.springframework.controller;

import akshay.springframework.domain.Doctor;
import akshay.springframework.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DoctorController {

    private DoctorService doctorService;

    @Autowired
    public void setDoctorService(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @RequestMapping("/doctors")
    public String listDoctors(Model model){
        model.addAttribute("doctors", doctorService.listOfDoctors());
        return "doctor";
    }
    @RequestMapping("/doctors/{id}")
    public String getProductById(Model model, @PathVariable Integer id){
        model.addAttribute("doctor", doctorService.getDoctorById(id));
        return "view";
    }
    @RequestMapping("/doctors/edit/{id}")
    public String edit(Model model, @PathVariable Integer id){
        model.addAttribute("doctor", doctorService.getDoctorById(id));
        return "doctorform";
    }

    @RequestMapping(value = "/addappointment", method = RequestMethod.POST)
    public String addAppointment(@RequestParam(value = "firstName") String firstName,@RequestParam(value = "lastName") String lastName, @RequestParam(value = "docId")Integer docId){
        doctorService.addAppointment(docId, firstName, lastName);
        return "redirect:/doctors/"+ docId;
    }

    @RequestMapping("/doctors/delete/{id}")
    public String delete(@PathVariable Integer id){
        doctorService.delete(id);
        return "redirect:/doctors";
    }
    @RequestMapping(value = "/doctors/delete/appointment/{docID}/{appointmentId}")
    public String deleteAppointment(@RequestParam(value = "docId") Integer docId, @RequestParam(value = "appointmentId") Integer appointmentId){
        doctorService.deleteAppointment(docId, appointmentId);
        return "redirect:/doctors" + docId;
    }

    @RequestMapping("/doctors/new")
    public String newProduct(Model model){
        model.addAttribute("doctor", new Doctor());
        return "doctorform";
    }

    @RequestMapping(value="/doctors", method = RequestMethod.POST)
    public String saveOrUpdateProduct(Doctor doctor){
        Doctor p = doctorService.saveOrUpdateDoctor(doctor);
        return "redirect:/doctors";
    }
}
