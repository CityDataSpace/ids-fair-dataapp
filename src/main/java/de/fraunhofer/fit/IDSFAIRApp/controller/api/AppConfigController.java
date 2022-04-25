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

import de.fraunhofer.fit.IDSFAIRApp.annotations.AppConfigEndpoint;

@AppConfigEndpoint
public class AppConfigController {

    String AppConfig = "AppConfiguration -- Goes Here";

    @Operation(summary = "Get the app configuration data",
            description = "Get the app configuration data",
            tags = {"AppConfig"},
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Get the app configuration data successfully",
                        content = @Content(
                                mediaType = "application/text",
                                schema = @Schema(implementation = String.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "Error getting the app configuration data",
                        content = @Content(
                                mediaType = "application/text",
                                schema = @Schema(implementation = String.class)
                        ))
            })
    @RequestMapping(value= "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getAppConfig(){
        try {
            return new ResponseEntity<>(AppConfig, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Error getting the app configuration data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Operation(summary = "Sets the app configuration data",
            description = "Sets the app configuration data",
            tags = {"AppConfig"},
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Set the app configuration data successfully",
                        content = @Content (
                                mediaType = "application/text",
                                schema = @Schema(implementation = String.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "Error setting the app configuration data",
                        content = @Content (
                                mediaType = "application/text",
                                schema = @Schema(implementation = String.class)
                        )
                )
    })
    @RequestMapping(value= "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> setAppConfig(
            @RequestBody(
                    description = "App configuration data",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody String AppConfig
    ){
        try{
            this.AppConfig = AppConfig;
            return new ResponseEntity<>(this.AppConfig, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Error setting the app configuration: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}