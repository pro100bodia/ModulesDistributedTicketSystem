package com.ticketmaster.service.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class TicketModel {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;

    private Set<UserModel> users;

    public TicketModel() {
    }

    public TicketModel(Long id, String title, String description, LocalDateTime createdAt, Set<UserModel> users) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.users = users;
    }

    public TicketModel(Long id, String title, String description, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<UserModel> getUsers() {
        return users;
    }

    public void setUsers(Set<UserModel> users) {
        this.users = users;
    }
}
