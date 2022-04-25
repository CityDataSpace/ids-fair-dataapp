package de.fraunhofer.fit.IDSFAIRApp.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.fraunhofer.fit.IDSFAIRApp.annotations.FAIRvalidatorInputEndpoint;
import de.fraunhofer.fit.IDSFAIRApp.model.data.ExampleData;
import de.fraunhofer.fit.IDSFAIRApp.model.data.FAIRdataStore;
import de.fraunhofer.fit.IDSFAIRApp.model.data.FAIRvalidatorInputModel;

import java.util.List;

@FAIRvalidatorInputEndpoint
public class FAIRvalidatorInputController {

    @Operation(summary = "Sets the FAIRvalidator input data",
            description = "This endpoint sets the FAIRvalidator input data",
            tags = {"AppInput"},
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "FAIRvalidator input data set successfully",
                        content = @Content (
                                mediaType = "application/json",
                                schema = @Schema(implementation = FAIRvalidatorInputModel.class)
                                
                        )),
                @ApiResponse(
                        responseCode = "500",
                        description = "Error setting the FAIRvalidator input data",
                        content = @Content(
                                mediaType = "application/text",
                                schema = @Schema(implementation = String.class)
                        ))
            })
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Object> setAppInputData(
            @RequestBody(
                    description="FAIRvalidator Input data that needs to be added to the app",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FAIRvalidatorInputModel.class)
                            
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody FAIRvalidatorInputModel inData
    ) {
        try {
        	FAIRdataStore.setExampleData(inData);
            return new ResponseEntity<>("FAIRvalidator input is set successfully. " + inData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error setting FAIRvalidator input data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
