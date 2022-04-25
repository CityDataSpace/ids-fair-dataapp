package de.fraunhofer.fit.IDSFAIRApp.model.processing;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name="ProcessWrapper", description="App processing wrapper")
public class ProcessWrapper {

    private Boolean processing = true;

    public ProcessWrapper(Boolean processing) {
        this.processing = processing;
    }

    public ProcessWrapper(){}

    public Boolean getProcessing() {
        return processing;
    }

    public void setProcessing(Boolean processing) {
        this.processing = processing;
    }
}
