package org.com.service.impl;

import org.com.entity.Institution;
import org.com.entity.Talon;
import org.com.repository.InstitutionRepository;
import org.com.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InstitutionServiceImpl implements InstitutionService {
    @Autowired
    private InstitutionRepository iRepository;

    @Override
    public Institution read(Long id) {
        return iRepository.findById(id).get();
    }

    @Override
    public List<Institution> read() {
        return iRepository.findAll();
    }

    @Override
    public void save(Institution entity) {
        iRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        iRepository.deleteById(id);
    }

    @Override
    public void edit(Institution entity) {
        Institution institution = iRepository.findById(entity.getId()).orElseThrow(IllegalArgumentException::new);
        institution.setName(entity.getName());
        institution.setAddress(entity.getAddress());
        institution.setTel(entity.getTel());
        institution.setMail(entity.getMail());
        iRepository.save(institution);
    }

    @Override
    public Institution readByName(String name) {
        return iRepository.findByName(name);
    }
}
