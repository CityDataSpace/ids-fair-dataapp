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

import de.fraunhofer.fit.IDSFAIRApp.annotations.AppUsagePolicyEndpoint;

@AppUsagePolicyEndpoint
public class AppUsagePolicyController {

    String usagePolicy = "TODO: Need to be implemented";

    @Operation(summary = "Parses the app's usage policies",
            description = "Endpoint to receive usage policies",
            tags = {"usagePolicy"},
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Parse the app's usage policies successfully",
                        content = @Content(
                                mediaType = "application/text",
                                schema = @Schema(implementation = String.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "Error parsing the app's usage policies",
                        content = @Content(
                                mediaType = "application/text",
                                schema = @Schema(implementation = String.class)
                        ))
            })
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> parsePolicy(
            @RequestBody(
                    description="App usage policies",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody String usagePolicy
    ){
        try{
            this.usagePolicy = usagePolicy;
            return new ResponseEntity<>(this.usagePolicy, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Error parsing the app's usage policies", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
