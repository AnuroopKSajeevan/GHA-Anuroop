package com.revature.task.controller;

import com.revature.task.models.Project;
import com.revature.task.models.Task;
import com.revature.task.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService service;

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        return new ResponseEntity<>(service.getAllProjects(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Project> addProject(@RequestBody Project project) {
        return new ResponseEntity<>(service.addProject(project), HttpStatus.CREATED);
    }

    @GetMapping("/{project_id}")
    public ResponseEntity<Project> getProjectById(@PathVariable long project_id){
        return new ResponseEntity<Project>(service.getProjectById(project_id).get(),HttpStatus.OK);
    }

    @PutMapping("/{project_id}")
    public ResponseEntity<Project> updateTask(@RequestBody Project project,@PathVariable long project_id){
        return new ResponseEntity<Project>(service.updateProject(project),HttpStatus.OK);
    }

    @DeleteMapping("/{project_id}")
    public ResponseEntity<Project> deleteProject(@PathVariable long project_id){
        return new ResponseEntity<Project>(service.deleteProject(project_id),HttpStatus.OK);
    }

    @GetMapping("/{project_id}/tasks")
    public ResponseEntity<Set<Task>> getTasksByProjectId(@PathVariable long project_id) {
        Set<Task> tasks = service.getTasksByProjectId(project_id);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

}