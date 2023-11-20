package com.saviocavalcante.todosimple.services;

import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saviocavalcante.todosimple.models.User;
import com.saviocavalcante.todosimple.repositories.TaskRepository;
import com.saviocavalcante.todosimple.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);

        return user.orElseThrow(() -> new RuntimeException(
                "Usuario não encontrado! id: " + id));
    }

    @Transactional
    public User create(User user) {
        user.setId(null);
        user = userRepository.save(user);
        taskRepository.saveAll(user.getTasks());

        return user;
    }

    @Transactional
    public User update(User user) {
        User newUser = findById(user.getId());
        newUser.setPassword(user.getPassword());

        return userRepository.save(newUser);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir, pois há entidades relacionadas");
        }
    }

}
