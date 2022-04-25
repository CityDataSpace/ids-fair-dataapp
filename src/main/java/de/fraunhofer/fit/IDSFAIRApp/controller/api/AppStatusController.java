package de.fraunhofer.fit.IDSFAIRApp.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.fraunhofer.fit.IDSFAIRApp.annotations.AppStatusEndpoint;

@AppStatusEndpoint
public class AppStatusController {

    String status = "TODO: Need to be implemented";

    @Operation(summary = "Gets the app status information",
            description = "This endpoint gets the app status information",
            tags = {"status"},
            responses = {
                @ApiResponse(
                    responseCode = "200",
                    description = "Get the app status information successfully",
                    content = @Content(
                            mediaType = "application/text",
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error getting the app status information",
                    content = @Content(
                            mediaType = "application/text",
                            schema = @Schema(implementation = String.class)
                    ))
            })
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Object> getAppStatusData() {
        try{
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Error getting the app status information: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
