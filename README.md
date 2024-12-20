# Task APP com SLA

App de exemplo Tasks com SLA. 

- H2/Postgres
--H2 = application.yaml
-- Postgres = application.properties
- Swagger
- Pacotes
--config
--controller
--dto
--enums
--model
--repository
--service

config classe: __SwaggerConfig.java__
```java
@OpenAPIDefinition(
		info = @Info(
			title = "Task SLA API",
			description = "Complete API for tasksla",
			summary = "Uma API completa para gerenciar tasks com SLA",
			termsOfService = "https://app.swaggerhub.com/eula",
			license = @License(
				name = "GPL 3.0",
				url = "https://opensource.org/license/gpl-3-0"
			),
			contact = @Contact (
				name = "SpringBoot Developer",
				email = "localhost@local.com",
				url = "https://springdoc.org/"
			)
		)	
	)
public class SwaggerConfig {
}
```
classe: __TaskModel.java__
```java
...
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
...
```
