package de.fraunhofer.fit.IDSFAIRApp.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.fraunhofer.fit.IDSFAIRApp.annotations.IDSEndpoint;
import de.fraunhofer.fit.IDSFAIRApp.model.IDSAppResource;
import de.fraunhofer.iais.eis.AppResource;
import de.fraunhofer.iais.eis.AppResourceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Base64;

@IDSEndpoint
public class IDSController {

    @Operation(summary = "Gets the ids app resource description as json",
            description = "",
            tags = {"ids"},
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Get ids app resource description as json",
                        content = @Content(
                                mediaType = "application/json"
                                //schema = @Schema(implementation = AppResourceImpl.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "Error getting the app resource description as json",
                        content = @Content(
                                mediaType = "application/text",
                                schema = @Schema(implementation = String.class)
                        )
                )
            })
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Object> selfDescription(){

        try{
            AppResource appResource = IDSAppResource.generateIDSAppResource();
            return new ResponseEntity<>(appResource, HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>("Error generating IDSAppResource: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Gets the ids app resource description as base64 encoded string",
            description = "",
            tags = {"ids"},
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Gets ids app resource description as base64 encoded string",
                        content = @Content(
                                mediaType = "application/text",
                                schema = @Schema(implementation = String.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "Error getting the app resource description as base64 string",
                        content = @Content(
                                mediaType = "application/text",
                                schema = @Schema(implementation = String.class)
                        )
                )
            }
        )
    @RequestMapping(value = "/encoded", method = RequestMethod.GET)
    public ResponseEntity<Object> selfDescriptionAsBase64(){

        try{
            AppResource appResource = IDSAppResource.generateIDSAppResource();

            ObjectMapper objectMapper = new ObjectMapper();
            String app = objectMapper.writeValueAsString(appResource);
            String base64 = Base64.getEncoder().encodeToString(app.getBytes());

            return new ResponseEntity<>(base64, HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>("Error generating IDSAppResource in BASE64: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}