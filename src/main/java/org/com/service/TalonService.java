package org.com.service;

import org.com.entity.Talon;
import org.com.entity.User;

import java.util.List;

public interface TalonService extends Service<Talon>{
    List<Talon> readByDoctorDolznostAndUserNullAndInstitutionName(String dDolznost, String iName);

    Talon addUserToTalon(String login, Long id);

    Talon removeUserFromTalon(Long id);
}
