package com.saviocavalcante.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saviocavalcante.todosimple.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // User fList<User> findById(Long id);

    // List<User> findAll();

}
