package br.com.tasksla.model;

import java.time.LocalDateTime;

import br.com.tasksla.enums.TaskStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor(staticName = "build")
@Getter @Setter
@Table(name = "tb_task")
public class TaskModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTask;
	
	@Column(name = "title", unique = true)
	private String title;
	
	@Column(name = "description")
	private String description;
	
	private LocalDateTime startTime;
	
	private LocalDateTime endTime;
	
	private LocalDateTime SLADeadLine;
	
	@Enumerated(EnumType.STRING)
	private TaskStatus taskStatus;
	
	public boolean isSLAMet() {
		if(this.endTime == null) {
			return false;
		}
		return !this.endTime.isAfter(this.SLADeadLine);
	}
	
	public boolean isOverflowSLA() {
	    if (this.endTime == null) {
	        return LocalDateTime.now().isAfter(SLADeadLine);
	    } else {
	        return this.endTime.isAfter(SLADeadLine);
	    }
	}

	
	public TaskModel() {
		this.startTime = LocalDateTime.now();
		this.SLADeadLine = LocalDateTime.now().plusHours(24);
		this.taskStatus = TaskStatus.PENDENTE;
	}
	
	
}
