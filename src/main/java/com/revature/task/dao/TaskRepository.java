package com.revature.task.dao;

import com.revature.task.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task,Long> {

}
