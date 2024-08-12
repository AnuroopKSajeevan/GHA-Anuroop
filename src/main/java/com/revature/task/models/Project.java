package com.revature.task.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="Projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long project_id;

    @Column(name = "project_name")
    private String project_name;

    @Column(name = "project_description")
    private String project_description;

    @Column(name = "start_date")
    private LocalDate start_date;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "client_id")
    private long client_id;

    @OneToMany(mappedBy = "project_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> tasks;

    public Project(String project_name, String project_description, LocalDate start_date, LocalDate deadline, long client_id, Set<Task> tasks) {
        this.project_name = project_name;
        this.project_description = project_description;
        this.start_date = start_date;
        this.deadline = deadline;
        this.client_id = client_id;
        this.tasks = tasks;
    }

    public Project() {

    }

    public long getProject_id() {
        return project_id;
    }

    public void setProject_id(long project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_description() {
        return project_description;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}