package com.example.taskmanager.Controller;


import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;
import com.example.taskmanager.service.TaskService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // CREATE TASK
    @PostMapping
    public Mono<Task> createTask(@RequestBody Task task) {
        return taskService.createTask(task.getTitle(), task.getDescription());
    }

    // GET ALL TASKS
    @GetMapping
    public Flux<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // GET TASK BY ID
    @GetMapping("/{id}")
    public Mono<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    // UPDATE TASK (title / description)
    @PutMapping("/{id}")
    public Mono<Task> updateTask(
            @PathVariable Long id,
            @RequestBody Task task
    ) {
        return taskService.updateTask(id, task.getTitle(), task.getDescription());
    }

    // UPDATE TASK STATUS
    @PutMapping("/{id}/status/{status}")
    public Mono<Task> updateStatus(
            @PathVariable Long id,
            @PathVariable TaskStatus status
    ) {
        return taskService.updateTaskStatus(id, status);
    }

    // DELETE TASK
    @DeleteMapping("/{id}")
    public Mono<Void> deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }

    // GET TASKS BY STATUS
    @GetMapping("/status/{status}")
    public Flux<Task> getTasksByStatus(@PathVariable TaskStatus status) {
        return taskService.getTasksByStatus(status);
    }
}