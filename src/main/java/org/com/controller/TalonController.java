package org.com.controller;

import org.com.entity.Talon;
import org.com.repository.TalonRepository;
import org.com.service.TalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/talons")
public class TalonController extends AbstractController<Talon>{
    @Autowired
    private TalonService service;
    @Autowired
    private TalonRepository tRepository;

    @Override
    public TalonService getService() {
        return service;
    }

    @GetMapping(params = {"dDolznost", "iName"})
    public ResponseEntity<List<Talon>> getByDoctorDolznostAndUserNullAndInstitutionName(@RequestParam Map<String,String> params) {
        List<Talon> talons = getService().readByDoctorDolznostAndUserNullAndInstitutionName(params.get("dDolznost"), params.get("iName"));
        if (talons.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(talons, headers, HttpStatus.OK);
    }

    @GetMapping(params = {"dDolznost", "iName", "date"})
    public ResponseEntity<List<Talon>> getByDoctorDolznostAndUserNullAndInstitutionNameAtTheCertainTime(@RequestParam Map<String,String> params) {
        List<Talon> talons = tRepository.findByDoctorDolznostAndUserNullAndInstitutionNameAtTheCertainTime(
                params.get("dDolznost"), params.get("iName"), params.get("date"));
        if (talons.isEmpty()) {
            System.out.println("По введенным вами данным подходящих талонов найдено не было.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for (Talon talon : talons) {
            System.out.println(talon);
        }
        return new ResponseEntity<>(talons, headers, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PatchMapping(params = "id")
    public ResponseEntity<Talon> putUserToTalon(@RequestParam("id") Long id, Principal principal){
        Talon talon = getService().read(id);
        if (talon.getUser() == null) return new ResponseEntity<>(getService().addUserToTalon(principal.getName(), id), headers, HttpStatus.OK);
        else if(talon.getUser().getLogin().equals(principal.getName())) {
            return new ResponseEntity<>(getService().removeUserFromTalon(id), headers, HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
