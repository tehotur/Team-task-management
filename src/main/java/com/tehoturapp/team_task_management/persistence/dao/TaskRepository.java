package com.tehoturapp.team_task_management.persistence.dao;

import com.tehoturapp.team_task_management.persistence.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
