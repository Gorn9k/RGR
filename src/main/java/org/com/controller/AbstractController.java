package org.com.controller;

import org.com.entity.AbstractEntity;
import org.com.service.Service;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

public abstract class AbstractController<T extends AbstractEntity> {

    protected HttpHeaders headers;

    @PostConstruct
    private void init(){
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public abstract Service<T> getService();

    @GetMapping
    public ResponseEntity<List<T>> get(){
        List<T> entities = getService().read();
        if(entities.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping(params = "id")
    public ResponseEntity<T> getById(@RequestParam("id") Long id){
        T entity = getService().read(id);
        if(entity == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<String> put(@RequestBody T entity){
        getService().edit(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<String> post(@RequestBody T entity){
        getService().save(entity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(params = "id")
    public ResponseEntity<String> delete(@RequestParam("id") Long id){
        getService().delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
