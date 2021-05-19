package org.com.service.impl;

import org.com.entity.Doctor;
import org.com.entity.Institution;
import org.com.entity.Talon;
import org.com.entity.User;
import org.com.repository.DoctorRepository;
import org.com.repository.InstitutionRepository;
import org.com.repository.TalonRepository;
import org.com.repository.UserRepository;
import org.com.service.TalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TalonServiceImpl implements TalonService {
    @Autowired
    private TalonRepository tRepository;
    @Autowired
    private UserRepository uRepository;
    @Autowired
    private DoctorRepository dRepository;
    @Autowired
    private InstitutionRepository iRepository;

    @Override
    public Talon read(Long id) {
        return tRepository.findById(id).get();
    }

    @Override
    public List<Talon> read() {
        return tRepository.findAll();
    }

    @Override
    public void save(Talon entity) {
        Doctor doctor = dRepository.findById(entity.getDoctor().getId()).orElseThrow(IllegalArgumentException::new);
        Institution institution = iRepository.findById(entity.getInstitution().getId()).orElseThrow(IllegalArgumentException::new);
        entity.setDoctor(doctor);
        entity.setInstitution(institution);
        institution.getTalons().add(entity);
        doctor.getTalons().add(entity);
        tRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        tRepository.deleteById(id);
    }

    @Override
    public void edit(Talon entity) {
        Talon talon = tRepository.findById(entity.getId()).orElseThrow(IllegalArgumentException::new);
        User user = entity.getUser() == null ?
                null : uRepository.findById(entity.getUser().getId()).orElseThrow(IllegalArgumentException::new);
        Doctor doctor = dRepository.findById(entity.getDoctor().getId()).orElseThrow(IllegalArgumentException::new);
        Institution institution = iRepository.findById(entity.getInstitution().getId()).orElseThrow(IllegalArgumentException::new);
        talon.setDoctor(doctor);
        talon.setUser(user);
        talon.setInstitution(institution);
        talon.setDate(entity.getDate());
        talon.setNomerKab(entity.getNomerKab());
        if (!(user == null)) user.getTalons().add(talon);
        doctor.getTalons().add(talon);
        institution.getTalons().add(talon);
        tRepository.save(talon);
    }

    @Override
    public Talon addUserToTalon(String login, Long id){
        Talon talon = tRepository.findById(id).get();
        User user = uRepository.findByLogin(login);
        talon.setUser(user);
        edit(talon);
        return talon;
    }

    @Override
    public Talon removeUserFromTalon(Long id) {
        Talon talon = tRepository.findById(id).get();
        talon.setUser(null);
        edit(talon);
        return talon;
    }

    @Override
    public List<Talon> readByDoctorDolznostAndUserNullAndInstitutionName(String dDolznost, String iName) {
        return tRepository.findByDoctorDolznostAndUserNullAndInstitutionName(dDolznost, iName);
    }
}
