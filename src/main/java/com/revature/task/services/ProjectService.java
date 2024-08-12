package com.revature.task.services;

import com.revature.task.dao.ProjectRepository;
import com.revature.task.models.Project;
import com.revature.task.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project addProject(Project project) {
        projectRepository.save(project);
        return project;
    }

    public Optional<Project> getProjectById(long project_id) {
        return projectRepository.findById(project_id);
    }

    public Project updateProject(Project project) {
        projectRepository.save(project);
        return project;
    }

    public Project deleteProject(long project_id) {
        Optional<Project> project = getProjectById(project_id);
        projectRepository.deleteById(project_id);
        return project.get();
    }
    public Set<Task> getTasksByProjectId(long project_id) {
        Optional<Project> project = getProjectById(project_id);
        return project.map(Project::getTasks).orElse(null);
    }
}