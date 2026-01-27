package com.example.taskmanager.repository;



import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface TaskRepository extends ReactiveCrudRepository<Task, Long> {


    // SELECT * FROM tasks WHERE status = ?
    Flux<Task> findByStatus(TaskStatus status);


    // ORDER BY created_at DESC
    Flux<Task> findAllByOrderByCreatedAtDesc();
}