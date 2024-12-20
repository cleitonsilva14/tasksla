package br.com.tasksla.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tasksla.dto.TaskDto;
import br.com.tasksla.enums.TaskStatus;
import br.com.tasksla.model.TaskModel;
import br.com.tasksla.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;



@Service
public class TaskService {
	
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);


	@Autowired
	private TaskRepository taskRepository;
	
	public List<TaskModel> findAllTasks(){
		return taskRepository.findAll();
	}

	public TaskModel saveTask(TaskDto taskDto) {
		TaskModel task = new TaskModel();
		task.setTitle(taskDto.getTitle());		
		task.setDescription(taskDto.getDescription());
		return taskRepository.save(task);
	}
	
	public TaskModel completeTask(Long taskId) {
		Optional<TaskModel> taskOptional = taskRepository.findById(taskId);
		if(taskOptional.isPresent()) {
			TaskModel task = taskOptional.get();
			if(task.getTaskStatus().equals(TaskStatus.FINALIZADO)) {
				logger.warn("Task is already FINALIZADO");
				return task;
			}
			task.setEndTime(LocalDateTime.now());
			task.setTaskStatus(TaskStatus.FINALIZADO);
			return taskRepository.save(task);
		}
		throw new EntityNotFoundException("Task Id not found!");
	}
	
	public boolean checkSLA(Long taskId) {
		Optional<TaskModel> taskOpt = taskRepository.findById(taskId);
		if(taskOpt.isPresent()) {
			TaskModel task = taskOpt.get();
			return task.isOverflowSLA();
		}
		throw new RuntimeException("Task Id not found!");
	}

	public Optional<TaskModel> findTaskById(Long taskId) {
		return taskRepository.findById(taskId);
	}
	
	
}
