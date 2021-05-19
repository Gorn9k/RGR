package org.com.controller;

import org.com.entity.User;
import org.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserService service;

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(params = "login")
    public ResponseEntity<User> getByLogin(@RequestParam("login") String login, Principal principal) {
        User user = service.readByLogin(login);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else if (!login.equals(principal.getName()) ||
                !service.readByLogin(principal.getName()).getAuthority().equals("ROLE_ADMIN"))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<User>> get(){
        List<User> entities = service.read();
        if(entities.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(params = "id")
    public ResponseEntity<User> getById(@RequestParam("id") Long id){
        User entity = service.read(id);
        if(entity == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> put(@RequestBody User entity, Principal principal){
        if (service.readByLogin(principal.getName()).getAuthority().equals("ROLE_ADMIN") ||
                service.read(entity.getId()).getLogin().equals(principal.getName())) {
            service.edit(entity);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post(@RequestBody User entity){
        service.save(entity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(params = "id")
    public ResponseEntity<String> delete(@RequestParam("id") Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
