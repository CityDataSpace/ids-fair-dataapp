package de.fraunhofer.fit.IDSFAIRApp.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.fraunhofer.fit.IDSFAIRApp.annotations.AppOutputEndpoint;
import de.fraunhofer.fit.IDSFAIRApp.annotations.FAIRvalidatorOutputEndpoint;
import de.fraunhofer.fit.IDSFAIRApp.model.data.Data;
import de.fraunhofer.fit.IDSFAIRApp.model.data.ExampleData;
import de.fraunhofer.fit.IDSFAIRApp.model.data.FAIRdataStore;
import de.fraunhofer.fit.IDSFAIRApp.model.data.FAIRvalidatorInputModel;
import de.fraunhofer.fit.IDSFAIRApp.model.data.FAIRvalidatorOutputResponseModel;
import de.fraunhofer.fit.IDSFAIRApp.service.FAIRvalidatorService;

import java.io.File;
import java.net.URLConnection;
import java.util.ArrayList;

@FAIRvalidatorOutputEndpoint 
public class FAIRvalidatorOutputController {

	@Autowired
	private FAIRvalidatorService fService;
	
    @Operation(summary = "Gets the FAIRvalidator output data",
            description = "This endpoint gets the app output data",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Get FAIRvalidator output data successfully with list of data",
                        content = @Content(
                                mediaType = "application/json",
                                array = @ArraySchema(
                                        schema = @Schema(implementation = FAIRvalidatorOutputResponseModel.class)
                                )
                        )),
                @ApiResponse(
                        responseCode = "500",
                        description = "Error getting the FAIRvalidator output data with errormessage",
                        content = @Content(
                                mediaType = "application/text",
                                schema = @Schema(implementation = String.class)
                        ))
            })
    
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Object> getAppOutputData(){
        try{
        	FAIRvalidatorInputModel  inputdata = FAIRdataStore.getExampleData();
            
			String baseURL = "http://127.0.0.1:5000";
			FAIRvalidatorOutputResponseModel res = this.fService.getValidation(inputdata,baseURL);
			
		
            
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Error getting FAIRvalidator output data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}