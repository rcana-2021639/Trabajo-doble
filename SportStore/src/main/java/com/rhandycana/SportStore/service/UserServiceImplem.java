package com.rhandycana.SportStore.service;

import com.rhandycana.SportStore.model.*;
import com.rhandycana.SportStore.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplem implements UserService{

    private final UserRepository userRepository;

    public UserServiceImplem(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User saveUser(User user) {
        List<User> lista = userRepository.findAll();
        for (User u : lista) {
            if (u.getFirstName().equalsIgnoreCase(user.getFirstName())) {
                user.setFirstName("ERROR_NOMBRE_REPETIDO");
                return user;
            }
            if (u.getLastName().equalsIgnoreCase(user.getLastName())) {
                user.setLastName("ERROR_APELLIDO_REPETIDO");
                return user;
            }
            if (u.getEmail().equalsIgnoreCase(user.getEmail())) {
                user.setEmail("ERROR_EMAIL_REPETIDO");
                return user;
            }
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Integer id, User user) {
        User exisrinUser = userRepository.findById(id).orElse(null);
        if (exisrinUser != null) {
            List<User> lista = userRepository.findAll();
            for (User u : lista) {
                if (!u.getId().equals(id)) {
                    if (u.getFirstName().equalsIgnoreCase(user.getFirstName())) {
                        user.setFirstName("ERROR_NOMBRE_REPETIDO");
                        return user;
                    }
                    if (u.getLastName().equalsIgnoreCase(user.getLastName())) {
                        user.setLastName("ERROR_APELLIDO_REPETIDO");
                        return user;
                    }
                    if (u.getEmail().equalsIgnoreCase(user.getEmail())) {
                        user.setEmail("ERROR_EMAIL_REPETIDO");
                        return user;
                    }
                }
            }
            exisrinUser.setFirstName(user.getFirstName());
            exisrinUser.setLastName(user.getLastName());
            exisrinUser.setEmail(user.getEmail());
            return userRepository.save(exisrinUser);
        }
        return null;
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
