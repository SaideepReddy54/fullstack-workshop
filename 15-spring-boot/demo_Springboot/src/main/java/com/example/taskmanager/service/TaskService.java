package com.example.taskmanager.service;


import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Mono<Task> createTask(String title, String description) {
        if (title == null || title.trim().isEmpty()) {
            return Mono.error(new IllegalArgumentException("Task title cannot be empty"));
        }

        Task task = new Task(title, description);
        return taskRepository.save(task);
    }

    public Flux<Task> getAllTasks() {
        return taskRepository.findAllByOrderByCreatedAtDesc();
    }

    public Mono<Task> getTaskById(Long id) {
        return taskRepository.findById(id)
                .switchIfEmpty(Mono.error(
                        new IllegalArgumentException("Task not found: " + id)
                ));
    }

    public Mono<Task> updateTaskStatus(Long id, TaskStatus newStatus) {
        return taskRepository.findById(id)
                .switchIfEmpty(Mono.error(
                        new IllegalArgumentException("Task not found: " + id)
                ))
                .flatMap(task -> {
                    task.setStatus(newStatus);
                    return taskRepository.save(task);
                });
    }

    public Mono<Task> updateTask(Long id, String title, String description) {
        return taskRepository.findById(id)
                .switchIfEmpty(Mono.error(
                        new IllegalArgumentException("Task not found: " + id)
                ))
                .flatMap(task -> {
                    if (title != null && !title.trim().isEmpty()) {
                        task.setTitle(title);
                    }
                    if (description != null) {
                        task.setDescription(description);
                    }
                    return taskRepository.save(task);
                });
    }

    public Mono<Void> deleteTask(Long id) {
        return taskRepository.findById(id)
                .switchIfEmpty(Mono.error(
                        new IllegalArgumentException("Task not found: " + id)
                ))
                .flatMap(taskRepository::delete);
    }

    public Flux<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    public Mono<Task> completeTask(Long id) {
        return updateTaskStatus(id, TaskStatus.COMPLETED);
    }

    public Mono<Task> startTask(Long id) {
        return updateTaskStatus(id, TaskStatus.IN_PROGRESS);
    }
}