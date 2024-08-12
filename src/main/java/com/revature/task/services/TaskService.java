package com.revature.task.services;

import com.revature.task.dao.TaskRepository;
import com.revature.task.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task addTask(Task task) {
        taskRepository.save(task);
        return task;
    }

    public Optional<Task> getTaskById(long id) {
        return taskRepository.findById(id);
    }

    public Task updateTask(Task task) {
        taskRepository.save(task);
        return task;
    }

    public Task deleteTask(long id) {
        Optional<Task> task = getTaskById(id);
        taskRepository.deleteById(id);
        return task.get();
    }

}