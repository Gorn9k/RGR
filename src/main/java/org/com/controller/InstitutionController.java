package org.com.controller;

import org.com.entity.Institution;
import org.com.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/institutions")
public class InstitutionController extends AbstractController<Institution>{
    @Autowired
    private InstitutionService service;

    @Override
    public InstitutionService getService() {
        return service;
    }

    @GetMapping(params = "name")
    public ResponseEntity<Institution> getByName(@RequestParam("name") String name) {
        Institution institution = getService().readByName(name);
        if (institution == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(institution, headers, HttpStatus.OK);
    }
}
