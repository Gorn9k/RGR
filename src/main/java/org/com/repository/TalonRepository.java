package org.com.repository;

import java.util.List;

import org.com.entity.Talon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalonRepository extends JpaRepository<Talon, Long>{
    List<Talon> findByDoctorDolznostAndUserNullAndInstitutionName(String dDolznost, String iName);
}
