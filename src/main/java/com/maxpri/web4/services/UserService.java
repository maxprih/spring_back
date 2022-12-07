package com.maxpri.web4.services;

import com.maxpri.web4.models.User;
import com.maxpri.web4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
