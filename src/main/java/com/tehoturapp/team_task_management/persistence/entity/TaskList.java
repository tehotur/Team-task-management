package com.tehoturapp.team_task_management.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task_list")
public class TaskList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "taskList", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Task> tasks;

    @OneToMany(mappedBy = "taskList")
    private List<User> users;

}
