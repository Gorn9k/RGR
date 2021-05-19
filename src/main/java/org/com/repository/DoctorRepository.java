package org.com.repository;

import java.util.List;

import org.com.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{
    List<Doctor> findByDolznostAndInstitutionName(String dolznost, String iName);
}
