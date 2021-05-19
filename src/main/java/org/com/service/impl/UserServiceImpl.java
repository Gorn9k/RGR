package org.com.service.impl;

import org.com.entity.Talon;
import org.com.entity.User;
import org.com.repository.TalonRepository;
import org.com.repository.UserRepository;
import org.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository uRepository;

    @Override
    public User readByLogin(String login) {
        return uRepository.findByLogin(login);
    }

    @Override
    public User read(Long id) {
        return uRepository.findById(id).get();
    }

    @Override
    public List<User> read() {
        return uRepository.findAll();
    }

    @Override
    public void save(User entity) {
        entity.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
        uRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        uRepository.deleteById(id);
    }

    @Override
    public void edit(User entity) {
        User user = uRepository.findById(entity.getId()).orElseThrow(IllegalArgumentException::new);
        user.setLogin(entity.getLogin());
        user.setPassword(entity.getPassword());
        user.setMail(entity.getMail());
        user.setAuthority(entity.getAuthority());
        uRepository.save(user);
    }
}
