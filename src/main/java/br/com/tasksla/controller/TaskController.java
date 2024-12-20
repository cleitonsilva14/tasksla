package br.com.tasksla.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tasksla.dto.TaskDto;
import br.com.tasksla.model.TaskModel;
import br.com.tasksla.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Task", description = "Task endpoints")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@Operation(summary = "Buscar todos as Tasks", description = "Obter todas as tasks nesse endpoint.")
	@ApiResponses({
			@ApiResponse(responseCode = "200")
	})
	@GetMapping
	public ResponseEntity<List<TaskModel>> findAllTasks(){
		return ResponseEntity.status(HttpStatus.OK).body(taskService.findAllTasks());
	}
	
	@PostMapping
	public ResponseEntity<TaskModel> saveTask(@RequestBody @Valid TaskDto taskDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(taskService.saveTask(taskDto));
	}
	
	@PutMapping("/{taskId}/complete")
	public ResponseEntity<TaskModel> completeTask(@PathVariable Long taskId){
		return ResponseEntity.status(HttpStatus.OK).body(taskService.completeTask(taskId));
	}
	
	@Operation(
		    summary = "Buscar o status do SLA de uma Task",
		    description = "Verificar o SLA de uma Task, se está violado ou não."
		)
		@ApiResponses({
		    @ApiResponse(
		        responseCode = "200", 
		        description = "SLA verificado com sucesso. Retorna um valor booleano indicando se o SLA foi violado ou não.",
		        content = @Content(mediaType = "application/json", schema = @Schema(type = "boolean"))
		    ),
		    @ApiResponse(
		        responseCode = "404", 
		        description = "Task não encontrada. O ID da task fornecido não corresponde a uma task existente.",
		        content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
		    )
		})
	@GetMapping("/{taskId}/sla")
	public ResponseEntity<Object> checkSLA(@PathVariable Long taskId) {
		Optional<TaskModel> taskOpt = taskService.findTaskById(taskId);
		
		if(taskOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(taskService.checkSLA(taskId));
		}
		
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task Id not found!");
		
	}
	
}
