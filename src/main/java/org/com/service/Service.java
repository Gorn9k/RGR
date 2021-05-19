package org.com.service;

import java.util.List;
import org.com.entity.AbstractEntity;

public interface Service<T extends AbstractEntity> {
    T read(Long id);
    List<T> read();
    void save(T entity);
    void delete(Long id);
    void edit(T entity);
}
