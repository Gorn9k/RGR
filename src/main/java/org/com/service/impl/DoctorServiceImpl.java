package org.com.service.impl;

import org.com.entity.Doctor;
import org.com.entity.Institution;
import org.com.repository.DoctorRepository;
import org.com.repository.InstitutionRepository;
import org.com.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository dRepository;
    @Autowired
    private InstitutionRepository iRepository;

    @Override
    public List<Doctor> readByDolznostAndInstitutionName(String dolznost, String iName) {
        return dRepository.findByDolznostAndInstitutionName(dolznost, iName);
    }

    @Override
    public Doctor read(Long id) {
        return dRepository.findById(id).get();
    }

    @Override
    public List<Doctor> read() {
        return dRepository.findAll();
    }

    @Override
    public void save(Doctor entity) {
        Institution institution = iRepository.findById(entity.getInstitution().getId()).orElseThrow(IllegalArgumentException::new);
        institution.getDoctors().add(entity);
        dRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        dRepository.deleteById(id);
    }

    @Override
    public void edit(Doctor entity) {
        Doctor doctor = dRepository.findById(entity.getId()).orElseThrow(IllegalArgumentException::new);
        Institution institution = iRepository.findById(entity.getInstitution().getId()).orElseThrow(IllegalArgumentException::new);
        doctor.setSurname(entity.getSurname());
        doctor.setName(entity.getName());
        doctor.setOtchestvo(entity.getOtchestvo());
        doctor.setDolznost(entity.getDolznost());
        doctor.setTel(entity.getTel());
        doctor.setNomerKab(entity.getNomerKab());
        doctor.setInstitution(institution);
        institution.getDoctors().add(doctor);
        dRepository.save(doctor);
    }
}
