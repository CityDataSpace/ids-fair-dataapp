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

import de.fraunhofer.fit.IDSFAIRApp.annotations.AppInputEndpoint;
import de.fraunhofer.fit.IDSFAIRApp.model.data.Data;
import de.fraunhofer.fit.IDSFAIRApp.model.data.ExampleData;

import java.util.List;

@AppInputEndpoint
public class AppInputController {

    @Operation(summary = "Sets the app input data",
            description = "This endpoint sets the app input data",
            tags = {"AppInput"},
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "App input data set successfully",
                        content = @Content (
                                mediaType = "application/json",
                                array = @ArraySchema(
                                        schema = @Schema(implementation = Data.class)
                                )
                        )),
                @ApiResponse(
                        responseCode = "500",
                        description = "Error setting the app input data",
                        content = @Content(
                                mediaType = "application/text",
                                schema = @Schema(implementation = String.class)
                        ))
            })
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Object> setAppInputData(
            @RequestBody(
                    description="App Input data that needs to be added to the app",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Data.class)
                            )
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody List<Data> inData
    ) {
        try {
            ExampleData.exampleData.clear();
            ExampleData.exampleData.addAll(inData);
            return new ResponseEntity<>(ExampleData.getExampleData(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error setting app input data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
