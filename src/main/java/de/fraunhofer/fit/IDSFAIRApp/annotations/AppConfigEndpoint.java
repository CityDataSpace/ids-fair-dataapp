package de.fraunhofer.fit.IDSFAIRApp.annotations;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@RestController
@RequestMapping("/config")
@Tag(name="App: Configuration / Parameterization", description = "App Configuration")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AppConfigEndpoint {

}
