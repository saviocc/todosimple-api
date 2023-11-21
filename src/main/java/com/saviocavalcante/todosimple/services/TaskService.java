package com.saviocavalcante.todosimple.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saviocavalcante.todosimple.models.Task;
import com.saviocavalcante.todosimple.models.User;
import com.saviocavalcante.todosimple.repositories.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(Long id) {
        Optional<Task> task = taskRepository.findById(id);

        return task.orElseThrow(() -> new RuntimeException(
                "Tarefa não encontrado! id: " + id));
    }

    public List<Task> findAllByYserId(Long userId) {
        List<Task> tasks = taskRepository.findByUser_Id(userId);
        return tasks;
    }

    @Transactional
    public Task create(Task obj) {
        User user = userService.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        obj = taskRepository.save(obj);
        return obj;
    }

    @Transactional
    public Task update(Task obj) {
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return taskRepository.save(newObj);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir, pois há entidades relacionadas");
        }
    }

}
