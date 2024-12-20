package br.com.tasksla.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

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
