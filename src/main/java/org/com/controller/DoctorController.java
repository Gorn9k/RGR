package org.com.controller;

import org.com.entity.Doctor;
import org.com.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/doctors")
public class DoctorController extends AbstractController<Doctor>{
    @Autowired
    private DoctorService service;

    @Override
    public DoctorService getService() {
        return service;
    }

    @GetMapping(params = {"dolznost", "iName"})
    public ResponseEntity<List<Doctor>> getByDolznostAndInstitutionName(@RequestParam("dolznost") String dolznost, @RequestParam("iName") String iName){
        List<Doctor> doctors = getService().readByDolznostAndInstitutionName(dolznost, iName);
        if(doctors.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(doctors, headers, HttpStatus.OK);
    }
}
