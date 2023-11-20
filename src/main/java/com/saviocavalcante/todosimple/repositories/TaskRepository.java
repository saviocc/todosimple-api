package com.saviocavalcante.todosimple.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.saviocavalcante.todosimple.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser_Id(Long id);

    // jpql
    // @Query(value = "SELECT t FROM Task t WHERE t.user.id = :id")
    // List<Task> findByUser_Id(@Param "id" Long id);

    // sql
    // @Query(value = "SELECT * FROM task t WHERE t.user_id = :id", nativeQuery =
    // true)
    // List<Task> findByUser_Id(@Param "id" Long id);

}
