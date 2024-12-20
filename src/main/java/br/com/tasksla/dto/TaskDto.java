package br.com.tasksla.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskDto {

	@NotBlank(message = "title must be not blank")
	private String title;
	
	@NotBlank(message = "description must be not blank")
	private String description;
	
}
