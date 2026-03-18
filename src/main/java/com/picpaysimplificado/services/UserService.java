package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.exceptions.UserNotFoundException;
import com.picpaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User save(User user) {
        return this.userRepository.save(user);
    }

    public User findUserById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }
}
