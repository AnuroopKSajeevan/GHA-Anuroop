package com.revature.task.Project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.task.controller.ProjectController;
import com.revature.task.models.Project;
import com.revature.task.services.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectController.class)
public class ProjectTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService service;

    @Autowired
    private ObjectMapper objectMapper;

    private Project project;

    @BeforeEach
    public void setup() {
        project = new Project("Project 1", "Description", LocalDate.now(), LocalDate.now().plusDays(10), 1L, Collections.emptySet());
    }

    @Test
    public void testGetAllProjects() throws Exception {
        when(service.getAllProjects()).thenReturn(Collections.singletonList(project));

        mockMvc.perform(get("/api/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].project_name").value("Project 1"));
    }

    @Test
    public void testAddProject() throws Exception {
        when(service.addProject(any(Project.class))).thenReturn(project);

        mockMvc.perform(post("/api/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.project_name").value("Project 1"));
    }

    @Test
    public void testGetProjectById() throws Exception {
        when(service.getProjectById(1L)).thenReturn(Optional.of(project));

        mockMvc.perform(get("/api/projects/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.project_name").value("Project 1"));
    }

    @Test
    public void testUpdateProject() throws Exception {
        when(service.updateProject(any(Project.class))).thenReturn(project);

        mockMvc.perform(put("/api/projects/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.project_name").value("Project 1"));
    }

    @Test
    public void testDeleteProject() throws Exception {
        when(service.deleteProject(1L)).thenReturn(project);

        mockMvc.perform(delete("/api/projects/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.project_name").value("Project 1"));
    }

    @Test
    public void testGetTasksByProjectId() throws Exception {
        when(service.getTasksByProjectId(1L)).thenReturn(Collections.emptySet());

        mockMvc.perform(get("/api/projects/1/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
}
