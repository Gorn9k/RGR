package org.com.service;

import org.com.entity.Institution;

public interface InstitutionService extends Service<Institution>{
    Institution readByName(String name);
}
