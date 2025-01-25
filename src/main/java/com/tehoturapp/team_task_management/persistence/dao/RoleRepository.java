package com.tehoturapp.team_task_management.persistence.dao;

import com.tehoturapp.team_task_management.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
