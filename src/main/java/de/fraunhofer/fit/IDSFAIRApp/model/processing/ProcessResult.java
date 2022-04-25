package de.fraunhofer.fit.IDSFAIRApp.model.processing;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name="ProcessResult", description="App processing result")
public class ProcessResult {

    @JsonProperty("processed")
    private boolean processed;

    private String message;

    public ProcessResult(){}

    public ProcessResult(boolean processed, String message){
        this.processed = processed;
        this.message = message;
    }

    public boolean getProcessed(){
        return this.processed;
    }

    public void setProcessed(boolean processed){
        this.processed = processed;
    }

    public String getMessage(){
        return this.message;
    }
}

