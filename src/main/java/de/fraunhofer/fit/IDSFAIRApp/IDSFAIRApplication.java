package de.fraunhofer.fit.IDSFAIRApp;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IDSFAIRApplication {

	public static void main(String[] args) {
		SpringApplication.run(IDSFAIRApplication.class, args);
	}

	/**
	 * Creates the OpenAPI main description.
	 *
	 * @return The OpenAPI
	 */
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components())
				.info(new Info()
						.title("IDS-Smart-Data-App-Template")
						.description(
								"This is the API documentation of the IDS-Smart-Data-App using springdoc-openapi and OpenAPI 3. "+
								"The documentation is related to the <a href=\"https://industrialdataspace.jiveon.com/docs/DOC-2604\">jive documentation</a>"
						)
						.version("v3.0")
						.contact(new Contact()
								.name("Dominic Reuter")
								.email("dominic.reuter@fit.fraunhofer.de")
						)
						.license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.txt"))
				);
	}
}
