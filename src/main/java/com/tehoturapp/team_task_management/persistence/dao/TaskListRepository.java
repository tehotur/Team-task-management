package com.tehoturapp.team_task_management.persistence.dao;

import com.tehoturapp.team_task_management.persistence.entity.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Integer> {
}
