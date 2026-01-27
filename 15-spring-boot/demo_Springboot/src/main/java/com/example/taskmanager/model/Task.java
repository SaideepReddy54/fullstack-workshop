package com.example.taskmanager.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


import java.time.LocalDateTime;


@Table("tasks")
public class Task {


    @Id
    private Long id;


    private String title;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;


    // Required by Spring
    public Task() {
        this.status = TaskStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }


    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = TaskStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }


    public Task(Long id, String title, String description,
                TaskStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
    }


// getters & setters


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public TaskStatus getStatus() {
        return status;
    }


    public void setStatus(TaskStatus status) {
        this.status = status;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    @Override
    public String toString() {
        return String.format("[%d] %s - %s (%s)",
                id, title, description, status);
    }
}