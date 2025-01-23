package com.tehoturapp.team_task_management.mapper;

public interface DtoMapper<E, D>{
    E toEntity(D d);
    D toDto(E e);
}
