package br.com.tasksla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tasksla.model.TaskModel;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel,Long> {

}
