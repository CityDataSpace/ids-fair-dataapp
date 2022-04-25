package de.fraunhofer.fit.IDSFAIRApp.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.fraunhofer.fit.IDSFAIRApp.annotations.AppOutputEndpoint;
import de.fraunhofer.fit.IDSFAIRApp.annotations.FAIRvalidatorOutputEndpoint;
import de.fraunhofer.fit.IDSFAIRApp.model.data.Data;
import de.fraunhofer.fit.IDSFAIRApp.model.data.ExampleData;

import java.util.ArrayList;

@AppOutputEndpoint
public class AppOutputController {

    @Operation(summary = "Gets the app output data",
            description = "This endpoint gets the app output data",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Get app output data successfully with list of data",
                        content = @Content(
                                mediaType = "application/json",
                                array = @ArraySchema(
                                        schema = @Schema(implementation = Data.class)
                                )
                        )),
                @ApiResponse(
                        responseCode = "500",
                        description = "Error getting the app output data with errormessage",
                        content = @Content(
                                mediaType = "application/text",
                                schema = @Schema(implementation = String.class)
                        ))
            })
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Object> getAppOutputData(){
        try{
            ArrayList<Data> outData = ExampleData.getExampleData();
            return new ResponseEntity<>(outData, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Error getting app output data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}