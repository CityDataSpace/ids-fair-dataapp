package de.fraunhofer.fit.IDSFAIRApp.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.fraunhofer.fit.IDSFAIRApp.annotations.AppProcessEndpoint;
import de.fraunhofer.fit.IDSFAIRApp.model.data.Data;
import de.fraunhofer.fit.IDSFAIRApp.model.data.ExampleData;
import de.fraunhofer.fit.IDSFAIRApp.model.processing.ProcessResult;
import de.fraunhofer.fit.IDSFAIRApp.model.processing.ProcessWrapper;


@AppProcessEndpoint
public class AppProcessController {

    @Operation(
            summary = "Starts the App Processing",
            description = "If proccesing needs to be started explicitly this Endpoint can be used",
            tags = {"AppProcess"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "App Processing started",
                            content = @Content (
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProcessResult.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "200",
                            description = "App Processing stopped",
                            content = @Content (
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProcessResult.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "App Processing failed with error message",
                            content = @Content (
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProcessResult.class)
                            )
                    )

    })
    @RequestMapping(value= "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> startAppProcessing(
            @RequestBody(
                    description = "App Processing flag - true or false to start / stop processing",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProcessWrapper.class)
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody ProcessWrapper processWrapper
    ){

        // TODO: Implement App Processing logic here
        // Simple example constructs an email address at Fraunhofer FIT from first and last name
        try {
            if(processWrapper.getProcessing()){
            	for(Data d : ExampleData.getExampleData()) {
            	    if(d.getEmail().equals("")) {
            	        d.setEmail(d.getFirst_name() + "." + d.getLast_name() + "@fit.fraunhofer.de");
            	    }
            	}
                return new ResponseEntity<>(new ProcessResult(true, "App Processing started"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ProcessResult(false, "App Processing stopped"), HttpStatus.OK);
            }
        } catch (Exception e){
            return new ResponseEntity<>( new ProcessResult(false, "Error during App Processing: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
