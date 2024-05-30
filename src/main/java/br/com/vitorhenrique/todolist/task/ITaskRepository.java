package br.com.vitorhenrique.todolist.task;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskRepository extends JpaRepository <TaskMoldel , UUID> {
    
}
