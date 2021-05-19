package org.com.service;

import org.com.entity.Doctor;
import java.util.List;

public interface DoctorService extends Service<Doctor>{
    List<Doctor> readByDolznostAndInstitutionName(String dolznost, String iName);
}
